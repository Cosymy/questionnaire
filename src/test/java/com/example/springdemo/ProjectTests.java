package com.example.springdemo;


import com.example.springdemo.dao.entity.ProjectEntity;
import com.example.springdemo.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    public void testQueryProjectList_Success() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.queryProjectList(any(ProjectEntity.class)))
                .thenReturn(Arrays.asList(projectEntity));

        mockMvc.perform(post("/queryProjectList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryProjectList_NoData() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.queryProjectList(any(ProjectEntity.class)))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(post("/queryProjectList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryProjectList_Exception() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.queryProjectList(any(ProjectEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/queryProjectList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddProjectInfo_Success() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.addProjectInfo(any(ProjectEntity.class)))
                .thenReturn(1);

        mockMvc.perform(post("/addProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddProjectInfo_Failure() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.addProjectInfo(any(ProjectEntity.class)))
                .thenReturn(0);

        mockMvc.perform(post("/addProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddProjectInfo_Exception() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.addProjectInfo(any(ProjectEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/addProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyProjectInfo_Success() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.modifyProjectInfo(any(ProjectEntity.class)))
                .thenReturn(1);

        mockMvc.perform(post("/modifyProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyProjectInfo_Failure() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.modifyProjectInfo(any(ProjectEntity.class)))
                .thenReturn(0);

        mockMvc.perform(post("/modifyProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyProjectInfo_Exception() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.modifyProjectInfo(any(ProjectEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/modifyProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteProjectById_Success() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.deleteProjectById(any(ProjectEntity.class)))
                .thenReturn(1);

        mockMvc.perform(post("/deleteProjectById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProjectById_Failure() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.deleteProjectById(any(ProjectEntity.class)))
                .thenReturn(0);

        mockMvc.perform(post("/deleteProjectById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProjectById_Exception() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("projectName");

        when(projectService.deleteProjectById(any(ProjectEntity.class)))
                .thenThrow(new RuntimeException("Some exception"));

        mockMvc.perform(post("/deleteProjectById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectEntity)))
                .andExpect(status().isOk());
    }

}
