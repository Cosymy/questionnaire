package com.example.springdemo.dao;

import com.example.springdemo.dao.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProjectEntityMapper {
    /**
     *搜索项目
     */
    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity);
    /**
     * 增加项目
     */
    int insert(ProjectEntity projectEntity);
    /**
     * 增加数据
     */

    /**
     * 修改项目
     */
     int updateByPrimaryKeySelective(ProjectEntity projectEntity);
     /**
     * 删除项目
     */
     int deleteProjectById(ProjectEntity projectEntity);
    /**
     * 根据项目id查询项目名
     * @author
     */
    String queryProjectName(String id);
}
