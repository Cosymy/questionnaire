package com.example.springdemo;

import com.example.springdemo.dao.UserEntityMapper;
import com.example.springdemo.dao.entity.UserEntity;
import com.example.springdemo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Mock
    private UserEntityMapper userEntityMapper;


    @Test
    public void testUserLogin_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");
        userEntity.setPassword("password");

        when(userService.selectUserInfo(any(UserEntity.class)))
                .thenReturn(Arrays.asList(userEntity));

        mockMvc.perform(post("/admin/userLogin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }
    

    @Test
    public void testUserLogin_Failure() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");
        userEntity.setPassword("password");

        when(userService.selectUserInfo(any(UserEntity.class)))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(post("/admin/userLogin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());

    }


    @Test
    public void testUserLogin_Exception() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");
        userEntity.setPassword("password");

        when(userService.selectUserInfo(any(UserEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/admin/userLogin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }


    @Test
    public void testQueryUserList_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.queryUserList(any(UserEntity.class)))
                .thenReturn(Arrays.asList(userEntity));

        mockMvc.perform(post("/admin/queryUserList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryUserList_NoData() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.queryUserList(any(UserEntity.class)))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(post("/admin/queryUserList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());

    }

    @Test
    public void testQueryUserList_Exception() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.queryUserList(any(UserEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/admin/queryUserList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyUser_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.modifyUserInfo(any(UserEntity.class)))
                .thenReturn(1);

        mockMvc.perform(post("/admin/modifyUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyUser_Failure() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.modifyUserInfo(any(UserEntity.class)))
                .thenReturn(0);

        mockMvc.perform(post("/admin/modifyUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyUser_Exception() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.modifyUserInfo(any(UserEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/admin/modifyUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUser_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.addUserInfo(any(UserEntity.class)))
                .thenReturn(1);

        mockMvc.perform(post("/admin/addUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUser_Failure() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.addUserInfo(any(UserEntity.class)))
                .thenReturn(0);

        mockMvc.perform(post("/admin/addUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUser_Exception() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.addUserInfo(any(UserEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/admin/addUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.deleteUserById(any(UserEntity.class)))
                .thenReturn(1);

        mockMvc.perform(post("/admin/deleteUserinfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser_Failure() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.deleteUserById(any(UserEntity.class)))
                .thenReturn(0);

        mockMvc.perform(post("/admin/deleteUserinfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser_Exception() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        when(userService.deleteUserById(any(UserEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/admin/deleteUserinfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }






//    //@Test
//    void contextLoads() {
//    }
//    @Autowired
//    private UserEntityMapper userEntityMapper;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private ProjectService projectService;
//
//    @Autowired
//    private UserController userController;
//    @Autowired
//    private ProjectController projectController;
//    Logger log = Logger.getLogger(Demo3ApplicationTests.class);
//    @Test
//    public void queryUserList() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        List<UserEntity> list = userEntityMapper.queryUserList(userEntity);
//        if(CollectionUtils.isEmpty(list)){
//            // 记录error级别的信息
//        }else{
//            System.out.println(list);
//            // 记录info级别的信息
//            log.info(">>queryUserList用户列表查询测试成功");
//        }
//    }
//
//    @Test
//    public void selectUserInfo() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("admin");
//        userEntity.setPassword("123");
//        List<UserEntity> list = userEntityMapper.selectUserInfo(userEntity);
//        if(CollectionUtils.isEmpty(list)){
//            // 记录error级别的信息
//        }else{
//            System.out.println(list);
//            // 记录info级别的信息
//            log.info(">>qselectUserInfo用户登录测试成功");
//        }
//    }
//
//    @Test
//    public void insert() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(UUIDUtil.getOneUUID());
//        userEntity.setStatus("1");
//        userEntity.setUsername("LS");
//        userEntity.setPassword("123");
//        int i = userEntityMapper.insert(userEntity);
//        if(i==0){
//            // 记录error级别的信息
//        }else{
//            System.out.println(i);
//            // 记录info级别的信息
//            log.info(">>insert用户插入测试成功");
//        }
//    }
//
//    @Test
//    public void deleteUserByName() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("aaaaa");
//        int i = userEntityMapper.deleteUserById(userEntity);
//        if(i==0){
//            // 记录error级别的信息
//        }else{
//            System.out.println(i);
//            // 记录info级别的信息
//            log.info(">>delete用户删除测试成功");
//        }
//    }
//
//    @Test
//    public void queryProjectList() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
//        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
//        //调用ProjectMapper的方法
//        ProjectEntity projectEntity = new ProjectEntity();
//        List<ProjectEntity> list = projectEntityMapper.queryProjectList(projectEntity);
//        if(CollectionUtils.isEmpty(list)){
//            // 记录error级别的信息
//        }else{
//            System.out.println(list);
//            // 记录info级别的信息
//            log.info(">>queryProjectList项目列表查询测试成功");
//        }
//    }
//
//    @Test
//    public void insertProject() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
//        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
//        //调用ProjectMapper的方法
//        ProjectEntity projectEntity = new ProjectEntity();
//        projectEntity.setId(UUIDUtil.getOneUUID());
//        projectEntity.setCreationDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//        projectEntity.setUserId("1");
//        projectEntity.setProjectContent("这是一个项目");
//        projectEntity.setCreatedBy("it");
//        projectEntity.setProjectName("项目");
//        projectEntity.setProjectContent("fhbjnk");
//
//        int i = projectEntityMapper.insert(projectEntity);
//        if(i==0){
//            // 记录error级别的信息
//        }else{
//            System.out.println(i);
//            // 记录info级别的信息
//            log.info(">>insert项目插入测试成功");
//        }
//    }
//    @Test
//    public void deleteProjectById() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
//        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
//        //调用ProjectMapper的方法
//        ProjectEntity projectEntity = new ProjectEntity();
//        projectEntity.setId("1");
//        int i = projectEntityMapper.deleteProjectById(projectEntity);
//        if(i==0){
//            // 记录error级别的信息
//        }else{
//            System.out.println(i);
//            // 记录info级别的信息
//            log.info(">>delete项目删除测试成功");
//        }
//    }
//
//    @Test
//    public void updateByPrimaryKeySelective() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
//        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
//        //调用ProjectMapper的方法
//        ProjectEntity projectEntity = new ProjectEntity();
//        projectEntity.setId("1");
//        projectEntity.setProjectName("项目一");
//        projectEntity.setProjectContent("一个项目");
//        int i = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
//        if(i==0){
//            // 记录error级别的信息
//        }else{
//            System.out.println(i);
//            // 记录info级别的信息
//            log.info(">>delete项目修改测试成功");
//        }
//    }
}
