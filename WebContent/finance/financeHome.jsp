<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>财务中心|主页</title>
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
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
		<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/financecenter.css" rel="stylesheet">
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
					<a class="navbar-brand" href="">财务中心</a>
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
								<a href="financeHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li  >
								<a href="centerIn.jsp"><i class="fa fa-download nav_icon"></i>与供货商结算<span class="fa arrow"></span></a>
								 <ul class="nav nav-second-level">
							    	<li>
                                        <a href="clearSupplier.jsp">进行结算</a>
                                    </li>
                                    <li>
                                        <a href="selectClearedSup.jsp">已结算订单查询</a>
                                    </li>
                                </ul>
							</li>
							<li>
								<a href="#"><i class="fa fa-upload nav_icon"></i>与分站结算<span class="fa arrow"></span></a>
							    <ul class="nav nav-second-level">
							    	<li>
                                        <a href="clearSubstation.jsp">进行结算</a>
                                    </li>
                                    <li>
                                        <a href="selectClearedSub.jsp">已结算订单查询</a>
                                    </li>
                               </ul>   
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-upload nav_icon"></i>发票管理<span class="fa arrow"></span></a>
							    <ul class="nav nav-second-level">
							    	<li>
                                        <a href="registerInvoice.jsp">发票登记</a>
                                    </li>
                                    <li>
                                        <a href="getSubstationInvoice.jsp">发票领用</a>
                                    </li>
                                    <li>
                                        <a href="selectInvoice.jsp">发票查询</a>
                                    </li>
                                    <li>
                                        <a href="abandonInvoice.jsp">发票作废</a>
                                    </li>
                               </ul>   
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
						<p style="margin: 40px 0 0 20px;">职位：财务中心管理员</p>
					</div>
					<div style="display: flex;flex-direction: column;">
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 20px;font-family: '微软雅黑';">与供应商结算</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px;">财务会定期和供应商进行资金结算</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='clearSupplier.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 10px;font-size: 20px;font-family: '微软雅黑';">与分站结算</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px;">财务会定期和分站进行资金结算</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='clearSubstation.jsp'">前往</button>
							</div>
						</div>
					</div>
					<div style="display: flex;flex-direction: column;">
					<div class="homeblock2">		
							<p style="margin: 15px;font-size: 20px;font-family: '微软雅黑';">发票登记</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='registerInvoice.jsp'">前往</button>
							</div>
					</div>
					<div class="homeblock2">		
							<p style="margin: 15px;font-size: 20px;font-family: '微软雅黑';">发票领用</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='getSubstationInvoice.jsp'">前往</button>
							</div>
					</div>
					</div>
				</div>

			</div>
			<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="${pageContext.request.contextPath}/js/metisMenu.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	</body>

</html>