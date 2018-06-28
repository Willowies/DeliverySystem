<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="<%=basePath%>/css/bootstrap.min.css" rel='stylesheet'
	type='text/css' />
<!-- Custom CSS -->
<link href="<%=basePath%>/css/style.css" rel='stylesheet'
	type='text/css' />
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
							<li >
								<a href="substationHome.jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li class="highlight-item">
								<a href="modifyOrderStatus.jsp"><i class="fa fa-edit nav_icon"></i>生成订单</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="">
									<i class="fa fa-tasks nav_icon"></i>客户管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
								    <li><a href="registerClient.jsp">新增客户</a></li>
								    <li><a href="selectResult.jsp">查询客户信息</a></li>
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
		<div id="page-wrapper" style="background: #f2f2f2; min-width: 800px;">
			<div class="container-fluid">
				<div class="row">
					<div class="page-navigation">退订订单/ cancelOrder</div>
				</div>

				<div class="row">
					<!--  第一列卡片  -->
					<div class="col-md-3">
						<form action="../Order/OrderServlet?action=selectOrderByPage" method="post" role="form">
							<div class="selectblock1">
								<p>客户信息搜索</p>
								<div class="form-group">
									<label class="hidden"></label>
									<p>客户名</p>
									<input type="text" class="form-control" placeholder="客户名"
										name="clientName">
								</div>
								<div class="form-group">
									<label class="hidden"></label>
									<p>客户手机</p>
									<input type="text" class="form-control" placeholder="输入11位手机号"
										name="clientMobilephone">
								</div>
								<div class="form-group">
									<label class="hidden"></label>
									<p>客户座机</p>
									<input type="text" class="form-control" placeholder="输入8位座机号"
										name="clientPhonenumber">
								</div>
								<div class="form-group">
									<label class="hidden"></label>
									<p>客户身份证</p>
									<input type="text" class="form-control" placeholder="输入16位身份证号"
										name="clientIc">
								</div>
								<div class="form-group">
									<label class="hidden"></label>
									<p>订单号</p>
									<input type="text" class="form-control" name="orderId">
								</div>
								<div class="form-group">
									<label class="hidden"></label>
									<p>订单状态</p>
									<select name="orderState" class="form-control">
										<option value="0">全部</option>
										<option value="1">可分配</option>
										<option value="2">退订</option>
										<option value="3">缺货</option>
										<option value="4">全部退货</option>
										<option value="5">部分退货</option>
										<option value="6">已调度</option>
										<option value="7">中心库房出库</option>
										<option value="8">分站库房到货</option>
										<option value="9">已分配</option>
										<option value="10">已领货</option>
										<option value="11">已完成</option>
									</select>
								</div>
								<!-- 
								<div class="form-group">
									<label class="hidden"></label>
									<p>生成日期</p>
									<input type="date" class="form-control" placeholder=""
										name="generateDate">
								</div>
								<div class="form-group">
									<label class="hidden"></label>
									<p>完成日期</p>
									<input type="date" class="form-control" placeholder=""
										name="finishDate">
								</div>
								 -->
								<div class="form-group">
									<label class="hidden"></label>
									<button type="submit" class="form-control btn-info">搜索</button>
								</div>
							</div>
						</form>
					</div>
					<!--  第一列卡片  -->

					<!--  第二列卡片  -->
					<div class="col-md-9">
						<div class="row">
							<div class="selectblock3 col-md-12">
								<p>订单信息</p>
								<table class="table table-hover  table-condensed">
									<thead>
										<tr class="active">
											<th>订单号</th>
											<th>订单类型</th>
											<th>生成日期</th>
											<th>要求完成日期</th>
											<th>订单状态</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${resultList}" var="neworder">
											<tr class="" id="a${neworder.orderId}"
												onClick="selectNewOrder(this)">
												<th>${neworder.orderId}</th>
												<th>${neworder.orderState}</th>
												<th>${neworder.generateDate}</th>
												<th>${neworder.requireDate}</th>
												<th>${neworder.orderState}</th>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!--  页码栏    -->
						<div class="row">
							<div class="col-md-4 col-md-offset-4">
								<ul class="pagination">
									<c:forEach begin="1" end="${pagecount}" var="p">
										<c:if test="${p==pageNum}">
											<li>${p}</li>
										</c:if>
										<c:if test="${p!=pageNum}">
											<li><a
												href="OrderServlet?action=selectNewPageByCount,pageNum=${p}">${p}</a></li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</div>

						<ul class="pagination">
							<c:forEach begin="1" end="${pagecount}" var="p">
								<c:if test="${p==pageNum}">
									<li>${p}</li>
								</c:if>
								<c:if test="${p!=pageNum}">
									<li><a
										href="OrderServlet?action=selectNewPageByCount,pageNum=${p}">${p}</a></li>
								</c:if>
							</c:forEach>

						</ul>
					</div>
					<c:forEach begin="1" end="${pagecount}" var="p">
						<c:if test="${p==pageNum}">
							${p}
						</c:if>
						<c:if test="${p!=pageNum}">
							<a href="userManageServlet?pageNum=${p}">${p}</a>
						</c:if>
					</c:forEach>

					<!--  第二列卡片  -->

				</div>
			</div>
		</div>
	</div>
	<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

</body>

</html>