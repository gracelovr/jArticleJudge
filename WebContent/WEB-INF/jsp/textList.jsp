<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${ctx}/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href='https://fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href="${ctx}/css/buttons.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/js/move-top.js"></script>
<script type="text/javascript" src="${ctx}/js/easing.js"></script>
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
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
	$(function(){
		 var boxs  = $("input[type='checkbox'][id^='box_']");
	 	   
	 	  /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	    	$("tr[id^='data_']").hover(function(){
	    		$(this).css("backgroundColor","#eeccff");
	    	},function(){
	    		$(this).css("backgroundColor","#ffffff");
	    	})
		$("#delete").click(function(){
	 		   /** 获取到用户选中的复选框  */
	 		   var checkedBoxs = boxs.filter(":checked");
	 		   if(checkedBoxs.length < 1){
	 			   $.ligerDialog.error("请选择一篇需要删除的文章！");
	 		   }else{
	 			   /** 得到用户选中的所有的需要删除的ids */
	 			   var ids = checkedBoxs.map(function(){
	 				   return this.value;
	 			   })
	 			   
	 			   $.ligerDialog.confirm("确认要删除吗?","删除文章",function(r){
	 				   if(r){
	 					   // alert("删除："+ids.get());
	 					   // 发送请求
	 					   window.location = "${ctx}/deleteText?ids=" + ids.get();
	 				   }
	 			   });
	 		   }
	 	   })
	})
</script>
<title>文章列表</title>
</head>
<body>
<div class="banner">
		<div class="container">
			<div class="banner_top">
				<div class="banner_top_left">
					<p>欢迎&nbsp<span>${sessionScope.info_session.nickname}</span>
<a href="${ctx }/loginForm" class="button  button-borderless button-rounded button-primary">退出登录</a>
					</p>

				</div>
				<div class="banner_top_right" style="width: 40%">
					<nav class="navbar navbar-default">
					<div class="collapse navbar-collapse nav-wil"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav cl-effect-14">
						<li><a href="${ctx }/mainPage">主页</a></li>
							<li><a href="${ctx }/infoEdit?flag=1">我的信息</a></li>
							<li><a href="${ctx }/textList">我的文章</a></li>

						</ul>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<nav class="navbar navbar-default">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
				  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				  </button>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<!-- /.navbar-collapse -->	
				
			</nav>
			<table width="100%" height="90%" border="0" cellpadding="5"
		cellspacing="0" class="table">
		<!-- 查询区  -->
		<tr valign="top">
			<td height="30">
				<table width="100%" border="0" cellpadding="0" cellspacing="10"
					class="table" style="background-color:transparent">
					<tr>
						<td class="fftd">
							<form name="userform" method="post" id="userform"
								action="${ctx}/showText">
								<table width="100%" border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td class="font3" style="font-size:16px;color:white">标题：<input type="text" name="text_title">
											等级：<input type="text" name="text_lvl"> <input
											type="submit" class="button button-pill button-tiny" value="搜索" /> <input id="delete" class="button button-pill button-tiny" type="button"
											value="删除" />
										</td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<!-- 数据展示区 -->
		<tr valign="top">
			<td height="20">
				<table width="100%" border="1" cellpadding="5" cellspacing="0"
					style="border: #c2c6cc 1px solid; border-collapse: collapse;">
					<tr class="main_trbg_tit" align="center">
						<td><input type="checkbox" name="checkAll" id="checkAll"></td>
						<td>文章标题</td>
						<td>文章等级</td>
						<td align="center">查看详情</td>
					</tr>
					<c:forEach items="${requestScope.texts}" var="text"
						varStatus="stat">
						<tr id="data_${stat.index}" align="center" class="main_trbg"
							>
							<td><input type="checkbox" id="box_${stat.index}"
								value="${text.text_id}"></td>
							<td>${text.text_title}</td>
							<td>
							<c:choose>
									<c:when test="${text.text_lvl==0}">
								无法判断
							</c:when>
									<c:otherwise>
								N${text.text_lvl}
							</c:otherwise>
								</c:choose>
								</td>
								<td align="center" width="40px;"><a
								href="${ctx}/viewDetail?id=${text.text_id}"> <img
									title="查看详情" src="${ctx}/images/update.gif" /></a></td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<!-- 分页标签 -->
		<tr valign="top">
			<td align="center" class="font3"><Jaj:pager
					pageIndex="${requestScope.pageModel.pageIndex}"
					pageSize="${requestScope.pageModel.pageSize}"
					recordCount="${requestScope.pageModel.recordCount}" style="digg"
					submitUrl="${ctx}/showText?pageIndex={0}" /></td>
		</tr>
	</table>
		
			
			
		</div>
	</div>
</body>
</html>