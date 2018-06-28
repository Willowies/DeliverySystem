<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>分站管理|发票领用</title>
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
		<link href="${pageContext.request.contextPath}/css/financecenterup.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="${pageContext.request.contextPath}/js/metisMenu.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/custom.js"></script>
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
					<a class="navbar-brand" href="">分站中心</a>
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
								<a href="substationHome.jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="searchWorkOrder.jsp"><i class="fa fa-search nav_icon"></i>查询任务单
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="workAssign.jsp"><i class="fa fa-tasks nav_icon"></i>任务分配</a>
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
									<li  class="highlight-item"><a href=".jsp">发票领用</a></li>
									<li><a href="abandonInvoice.jsp">发票作废</a></li>
									<li><a href="selectInvoice.jsp">发票查询</a></li>
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
					<strong>领用发票 / getInvoice page</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: column;align-items:flex-start;">
					<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<form action="../invoiceManageServlet?action=selectWorkOrderC" method="post" role="form">
							
								<p style="float:left; margin: 17px 20px 5px 80px;width= 20px;font-size: 15px;">任务单号</p>
								<div style="float:left;margin: 11px 20px 0 40px; width:254px;">
									<input type="text" name="invoiceId" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
								<button type="submit" onclick="" class="buttonB" style=" margin:10px 0 0 40px; float:left">搜索</button>
							
						</form>
						
					</div>
				<!--/# 搜索 -->
					<!-- 搜索结果，隐藏 -->
				<div style="margin:0 auto;">
				<form method="post">
					<div class="searchResult">
						<div class="searchResultNav">
							<p class="p70">任务单号</p>
							<p class="p45">订单号</p>
							<p class="p70">商品名</p>
							<p class="p70">商品数量</p>
							<p class="p70">计量单位</p>
							<p class="p40">金额</p>
							<p class="p70">日期</p>
						</div>
						<div class="searchItem">
							<p class="p70">${workorderC.workId}</p>
							<p class="p45">${workorderC.orderId}</p>
							<p class="p70">${neworderC.productName}</p>
							<p class="p70">${neworderC.productQuantity}</p>
							<p class="p70">${neworderC.productUnit}</p>
							<p class="p40">${neworderC.total}</p>
							<p class="p70">${workorderC.createDate}</p>
						</div>
					</div>
				</form>
				</div>
				<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<form action="../invoiceManageServlet?action=getClientInvoice" method="post" role="form">
							
								<p style="float:left; margin: 17px 20px 5px 80px;width= 20px;font-size: 15px;">发票号</p>
								<div style="float:left;margin: 11px 20px 0 40px; width:254px;">
									<input type="text" name="invoiceId" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
								<button type="submit" onclick="" class="buttonB" style=" margin:10px 0 0 40px; float:left">领用</button>
							
						</form>
					</div>
				</div>
			</div>
			<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->
		<!-- Nav CSS -->
<%
	Object message = request.getSession().getAttribute("messageGCI");
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