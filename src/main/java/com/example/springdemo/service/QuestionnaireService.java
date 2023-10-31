package com.example.springdemo.service;

import com.example.springdemo.common.utils.DateUtil;
import com.example.springdemo.common.utils.UUIDUtil;
import com.example.springdemo.dao.QuestionnaireEntityMapper;
import com.example.springdemo.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionnaireService {
    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;
    @Autowired
    private ProjectService projectService;

    public int updateByPrimaryKeySelective(QuestionnaireEntity record){
        return questionnaireEntityMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(QuestionnaireEntity record){
        return questionnaireEntityMapper.updateByPrimaryKey(record);
    }
    /**
     * 根据项目id查询发布中问卷数量
     * @param questionnaireEntity
     * @return
     */
    public int queryIsReleasedQuestionnaireCount(QuestionnaireEntity questionnaireEntity) {
        return questionnaireEntityMapper.queryIsReleasedQuestionnaireCount(questionnaireEntity);
    }

    /**
     * 添加問卷
     * @param questionnaireEntity
     */
    public void addQuestionnaire(QuestionnaireEntity questionnaireEntity) {
        String userId = questionnaireEntity.getCreatedBy();
        String id = UUIDUtil.getOneUUID();
        questionnaireEntity.setId(id);
        // 获取用户信息
        questionnaireEntity.setCreatedBy(userId);
        questionnaireEntity.setLastUpdatedBy(userId);
        // 获取当前时间
        Date date = DateUtil.getCreateTime();
        questionnaireEntity.setCreationDate(date);
        questionnaireEntity.setLastUpdateDate(date);
        questionnaireEntityMapper.insertSelective(questionnaireEntity);
    }

    /**
     * 根据项目id查询所有问卷
     * @param parentId
     * @return
     */
    public List<Map<String,Object>> queryAllQuestionnaireByProjectId(String parentId){
        return questionnaireEntityMapper.queryAllQuestionnaireByProjectId(parentId);
    }

    /**
     * 根据项目id查询问卷个数
     */
    public int selectQuestionCountByProjectId(String parentId) {
        return questionnaireEntityMapper.selectQuestionCountByProjectId(parentId);
    }

    /**
     * 根据问卷id查询问卷isStop状态
     */
    public String queryQuestionnaireIsStop(String id) {
        return questionnaireEntityMapper.queryQuestionnaireIsStop(id);
    }
    /**
     * 根据问卷id修改问卷
     */
    public void modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) {
        Date newDate = DateUtil.getCreateTime();
        questionnaireEntity.setLastUpdateDate(newDate);
        questionnaireEntityMapper.modifyQuestionnaireInfo(questionnaireEntity);
    }

    /**
     * 设计问卷
     * @param map
     * @return
     */
    public int modifyQuestionnaire(Map map) {
        Date date = DateUtil.getCreateTime();
        map.put("lastUpdateDate", date);
        return questionnaireEntityMapper.modifyQuestionnaire(map);
    }
    /**
     * 根据问卷id查询问卷
     */
    public QuestionnaireEntity queryByPrimaryKey(String id) {
        return questionnaireEntityMapper.queryByPrimaryKey(id);
    }

    /**
     * 根据id删除问卷
     */
    public void deleteByPrimaryKey(String id) {
        questionnaireEntityMapper.deleteByPrimaryKey(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public QuestionnaireEntity queryQuestContextEnd(String id){
        return questionnaireEntityMapper.queryQuestContextEnd(id);
    }

    /**
     *
     * @param map
     * @return
     */
    public int addSendQuestionnaire(HashMap<String, Object> map){
        map.put("questionStop","1");
        // 获取当前时间
        Date date = DateUtil.getCreateTime();
        map.put("lastUpdateDate",date);
        map.put("releaseTime",date);
        return questionnaireEntityMapper.addSendQuestionnaire(map);
    }

    /**
     * 查询历史问卷
     *
     * @param map
     * @return
     */
    public List<Map<String,Object>> queryHistoryQuestionnaire(HashMap<String, Object> map) {
        return questionnaireEntityMapper.queryHistoryQuestionnaire(map);
    }

    /**
     * 查询问卷模板
     * @param dataId
     * @return
     */
    public List<Map<String,Object>> queryQuestionnaireModel(String dataId) {
        return questionnaireEntityMapper.queryQuestionnaireModel(dataId);
    }
    /**
     * 修改问卷状态为4
     * @param map
     * @return
     */
    public int modifyQuestionnaireStatus(HashMap<String, Object> map) {
        return questionnaireEntityMapper.modifyQuestionnaireStatus(map);
    }
    /**
     *
     * @param map
     * @return
     */
    public List<Object> queryQuestionnaireList(HashMap<String, Object> map) {
        List<Object> list = new ArrayList<Object>();
        List<Map<String,Object>> tempResult = questionnaireEntityMapper.queryQuestionnaireList(map);
        for(Map<String,Object> temp : tempResult) {
            String projectName = projectService.queryProjectName(temp.get("projectId").toString());
            temp.put("projectName",projectName);
            list.add(temp);
        }
        return list;
    }

    /**
     * 修改历史问卷状态
     * @param map
     * @return
     */
    public int modifyHistoryQuestionnaireStatus(HashMap<String, Object> map){
        map.put("questionStop","1");
        String endTimeStr = map.get("endTime").toString();
        Date endTime = DateUtil.getMyTime(endTimeStr);
        map.put("endTime",endTime);
        return questionnaireEntityMapper.modifyHistoryQuestionnaireStatus(map);
    }

    /**
     * 预览问卷，根据问卷id查询问卷
     *
     * @param map
     * @return
     */
    public Map<String, String> queryQuestionnaireById(HashMap<String, Object> map) {
        return questionnaireEntityMapper.queryQuestionnaireById(map);
    }

    public int pauseQuestionnaire(Map<String, Object> map) {
        return questionnaireEntityMapper.pauseQuestionnaire(map);
    }


}
