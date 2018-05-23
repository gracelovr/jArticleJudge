package org.jArticleJudge.dao.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.jArticleJudge.domain.Password;
import org.jArticleJudge.domain.User;
import static org.jArticleJudge.util.common.JajConstants.*;;


public class PwDynaSqlProvider {
	
	// 动态插入
	public String insertPw(Password pw){
		
		return new SQL(){
			{
				INSERT_INTO(PASSWORDTABLE);
				if(pw.getUser_id()!= null && !pw.getUser_id().toString().equals("")){
					VALUES("user_id", "#{user_id}");
				}
				if(pw.getPw_content() != null && !pw.getPw_content().equals("")){
					VALUES("pw_content", "#{pw_content}");
				}
			}
		}.toString();
	}
	
	// 动态更新
		public String updatePw(Password pw){
			
			return new SQL(){
				{
					UPDATE(PASSWORDTABLE);
					
					
					if(pw.getPw_content()!= null){
						SET(" pw_content = #{pw_content} ");
					}
					WHERE(" user_id = #{user_id} ");
				}
			}.toString();
		}
}
