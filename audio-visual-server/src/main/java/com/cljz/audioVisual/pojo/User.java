package com.cljz.audioVisual.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tb_user")
public class User {

	public User() {
	}

	public User(String id, String name, String roles, String avatar, String email, String state) {
		this.id = id;
		this.name = name;
		this.roles = roles;
		this.avatar = avatar;
		this.email = email;
		this.state = state;
	}

	@Id
	@Column(name = "id" )
	private String id;
  	@Column(name = "name" )
	private String name;
  	@Column(name = "password" )
	private String password;
  	@Column(name = "roles" )
	private String roles;
  	@Column(name = "avatar" )
	private String avatar;
  	@Column(name = "email" )
	private String email;
  	@Column(name = "state" )
	private String state;


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


	public String getPassword() {
		return password;
  }

	public void setPassword(String password) {
		this.password = password;
	}


	public String getRoles() {
		return roles;
  }

	public void setRoles(String roles) {
		this.roles = roles;
	}


	public String getAvatar() {
		return avatar;
  }

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getEmail() {
		return email;
  }

	public void setEmail(String email) {
		this.email = email;
	}


	public String getState() {
		return state;
  }

	public void setState(String state) {
		this.state = state;
	}

}
