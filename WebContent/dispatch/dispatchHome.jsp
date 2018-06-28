<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>调度中心</title>
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
		<!-- Bootstrap Core CSS -->
		<link href="../css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="../css/style.css" rel='stylesheet' type='text/css' />
		<link href="../css/font-awesome.css" rel="stylesheet">
		<link href="../css/dispatch-center.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="../js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="../js/bootstrap.min.js"></script>
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
					<a class="navbar-brand" href="">调度中心</a>
				</div>
				<!-- /.navbar-header -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
					</li>
					<li class="dropdown">
						<a href="" onclick="window.location.href='exit'" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="./images/exit.png" alt="" /></a>
					</li>
				</ul>
				<!--左侧导航栏-->
				<div class="navbar-default sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li class="highlight-item">
								<a href="dispatchHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="dispatchGoods.jsp"><i class="fa fa-exchange nav_icon"></i>调度商品</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="modifyOrderStatus.jsp"><i class="fa fa-edit nav_icon"></i>修改订单状态</span>
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="searchWorkOrder.jsp"><i class="fa fa-search nav_icon"></i>查询任务单</span>
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
				<div class="page-navigation">主页 / Home page</div>
				<div style="display: flex;flex-direction: row;">
					<div class="homeblock1">
						<div style="display: flex;margin: 40px 10px;">
							<img src="../images/profile.png" />
							<p style="margin: 30px 0 0 10px;">姓名：${employee.employeeName}</p>
						</div>
						<hr style="border-top: 2px solid #999; width: 220px;" />
						<p style="margin: 40px 0 0 20px;">职位：调度中心管理员</p>
					</div>
					<div style="display: flex;flex-direction: column;">
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 20px;font-family: '微软雅黑';">调度商品</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px;">手动调度可分配订单到对应分站</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='dispatchGoods.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 10px;font-size: 20px;font-family: '微软雅黑';">修改订单状态</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px;">商品到货后，修改缺货订单至可分配</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='modifyOrderStatus.jsp'">前往</button>
							</div>
						</div>
					</div>
					<div class="homeblock2">
							
							<p style="margin: 15px;font-size: 20px;font-family: '微软雅黑';">查询任务单</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='searchWorkOrder.jsp'">前往</button>
							</div>
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