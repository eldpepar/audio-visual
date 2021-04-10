package com.cljz.audioVisual.controller.user;

import com.cljz.audioVisual.pojo.User;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class UserLoginApi {

    @Autowired
    private UserLoginService userLoginService;

    //获取验证码
    @GetMapping("/captcha")
    public void getCaptcha(String captchaKey, HttpServletResponse response) throws IOException, FontFormatException {
        userLoginService.createCaptcha(response, captchaKey);
    }

    //邮箱验证码
    @GetMapping("/verify_code")
    public ResponseResult sendVerifyCode(HttpServletRequest request, @RequestParam("email") String emailAddress) throws MessagingException {
        return userLoginService.sendEmail(request, emailAddress);
    }

    //登录
    @PostMapping("/{captcha}/{captcha_key}")
    public ResponseResult login(@PathVariable("captcha") String captcha,
                                @RequestBody User user  ,
                                @PathVariable("captcha_key") String captchaKey,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        return userLoginService.login(captcha.toLowerCase(), captchaKey, user, request, response);
    }

    //检测是否登录
    @GetMapping("/check-token")
    public ResponseResult parseToeken(HttpServletRequest request, HttpServletResponse response) {
        return userLoginService.parseToken(request, response);
    }

    //退出登录
    @GetMapping("/logout")
    public ResponseResult logout(HttpServletRequest request, HttpServletResponse response) {
        return userLoginService.logout(request, response);
    }

    //注册用户
    @PostMapping
    public ResponseResult register(@RequestBody User sobUser, String emailCode, String captchaCode, String captchaKey, HttpServletRequest request) {
        return userLoginService.registerUser(sobUser, emailCode, captchaCode, captchaKey, request);
    }
}