package com.example.springdemo.dao;

import com.example.springdemo.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
//@Repository
public interface UserEntityMapper {

    /**
     * 查询用户列表
     */
    List<UserEntity> queryUserList(UserEntity userEntity);

    /**
     * 创建用户基本信息
     */
    int insert(UserEntity userEntity);

    /**
     * 通过用户名删除用户
     */
    int deleteUserById(UserEntity userEntity);

    /**
     * 编辑用户信息
     */
    int updateByPrimaryKeySelective(UserEntity userEntity);
    /**
     * 查询用户
     */
    List<UserEntity> selectUserInfo(UserEntity userEntity);
}
