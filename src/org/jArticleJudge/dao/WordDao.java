package org.jArticleJudge.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jArticleJudge.dao.provider.UserDynaSqlProvider;
import org.jArticleJudge.dao.provider.WordDynaSqlProvider;
import org.jArticleJudge.domain.User;
import org.jArticleJudge.domain.Word;

import static org.jArticleJudge.util.common.JajConstants.WORDTABLE;
public interface WordDao {
	// 动态查询
	@SelectProvider(type=WordDynaSqlProvider.class,method="selectWithParam")
	List<Word> selectByPage(Map<String, Object> params);
	
	// 动态插入词条
	@SelectProvider(type=WordDynaSqlProvider.class,method="insertWord")
	void save(Word word);
		
	// 根据id删除词条
	@Delete(" delete from "+WORDTABLE+" where word_id = #{id} ")
	void deleteById(Integer id);
	
	@Select("select * from "+WORDTABLE+" where word_content = #{content}")
	List<Word> findWordByContent(String content);
	
	@Select("select word_lvl from "+WORDTABLE+" where word_content = #{content}")
	Integer checkLvl(String content);
	
	@Select("select * from "+WORDTABLE+" where word_lvl = #{lvl}")
	List<Word> findWordByLvl(int lvl);

	// 根据参数查询词条总数
	@SelectProvider(type=WordDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
}
