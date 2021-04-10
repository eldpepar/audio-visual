package com.cljz.audioVisual.controller.root;

import com.cljz.audioVisual.pojo.User;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/root")
@PreAuthorize("@permission.root()")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class UserApi {

    @Autowired
    private UserService userService;

    //添加用户接口
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    //修改用户接口
    @PutMapping("/user/{userId}")
    public ResponseResult updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        return userService.updateUser(userId,user);
    }

    //删除用户接口
    @DeleteMapping("/user/{userId}")
    public ResponseResult deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUserById(userId);
    }

    //显示用户列表
    @GetMapping("/list")
    public ResponseResult listUsers() {
        return userService.listUsers();
    }

    //禁用用户
    @GetMapping("/user/{userId}")
    public ResponseResult listUsers(@PathVariable("userId") String userId) {
        return userService.stopUser(userId);
    }
}