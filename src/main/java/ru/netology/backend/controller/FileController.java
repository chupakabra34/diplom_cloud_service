package ru.netology.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.backend.dto.FileDto;
import ru.netology.backend.entity.File;
import ru.netology.backend.security.MyUserDetailsService;
import ru.netology.backend.service.FileService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */


@RestController
public class FileController {

    private final FileService fileService;

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/list")
    public List<FileDto> listFile(@RequestParam String limit) {
        return fileService.findAllByUser(MyUserDetailsService.getCurrentUser())
                .stream()
                .map(this::mapToFileResponse)
                .limit(Long.parseLong(limit))
                .collect(Collectors.toList());
    }

    private FileDto mapToFileResponse(File fileEntity) {
        FileDto fileResponse = new FileDto();
        fileResponse.setFilename(fileEntity.getName());
        fileResponse.setSize(fileEntity.getSize());

        return fileResponse;
    }

    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.save(file);

            log.info("File uploaded successfully: {}", file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            log.error("Could not upload the file: {}", file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @DeleteMapping("/file")
    public ResponseEntity<String> deleteFile(@RequestParam String filename) {
        try {
            fileService.deleteFileByName(filename);

            log.info("File deleted successfully: {}", filename);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File deleted successfully: %s", filename));
        } catch (Exception e) {
            log.info("Could not deleted the file: {}", filename);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not deleted the file: %s!", filename));
        }
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> getFile(@RequestParam String filename) {
        Optional<File> fileOptional = fileService.findByName(filename);

        if (fileOptional.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }
        File fileEntity = fileOptional.get();
        log.info("File download successfully: {}", filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }

    @PutMapping("/file")
    public ResponseEntity<String> updateFile(@RequestParam String filename, @RequestBody FileDto name) {
        try {
            fileService.updateFile(filename, name.getFilename());

            log.info("File update successfully: {}", name.getFilename());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File update successfully: %s", name.getFilename()));
        } catch (Exception e) {
            log.error("Could not update the file: {}", filename);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not update the file: %s!", filename));
        }
    }
}