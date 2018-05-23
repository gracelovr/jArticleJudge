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
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
	$(function(){
		var list = ${requestScope.lvlInfo};
		var text = "${requestScope.text}";
		for(var i = 0; i < list.length; i++){
			if(i == 5){
			if(list[i] == 0){
				$("#li"+i).append("无法判断");
			}else{
				$("#li"+i).append("N" + list[i]);
			}
			$("#lvl").attr("value",list[i]);
			}else{
			$("#li"+i).append(list[i]);
			}
		}
	});
</script>
<title>分析结果</title>
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
				<div class="banner_top_right" style="width:39%">
					<nav class="navbar navbar-default">
					<div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav cl-effect-14">
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
			
		<h3 class="bars">分析结果</h3>
			<ul class="list-group">
			  <li id="li7" class="list-group-item">总词汇数：</li>
			  <li id="li6" class="list-group-item">识别词汇数：</li>
			  <li id="li0" class="list-group-item">一级词汇数：</li>
			  <li id="li1" class="list-group-item">二级词汇数：</li>
			  <li id="li2" class="list-group-item">三级词汇数：</li>
			  <li id="li3" class="list-group-item">四级词汇数：</li>
			  <li id="li4" class="list-group-item">五级词汇数：</li>
			  <li id="li5" class="list-group-item">文章估计难度：</li>
			</ul>
			<h3 class="bars">保存你的文章</h3>
			<form role="form" action="${ctx }/saveText" method="post">
				<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">&nbsp&nbsp文章名&nbsp&nbsp</span>
				<input type="text" class="form-control" placeholder="Title" name="title" aria-describedby="basic-addon1">
				<input type="text" name="text" value="${requestScope.text}" style="display:none">
				<input type="text" name="lvl" id="lvl" style="display:none">
			</div>
			 <input type="submit"  class="button button-3d button-primary button-rounded  btn-group-justified">保存该文章</a>
			</form>
		</div>
	</div>
</body>
</html>