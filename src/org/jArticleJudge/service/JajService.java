package org.jArticleJudge.service;

import java.util.List;

import org.jArticleJudge.domain.Text;
import org.jArticleJudge.domain.User;
import org.jArticleJudge.domain.UserInfo;
import org.jArticleJudge.domain.Word;
import org.jArticleJudge.util.tag.PageModel;


public interface JajService {
//TODO 新增的userinfo，password，text方法
	/** adduserinfo() findinfobyid() updateinfobyid() deleteinfobyid()
	 *  findalltext(text,pagemodel) findtext(userid,text,pagemodel) addtext(userid,text),deletetextbyid(textid)， deletetextbyuserid(userid)
	 *  	 * */

	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname,String password);
	
	/**
	 * 用户注册
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	void register(String username,String password);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	User findUserById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 * */
	List<User> findUser(User user,PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeUserById(Integer id);
	
	/**
	 * 修改用户
	 * @param User 用户对象
	 * */
	void modifyUser(User user,String password);
	
	/**
	 * 添加用户
	 * @param User 用户对象
	 * */
	void addUser(User user,String password);
	
	/**
	 * 添加词条
	 * @param Word 词条对象
	 * */
	void addWord(Word word);
	
	/**
	 * 删除词条
	 * @param id 词条id
	 * */
	void deleteWord(int id);
	
	/**
	 * 根据内容查询词条
	 * @param content 词条内容
	 * aborted
	 * */
	List<Word> findWordByContent(String content, PageModel pageModel);
	//TODO 改为分页查询
	/**
	 * 根据等级查询词条
	 * @param lvl 等级
	 * aborted
	 * */
	List<Word> findWordByLvl(int lvl, PageModel pageModel);
	//TODO 改为分页查询
	/**
	 * 获得所有词条
	 * @return Word对象的List集合
	 * */
	List<Word> findWord(Word word,PageModel pageModel);
	
	/**
	 * 添加用户详情
	 * @param userinfo 用户详情
	 * */
	void addUserInfo(UserInfo info);
	
	/**
	 * 查询用户详情
	 * @param id 用户id
	 * @return userinfo 用户详情
	 * */
	UserInfo findInfoById(Integer id);
	
	/**
	 * 更新用户详情
	 * @param UserInfo 用户详情
	 * */
	void updateInfoById(UserInfo info);

	/**
	 * 删除用户详情
	 * @param id 用户id
	 * */
	void deleteInfoById(Integer id);
	
	/**
	 * 查询所有文章
	 * @param Text 查询条件
	 * @param pagemodel 分页条件
	 * @return List<Text> 文章列表
	 * */
	List<Text> findText(Text text, PageModel pageModel);
	
	/**
	 * 根据文章id删除文章
	 * @param id 文章编号
	 * */
	void deleteTextById(Integer id);
	
	/**
	 * 根据用户id删除文章
	 * @param id 用户编号
	 * */
	void deleteTextByUserId(Integer id);
	

	/**
	 * 插入文章
	 * @param Text 文章
	 * */
	void addText(Text text);
	
	/**
	 * 查询单词
	 * @param String content单词内容
	 * @return int lvl 等级
	 * */
	public int checkLvl(String content);
	
	/**
	 * 用户名唯一性检测
	 * @param registerName 用户名
	 * @return boolean 是否存在
	 * */
	public boolean registerCheck(String registerName);
	
	/**
	 * 根据文章id查询文章
	 * @param id 文章id
	 * @return Text 文章
	 * */
	public Text findText(Integer id);
}
