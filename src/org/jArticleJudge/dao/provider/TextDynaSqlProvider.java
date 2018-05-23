package org.jArticleJudge.dao.provider;

import static org.jArticleJudge.util.common.JajConstants.INFOTABLE;
import static org.jArticleJudge.util.common.JajConstants.TEXTTABLE;
import static org.jArticleJudge.util.common.JajConstants.USERTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jArticleJudge.domain.*;

public class TextDynaSqlProvider {
	// 动态查询总数量
		public String count(Map<String, Object> params){
			return new SQL(){
				{
					SELECT("count(*)");
					FROM(TEXTTABLE);
					if(params.get("text") != null){
						Text text = (Text)params.get("text");
						if(text.getText_title() != null && !text.getText_title().equals("")){
							WHERE(" text_title LIKE CONCAT('%',#{text.text_title},'%') ");
						}
						if(text.getText_lvl() != null && !text.getText_lvl().toString().equals("")){
							WHERE(" text_lvl = #{text.text_lvl} ");
						}
					}
				}
			}.toString();
		}
		
		// 动态插入
		public String insertText(Text text){
			
			return new SQL(){
				{
					INSERT_INTO(TEXTTABLE);
					if(text.getUser_id()!= null && !text.getUser_id().toString().equals("")){
						VALUES("user_id", "#{user_id}");
					}
					if(text.getText_title() != null && !text.getText_title().equals("")){
						VALUES("text_title", "#{text_title}");
					}
					if(text.getText_lvl() != null && !text.getText_lvl().toString().equals("")){
						VALUES("text_lvl", "#{text_lvl}");
					}
					if(text.getText_content() != null && !text.getText_content().equals("")){
						VALUES("text_content", "#{text_content}");
					}
				}
			}.toString();
		}
		
		// 分页动态查询
		public String selectWithParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(TEXTTABLE);
					if(params.get("text") != null){
						Text text = (Text)params.get("text");
						if(text.getText_lvl() != null && !text.getText_lvl().toString().equals("")){
							WHERE("  text_lvl=#{text.text_lvl} ");
						}
						if(text.getText_title() != null && !text.getText_title().equals("")){
							WHERE(" text_title LIKE CONCAT('%',#{text.text_title},'%') ");
						}
						if(text.getUser_id() != null && !text.getUser_id().toString().equals("")){
							WHERE(" user_id=#{text.user_id} ");
						}
					}
				}
			}.toString();
			
			if(params.get("pageModel") != null){
				sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
			}
			
			return sql;
		}
}
