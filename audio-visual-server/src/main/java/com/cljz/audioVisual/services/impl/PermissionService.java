package com.cljz.audioVisual.services.impl;
import com.cljz.audioVisual.pojo.User;
import com.cljz.audioVisual.services.UserLoginService;
import com.cljz.audioVisual.utils.Constants;
import com.cljz.audioVisual.utils.CookieUtils;
import com.cljz.audioVisual.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service("permission")
public class PermissionService {

    @Autowired
    private UserLoginService userService;

    //管理员
    public boolean admin() {
        // 获取到当前权限所有的角色，进行角色对比即可确定权限
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //如果token返回false
        String token = CookieUtils.getCookie(request, Constants.User.COOKIE_TOKEN_KEY);
        if (TextUtils.isEmply(token)) {
            return false;
        }
        User cljzUser = userService.checkUser(request, response);
        if (cljzUser == null || TextUtils.isEmply(cljzUser.getRoles())) {
            return false;
        }
        if (Constants.User.ROLE_ADMIN.equals(cljzUser.getRoles()) || Constants.User.ROLE_ROOT.equals(cljzUser.getRoles())) {
            return true;
        }
        return false;
    }

    //超级管理员
    public boolean root() {
        // 获取到当前权限所有的角色，进行角色对比即可确定权限
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //如果token返回false
        String token = CookieUtils.getCookie(request, Constants.User.COOKIE_TOKEN_KEY);
        if (TextUtils.isEmply(token)) {
            return false;
        }
        User cljzUser = userService.checkUser(request, response);
        if (cljzUser == null || TextUtils.isEmply(cljzUser.getRoles())) {
            return false;
        }
        if (Constants.User.ROLE_ROOT.equals(cljzUser.getRoles())) {
            return true;
        }
        return false;
    }
}