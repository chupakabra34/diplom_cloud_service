package ru.netology.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ru.netology.backend.entity.File;
import ru.netology.backend.entity.User;
import ru.netology.backend.repository.FileRepository;
import ru.netology.backend.security.MyUserDetailsService;

import static org.mockito.Mockito.*;

/**
 * @author VladSemikin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileService.class)
@WithMockUser(username = "user")
class FileServiceTest {

    @MockBean
    private FileRepository fileRepository;

    @Autowired
    private FileService fileService;

    @Test
    void save() {
        File file = new File();
        file.setData(new byte[]{});
        file.setName("file");

        fileService.save(new MockMultipartFile(file.getName(), file.getData()));

        verify(fileRepository, times(1)).save(any());
    }

    @Test
    void findByName() {
        fileService.findByName("file");

        verify(fileRepository, times(1))
                .findByNameAndUser(anyString(), ArgumentMatchers.any(User.class));
    }

    @Test
    void findAllByUser() {
        fileService.findAllByUser(MyUserDetailsService.getCurrentUser());

        verify(fileRepository, times(1))
                .findAllByUser(ArgumentMatchers.any(User.class));
    }

    @Test
    void deleteFileByName() {
        fileService.deleteFileByName(anyString());


        verify(fileRepository, times(1))
                .deleteByName(anyString());
    }

    @Test
    void updateFile() {
        fileService.updateFile("name", "newName");

        verify(fileRepository, times(1))
                .findByNameAndUser(anyString(), ArgumentMatchers.any(User.class));
    }
}