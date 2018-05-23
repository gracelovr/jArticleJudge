package org.jArticleJudge.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.java.sen.StringTagger;
import net.java.sen.Token;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jArticleJudge.domain.Text;
import org.jArticleJudge.domain.User;
import org.jArticleJudge.domain.UserInfo;
import org.jArticleJudge.domain.Word;
import org.jArticleJudge.service.JajService;
import org.jArticleJudge.util.common.JajConstants;
import org.jArticleJudge.util.common.SHA256Util;
import org.jArticleJudge.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;



/**
 *  JajController是一个基于注解的控制器,
 *  可以同时处理多个请求动作，并且无须实现任何接口。
 *  org.springframework.stereotype.Controller注解用于指示该类是一个控制器
 */
@Controller
public class JajController{
	//TODO 新增的用户详情和文章方法
	/* showInfo(modelandview)
	 * editInfo(flag,userinfo,modelandview) 
	 * addtext(text,modelandview) 
	 * showalltext(pageindex,text,model) 
	 * showtext(pageindex,text,model) 
	 * deletetext(ids,modelandview)
	 * */
	/**
	 * 自动注入JajService
	 * */
	@Autowired
	@Qualifier("JajService")
	private JajService JajService;
		
	/**
	 * 处理登录请求
	 * @param String loginname  登录名
	 * @param String password 密码
	 * @return 跳转的视图
	 * */
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("loginname") String loginname,
			 @RequestParam("password") String password,
			 HttpSession session,
			 ModelAndView mv){
		// 调用业务逻辑组件判断用户是否可以登录
		password = SHA256Util.getSHA256Str(password);
		User user = JajService.login(loginname, password);
		if(user != null){
			// 将用户保存到HttpSession当中
			session.setAttribute(JajConstants.USER_SESSION, user);
			// 客户端跳转到main页面
			if(user.getUser_type()==1){
			mv.setViewName("redirect:/main");
			}else{
				UserInfo info = JajService.findInfoById(user.getUser_id());
				session.setAttribute(JajConstants.INFO_SESSION, info);
				mv.setViewName("redirect:/mainPage");
			}
		}else{
			// 设置登录失败提示信息
			mv.addObject("message", "登录名或密码错误!请重新输入");
			// 服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
		}
		return mv;
		
	}
	
	/**
	 * 处理注册请求
	 * @param String loginname  登录名
	 * @param String password 密码
	 * @return 跳转的视图
	 * */
	@RequestMapping(value="/register")
	 public ModelAndView register(@RequestParam("loginname") String loginname,
			 @RequestParam("password") String password,
			 HttpSession session,
			 ModelAndView mv){
		if(loginname == null || loginname.equals("")){
			// 设置注册失败提示信息
				mv.addObject("message", "姓名不能为空!");
			// 服务器内部跳转到注册页面
				mv.setViewName("forward:/registerForm");
		}
		if(loginname.length()>=20){
			// 设置注册失败提示信息
			mv.addObject("message", "姓名不能超过20个字符!");
		    // 服务器内部跳转到注册页面
			mv.setViewName("forward:/registerForm");
		}
		if(password.length()>=50){
			// 设置注册失败提示信息
			mv.addObject("message", "密码不能超过50个字符!");
			// 服务器内部跳转到注册页面
			mv.setViewName("forward:/registerForm");
		}
		if(password == null || password.equals("")){
			// 设置注册失败提示信息
			mv.addObject("message", "密码不能为空!");
			// 服务器内部跳转到注册页面
			mv.setViewName("forward:/registerForm");
		}
		password = SHA256Util.getSHA256Str(password);
		JajService.register(loginname, password);
		// 调用业务逻辑组件判断用户是否可以登录
		User user = JajService.login(loginname, password);
		if(user != null){
			// 将用户保存到HttpSession当中
			session.setAttribute(JajConstants.USER_SESSION, user);
			// 客户端跳转到main页面
			if(user.getUser_type()==1){
			mv.setViewName("redirect:/main");
			}else{
				UserInfo info = JajService.findInfoById(user.getUser_id());
				session.setAttribute(JajConstants.INFO_SESSION, info);
				mv.setViewName("redirect:/mainPage");
			}
		}else{
			// 设置登录失败提示信息
			mv.addObject("message", "注册失败!请联系管理员");
			// 服务器内部跳转到注册页面
			mv.setViewName("forward:/registerForm");
		}
		return mv;
		
	}
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param employee 模糊查询参数
	 * @param Model model
	 * */
	@RequestMapping(value="/user/selectUser")
	 public String selectUser(Integer pageIndex,
			 @ModelAttribute User user,
			 Model model){
		System.out.println("user = " + user);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<User> users = JajService.findUser(user, pageModel);
		model.addAttribute("users", users);
		model.addAttribute("pageModel", pageModel);
		return "user/user";
		
	}
	
	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/user/removeUser")
	 public ModelAndView removeUser(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			JajService.removeUserById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/user/selectUser");
		// 返回ModelAndView
		return mv;
	}
	
	
	/**
	 * 处理修改用户请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param User user  要修改用户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/user/updateUser")
	 public ModelAndView updateUser(
			 String flag,
			 String id,
			 @ModelAttribute User user,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询用户
			User target = JajService.findUserById(Integer.parseInt(id));
			// 设置Model数据
			mv.addObject("user", target);
			// 返回修改员工页面
			mv.setViewName("/user/showUpdateUser");
		}else{
			// 执行修改操作
			String password = SHA256Util.getSHA256Str(user.getPassword());
			JajService.modifyUser(user,password);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/user/selectUser");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param User user  要添加用户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/user/addUser")
	 public ModelAndView addUser(
			 String flag,
			 @ModelAttribute User user,
			 @ModelAttribute String password,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("/user/showAddUser");
		}else{
			// 执行添加操作
			password = SHA256Util.getSHA256Str(password);
			JajService.addUser(user,password);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/user/selectUser");
		}
		// 返回
		return mv;
	}
	@RequestMapping(value="/word/showAddWordFile")
	public ModelAndView showAddWordFile(ModelAndView mv){
		mv.setViewName("/word/showAddWordFile");
		return mv;
	}
	/**
	 * 处理添加词条请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param File file  要添加的词条文件
	 * @param ModelAndView mv
	 * @throws IOException 
	 * @throws IllegalStateException  IOException
	 * @throws InvalidFormatException 
	 * */
	@RequestMapping(value="/word/addWordFile")
	public ModelAndView addWord(
			String flag,
			HttpServletRequest request,
			@RequestParam("file")MultipartFile file,
			ModelAndView mv) throws IllegalStateException, IOException{
		if(flag.equals("1")){
			mv.setViewName("/word/showAddWordFile");
		}else{
			if(!file.isEmpty()){
				if(file.getContentType().equals("application/vnd.ms-excel")){
					String path = request.getServletContext().getRealPath("/upload/");
					String filename = file.getOriginalFilename();
					File filepath = new File(path,filename);
					if(!filepath.getParentFile().exists()){
						filepath.getParentFile().mkdirs();
					}
					file.transferTo(new File(path + File.separator + filename));
					File file1 = new File(path + File.separator + filename);
					 Word word = new Word();
					 Double b;
					
						//1.读取Excel的对象  
			            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file1));  
			            //2.Excel工作薄对象  
			            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);  
			            //3.Excel工作表对象  
			            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);  
			          //总行数  
			            int rowLength = hssfSheet.getLastRowNum()+1; 
			            HSSFCell hssfCell1 = null;
			            HSSFCell hssfCell2 = null;
			            for (int i = 0; i < rowLength; i++) {  
			                //获取Excel工作表的行  
			                HSSFRow hssfRow1 = hssfSheet.getRow(i);  
			                    //获取指定单元格  
			                   hssfCell1 = hssfRow1.getCell(0);  
			                   hssfCell2 = hssfRow1.getCell(1); 
			                    //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：  
			                    //Cannot get a STRING value from a NUMERIC cell  
			                    //将所有的需要读的Cell表格设置为String格式  
			                   
			                    word.setWord_content(hssfCell1.getStringCellValue());
			                    b = hssfCell2.getNumericCellValue();
			                    word.setWord_lvl(b.intValue());
			                    JajService.addWord(word);
			            }  
					
					mv.addObject("message", "文件上传处理成功");
					mv.setViewName("redirect:/word/showAddWordFile");
				}else{
					mv.addObject("message", "不支持的文件格式，请重新上传（.xls文件）");
					mv.setViewName("/word/showAddWordFile");
				}
			}else{
				mv.addObject("message", "空文件，请重新上传");
				mv.setViewName("/word/showAddWordFile");
			}
		}
		//返回
		return mv;
	}
	
	/**
	 * 处理删除词条请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/word/deleteWord")
	 public ModelAndView deleteWord(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除词条
			JajService.deleteWord(Integer.parseInt(id));;
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/word/selectWord");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param employee 模糊查询参数
	 * @param Model model
	 * */
	@RequestMapping(value="/word/selectWord")
	 public String selectWord(Integer pageIndex,
			 @ModelAttribute Word word,
			 Model model){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询词条信息     */
		List<Word> words = JajService.findWord(word, pageModel);
		model.addAttribute("words", words);
		model.addAttribute("pageModel", pageModel);
		return "word/wordList";
		
	}
	/**
	 * 处理普通用户查询难度请求
	 * @param text 请求的文本
	 * @return 分析结果
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 * */
	@RequestMapping(value="/judge")
	public String judge(@RequestParam("text")String text,Model model) throws IllegalArgumentException, IOException{
		System.setProperty("sen.home", "F:\\workspace\\jArticleJudge\\WebContent\\");
		StringTagger tagger = StringTagger.getInstance();
		Token[] token = tagger.analyze(text); 
		/*前五个元素分别是N1到N5单词数量
		第六个元素为等级
		第七个元素为有效单词数量
		第八个元素为总单词数量
		*/
		int [] lvlArr = {0,0,0,0,0,0,0,0};
		lvlArr[7] = token.length;
		Integer tempLvl;
		for(int i = 0; i < token.length; i++){
			tempLvl = JajService.checkLvl(token[i].getBasicString());
			if(tempLvl != null && tempLvl != 0){
			lvlArr[tempLvl-1]++;
			lvlArr[6]++;
			}
		}
		//N5单词记1分，N4单词2分，N3单词3分，N2单词5分，N1单词8分
		double point;
		if(lvlArr[6] != 0){
			point = (lvlArr[0]*8 + lvlArr[1]*5 + lvlArr[2]*3 + lvlArr[3]*2 + lvlArr[4])/lvlArr[6];
			if(point < 2){
				lvlArr[5] = 5;
			}else if(point < 3){
				lvlArr[5] = 4;
			}else if(point < 5){
				lvlArr[5] = 3;
			}else if(point < 8){
				lvlArr[5] = 2;
			}else{
				lvlArr[5] = 1;
			}
		}else{
			lvlArr[5] = 0;
		}
		List<Integer> arr = new ArrayList<Integer>();
		for(int a : lvlArr){
			arr.add(a);
		}
		model.addAttribute("lvlInfo", arr);
		model.addAttribute("text",text);
		return "/result";
	}
	
	/**
	 * 显示用户详细信息
	 * 
	 * @return userinfo
	 * */
	@RequestMapping(value="/showInfo")
	public String showInfo(HttpServletRequest request, Model model){
		User user = (User) request.getSession().getAttribute(JajConstants.USER_SESSION);
		Integer id = user.getUser_id();
		UserInfo info = JajService.findInfoById(id);
		model.addAttribute("info", info);
		return "/infoEdit";
	}
	
	/**
	 * 处理修改详细信息请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Userinfo info  要修改信息的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/editInfo")
	 public ModelAndView editInfo(
			 HttpServletRequest request,String flag,
			 @ModelAttribute UserInfo info,
			 ModelAndView mv){
		// 获取当前用户
		User user = (User) request.getSession().getAttribute(JajConstants.USER_SESSION);
		if(flag.equals("1")){
			
			// 设置Model数据
			mv.addObject("userinfo", JajService.findInfoById(user.getUser_id()));
			// 返回修改员工页面
			mv.setViewName("/user/infoEdit");
		}else{
			// 执行修改操作
			info.setUser_id(user.getUser_id());
			JajService.updateInfoById(info);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/mainPage");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 保存文章
	 * @param Text 文章
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/saveText")
	public ModelAndView addText(HttpServletRequest request, 
			@RequestParam("text")String text_content,
			@RequestParam("title")String title,
			@RequestParam("lvl")String lvl,
			ModelAndView mv){
		User user = (User) request.getSession().getAttribute(JajConstants.USER_SESSION);
		Text text = new Text();
		text.setText_content(text_content);
		text.setText_title(title);
		text.setUser_id(user.getUser_id());
		text.setText_lvl(Integer.parseInt(lvl));
		JajService.addText(text);
		mv.setViewName("redirect:/mainPage");
		return mv;
	}
	
	/**
	 * 显示所有文章
	 * @return List<Text> 文章列表
	 * */
	@RequestMapping(value="/showText")
	public String showText(HttpServletRequest request, 
			Integer pageIndex,
			@ModelAttribute Text text,
			Model model){
		User user = (User) request.getSession().getAttribute(JajConstants.USER_SESSION);
		PageModel pageModel = new PageModel();
		String page = "";
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
			
		}
		if(user.getUser_type()==2){//普通用户
			text.setUser_id(user.getUser_id());
			page = "/textList";
		}else{
			page = "/allTextList";
		}
		List<Text> texts = JajService.findText(text, pageModel);
		model.addAttribute("texts", texts);
		model.addAttribute("pageModel", pageModel);
		return page;
	}
	
	/**
	 * 处理删除文章请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/deleteText")
	 public ModelAndView deleteText(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除词条
			JajService.deleteTextById(Integer.parseInt(id));;
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/showText");
		// 返回ModelAndView
		return mv;
	}
	
	//用来判断用户名是否唯一
    @RequestMapping("/registerCheck")
    @ResponseBody
    public String registerUserName(String registerName,HttpServletRequest request){
        //校验registerUserName方法传入的参数registerName是否为空
        if(StringUtils.isEmpty(registerName)){
            request.setAttribute("error", "用户名不能为空!");
        }
        boolean tOf = JajService.registerCheck(registerName);
        if(tOf == true){
            return "success";
        }else{
            return "notsuccess";
        }
    }
    
    //查看文章详情
    @RequestMapping("/viewDetail")
    public ModelAndView viewDetail(Integer id, ModelAndView mv){
    	Text text = JajService.findText(id);
    	mv.addObject("text", text);
    	mv.setViewName("/detail");
    	return mv;
    }
}

