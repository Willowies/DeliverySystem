<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
 <%@ page import="java.util.List"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>

	<head>
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
			
			window.onload = function(){
				$("#side-menu li").removeClass("active");
				$("#side-menu li ul").removeClass("in");
			}
		</script>
		<!-- Bootstrap Core CSS -->
		<link href="./css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="./css/style.css" rel='stylesheet' type='text/css' />
		<link href="./css/font-awesome.css" rel="stylesheet">
		<link href="./css/warehouse.css" rel="stylesheet">
		<link href="./css/delivery-center-stock-return.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="./js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="./js/bootstrap.min.js"></script>
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
						<a href="" onclick="window.location.href='exit'" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="./images/exit.png" alt="" /></a>
					</li>
				</ul>
				<!--左侧导航栏-->
				<div class="navbar-default sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li >
								<a href="./delivery/deliveryHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li>
								<a href=""><i class="fa fa-download nav_icon"></i>商品管理<span class="fa arrow"></span></a>
								 <ul class="nav nav-second-level">
							    	
                                    <li>
                                        <a href="./firstclass/firstClassHome.jsp">商品一级分类管理</a>
                                    </li>
                                    <li>
                                        <a href="./secondclass/secondClassHome.jsp">商品二级分类管理</a>
                                    </li>
                                    <li>
                                        <a href="">商品管理</a>
                                    </li>
                                </ul>  
							</li>	
							<li>
								<a href="#"><i class="fa fa-upload nav_icon"></i>库房管理<span class="fa arrow"></span></a>
							   <ul class="nav nav-second-level">
							   	
							    	<li>
                                        <a href="./warehouse1/setWarehouse.jsp">库房设置</a>
                                    </li>
                                    <li>
                                        <a href="./delivery/warehouseProductSetting.jsp">库房储备设置</a>
                                    </li>
                                    <li>
                                        <a href="./delivery/warehouseValue.jsp">库房量查询</a>
                                    </li>
                                    <li>
                                        <a href="./delivery/warehouseOrderSearch.jsp">出入单查询</a>
                                    </li>
                                </ul>    	

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href=""><i class="fa fa-download nav_icon"></i>供应商管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level">
							   	
							    	<li>
                                        <a href="./supService/supRegister.jsp">注册供应商</a>
                                    </li>
							    	<li>
                                        <a href="./supService/supSelectResult.jsp">查询供应商</a>
                                    </li>
                                    
                                </ul>    	
								<!-- /.nav-second-level -->
							</li>
							<li class="highlight-item">
								<a href="./delivery/stockManage.jsp"><i class="fa fa-briefcase nav_icon"></i>进货管理
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li class="active">
								<a href="./delivery/returnManage.jsp"><i class="fa fa-sign-out nav_icon"></i>退货管理</span>
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="./delivery/statistics.jsp"><i class="fa fa-sign-out nav_icon"></i>统计</span>
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
			<div id="page-wrapper" style="background: #f2f2f2;display: flex;">
				<form action="./deliveryPartServlet?action=searchLackProduct" method="post">
					<div class="page-navigation">进货管理  / Stock </div>
					<div class="searchblock4" style="height: 200px;width: 240px;">
						<p1>缺货检查</p1>
						<p>检查商品（名称为空则检查全部商品）</p>
						<input  type="text" name="productName" id="ipt1" style="width: 200px;">
						<div class="buttonblock">
							<button type="submit" style="margin-top: 20px;" onclick="checkform()">搜索</button>
						</div>
					</div>
				</form>
				<div class="resultblock2">
					<div class="title">
						<p4>一级分类</p4>
						<p4>二级分类</p4>
						<p4>商品代码</p4>
						<p4>商品名称</p4>
						<p4>现存库存</p4>
						<p4>预警库存</p4>
						<p4>缺货数量</p4>
						<p4>进货量</p4>
						<p4>日期</p4>
					</div>
					<form action="./deliveryPartServlet?action=stock" method="post" style="flex: 1;display: flex;flex-direction: column;">
					<div class="result-content" >
						<%
							List l = (List)request.getAttribute("orderList");
							if(!l.isEmpty()){
						%>
						<c:forEach items="${orderList}" var="order">
							<div class="result-item">
								<input type="radio" name="productId" value="${order.productId}" style="float: left;width: 20px;"/>
								<p3 style="margin-left: 10px;width:80px" "="">${order.firstClass}</p3>
								<p3 style="width: 80px;margin-left: 20px;">${order.secondClass}</p3>
								<p3 style="margin-left: 10px;width: 40px;">${order.productCode}</p3>
								<p3 style="margin-left: 10px;">${order.productName}</p3>
								<p3 style="margin-left: 20px;width: 80px;">${order.now}</p3>
								<p3 style="margin-left: 0px;width: 80px;">${order.warning}</p3>
								<p3 style="margin-left: 10px;">${order.lack}</p3>
								<p3 style="width: 70px;margin-left: 60px;">
									<input type="number" name="productQuantity${order.productId}" value="${order.lack}" style="width: 50px;" min="1" max="10000">
								</p3>
								<p3 style="width: 100px;margin-left: 0px;"><fmt:formatDate value="${order.stockDate}" type="date"/></p3>
							</div>
						</c:forEach>
						<%
						}else{
						%>
							<div class="result-item">
							
							<p3 style="margin-left: 10px;"> </p3>
							<p3 style="margin-left: 45px;"> </p3>
							<p3 style="margin-left: 65px;"> </p3>
							<p3 style="margin-left: 350px;">未查询到数据</p3>
							<p3 style="margin-left: 65px;"> </p3>
							<p3 style="margin-left: 65px;"> </p3>
							</div>
						<%
						}
						%>
					</div>
					<div class="result-foot" style="display: flex;">
						<div class="page-turner">
							<ul class="pagination pagination-lg">
								<li <c:if test="${page eq 1}">class="disabled "</c:if>>
									<a href="<c:if test="${page != 1}">./deliveryPartServlet?action=searchLackProductByPage&page=${page-1}</c:if>"><i class="fa fa-angle-left "></i></a>
								</li>
								<c:forEach begin="1" end="${stockPageNum}" var="p"  >
									<c:if test="${p==page}">
										<li class="active ">
											<a href=" ">${p}</a>
										</li>
									</c:if>
									<c:if test="${p!=page}">
										<li>
											<a href="./deliveryPartServlet?action=searchLackProductByPage&page=${p}"  >${p}</a>
										</li>
									</c:if>
									&nbsp;&nbsp;
								</c:forEach>
								<li <c:if test="${page eq stockPageNum}">class="disabled "</c:if>>
									<a href="<c:if test="${page < stockPageNum}">./deliveryPartServlet?action=searchLackProductByPage&page=${page+1}</c:if>"><i class="fa fa-angle-right "></i></a>
								</li>
							</ul>
						</div>
						<%
							if(!l.isEmpty()){
							%>
						<div class="operate-button" style="margin: 0 auto;">
							<button class="b" type="submit">进货</button>
						</div>
						<%}%>
					</div>
					</form>

				</div>
			</div>
			<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		<link href="./css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="./js/metisMenu.min.js"></script>
		<script src="./js/custom.js"></script>
	</body>

</html>