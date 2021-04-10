package com.cljz.audioVisual.dao;

import com.cljz.audioVisual.pojo.MovieUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieDao extends JpaRepository<MovieUrl,String>, JpaSpecificationExecutor<MovieUrl> {
    //根据用户名查询
    MovieUrl findOneByName(String name);

    //根据id查询
    MovieUrl findOneById(String id);
}
