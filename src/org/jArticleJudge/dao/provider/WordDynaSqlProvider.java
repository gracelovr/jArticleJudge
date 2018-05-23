package org.jArticleJudge.dao.provider;


import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.jArticleJudge.domain.User;
import org.jArticleJudge.domain.Word;

import static org.jArticleJudge.util.common.JajConstants.USERTABLE;
import static org.jArticleJudge.util.common.JajConstants.WORDTABLE;;
public class WordDynaSqlProvider {
	//动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(WORDTABLE);
				if(params.get("word") != null){
					Word word = (Word)params.get("word");
					if(word.getWord_content() != null && !word.getWord_content().equals("")){
						WHERE("  word_content LIKE CONCAT('%',#{word.word_content},'%') ");
					}
					if(word.getWord_lvl() != null && !word.getWord_lvl().equals("")){
						WHERE(" word_lvl = #{user.word_lvl ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}
	public String count(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(WORDTABLE);
				if(params.get("word") != null){
					Word word = (Word)params.get("word");
					if(word.getWord_content() != null && !word.getWord_content().equals("")){
						WHERE("  word_content LIKE CONCAT('%',#{word.word_content},'%') ");
					}
					if(word.getWord_lvl() != null && !word.getWord_lvl().equals("")){
						WHERE(" word_lvl = #{user.word_lvl ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}
	// 动态插入
		public String insertWord(Word word){
			
			return new SQL(){
				{
					INSERT_INTO(WORDTABLE);
					if(word.getWord_content() != null && !word.getWord_content().equals("")){
						VALUES("word_content", "#{word_content}");
					}
					if(word.getWord_lvl() != null && !word.getWord_lvl().equals("")){
						VALUES("word_lvl", "#{word_lvl}");
					}

				}
			}.toString();
		}
}
