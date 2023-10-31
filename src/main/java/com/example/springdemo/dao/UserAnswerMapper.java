package com.example.springdemo.dao;

import com.example.springdemo.dao.entity.UserAnswer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerMapper {

    UserAnswer  selectById(UserAnswer userAnswer);

    List<UserAnswer> selectByQuId(UserAnswer userAnswer);

    int insert(UserAnswer userAnswer);

}
