package no.responseweb.imagearchive.filestorefetcherservice.services;

import no.responseweb.imagearchive.model.FileItemDto;
import no.responseweb.imagearchive.model.FilePathDto;
import no.responseweb.imagearchive.model.FileStoreDto;

import java.util.UUID;

public interface FileInfoService {
    FileItemDto getFileItem(UUID fileItemId);
    FilePathDto getFilePath(UUID filePathId);
    FileStoreDto getFileStore(UUID fileStoreId);
}
