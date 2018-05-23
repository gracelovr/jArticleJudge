package org.jArticleJudge.domain;

import java.io.Serializable;
import java.util.Date;


public class Text implements Serializable {
	private Integer text_id; //文章id
	private Integer user_id; //
	private String text_title; //文章标题
	private String text_content; //文章内容
	private Integer text_lvl; //文章难度等级
	public Integer getText_id() {
		return text_id;
	}
	public void setText_id(Integer text_id) {
		this.text_id = text_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getText_title() {
		return text_title;
	}
	public void setText_title(String text_title) {
		this.text_title = text_title;
	}
	public String getText_content() {
		return text_content;
	}
	public void setText_content(String text_content) {
		this.text_content = text_content;
	}
	public Integer getText_lvl() {
		return text_lvl;
	}
	public void setText_lvl(Integer text_lvl) {
		this.text_lvl = text_lvl;
	}
	
}
