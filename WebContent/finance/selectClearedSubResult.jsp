<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
<!DOCTYPE html>
<html>
	<head>
		<title>财务中心|与分站结算</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/clearSubstationResult.js"></script>
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
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
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
							    	<li  class="highlight-item">
                                        <a href="clearSubstation.jsp">进行结算</a>
                                    </li>
                                    <li>
                                        <a href="selectCenterOut.jsp">已结算订单查询</a>
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
			<div id="page-wrapper" style="background: #f2f2f2;min-width: 800px;  min-height: 299px;">
				<div class="page-navigation">
					<strong>结算 / balance Page</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: row;align-items:flex-start;">
					<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">查询签收单</p>
						
						<form action="../clearMoneyServlet?action=selectClearedSub" method="post" role="form">
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">分站</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="text" name="substation" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
							</div>
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">签收日期</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="date" name="date" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
							</div>
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">商品名</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="text" name="product" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
							</div>
							<div class="buttonblock">
								<button type="submit" onclick="">搜索</button>
							</div>
						</form>
						
					</div>
				<!--/# 搜索 -->
					<!-- 搜索结果，隐藏 -->
					<div class="searchResult">
						<div class="searchResultNav">
							<p class="p45">订单号</p>
							<p class="p40">类型</p>
							<p class="p90">商品名称</p>
							<p class="p40">单价</p>
							<p class="p40">数量</p>
							<p class="p40">金额</p>
							<p class="p70">签收日期</p>
						</div>
						<c:forEach items="${resultClearedList}" var="sign">
							<div class="searchItem">
							<p class="p40">${sign.orderId}</p>
							<p class="p40"><c:if test="${sign.workType==1}">进货</c:if>
							<c:if test="${sign.workType==2}">退货</c:if>
							</p>
							<p class="p90">${sign.productName }</p>
							<p class="p40">${sign.productPrice }</p>
							<p class="p40">${sign.productQuantity }</p>
							<p class="p40">${sign.total }</p>
							<p class="p70">${sign.deliveryDate }</p>
							</div>
						</c:forEach>
						<div class="result-foot ">
						<div class="page-turner ">
							<ul class="pagination pagination-lg ">
								<li <c:if test="${page eq 1}">class="disabled "</c:if>>
									<a href="<c:if test="${page != 1}">../clearMoneyServlet?action=selectclearedSubByPage&page=${page-1}</c:if>"><i class="fa fa-angle-left "></i></a>
								</li>
								<c:forEach begin="1" end="${pageNum}" var="p"  >
									<c:if test="${p==page}">
										<li class="active ">
											<a href=" ">${p}</a>
										</li>
									</c:if>
									<c:if test="${p!=page}">
										<li>
											<a href="../clearMoneyServlet?action=selectclearedSubByPage&page=${p}"  >${p}</a>
										</li>
									</c:if>
									&nbsp;&nbsp;
								</c:forEach>
								<li <c:if test="${page eq pageNum}">class="disabled "</c:if>>
									<a href="<c:if test="${page < pageNum}">../clearMoneyServlet?action=selectclearedSubByPage&page=${page+1}</c:if>"><i class="fa fa-angle-right "></i></a>
								</li>
							</ul>
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