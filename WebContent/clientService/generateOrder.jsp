<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
					<a class="navbar-brand" href="">客户中心</a>
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
							<li>
								<a href="dispatchHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="dispatchGoods.jsp"><i class="fa fa-exchange nav_icon"></i>订单管理</a>
								<!-- /.nav-second-level -->
							</li>
							<li class="highlight-item">
								<a href="modifyOrderStatus.jsp"><i class="fa fa-edit nav_icon"></i>生成订单</span>
								</a>
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
					<form action="../Order/OrderServlet?action=locateClient" method="post" role = "form" class="col-md-4 col-lg-offset-3">
					<div class="page-navigation">生成订单/ generateOrder</div>
					<div class="searchblock">
						<p1>生成订单</p1>
						<div class="form-group">
							<label class="hidden"></label>
							<p>客户名</p>
							<input type="text" class="form-control"  placeholder="客户名" name= "clientName">
						</div>
						<div class="form-group">
							<label class="hidden"></label>
							<p>客户手机</p>
							<input type="text" class="form-control"  placeholder="输入11位手机号" name = "clientMobilephone">
						</div>
						<div class="form-group">
							<label class="hidden"></label>
							<p>客户座机</p>
							<input type="text" class="form-control"  placeholder="输入8位座机号" name = "clientPhonenumber">
						</div>
						<div class="form-group">
							<label class="hidden"></label>
							<p>客户身份证</p>
							<input type="text"  class="form-control"  placeholder="输入16位身份证号" name="clientIc">
						</div>
						<div class="form-group">
							<label class="hidden"></label>
							<p>生成类型</p>
							<select class="form-control" class="form-control" name = "orderType">
  								<option value="1">新订</option>
  								<option value="2">退订</option>
  								<option value="3">退货</option>
							</select>	
						</div>
											
						<div class="buttonblock form-group">
							<label class="hidden"></label>
							<button type="submit"  >搜索  </button>
						</div>
					</div>
					</form>
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