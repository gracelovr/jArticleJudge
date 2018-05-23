package org.jArticleJudge.dao;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jArticleJudge.dao.provider.PwDynaSqlProvider;
import org.jArticleJudge.dao.provider.UserDynaSqlProvider;
import org.jArticleJudge.domain.Password;
import org.jArticleJudge.domain.User;
import static org.jArticleJudge.util.common.JajConstants.*;


public interface PwDao {

	
	
	// 根据id查询密码
	@Select("select * from "+PASSWORDTABLE+" where user_ID = #{id}")
	Password selectById(Integer id);
	
	// 根据id删除密码
	@Delete(" delete from "+PASSWORDTABLE+" where user_id = #{id} ")
	void deleteById(Integer id);
		
	// 动态修改密码
	@SelectProvider(type=PwDynaSqlProvider.class,method="updatePw")
	void update(Password pw); 
	
	// 动态插入密码
	@SelectProvider(type=PwDynaSqlProvider.class,method="insertPw")
	void save(Password pw);

	
	
	
}
