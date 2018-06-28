<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>配送中心</title>
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
		<link href="../css/mystyle.css" rel='stylesheet' type='text/css' />
		<link href="../css/font-awesome.css" rel="stylesheet">
		<link href="../css/setWarehouse.css" rel="stylesheet">
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
					<a class="navbar-brand" href="">配送中心</a>
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
							<li >
								<a href="dispatchHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>库房管理</a>
							</li>
							<li class="highlight-item">
								<a href="setWarehouse.jsp"><i class="fa fa-edit nav_icon"></i>库房设置</span>
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="searchWorkOrder.jsp"><i class="fa fa-edit nav_icon"></i>库房储备设置</span>
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="searchWorkOrder.jsp"><i class="fa fa-search nav_icon"></i>库存量查询</span>
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="searchWorkOrder.jsp"><i class="fa fa-search nav_icon"></i>出入单查询</span>
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
				<form action="../warehouseServlet?action=updateWarehouse" method="post">
					<div class="page-navigation">新增库房/ Increase the warehouse</div>
					<div class="increaseWarehouse">
						<p1>更新库房</p1>
						<div>
							<p>库房名称：</p>
							<input type="text" name="warehouseName" value="${ware.warehouseName}">
						</div>
						<div>
							<p>库房名称：</p>
							<input type="text" name="warehouseName" value="${editwarehouseName}">
						</div>
						<div>
							<p>库房地址：</p>
							<input type="text" name="warehouseAddress" value="${editwarehouseAddress}">
						</div>
						<div>
							<p>库   管   员：</p>
							<input type="text" name="warehouseKeeper" value="${warehouseKeeper}">
						</div>
						<div>
							<p>库房等级：</p>
							<select name="warehouseRank" value="${warehouseRank}">
								<option value="1">中心库房</option>
								<option value="0">分站库房</option>
							</select>
						</div>
						<div class="buttonblock">
							<button type="submit" >保存</button>
							<button type="button" onclick="window.location.href='./setWarehouse.jsp'">取消</button>
						</div>
					</div>
				</form>
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