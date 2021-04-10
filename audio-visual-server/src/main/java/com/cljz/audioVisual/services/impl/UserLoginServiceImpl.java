package com.cljz.audioVisual.services.impl;

import com.cljz.audioVisual.dao.UserDao;
import com.cljz.audioVisual.pojo.User;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.UserLoginService;
import com.cljz.audioVisual.utils.*;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Random random;

    //注册用户
    @Override
    public ResponseResult registerUser(@RequestBody User sobUser, String emailCode, String captchaCode, String captchaKey, HttpServletRequest request) {
        //第一步：检查当前用户名是否已经注册
        String userName = sobUser.getName();
        if (TextUtils.isEmply(userName)) {
            return ResponseResult.FAILED("用户名不可以为空.");
        }
        User userByName = userDao.findOneByName(userName);
        if (userByName != null) {
            return ResponseResult.FAILED("该用户名已注册.");
        }
        //第二步：检查邮箱格式是否正确
        String email = sobUser.getEmail();
        if (TextUtils.isEmply(email)) {
            return ResponseResult.FAILED("邮箱地址不可以为空.");
        }
        if (!TextUtils.isEmailAddressOk(email)) {
            return ResponseResult.FAILED("邮箱地址格式不正确");
        }
        //第三步：检查该邮箱是否已经注册
        User userByEmail = userDao.findOneByEmail(email);
        if (userByEmail != null) {
            return ResponseResult.FAILED("该邮箱地址已经注册");
        }

        //第四步：检查邮箱验证码是否正确
        String emailVerifyCode = (String) redisUtil.get(Constants.User.KEY_EMAIL_CODE_CONTENT);

        if (TextUtils.isEmply(emailVerifyCode)) {
            return ResponseResult.FAILED("邮箱验证码已过期");
        }
        if (!emailVerifyCode.equals(emailCode)) {
            return ResponseResult.FAILED("邮箱验证码不正确");
        } else {
            //正确，干掉redis里的内容
            redisUtil.del(Constants.User.KEY_EMAIL_CODE_CONTENT);
        }

        //第五步：检查图灵验证码是否正确
        String captchaVerifyCode = (String) redisUtil.get(Constants.User.KEY_CAPTCHA_CONTENT + captchaKey);
        if (TextUtils.isEmply(captchaVerifyCode)) {
            return ResponseResult.FAILED("人类验证码已过期");
        }
        if (!captchaVerifyCode.equals(captchaCode)) {
            return ResponseResult.FAILED("人类验证码不正确");
        } else {
            redisUtil.del(Constants.User.KEY_CAPTCHA_CONTENT + captchaKey);
        }
        //达到可以注册的条件
        //第六步：对密码进行加密
        String password = sobUser.getPassword();
        if (TextUtils.isEmply(password)) {
            return ResponseResult.FAILED("密码不可以为空");
        }
        sobUser.setPassword(bCryptPasswordEncoder.encode(sobUser.getPassword()));
        //第七步：补全数据
        //包括：注册IP,登录IP,角色,头像,创建时间,更新时间
        String ipAddress = request.getRemoteAddr();
        sobUser.setAvatar(Constants.User.DEFAULT_AVATAR);
        sobUser.setRoles(Constants.User.ROLE_ADMIN);
        sobUser.setState("1");
        sobUser.setId(idWorker.nextId() + "");
        //第八步：保存到数据库中
        userDao.save(sobUser);
        //第九步：返回结果
        return ResponseResult.SUCCESS("注册成功").setData(sobUser);
    }

    //用户登录
    @Override
    public ResponseResult login(String captcha, String captchaKey, User user, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String userName = user.getName();
        if (TextUtils.isEmply(userName)) {
            return ResponseResult.FAILED("账号不能为空");
        }

        String password = user.getPassword();
        if (TextUtils.isEmply(password)) {
            return ResponseResult.FAILED("密码不能为空");
        }

        if (captcha == null) {
            return ResponseResult.FAILED("验证码不能为空");
        }

        User userLogin = userDao.findOneByName(userName);
        if (userLogin==null) {
            userLogin = userDao.findOneByEmail(userName);
        }

        if (userLogin==null) {
            return ResponseResult.FAILED("用户名或密码错误");
        }

        boolean matches = bCryptPasswordEncoder.matches(password, userLogin.getPassword());
        if (!matches) {
            return ResponseResult.FAILED("用户名或密码错误");
        }

        String captchaValue = (String) redisUtil.get(Constants.User.KEY_CAPTCHA_CONTENT + captchaKey);
        if (!captcha.equals(captchaValue)) {
            return ResponseResult.FAILED("验证码错误");
        }

        //判断用户的状态，如果是非正常，返回结果
        if (!"1".equals(userLogin.getState())) {
            return ResponseResult.FAILED("该账号已被禁止。");
        }

        //生成token
        Map<String, Object> claims = ClaimsUtils.UserClainms(userLogin);

        //token默认两个小时
        String token = JwtUtil.createToken(claims);

        //返回token的MD5值，token会保存在redis里
        //前端访问，携带token的md5key，从redis中获取

        String tokenKey = DigestUtils.md5DigestAsHex(token.getBytes());

        //保存token到redis中，有效期是两个小时，key是tokenKey
        redisUtil.set(Constants.User.COOKIE_TOKEN_KEY + tokenKey, token, 60 * 60 * 2);

        //把tokenKey写入cookie中去
        CookieUtils.setUpCookie(response, Constants.User.COOKIE_TOKEN_KEY,tokenKey);

        return ResponseResult.SUCCESS("登录成功:").setData(user.getName());
    }

    //退出登录
    @Override
    public ResponseResult logout(HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String tokenKey = CookieUtils.getCookie(request, Constants.User.COOKIE_TOKEN_KEY);
        User user = parseByTokenKey(tokenKey);
        if (user != null) {
            redisUtil.del(Constants.User.COOKIE_TOKEN_KEY + tokenKey);
            CookieUtils.deleteCookie(response,Constants.User.COOKIE_TOKEN_KEY);
            return ResponseResult.SUCCESS("退出登录成功").setData(user);
        } else {
            return ResponseResult.FAILED("退出登录失败");
        }
    }

    //生成验证码
    @Override
    public void createCaptcha(HttpServletResponse response, String captchaKey) throws IOException, FontFormatException {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体
        // specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        specCaptcha.setFont(Captcha.FONT_2);
        // 设置类型，纯数字、纯字母、字母数字混合
        //specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        String content = specCaptcha.text().toLowerCase();

        //验证码存入redis,十分钟有效
        redisUtil.set(Constants.User.KEY_CAPTCHA_CONTENT + captchaKey, content, 60*10);

        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }

    //发送邮箱验证码
    @Override
    public ResponseResult sendEmail(HttpServletRequest request, String emailAddress) {
            //防止暴力发送，限制发送间隔
            String remoteAddr = request.getRemoteAddr();
            if (remoteAddr != null) {
                remoteAddr = remoteAddr.replaceAll(":", "-");
            }
            Integer ipSendTime = (Integer) redisUtil.get(Constants.User.KEY_EMAIL_SEND__IP + remoteAddr);
            if (ipSendTime != null && ipSendTime > 10) {
                return ResponseResult.FAILED("验证码发送过于频繁");
            }
            Object hasEmailSend =  redisUtil.get(Constants.User.KEY_EMAIL_SEND_ADDRESS + emailAddress);
            if (hasEmailSend != null) {
                return ResponseResult.FAILED("验证码发送过于频繁");
            }

            //检查邮箱是否正确
            boolean isEmail = TextUtils.isEmailAddressOk(emailAddress);
            if (!isEmail) {
                return ResponseResult.FAILED("邮箱格式错误");
            }

            //0到999999
            int code = random.nextInt(999999);
            if (code < 100000) {
                code += 100000;
            }

            try {
                taskService.sendEmailVerifyCode(String.valueOf(code),emailAddress);
            }  catch (Exception e) {
                return ResponseResult.FAILED("验证码发送失败，稍后重试");
            }

            //做记录,发code
            if (ipSendTime == null) {
                ipSendTime = 0;
            }
            ipSendTime++;

            //1个小时的有效期
            redisUtil.set(Constants.User.KEY_EMAIL_SEND__IP + remoteAddr, ipSendTime, 60 * 60);
            redisUtil.set(Constants.User.KEY_EMAIL_SEND_ADDRESS + emailAddress, "true", 30);

            //保存code，十分钟内有效
            redisUtil.set(Constants.User.KEY_EMAIL_CODE_CONTENT, String.valueOf(code), 60 * 10);
            return ResponseResult.SUCCESS("验证码发送成功").setData(code);
    }


    @Override
    public User checkUser(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //获取token_key
        String tokenKey = CookieUtils.getCookie(request, Constants.User.COOKIE_TOKEN_KEY);
        if (tokenKey == null) {
            return  null;
        }
        User user = parseByTokenKey(tokenKey);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    private User parseByTokenKey(String tokenKey) {
        String token = (String) redisUtil.get(Constants.User.COOKIE_TOKEN_KEY + tokenKey);
        if (token == null) {
            return  null;
        } else {
            try {
                Claims claims = JwtUtil.parseJWT(token);
                return ClaimsUtils.claimsUser(claims);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public ResponseResult parseToken(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            User user = checkUser(request, response);
            if (user != null) {
                return ResponseResult.SUCCESS("获取用户成功").setData(user);
            } else {
                return ResponseResult.FAILED("用户未登录");
            }
        } catch (NullPointerException e) {
            CookieUtils.deleteCookie(response,Constants.User.COOKIE_TOKEN_KEY);
            return ResponseResult.FAILED("请重新登录");
        }
    }
}