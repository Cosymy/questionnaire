package com.example.springdemo;

import com.example.springdemo.dao.QuestionnaireEntityMapper;
import com.example.springdemo.dao.entity.QuestionnaireEntity;
import com.example.springdemo.service.ProjectService;
import com.example.springdemo.service.QuestionnaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionnaireServiceTests {

    @Mock
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private QuestionnaireService questionnaireService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddQuestionnaire() {
        // 创建测试数据
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setCreatedBy("user123");
        questionnaireEntity.setLastUpdatedBy("user123");

        // 模拟依赖方法的行为
        when(questionnaireEntityMapper.insertSelective(questionnaireEntity)).thenReturn(1);

        // 调用待测试的方法
        questionnaireService.addQuestionnaire(questionnaireEntity);

        // 验证方法调用和断言结果
        verify(questionnaireEntityMapper, times(1)).insertSelective(questionnaireEntity);
        assertEquals("user123", questionnaireEntity.getCreatedBy());
        assertEquals("user123", questionnaireEntity.getLastUpdatedBy());
    }

    @Test
    public void testQueryAllQuestionnaireByProjectId() {
        // 创建测试数据
        String projectId = "project123";

        // 模拟依赖方法的行为
        when(questionnaireEntityMapper.queryAllQuestionnaireByProjectId(projectId)).thenReturn(null);

        // 调用待测试的方法
        questionnaireService.queryAllQuestionnaireByProjectId(projectId);

        // 验证方法调用
        verify(questionnaireEntityMapper, times(1)).queryAllQuestionnaireByProjectId(projectId);
    }

}