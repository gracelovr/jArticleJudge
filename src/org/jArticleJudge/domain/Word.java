package org.jArticleJudge.domain;

public class Word {
	private Integer word_id;
	private String word_content;
	private Integer word_lvl;
	public Word() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getWord_id() {
		return word_id;
	}
	public void setWord_id(Integer word_id) {
		this.word_id = word_id;
	}
	public String getWord_content() {
		return word_content;
	}
	public void setWord_content(String word_content) {
		this.word_content = word_content;
	}
	public Integer getWord_lvl() {
		return word_lvl;
	}
	public void setWord_lvl(Integer word_lvl) {
		this.word_lvl = word_lvl;
	}
		
}
