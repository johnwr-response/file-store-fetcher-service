package no.responseweb.imagearchive.filestorefetcherservice.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.responseweb.imagearchive.filestorefetcherservice.services.FileInfoService;
import no.responseweb.imagearchive.model.FileItemDto;
import no.responseweb.imagearchive.model.FilePathDto;
import no.responseweb.imagearchive.model.FileStoreDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileStoreFetcherController {

    private final FileInfoService fileInfoService;

    @GetMapping("api/v1/fetchFile/{fileItemId}")
    public Object getOriginalFile(@PathVariable UUID fileItemId) {

        FileItemDto fileItemDto = fileInfoService.getFileItem(fileItemId);
        FilePathDto filePathDto = fileInfoService.getFilePath(fileItemDto.getFileStorePathId());
        FileStoreDto fileStoreDto = fileInfoService.getFileStore(filePathDto.getFileStoreId());

        return fileStoreDto.getLocalBaseUri() + File.separator + filePathDto.getRelativePath() + File.separator + fileItemDto.getFilename();
    }

}
