package no.responseweb.imagearchive.filestorefetcherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FileStoreFetcherServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileStoreFetcherServiceApplication.class, args);
    }
}
