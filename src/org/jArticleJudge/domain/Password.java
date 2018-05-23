package org.jArticleJudge.domain;

import java.io.Serializable;
import java.util.Date;


public class Password implements Serializable {
	private Integer pw_id; //密码id
	private Integer user_id; //用户id
	private String pw_content; //密文
	public Integer getPw_id() {
		return pw_id;
	}
	public void setPw_id(Integer pw_id) {
		this.pw_id = pw_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getPw_content() {
		return pw_content;
	}
	public void setPw_content(String pw_content) {
		this.pw_content = pw_content;
	}
	
}
