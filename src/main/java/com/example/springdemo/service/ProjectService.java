package com.example.springdemo.service;

import com.example.springdemo.common.utils.UUIDUtil;
import com.example.springdemo.dao.ProjectEntityMapper;
import com.example.springdemo.dao.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectEntityMapper projectEntityMapper;
    /**
     *查询项目列表、搜索项目
     */
    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity){
        List<ProjectEntity> result = projectEntityMapper.queryProjectList(projectEntity);
        return result;
    }
    /**
     *增加项目
     */
    public int addProjectInfo(ProjectEntity projectEntity){
        projectEntity.setId(UUIDUtil.getOneUUID());
        int projectResult = projectEntityMapper.insert(projectEntity);
        if(projectResult != 0){
            return 3;//数字3代表用户存在
        }else{
            return projectResult;
        }
    }
    /**
     *增加数据
     */
    public int insert(ProjectEntity projectEntity){
        return 0;
    }
    /**
     * 修改数据
     */
    public int modifyProjectInfo(ProjectEntity projectEntity){
        int projectResult = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        return projectResult;
    }
    /**
     * 删除项目
     */
    public int deleteProjectById(ProjectEntity projectEntity){
        int projectResult = projectEntityMapper.deleteProjectById(projectEntity);
        return projectResult;
    }
    /**
     * 根據id查询项目名称
     * @param id
     * @return
     */
    public String queryProjectName(String id) {
        return projectEntityMapper.queryProjectName(id);
    }
}
