package com.cljz.audioVisual.dao;

import com.cljz.audioVisual.pojo.DayVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DayVisitDao extends JpaRepository<DayVisit,String>, JpaSpecificationExecutor<DayVisit> {

}
