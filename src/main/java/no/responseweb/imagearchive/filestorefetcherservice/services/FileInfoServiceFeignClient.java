package no.responseweb.imagearchive.filestorefetcherservice.services;

import no.responseweb.imagearchive.filestorefetcherservice.config.FeignClientConfig;
import no.responseweb.imagearchive.model.FileItemDto;
import no.responseweb.imagearchive.model.FilePathDto;
import no.responseweb.imagearchive.model.FileStoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "file-info-service", url = "${feign.client.url.FileInfoServiceUrl}", configuration = FeignClientConfig.class)
public interface FileInfoServiceFeignClient {

    @GetMapping(value = FileInfoServiceFeignImpl.STORE_PATH)
    ResponseEntity<FileStoreDto> getFileStore(@PathVariable UUID fileStoreId);

    @GetMapping(value = FileInfoServiceFeignImpl.PATH_PATH)
    ResponseEntity<FilePathDto> getFilePath(@PathVariable UUID filePathId);

    @GetMapping(value = FileInfoServiceFeignImpl.ITEM_PATH)
    ResponseEntity<FileItemDto> getFileItem(@PathVariable UUID fileItemId);

}
