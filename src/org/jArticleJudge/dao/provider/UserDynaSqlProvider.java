package org.jArticleJudge.dao.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.jArticleJudge.domain.User;
import static org.jArticleJudge.util.common.JajConstants.USERTABLE;;


public class UserDynaSqlProvider {
	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(USERTABLE);
				if(params.get("user") != null){
					User user = (User)params.get("user");
					if(user.getUser_name() != null && !user.getUser_name().equals("")){
						WHERE("  user_name LIKE CONCAT('%',#{user.user_name},'%') ");
					}
					if(user.getUser_type() != null && !user.getUser_type().equals("")){
						WHERE(" user_type LIKE CONCAT('%',#{user.user_type},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(USERTABLE);
				if(params.get("user") != null){
					User user = (User)params.get("user");
					if(user.getUser_name() != null && !user.getUser_name().equals("")){
						WHERE(" user_name LIKE CONCAT('%',#{user.user_name},'%') ");
					}
					if(user.getUser_type() != null && !user.getUser_type().equals("")){
						WHERE(" user_type LIKE CONCAT('%',#{user.user_type},'%') ");
					}
				}
			}
		}.toString();
	}	
	
	// 动态插入
	public String insertUser(User user){
		
		return new SQL(){
			{
				INSERT_INTO(USERTABLE);
				if(user.getUser_name() != null && !user.getUser_name().equals("")){
					VALUES("user_name", "#{user_name}");
				}
				if(user.getUser_type() != null && !user.getUser_type().equals("")){
					VALUES("user_type", "#{user_type}");
				}
			}
		}.toString();
	}
	// 动态注册
		public String register(String username){
			
			return new SQL(){
				{
					INSERT_INTO(USERTABLE);
					if(username != null && !username.equals("")){
						VALUES("user_name", "#{username}");
					}
						
					VALUES("user_type", "0");//默认0为普通用户1为管理员，只有管理员能把用户升级成管理员
					
					
				}
			}.toString();
		}
	// 动态更新
		public String updateUser(User user){
			
			return new SQL(){
				{
					UPDATE(USERTABLE);
					if(user.getUser_name() != null){
						SET(" user_name = #{user_name} ");
					}
					
					if(user.getUser_type()!= null){
						SET(" user_type = #{user_type} ");
					}
					WHERE(" user_id = #{user_id} ");
				}
			}.toString();
		}
}
