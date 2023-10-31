package com.example.springdemo.dao;

import com.example.springdemo.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Mapper
@Repository
public interface QuestionnaireEntityMapper {
    int deleteByPrimaryKey(String id);
    int insert(QuestionnaireEntity record);
    int insertSelective(QuestionnaireEntity record);
    QuestionnaireEntity queryByPrimaryKey(String id);
    int updateByPrimaryKeySelective(QuestionnaireEntity record);
    int updateByPrimaryKey(QuestionnaireEntity record);

    /**
     * 根据项目id查询此项目下的问卷个数
     */
    int selectQuestionCountByProjectId(String projectId);

    /**
     * 根据项目id查询此项目下的全部问卷
     */
    List<Map<String,Object>> queryAllQuestionnaireByProjectId(String parentId);

    /**
     * 查询历史问卷/模糊搜索
     */
    List<Map<String,Object>> queryQuestionnaireList(HashMap<String, Object> map);

    /**
     * 修改历史问卷表状态
     */
    int modifyHistoryQuestionnaireStatus(HashMap<String, Object> map);
    /**
     * 添加问卷
     */
    int addQuestionnaire(HashMap<String, Object> map);
    /**
     * 根据问卷id查询问卷的详细信息
     */
    Map<String,String> queryQuestionnaireById(HashMap<String, Object> map);
    /**
     * 根据问卷id修改问卷
     */
    int modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity);
    /**
     * 根据项目id查询发布中问卷数量
     */
    int queryIsReleasedQuestionnaireCount(QuestionnaireEntity questionnaireEntity);
    /**
     * 查询问卷模板
     */
    List<Map<String,Object>> queryQuestionnaireModel(String dataId);

    /**
     * 查询历史问卷
     */
    List<Map<String,Object>> queryHistoryQuestionnaire(HashMap<String, Object> map);

    /**
     * 根据id修改问卷的全部信息
     */
    int modifyQuestionnaire(Map map);

    /**
     * 修改问卷状态
     */
    int modifyQuestionnaireStatus(HashMap<String, Object> map);
    /**
     * 添加发送问卷方式
     * @param map
     * @return
     */
    int addSendQuestionnaire(HashMap<String, Object> map);
    int pauseQuestionnaire(Map<String, Object> map);
    String queryQuestionnaireIsStop(String id);
    QuestionnaireEntity queryQuestContextEnd(String id);
}
