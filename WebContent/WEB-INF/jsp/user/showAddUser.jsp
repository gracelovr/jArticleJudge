<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Jaj系统——添加用户</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	
	$(function(){
    	/** 员工表单提交 */
		$("#userForm").submit(function(){
			var username = $("#user_name");
			var types = document.getElementsByName("user_type"); 
			var value = "";
			 for(var i = 0;i<types.length;i++)  
		        {  
		            if(types[i].checked==true)  
		            {value = types[i].value;  
		            break;}  
		        }  
			var password = $("#password");
			var msg = "";
			if ($.trim(username.val()) == ""){
				msg = "姓名不能为空！";
				user_name.focus();
			}else if ($.trim(type.val()) == ""){
				msg = "用户类型不能为空！";
				user_type.focus();
			}else if ($.trim(password.val()) == ""||$.trim(password.val()) == "新密码"){
				msg = "密码不能为空！";
				password.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#userForm").submit();
		});
    });
		

	</script>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td width="15" height="32"><img
				src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
			<td class="main_locbg font2"><img
				src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：用户管理
				&gt; 添加用户</td>
			<td width="15" height="32"><img
				src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
		</tr>
	</table>
	<table width="100%" height="90%" border="0" cellpadding="5"
		cellspacing="0" class="main_tabbor">
		<tr valign="top">
			<td>
				<form action="${ctx}/user/addUser" id="userForm" method="post">
					<!-- 隐藏表单，flag表示添加标记 -->
					<input type="hidden" name="flag" value="2">
					<input type="hidden" name="user_id" value="${sessionScope.user_session.user_id}">
					<table width="100%" border="0" cellpadding="0" cellspacing="10"
						class="main_tab">
						<tr>
							<td class="font3 fftd">
								<table>
									<tr>
										<td class="font3 fftd">姓&nbsp;名：<input type="text"
											name="user_name" id="username" size="20" value="${user.user_name}"/></td>
										<td class="font3 fftd">类&nbsp;型：
										管理员<input type="radio" id="user_type1" name="user_type" value="1" />
										普通用户<input type="radio" id="user_type2" name="user_type" value="2" /><br/></td>
									</tr>

									<tr>
										<td class="font3 fftd">密&nbsp;码：<input name="password"
											id="password" size="20" placeholder="新密码"/></td>
									</tr>

								</table>
							</td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td align="left" class="fftd"><input type="submit"
								value="添加">&nbsp;&nbsp;<input type="reset" value="取消 "></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
	<div style="height: 10px;"></div>
</body>
</html>