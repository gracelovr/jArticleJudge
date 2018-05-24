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
<script type="text/javascript" src="${ctx}/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href='https://fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href="${ctx}/css/buttons.css" rel="stylesheet" type="text/css" media="all" />
<link href="${ctx}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="${ctx}/js/move-top.js"></script>
<script type="text/javascript" src="${ctx}/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<title>我的信息</title>
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
				<div class="banner_top_right" style="width: 48%">
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
			<form  action="${ctx }/editInfo" id="testform"
			>
			<input type="hidden" name="flag" value="2">
			<div class="form-group input-group">
				<span class="input-group-addon" id="basic-addon1">&nbsp&nbsp昵称&nbsp&nbsp</span>
				<input type="text" class="form-control" name="nickname" placeholder="Nickname" aria-describedby="basic-addon1">
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon" id="basic-addon2">&nbsp&nbsp邮箱&nbsp&nbsp</span>
				<input id="email"type="text" class="form-control" name="user_mail" placeholder="E-mail" aria-describedby="basic-addon2">
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon" id="basic-addon3">&nbsp&nbsp手机&nbsp&nbsp</span>
				<input type="text" class="form-control" name="phone_num" placeholder="PhoneNumber" aria-describedby="basic-addon3">
			</div>
			<div class="form-group input-group controls input-append date form_date"  >
				<span class="input-group-addon" id="basic-addon4">&nbsp&nbsp生日&nbsp&nbsp</span>
				<input id="startTime" type="text" class="form-control"name="birthday" placeholder="Birthday" aria-describedby="basic-addon4" data-date-format="yyyy-mm-dd">

			</div>
			 <input type="submit"id="btn1" class="button button-3d button-primary button-rounded  btn-group-justified"value="提交修改">
			</form>
		</div>
	</div>
	<script>
		$('#startTime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
		
    });
		//$("#btn1").click(function(){
		//	var form = $("#testform").serialize();
		//	alert("");
		//});
	</script>
</body>
</html>