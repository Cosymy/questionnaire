package com.example.springdemo.controller;

import com.example.springdemo.beans.HttpResponseEntity;
import com.example.springdemo.dao.entity.UserEntity;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     *用户登录
     */
    @PostMapping("/userLogin")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<UserEntity> hasUser = userService.selectUserInfo(userEntity);
            if(CollectionUtils.isEmpty(hasUser)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setMassage("用户名或密码错误");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMassage("登录成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;

    }

    /**
     *用户列表查询
     */
    @PostMapping("/queryUserList")
    public HttpResponseEntity queryUserList(UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<UserEntity> hasProject = userService.queryUserList(userEntity);
            if(CollectionUtils.isEmpty(hasProject)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasProject.get(0));
                httpResponseEntity.setMassage("无用户信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasProject);
                httpResponseEntity.setMassage("查询成功");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     *用户添加
     */
    @PostMapping("/addUserInfo")
    public HttpResponseEntity addUser(@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = userService.addUserInfo(userEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMassage("创建成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMassage("创建失败");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     *用户修改
     */
    @PostMapping("/modifyUserInfo")

    public HttpResponseEntity modifyUser(@RequestBody UserEntity userEntity){
        System.out.println(userEntity);
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = userService.modifyUserInfo(userEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMassage("修改成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMassage("修改失败");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     *用户删除
     */
    @PostMapping( "/deleteUserinfo")

    public HttpResponseEntity deleteUser(@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        System.out.println(userEntity);
        System.out.println("111");
        try{
            int result = userService.deleteUserById(userEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMassage("删除成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMassage("删除失败");
            }
        }catch(Exception e){

        }
        return httpResponseEntity;
    }

}
