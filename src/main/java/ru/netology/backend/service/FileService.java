package ru.netology.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.backend.entity.File;
import ru.netology.backend.entity.User;
import ru.netology.backend.repository.FileRepository;
import ru.netology.backend.security.MyUserDetailsService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final Logger log = LoggerFactory.getLogger(FileService.class);

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void save(MultipartFile file) throws IOException {
        File fileEntity = new File();
        fileEntity.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setUser(MyUserDetailsService.getCurrentUser());
        fileRepository.save(fileEntity);
    }

    @Transactional
    public Optional<File> findByName(String name) {
        Optional<File> getFile = fileRepository.findByNameAndUser(name, MyUserDetailsService.getCurrentUser());
        return getFile;
    }

    @Transactional
    public List<File> findAllByUser(User user) {
        return fileRepository.findAllByUser(user);
    }

    @Transactional
    public void deleteFileByName(String name) {
        fileRepository.deleteByName(name);
    }

    @Transactional
    public void updateFile(String name, String newName) {
        Optional<File> getFile = fileRepository.findByNameAndUser(name, MyUserDetailsService.getCurrentUser());
        if (getFile.isPresent()) {
            getFile.get().setName(newName);
            fileRepository.save(getFile.get());
        }
    }
}
