<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>分站管理|主页</title>
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
		<link href="../css/substation-center.css" rel="stylesheet">
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
				<div class="navbar-header" style="padding-top:1px;">
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
							<li class="highlight-item">
								<a href="substationHome.jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="searchWorkOrder.jsp"><i class="fa fa-search nav_icon"></i>查询任务单
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="workAssign.jsp"><i class="fa fa-tasks nav_icon"></i>分配任务</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="signPrint.jsp"><i class="fa fa-print nav_icon"></i>打印商品签收单
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="feedbackRecord.jsp"><i class="fa fa-edit nav_icon"></i>回执录入
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="paymentQuery.jsp"><i class="fa fa-money nav_icon"></i>缴款查询
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="">
									<i class="fa fa-tag nav_icon"></i>发票管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
									<li><a href=".jsp">发票领用</a></li>
									<li><a href=".jsp">发票作废</a></li>
									<li><a href=".jsp">发票查询</a></li>
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
			<div id="page-wrapper" style="background: #f2f2f2;min-width: 800px;  min-height: 299px;">
				<div class="page-navigation">
					<strong>主页 / Home page</strong>
				</div>
				<div style="display: flex;flex-direction: row;">
					<div class="homeblock1">
						<div style="display: flex;margin: 40px 10px;">
							<img src="../images/profile.png" />
							<p style="margin: 30px 0 0 10px;">姓名：张三</p>
						</div>
						<hr style="border-top: 2px solid #999; width: 220px;" />
						<p style="margin: 40px 0 0 20px;">职位：分站管理员</p>
					</div>
					<div style="display: flex;flex-direction: column;">
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">查询任务单</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">查询任务单的详细信息。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='searchWorkOrder.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">分配任务</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">货物到分站后，开始分配任务给配送员。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='workAssign.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">打印签收单</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">分配完任务后，配送员打印签收单。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='signPrint.jsp'">前往</button>
							</div>
						</div>
					</div>
					<div style="display: flex;flex-direction: column;">
						<div class="homeblock2" style="margin-left:20px;">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">回执录入</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">配送员配送任务完成或失败之后，将回执录入系统中。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='feedbackRecord.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2" style="margin-left:20px;">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">缴款查询</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">用于统计一段时间内本分站内每种商品的送货收款情况，以及总的收款、退款金额。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='paymentQuery.jsp'">前往</button>
							</div>
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