package no.responseweb.imagearchive.filestorefetcherservice.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.responseweb.imagearchive.filestorefetcherservice.services.FileInfoService;
import no.responseweb.imagearchive.model.FileItemDto;
import no.responseweb.imagearchive.model.FilePathDto;
import no.responseweb.imagearchive.model.FileStoreDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileStoreFetcherController {

    private final FileInfoService fileInfoService;

    @GetMapping("api/v1/fetchFile/{fileItemId}")
    public Object getOriginalFile(@PathVariable UUID fileItemId) throws IOException {

        FileItemDto fileItemDto = fileInfoService.getFileItem(fileItemId);
        FilePathDto filePathDto = fileInfoService.getFilePath(fileItemDto.getFileStorePathId());
        FileStoreDto fileStoreDto = fileInfoService.getFileStore(filePathDto.getFileStoreId());

        String s = fileStoreDto.getLocalBaseUri() + File.separator + filePathDto.getRelativePath() + File.separator + fileItemDto.getFilename();
        Path path = Paths.get(s);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        String detectedMimeType = Files.probeContentType(path);
        MediaType detectedMediaType = MediaType.parseMediaType(detectedMimeType);
        log.info("Detected Mime Type: {}. Detected Media Type: {}", detectedMimeType, detectedMediaType);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,  "inline; filename=\"" +fileItemDto.getFilename()+ "\"");
        //noinspection SpellCheckingInspection
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileItemDto.getSize())
                .contentType(detectedMediaType)
                .body(resource);
    }

}
