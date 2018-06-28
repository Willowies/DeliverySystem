
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>财务中心|与供应商结算</title>
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
		<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/metisMenu.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/custom.js"></script>
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
							<li>
								<a href="financeHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li  >
								<a href="centerIn.jsp"><i class="fa fa-download nav_icon"></i>与供货商结算<span class="fa arrow"></span></a>
								 <ul class="nav nav-second-level">
							    	<li   class="highlight-item">
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
				<form action="../clearMoneyServlet?action=selectOrder" method="post">
					<div class="page-navigation">查询订单 / search page</div>
					<div class="searchblock">
						<p1>查询单据</p1>
						<p>供应商</p>
						<input type="text" name="supplier" id="createDate">
						<p>日期</p>
						<input type="date" name="date" id="createDate">
						<p>商品名</p>
						<input type="text" name="product" id="createDate">
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
		<!-- Metis Menu Plugin JavaScript -->


<%
	Object message = request.getSession().getAttribute("messageselectOrder");
	if(message!=null&&!"".equals(message)){
%>
	<script type="text/javascript">
	alert("<%=message%>");
	</script>
<%
	}
%>
	</body>

</html>