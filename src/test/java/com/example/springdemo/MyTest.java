package com.example.springdemo;


import com.example.springdemo.dao.ProjectEntityMapper;
import com.example.springdemo.dao.UserEntityMapper;
import com.example.springdemo.dao.entity.ProjectEntity;
import com.example.springdemo.dao.entity.UserEntity;
import com.example.springdemo.service.ProjectService;
import com.example.springdemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class MyTest {

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserService userService;

    @Mock
    private ProjectEntityMapper projectEntityMapper;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnThreeWhenUserAddedSuccessfully() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值
        // userEntity.setSomeField(someValue);

        when(userEntityMapper.insert(userEntity)).thenReturn(1);

        int result = userService.addUserInfo(userEntity);
        assertEquals(3, result);
    }

    @Test
    public void shouldHandleDatabaseException() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值
        // userEntity.setSomeField(someValue);

        when(userEntityMapper.insert(userEntity)).thenThrow(new RuntimeException());

        try {
            userService.addUserInfo(userEntity);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }


    @Test
    public void shouldReturnOneWhenUserModifiedSuccessfully() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值
        // userEntity.setSomeField(someValue);

        when(userEntityMapper.updateByPrimaryKeySelective(userEntity)).thenReturn(1);

        int result = userService.modifyUserInfo(userEntity);
        assertEquals(1, result);
    }

    @Test
    public void shouldReturnOneWhenUserDeletedSuccessfully() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值，如ID
        // userEntity.setId(someId);

        when(userEntityMapper.deleteUserById(userEntity)).thenReturn(1);

        int result = userService.deleteUserById(userEntity);
        assertEquals(1, result);
    }

    @Test
    public void shouldHandleDatabaseExceptionOnModify() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值
        // userEntity.setSomeField(someValue);

        when(userEntityMapper.updateByPrimaryKeySelective(userEntity)).thenThrow(new RuntimeException());

        try {
            userService.modifyUserInfo(userEntity);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    public void shouldHandleDatabaseExceptionOnDelete() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值，如ID
        // userEntity.setId(someId);

        when(userEntityMapper.deleteUserById(userEntity)).thenThrow(new RuntimeException());

        try {
            userService.deleteUserById(userEntity);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    public void shouldReturnUserListWhenSelectUserInfo() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值
        // userEntity.setSomeField(someValue);

        List<UserEntity> expectedList = new ArrayList<>();
        expectedList.add(userEntity);

        when(userEntityMapper.selectUserInfo(userEntity)).thenReturn(expectedList);

        List<UserEntity> result = userService.selectUserInfo(userEntity);
        assertEquals(expectedList, result);
    }

    @Test
    public void shouldReturnUserListWhenQueryUserList() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值
        // userEntity.setSomeField(someValue);

        List<UserEntity> expectedList = new ArrayList<>();
        expectedList.add(userEntity);

        when(userEntityMapper.queryUserList(userEntity)).thenReturn(expectedList);

        List<UserEntity> result = userService.queryUserList(userEntity);
        assertEquals(expectedList, result);
    }

    @Test
    public void shouldHandleDatabaseExceptionOnSelectUserInfo() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值
        // userEntity.setSomeField(someValue);

        when(userEntityMapper.selectUserInfo(userEntity)).thenThrow(new RuntimeException());

        try {
            userService.selectUserInfo(userEntity);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    public void shouldHandleDatabaseExceptionOnQueryUserList() {
        UserEntity userEntity = new UserEntity();
        // 为userEntity对象的字段设置一些值
        // userEntity.setSomeField(someValue);

        when(userEntityMapper.queryUserList(userEntity)).thenThrow(new RuntimeException());

        try {
            userService.queryUserList(userEntity);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }


    @Test
    public void shouldReturnProjectListWhenQueryProjectList() {
        ProjectEntity projectEntity = new ProjectEntity();
        // Set values for projectEntity
        // projectEntity.setSomeField(someValue);

        List<ProjectEntity> expectedList = new ArrayList<>();
        expectedList.add(projectEntity);

        when(projectEntityMapper.queryProjectList(projectEntity)).thenReturn(expectedList);

        List<ProjectEntity> result = projectService.queryProjectList(projectEntity);
        assertEquals(expectedList, result);
    }

    @Test
    public void shouldReturnThreeWhenAddProjectInfoSuccessfully() {
        ProjectEntity projectEntity = new ProjectEntity();
        // Set values for projectEntity
        // projectEntity.setSomeField(someValue);

        when(projectEntityMapper.insert(projectEntity)).thenReturn(1);

        int result = projectService.addProjectInfo(projectEntity);
        assertEquals(3, result);
    }

    @Test
    public void shouldReturnZeroWhenInsertProject() {
        ProjectEntity projectEntity = new ProjectEntity();
        // Set values for projectEntity
        // projectEntity.setSomeField(someValue);

        int result = projectService.insert(projectEntity);
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnOneWhenModifyProjectInfoSuccessfully() {
        ProjectEntity projectEntity = new ProjectEntity();
        // Set values for projectEntity
        // projectEntity.setSomeField(someValue);

        when(projectEntityMapper.updateByPrimaryKeySelective(projectEntity)).thenReturn(1);

        int result = projectService.modifyProjectInfo(projectEntity);
        assertEquals(1, result);
    }

    @Test
    public void shouldReturnOneWhenDeleteProjectByIdSuccessfully() {
        ProjectEntity projectEntity = new ProjectEntity();
        // Set values for projectEntity, such as ID
        // projectEntity.setId(someId);

        when(projectEntityMapper.deleteProjectById(projectEntity)).thenReturn(1);

        int result = projectService.deleteProjectById(projectEntity);
        assertEquals(1, result);
    }
}
