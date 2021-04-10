package com.cljz.audioVisual.services;

import com.cljz.audioVisual.pojo.User;
import com.cljz.audioVisual.response.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    //添加用户
    ResponseResult addUser(User user);

    //删除用户(根据id)
    ResponseResult deleteUserById(String id);

    //修改用户
    ResponseResult updateUser(String id, User user);

    //显示用户列表
    ResponseResult listUsers();

    //禁用用户
    ResponseResult stopUser(String Id);



}