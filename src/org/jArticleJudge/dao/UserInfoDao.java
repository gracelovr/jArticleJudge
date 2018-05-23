package org.jArticleJudge.dao;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jArticleJudge.dao.provider.PwDynaSqlProvider;
import org.jArticleJudge.dao.provider.UserInfoDynaSqlProvider;
import org.jArticleJudge.domain.Password;
import org.jArticleJudge.domain.User;
import org.jArticleJudge.domain.UserInfo;

import static org.jArticleJudge.util.common.JajConstants.*;


public interface UserInfoDao {

	
	
	// 根据id查询详情
	@Select("select * from "+INFOTABLE+" where user_ID = #{id}")
	UserInfo selectById(Integer id);
	
	// 根据id删除密码
	@Delete(" delete from "+INFOTABLE+" where user_id = #{id} ")
	void deleteById(Integer id);
		
	// 动态修改密码
	@SelectProvider(type=UserInfoDynaSqlProvider.class,method="updateInfo")
	void update(UserInfo info); 
	
	// 动态插入密码
	@SelectProvider(type=UserInfoDynaSqlProvider.class,method="insertInfo")
	void save(UserInfo info);

	
	
	
}
