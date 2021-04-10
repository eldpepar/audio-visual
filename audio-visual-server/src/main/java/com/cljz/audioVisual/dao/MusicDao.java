package com.cljz.audioVisual.dao;

import com.cljz.audioVisual.pojo.MusicUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MusicDao extends JpaRepository<MusicUrl,String>, JpaSpecificationExecutor<MusicUrl> {
    //根据用户名查询
    MusicUrl findOneByName(String name);

    //根据id查询
    MusicUrl findOneById(String id);
}
