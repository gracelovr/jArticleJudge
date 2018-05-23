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
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link
	href='https://fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
<link href="${ctx}/css/buttons.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="${ctx}/js/move-top.js"></script>
<script type="text/javascript" src="${ctx}/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<title>查看详情</title>
</head>
<body>
	<div class="banner">
		<div class="container">
			<div class="banner_top">
				<div class="banner_top_left">
					<p>
						欢迎&nbsp<span>${sessionScope.info_session.nickname}</span> <a
							href="${ctx }/loginForm"
							class="button  button-borderless button-rounded button-primary">退出登录</a>
					</p>

				</div>
				<div class="banner_top_right" style="width: 39%">
					<nav class="navbar navbar-default">
					<div class="collapse navbar-collapse nav-wil"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav cl-effect-14">
							<li><a href="${ctx }/infoEdit?flag=1">我的信息</a></li>
							<li><a href="${ctx }/textList">我的文章</a></li>

						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<nav class="navbar navbar-default"> <!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<!-- /.navbar-collapse --> </nav>

			<h1 class="bars">${requestScope.text.text_title}</h1>
			<div class="article_content">
				<p style="color: white">${requestScope.text.text_content}</p>
			</div>
			<br/>
			<h3 style="color:white" class="bars">难度</h3>
			<c:choose>
				<c:when test="${requestScope.text.text_lvl == 0}">
								无法判断
							</c:when>
				<c:otherwise>
								N${requestScope.text.text_lvl}
							</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>