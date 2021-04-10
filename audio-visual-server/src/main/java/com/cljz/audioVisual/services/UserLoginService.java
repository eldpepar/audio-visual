package com.cljz.audioVisual.services;

import com.cljz.audioVisual.pojo.User;
import com.cljz.audioVisual.response.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public interface UserLoginService {
    //注册用户
    ResponseResult registerUser(@RequestBody User sobUser, String emailCode, String captchaCode, String captchaKey, HttpServletRequest request);

    //登录
    ResponseResult login(String captcha, String captchaKey, User user, HttpServletRequest request, HttpServletResponse response);

    //退出登录
    ResponseResult logout(HttpServletRequest request,HttpServletResponse response);

    //获取登录信息
    User checkUser(HttpServletRequest request, HttpServletResponse response);

    //解析登录的token
    ResponseResult parseToken(HttpServletRequest request, HttpServletResponse response);

    //获取验证码
    void createCaptcha(HttpServletResponse response, String captchaKey) throws IOException, FontFormatException;

    //生成邮箱验证码
    ResponseResult sendEmail(HttpServletRequest request, String emailAddress);
}
