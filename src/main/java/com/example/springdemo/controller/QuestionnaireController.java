package com.example.springdemo.controller;

import com.example.springdemo.beans.HttpResponseEntity;
import com.example.springdemo.common.Constans;
import com.example.springdemo.common.utils.UUIDUtil;
import com.example.springdemo.dao.UserAnswerMapper;
import com.example.springdemo.dao.entity.QuestionnaireEntity;
import com.example.springdemo.dao.entity.UserAnswer;
import com.example.springdemo.service.QuestionnaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class QuestionnaireController {
    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    QuestionnaireService questionnaireService;
    @Autowired
    UserAnswerMapper userAnswerMapper;

    /**
     * 通过试卷id查询有哪些人答卷
     * @param userAnswer
     * @return
     * @throws IllegalAccessException
     */
    @PostMapping("getAnswerByQuId")
    public HttpResponseEntity getAnswerByQuId(@RequestBody UserAnswer userAnswer) throws IllegalAccessException {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData( userAnswerMapper.selectByQuId(userAnswer));
        httpResponseEntity.setMassage(Constans.STATUS_MESSAGE);
        return httpResponseEntity;
    }

    /**
     * 查询答卷详情信息
     * @param userAnswer
     * @return
     * @throws IllegalAccessException
     */
    @PostMapping("getAnswerById")
    public HttpResponseEntity getAnswerById(@RequestBody UserAnswer userAnswer) throws IllegalAccessException {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData( userAnswerMapper.selectById(userAnswer));
        httpResponseEntity.setMassage(Constans.STATUS_MESSAGE);
        return httpResponseEntity;
    }

    /**
     * 提交完成答题
     */
    @PostMapping("startAnswer")
    public HttpResponseEntity startAnswer(@RequestBody UserAnswer userAnswer) throws IllegalAccessException {
        userAnswer.setUserAnswerId(UUIDUtil.getOneUUID());
        userAnswer.setCreateTime(new Date());
        userAnswerMapper.insert(userAnswer);
        //统计答卷人数
        QuestionnaireEntity questionnaireEntity =new QuestionnaireEntity();
        questionnaireEntity.setId(userAnswer.getQuId());
        int answerTotal = questionnaireEntity.getAnswerTotal();
        answerTotal++;
        questionnaireEntity.setAnswerTotal(answerTotal);
        questionnaireService.updateByPrimaryKeySelective(questionnaireEntity);
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData(userAnswer);
        httpResponseEntity.setMassage(Constans.STATUS_MESSAGE);
        return httpResponseEntity;
    }

    /**
     * 通过问卷ID查询问卷信息
     *
     * @param questionnaireEntity
     * @return
     * @throws IllegalAccessException
     */
    @PostMapping("getQustById")
    public HttpResponseEntity getQustById(@RequestBody QuestionnaireEntity questionnaireEntity) throws IllegalAccessException {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        if (StringUtils.isEmpty(questionnaireEntity.getId())) {
            throw new RuntimeException("问卷ID不能为空");
        }
        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData(questionnaireService.queryByPrimaryKey(questionnaireEntity.getId()));
        httpResponseEntity.setMassage(Constans.STATUS_MESSAGE);
        return httpResponseEntity;
    }

    /**
     * 编辑或修改问卷
     *
     * @param questionnaireEntity
     * @return
     */
    @PostMapping("/modifyQuestionnaireOrInsert")
    public HttpResponseEntity modifyQuestionnaireOrInsert(@RequestBody QuestionnaireEntity questionnaireEntity) throws IllegalAccessException {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        if (StringUtils.isEmpty(questionnaireEntity.getProjectId())) {
            throw new RuntimeException("问卷关联项目不能为空");

        }
        if (StringUtils.isEmpty(questionnaireEntity.getId())) {
            //新
            questionnaireEntity.setReleaseTime(new Date());
            questionnaireEntity.setQuestionStop("5");
            questionnaireService.addQuestionnaire(questionnaireEntity);
        } else {
            //修改
            questionnaireService.modifyQuestionnaire(UUIDUtil.convertObjectToMap(questionnaireEntity));
        }
        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData(null);
        httpResponseEntity.setMassage(Constans.ADD_MESSAGE);
        return httpResponseEntity;
    }

    /**
     * 添加问卷
     */
    @PostMapping("/addQuestionnaire")
    public HttpResponseEntity addQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        String projectId = String.valueOf(questionnaireEntity.getProjectId());
        List<Map<String, Object>> data = questionnaireService.queryAllQuestionnaireByProjectId(projectId);
        boolean flag = isHaveQuestion(data, questionnaireEntity);
        if (flag) {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMassage(Constans.PROJECT_EXIST_QUESTION);
        } else {
            questionnaireService.addQuestionnaire(questionnaireEntity);
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMassage(Constans.ADD_MESSAGE);
        }
        return httpResponseEntity;
    }

    /**
     * 添加问卷时，检测是否有同dataId的问卷存在
     */
    private boolean isHaveQuestion(List<Map<String, Object>> list, QuestionnaireEntity questionnaireEntity) {
        if (list == null)
            return false;
        else {
            String dataId = questionnaireEntity.getDataId();
            String questionName = questionnaireEntity.getQuestionName();
            for (Map<String, Object> map2 : list) {
                String queryName = String.valueOf(map2.get("questionName"));
                if (questionName.equals(queryName)) {
                    String queryDataId = String.valueOf(map2.get("dataId"));
                    if (dataId.equals(queryDataId)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}
