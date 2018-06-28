<%@page import="java.util.ArrayList"%>
<%@page import="com.neuedu.model.po.WorkOrder"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<%
			String path =request.getContextPath();
	      	String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	     %>
	    <base href=<%=basePath%>>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>库房中心|中心库房调拨出库</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);
	
			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		</script>
		<!-- Bootstrap Core CSS -->
		<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<link href="css/font-awesome.css" rel="stylesheet">
		<link href="css/left-right.css" rel="stylesheet">
		<link href="css/bootstrap-select.min.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap-select.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
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
					<a class="navbar-brand" href="">库房中心</a>
				</div>
				<!-- /.navbar-header -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
					</li>
					<li class="dropdown">
						<a href="" onclick="window.location.href='exit'" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="images/exit.png" alt="" /></a>
					</li>
				</ul>
				<!--左侧导航栏-->
				<div class="navbar-default sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li >
								<a href="warehouse/warehouseHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li >
								<a href="warehouse/centerIn.jsp"><i class="fa fa-download nav_icon"></i>中心库房购货入库</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-upload nav_icon"></i>中心库房调拨出库<span class="fa arrow"></span>
								</a>
							    <ul class="nav nav-second-level">
							    	<li class="highlight-item">
                                        <a href="warehouse/centerOut.jsp">调拨出库</a>
                                    </li>
                                    <li>
                                        <a href="warehouse/selectCenterOut.jsp">查询出库信息</a>
                                    </li>
                                    <li>
                                        <a href="warehouse/selectDistribution.jsp">查询分发信息</a>
                                    </li>
                                </ul>    	

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="warehouse/subWarehouseIn.jsp"><i class="fa fa-download nav_icon"></i>分站库房调拨入库
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="warehouse/receive.jsp"><i class="fa fa-briefcase nav_icon"></i>领货
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-sign-out nav_icon"></i>退货管理<span class="fa arrow"></span>
								</a>
							    <ul class="nav nav-second-level">
							    	<li>
                                        <a href="warehouse/returnRecord.jsp">退货登记</a>
                                    </li>
                                    <li>
                                        <a href="warehouse/subReturnOut.jsp">分站退货出库</a>
                                    </li>
                                    <li>
                                        <a href="warehouse/centerReturnIn.jsp">中心库房退货入库</a>
                                    </li>
                                    <li>
                                        <a href="warehouse/centerReturnOut">中心库房退货出库</a>
                                    </li>
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
					<strong>中心库房调拨出库 / Transfer goods out of the central warehouse</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: row;align-items:flex-start;">
					<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">搜索货物调拨单</p>
						
						<form action="warehouseManageServlet?action=selectTransfer" method="post" role="form">
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">要求完成日期</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="date" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" name="completeDate">
								</div>
							</div>
							<div class="buttonblock">
								<button type="submit">搜索</button>
							</div>
						</form>
						
					</div>
					<!--/# 搜索 -->
					<!-- 搜索结果，隐藏 -->
					<div class="searchResult">
					<form action="warehouseManageServlet?action=centerOut" method="post">
						<div class="searchResultNav">
							<p class="p20"></p>
							<p class="p70">调拨单号</p>
							<p class="p70">出库库房</p>
							<p class="p70">入库库房</p>
							<p class="p70">商品代码</p>
							<p class="p100">调拨商品数量</p>
							<p class="p100">商品计量单位</p>
							<p class="p100">计划出库时间</p>
							<p class="p60">订单号</p>
							<p class="p70">任务单号</p>
						</div>
						<c:forEach items="${resultList}" var="transferOrder">
							<div class="searchItem">
							<p class="p20"><input type="radio" name="transferOrder" value="${transferOrder.productTransferId}" /></p>
							<p class="p70">${transferOrder.productTransferId}</p>
							<p class="p70">${transferOrder.outWarehouseId}</p>							
							<p class="p70">${transferOrder.inWarehouseId}</p>
							<p class="p70">${transferOrder.productId}</p>
							<p class="p100">${transferOrder.productQuantity}</p>
							<p class="p100">${transferOrder.productUnit}</p>
							<p class="p100">${transferOrder.planOutDate}</p>
							<p class="p60">${transferOrder.orderId}</p>
							<p class="p70">${transferOrder.workId}</p>
							</div>
						</c:forEach>
					
						<div class="pageFoot">
							<ul class="pagination" style="margin:10px 0;">
						        <li <c:if test="${page eq 1}">class="disabled "</c:if>>
									<a href="<c:if test="${page != 1}">warehouseManageServlet?action=selectTransferByPage&page=${page-1}</c:if>"><i class="fa fa-angle-left "></i></a>
								</li>
								<c:forEach begin="1" end="${pageNum}" var="p"  >
									<c:if test="${p==page}">
										<li class="active ">
											<a href=" ">${p}</a>
										</li>
									</c:if>
									<c:if test="${p!=page}">
										<li>
											<a href="warehouseManageServlet?action=selectTransferByPage&page=${p}"  >${p}</a>
										</li>
									</c:if>
									&nbsp;&nbsp;
								</c:forEach>
								<li <c:if test="${page eq pageNum}">class="disabled "</c:if>>
									<a href="<c:if test="${page < pageNum}">warehouseManageServlet?action=selectTransferByPage&page=${page+1}</c:if>"><i class="fa fa-angle-right "></i></a>
								</li>
						    </ul>
						</div>
							
						<button class="buttonInCenter" type="submit">出库</button>
					</form>
					</div>
					
				</div>
			</div>
			<!-- /#page-wrapper -->
		</div>
		<%
	Object message = request.getAttribute("message");
	if(message!=null&&!"".equals(message)){
%>
	<script type="text/javascript">
	alert("<%=message%>");
	</script>
<%
	}
%>
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		<link href="css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="js/metisMenu.min.js"></script>
		<script src="js/custom.js"></script>
	</body>
</html>