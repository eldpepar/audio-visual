package com.cljz.audioVisual.dao;

import com.cljz.audioVisual.pojo.Tv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TvDao  extends JpaRepository<Tv,String>, JpaSpecificationExecutor<Tv> {
    //根据用户名查询
    Tv findOneByName(String name);

    //根据id查询
    Tv findOneById(String id);
}