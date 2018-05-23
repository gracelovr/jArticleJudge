package org.jArticleJudge.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jArticleJudge.dao.*;

import org.jArticleJudge.domain.Word;
import org.jArticleJudge.domain.Password;
import org.jArticleJudge.domain.Text;
import org.jArticleJudge.domain.User;
import org.jArticleJudge.domain.UserInfo;
import org.jArticleJudge.util.tag.PageModel;
import org.jArticleJudge.service.JajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("JajService")
public class JajServiceImpl implements JajService{

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WordDao WordDao;
	
	@Autowired
	private PwDao PwDao;
	
	@Autowired
	private UserInfoDao UserInfoDao;
	
	@Autowired
	private TextDao TextDao;
	/*****************用户服务接口实现*************************************/
	/**
	 * JajServiceImpl接口login方法实现
	 *  @see { JajService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}

	/**
	 * JajServiceImpl接口findUser方法实现
	 * @see { JajService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<User> users = userDao.selectByPage(params);
		 
		return users;
	}
	
	/**
	 * JajServiceImpl接口findUserById方法实现
	 * @see { JajService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	
	/**
	 * JajServiceImpl接口removeUserById方法实现
	 * @see { JajService }
	 * */
	@Override
	public void removeUserById(Integer id) {
		synchronized (this) {
			userDao.deleteById(id);
			PwDao.deleteById(id);
			UserInfoDao.deleteById(id);
			TextDao.deleteById(id);
		}
		
	}
	
	/**
	 * JajServiceImpl接口addUser方法实现
	 * @see { JajService }
	 * */
	@Override
	public void modifyUser(User user,String password) {
		synchronized (this) {
		userDao.update(user);
		Password pw = new Password();
		pw.setUser_id(user.getUser_id());
		pw.setPw_content(password);
		PwDao.update(pw);
		}
	}
	
	/**
	 * JajServiceImpl接口modifyUser方法实现
	 * @see { JajService }
	 * */
	@Override
	public void addUser(User user,String password) {
		synchronized (this) {
		userDao.save(user);
		Password pw = new Password();
		pw.setUser_id(userDao.findId(user.getUser_name()));
		pw.setPw_content(password);
		PwDao.save(pw);
		}
	}

	@Override
	public void register(String username, String password) {
		synchronized (this) {
		userDao.register(username);
		Password pw = new Password();
		pw.setUser_id(userDao.findId(username));
		pw.setPw_content(password);
		PwDao.save(pw);
		}
	}

	@Override
	public void addWord(Word word) {
		WordDao.save(word);
	}

	@Override
	public void deleteWord(int id) {
		WordDao.deleteById(id);
	}

	@Override
	public List<Word> findWordByContent(String content, PageModel pageModel) {
		return WordDao.findWordByContent(content);
	}

	@Override
	public List<Word> findWordByLvl(int lvl, PageModel pageModel) {
		return WordDao.findWordByLvl(lvl);
	}
	/**
	 * JajServiceImpl接口findWord方法实现
	 * @see { JajService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Word> findWord(Word word,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("word", word);
		int recordCount = WordDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<Word> words = WordDao.selectByPage(params);
		 
		return words;
	}

	@Override
	public void addUserInfo(UserInfo info) {
		UserInfoDao.save(info);
	}

	@Override
	public UserInfo findInfoById(Integer id) {
		return UserInfoDao.selectById(id);
	}

	@Override
	public void updateInfoById(UserInfo info) {
		UserInfoDao.update(info);
	}

	@Override
	public void deleteInfoById(Integer id) {
		UserInfoDao.deleteById(id);
	}

	@Override
	public List<Text> findText(Text text, PageModel pageModel) {
		Map<String,Object> params = new HashMap<>();
		params.put("text", text);
		int recordCount = TextDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<Text> texts = TextDao.selectByPage(params);
		 
		return texts;
	}

	@Override
	public void deleteTextById(Integer id) {
		TextDao.deleteByTextId(id);
	}

	@Override
	public void deleteTextByUserId(Integer id) {
		TextDao.deleteById(id);
	}

	@Override
	public void addText(Text text) {
		TextDao.save(text);
	}

	@Override
	public int checkLvl(String content) {
		Integer lvl = WordDao.checkLvl(content);
		if(lvl != null){
		return lvl;
		}else{
			return 0;
		}
	}

	@Override
	public boolean registerCheck(String registerName) {
		Integer id = userDao.findId(registerName);
		if(id==null){
			return true;
		}
		return false;
	}

	@Override
	public Text findText(Integer id) {
		Text text = TextDao.findText(id);
		return text;
	}

}
