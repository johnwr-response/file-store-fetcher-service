package no.responseweb.imagearchive.filestorefetcherservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.responseweb.imagearchive.model.FileItemDto;
import no.responseweb.imagearchive.model.FilePathDto;
import no.responseweb.imagearchive.model.FileStoreDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileInfoServiceFeignImpl implements FileInfoService {
    public static final String STORE_PATH = "api/v1/fileStore/{fileStoreId}";
    public static final String PATH_PATH = "api/v1/filePath/{filePathId}";
    public static final String ITEM_PATH = "api/v1/fileItem/{fileItemId}";

    private final FileInfoServiceFeignClient fileInfoServiceFeignClient;

    @Override
    public FileStoreDto getFileStore(UUID fileStoreId) {
        ResponseEntity<FileStoreDto> responseEntity = fileInfoServiceFeignClient.getFileStore(fileStoreId);
        return responseEntity.getBody();
    }

    @Override
    public FilePathDto getFilePath(UUID filePathId) {
        ResponseEntity<FilePathDto> responseEntity = fileInfoServiceFeignClient.getFilePath(filePathId);
        return responseEntity.getBody();
    }

    @Override
    public FileItemDto getFileItem(UUID fileItemId) {
        ResponseEntity<FileItemDto> responseEntity = fileInfoServiceFeignClient.getFileItem(fileItemId);
        return responseEntity.getBody();
    }

}
