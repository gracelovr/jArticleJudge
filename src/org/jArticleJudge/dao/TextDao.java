package org.jArticleJudge.dao;


import java.util.List;
import java.util.Map;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jArticleJudge.dao.provider.TextDynaSqlProvider;
import org.jArticleJudge.dao.provider.UserDynaSqlProvider;
import org.jArticleJudge.domain.Text;
import org.jArticleJudge.domain.User;
import org.jArticleJudge.domain.Word;

import static org.jArticleJudge.util.common.JajConstants.*;


public interface TextDao {

	
	
	// 根据 用户id查询文章
	@Select("select * from "+TEXTTABLE+" where user_ID = #{id}")
	List<Text> selectById(Integer id);
	
	// 根据id查询文章
	@Select("select * from " + TEXTTABLE + " where text_id = #{id}")
	Text findText(Integer id);
	// 根据id删除文章
	@Delete(" delete from "+TEXTTABLE+" where text_id = #{id} ")
	void deleteByTextId(Integer id);
		
	// 根据用户id删除文章
		@Delete(" delete from "+TEXTTABLE+" where user_id = #{id} ")
		void deleteById(Integer id);
	
	// 动态插入文章
	@SelectProvider(type=TextDynaSqlProvider.class,method="insertText")
	void save(Text text);

	@SelectProvider(type=TextDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);

	// 动态查询
	@SelectProvider(type=TextDynaSqlProvider.class,method="selectWithParam")
	List<Text> selectByPage(Map<String, Object> params);
	
	
	
}
