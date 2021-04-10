package com.cljz.audioVisual.services.impl;

import com.cljz.audioVisual.dao.UserDao;
import com.cljz.audioVisual.pojo.User;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.UserService;
import com.cljz.audioVisual.utils.Constants;
import com.cljz.audioVisual.utils.IdWorker;
import com.cljz.audioVisual.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //添加用户
    @Override
    public ResponseResult addUser(User user) {
        //检查用户名格式
        String name = user.getName();
        if (TextUtils.isEmply(name)) {
            return ResponseResult.FAILED("用户名不可以为空.");
        }

        //检查当前用户名是否已经注册
        User userByName = userDao.findOneByName(name);
        if (userByName != null) {
            return ResponseResult.FAILED("该用户名已注册.");
        }

        //检查邮箱格式是否正确
        String email = user.getEmail();
        if (TextUtils.isEmply(email)) {
            return ResponseResult.FAILED("邮箱地址不可以为空.");
        }
        if (!TextUtils.isEmailAddressOk(email)) {
            return ResponseResult.FAILED("邮箱地址格式不正确");
        }

        //检查该邮箱是否已经注册
        User userByEmail = userDao.findOneByEmail(email);
        if (userByEmail != null) {
            return ResponseResult.FAILED("该邮箱地址已经注册");
        }

        String password = user.getPassword();
        if (TextUtils.isEmply(password)) {
            return ResponseResult.FAILED("密码不可以为空");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        //补全信息
//        user.setAvatar(Constants.User.DEFAULT_AVATAR);
        user.setRoles(Constants.User.ROLE_ADMIN);
        user.setState("1");
        user.setId(idWorker.nextId() + "");

        //保存数据
        userDao.save(user);
        //返回结果
        return ResponseResult.SUCCESS("添加成功").setData(user);
    }


    //根据用户id删除用户
    @Override
    public ResponseResult deleteUserById(String id) {
        User user = userDao.findOneById(id);

        if (user == null) {
            return ResponseResult.SUCCESS("没有该用户");
        } else {
            if (user.getRoles().equals(Constants.User.ROLE_ROOT)) {
                return ResponseResult.FAILED("不可删除");
            }
            userDao.delete(user);
        }
        return ResponseResult.SUCCESS("删除成功");
    }

    //修改用户
    @Override
    public ResponseResult updateUser(String id, User user) {
        User oldUser = userDao.findOneById(id);

        if (oldUser == null) {
            return ResponseResult.FAILED("用户不存在");
        }
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        oldUser.setAvatar(user.getAvatar());
        userDao.save(oldUser);
        return ResponseResult.SUCCESS("修改用户成功").setData(oldUser.getName());
    }


    //显示用户列表
    @Override
    public ResponseResult listUsers() {
        ResponseResult result = ResponseResult.SUCCESS("获取成功").setData(userDao.findAll());
        return result;
    }

    //禁用用户
    @Override
    public ResponseResult stopUser(String Id) {
        User user = userDao.findOneById(Id);

        if (user.getRoles().equals(Constants.User.ROLE_ROOT)) {
            return ResponseResult.FAILED("不可禁用");
        }
        if (user == null) {
            return ResponseResult.FAILED("用户不存在");
        }
        String state = user.getState();
        if ("1".equals(state)) {
            user.setState("0");
        } else {
            user.setState("1");
        }
        userDao.save(user);
        return ResponseResult.SUCCESS("禁用（解禁）用户成功");
    }

}
