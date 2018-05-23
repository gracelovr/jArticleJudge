package org.jArticleJudge.domain;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {

	private Integer user_id;			// id
	private String user_name;	// 用户名
	private Integer user_type;		// 状态
	private String password; //密码临时存储
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 无参数构造器
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	@Override
	public String toString() {
		return "User [id=" + user_id + ", username=" + user_name +  ", type=" + user_type + "]";
	}
	
	
}
