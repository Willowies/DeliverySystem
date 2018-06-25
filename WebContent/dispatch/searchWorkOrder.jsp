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
		<!--
        	下拉框的滚轮和回收js
        -->
        <script src="../js/autocomplete.js"></script>
        <link href="../css/autocomplete.css" rel='stylesheet' type='text/css' />
		<script type="text/javascript">
			var proposals = ['at', 'boat', 'bear', 'chief', 'dog', 'drink', 'elephant', 'fruit', 'grave', 'hotel', 'illness', 'London', 'motorbike', 'xw素材网官网', 'xw素材网之家', 'xw素材网下载'];
			$(document).ready(function() {
				$('#search-form').autocomplete({
					hints: proposals,
					width: 420,
					height: 30,
					onSubmit: function(text) {
						$('#message').html('Selected: <b>' + text + '</b>');
					}
				});
			});
		</script>
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
								<a href="dispatchGoods.jsp"><i class="fa fa-exchange nav_icon"></i>调度商品</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="modifyOrderStatus.jsp"><i class="fa fa-edit nav_icon"></i>修改订单状态</span>
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li class="highlight-item">
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
				<form action="../dispatchManageServlet?action=searchWorkOrder" method="post">
					<div class="page-navigation">查询任务单 / Search workOrder</div>
					<div class="searchblock3">
						<p1>搜索任务单</p1>
						<p>任务单号</p>
						<input type="number" name="workId" id="workId">
						<p>要求完成日期</p>
						<input type="date" name="requireDate" id="requireDate">
						<p>选择分站</p>
						<div id="search-form"></div>
						<div id="message"></div>
						<p>任务单类型</p>
						<select name="orderType">
							<option value="1">新订</option>
							<option value="2">退货</option>
						</select>
						<p>任务单状态</p>
						<select name="orderStatus">
							<option value="1">已调度</option>
							<option value="2">可分配</option>
							<option value="3">已分配</option>
							<option value="4">已领货</option>
							<option value="5">已完成</option>
						</select>
						<p>客户姓名</p>
						<input type="text" name="clientName" id="clientName">
						<p>客户电话</p>
						<input type="number" name="clientPhone" id="clientPhone">
						<div class="buttonblock">
							<button type="submit">搜索</button>
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