package com.cljz.audioVisual.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table (name = "tb_visit")
public class DayVisit {

	@Id
	@Column(name = "id")
	private String id;
  	@Column(name = "count")
	private String count;
  	@Column(name = "`date`" )
	private Date date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
