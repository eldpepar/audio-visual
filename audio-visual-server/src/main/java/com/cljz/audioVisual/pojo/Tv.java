package com.cljz.audioVisual.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table (name ="tb_tv" )
public class Tv {

  	@Id
	@Column(name = "id" )
	private String id;
  	@Column(name = "name" )
	private String name;
  	@Column(name = "icon" )
	private String icon;
  	@Column(name = "url" )
	private String url;
  	@Column(name = "user" )
	private String user;


	public String getId() {
		return id;
  }

	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
  }

	public void setName(String name) {
		this.name = name;
	}


	public String getIcon() {
		return icon;
  }

	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getUrl() {
		return url;
  }

	public void setUrl(String url) {
		this.url = url;
	}


	public String getUser() {
		return user;
  }

	public void setUser(String user) {
		this.user = user;
	}

}
