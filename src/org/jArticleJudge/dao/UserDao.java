package org.jArticleJudge.dao;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jArticleJudge.dao.provider.UserDynaSqlProvider;
import org.jArticleJudge.domain.User;
import static org.jArticleJudge.util.common.JajConstants.*;


public interface UserDao {

	// 根据登录名和密码查询用户
	@Select("select * from "+USERTABLE+" natural join " + PASSWORDTABLE + " where user_name = #{username} and pw_content = #{password}")
	User selectByLoginnameAndPassword(
			@Param("username") String username,
			@Param("password") String password);//加密过后的密码直接从service传过来
	//根据用户名查询id
	@Select("select user_id from " + USERTABLE + " where user_name = #{username}")
	Integer findId(@Param("username") String username);
	// 根据id查询用户
	@Select("select * from "+USERTABLE+" where user_ID = #{id}")
	User selectById(Integer id);
	
	// 根据id删除用户
	@Delete(" delete from "+USERTABLE+" where user_id = #{id} ")
	void deleteById(Integer id);
		
	// 动态修改用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user); 
		
	// 动态查询
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWithParam")
	List<User> selectByPage(Map<String, Object> params);
	
	// 根据参数查询用户总数
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	// 动态插入用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);

	@SelectProvider(type=UserDynaSqlProvider.class,method="register")
	void register(String username);
	
	
}
