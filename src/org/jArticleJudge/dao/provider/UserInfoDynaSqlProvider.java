package org.jArticleJudge.dao.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.jArticleJudge.domain.Password;
import org.jArticleJudge.domain.User;
import org.jArticleJudge.domain.UserInfo;

import static org.jArticleJudge.util.common.JajConstants.*;;


public class UserInfoDynaSqlProvider {
	
	// 动态插入
	public String insertInfo(UserInfo info){
		
		return new SQL(){
			{
				INSERT_INTO(INFOTABLE);
				if(info.getUser_id()!= null && !info.getUser_id().toString().equals("")){
					VALUES("user_id", "#{user_id}");
				}
				if(info.getNickname() != null && !info.getNickname().equals("")){
					VALUES("nickname", "#{nickname}");
				}
				if(info.getUser_mail() != null && !info.getUser_mail().equals("")){
					VALUES("user_mail", "#{user_mail}");
				}
				if(info.getPhone_num() != null && !info.getPhone_num().equals("")){
					VALUES("phone_num", "#{phone_num}");
				}
				if(info.getBirthday() != null && !info.getBirthday().equals("")){
					VALUES("birthday", "#{birthday}");
				}
			}
		}.toString();
	}
	
	// 动态更新
		public String updateInfo(UserInfo info){
			
			return new SQL(){
				{
					UPDATE(INFOTABLE);
					if(info.getNickname() != null){
						SET(" nickname = #{nickname} ");
					}
					if(info.getUser_mail() != null){
						SET(" user_mail = #{user_mail} ");
					}
					if(info.getPhone_num() != null){
						SET(" phone_num = #{phone_num} ");
					}
					if(info.getBirthday() != null){
						SET(" birthday = #{birthday} ");
					}
					
					
					WHERE(" user_id = #{user_id} ");
				}
			}.toString();
		}
}
