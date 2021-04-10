package com.cljz.audioVisual.dao;

import com.cljz.audioVisual.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    //根据用户名查询
    User findOneByName(String name);

    //根据id查询
    User findOneById(String id);

    //根据邮箱查询
    User findOneByEmail(String email);
}