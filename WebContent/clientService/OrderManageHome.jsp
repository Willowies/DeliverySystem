<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >

<html>

	<head>
		<title>客户中心</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		</script>
		<script type="application/x-javascript" src="js/order.js"></script>
		<%
    		String path = request.getContextPath();
    		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		%>
		<base href="<%=basePath%>">
		<!-- Bootstrap Core CSS -->
		<link href="<%=basePath%>/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="<%=basePath%>/css/style.css" rel='stylesheet' type='text/css' />
		<link href="<%=basePath%>/css/font-awesome.css" rel="stylesheet">
		<link href="<%=basePath%>/css/clientService.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="<%=basePath%>/js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="<%=basePath%>/js/bootstrap.min.js"></script>
		<!-- Nav CSS -->
		<link href="<%=basePath%>/css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="<%=basePath%>/js/metisMenu.min.js"></script>
		<script src="<%=basePath%>/js/custom.js"></script>
	</head>

	<body>
		<div id="wrapper">
			<!-- Navigation -->
			<nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
				<div class="navbar-header" style="padding-top:1px;">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		                   <span class="sr-only">Toggle navigation</span>
		                   <span class="icon-bar"></span>
		                   <span class="icon-bar"></span>
		                   <span class="icon-bar"></span>
	                </button>
					<a class="navbar-brand" href="">客户服务中心</a>
				</div>
				<!-- /.navbar-header -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
					</li>
					<li class="dropdown">
						<a href="" onclick="window.location.href='exit'" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="../images/exit.png" alt="" /></a>
					</li>
				</ul>
				<!--左侧导航栏-->
				<div class="navbar-default sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li class="highlight-item">
								<a href="<%=basePath%>/clientService/OrderManageHome.jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li >
								<a href="<%=basePath%>/clientService/generateOrder.jsp"><i class="fa fa-edit nav_icon"></i>生成订单</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="">
									<i class="fa fa-tasks nav_icon"></i>客户管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
								    <li><a href="<%=basePath%>/clientService/registerClient.jsp">新增客户</a></li>
								    <li><a href="<%=basePath%>/clientService/selectResult.jsp">查询客户信息</a></li>
								</ul>
								
								<!-- /.nav-second-level -->
							</li>
							

						</ul>
					</div>
					<!-- /.sidebar-collapse -->
				</div>
				<!-- /.navbar-static-side -->
			</nav>
			<!--页面-->
			<div id="page-wrapper" style="background: #f2f2f2;min-width: 800px;">
				<div class="container">
					<div class="homeblock1 col-md-3 container">
						<img class="img-circle center-block" src="../img/profile.png"/>
						<hr>
						<p>姓名：</p>
						<p>职位员：</p>
					</div>
					<div class="homeblock2 col-md-3">
						<h4>生成订单</h4>
						<hr>
						<p>根据提供的信息查询订单</p>
						<a class="btn btn-info " href="#" role="button">前往</a>
					</div>
					<div class="homeblock2 col-md-3">
						<h4>查询订单</h4>
						<hr>
						<p></p>
						<a class="btn btn-default" href="#" role="button">前往</a>
					</div>
					<div class="homeblock2 col-md-3">
						<h4>查询工作量</h4>
						<hr>
						<a class="btn btn-default" href="#" role="button">前往</a>
					</div>
				</div>
			</div>
			<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		<link href="../css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="../js/metisMenu.min.js"></script>
		<script src="../js/custom.js"></script>
	</body>

</html>