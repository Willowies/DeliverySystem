<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
 <%@ page import="java.util.List"%>
   <%@ page import="com.neuedu.model.po.SubReturnRecord"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>

	<head>
		<title>库房中心</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
			
function checkform(){ 
    if(document.getElementById('ipt1').value.length==0){    
        alert('输入为空！');
        document.getElementById('ipt1').focus();
        return false;
    }
    if(document.getElementById('ipt1').value=="请输入搜索内容"){    
        alert('输入为空！');
        document.getElementById('ipt1').focus();
        return false;
    }
}
		</script>
		<!-- Bootstrap Core CSS -->
		<link href="./css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="./css/style.css" rel='stylesheet' type='text/css' />
		<link href="./css/font-awesome.css" rel="stylesheet">
		<link href="./css/warehouse.css" rel="stylesheet">
		<link href="./css/warehouse-center-return.css" rel="stylesheet">
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
					<a class="navbar-brand" href="">库房中心</a>
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
								<a href="warehouseHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="centerIn.jsp"><i class="fa fa-download nav_icon"></i>中心库房购货入库</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-upload nav_icon"></i>中心库房调拨出库<span class="fa arrow"></span>
								</a>
							    <ul class="nav nav-second-level">
							    	<li>
                                        <a href="transferCenterOut.jsp">调拨出库</a>
                                    </li>
                                    <li>
                                        <a href="selectCenterOut.jsp">查询出库信息</a>
                                    </li>
                                    <li>
                                        <a href="selectDistribution">查询分发信息</a>
                                    </li>
                                </ul>    	

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="subWarehouseIn.jsp"><i class="fa fa-download nav_icon"></i>分站库房调拨入库
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="receive.jsp"><i class="fa fa-briefcase nav_icon"></i>领货
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li class="active">
								<a href="#"><i class="fa fa-sign-out nav_icon"></i>退货管理<span class="fa arrow"></span>
								</a>
							    <ul class="nav nav-second-level">
							    	<li >
                                        <a href="./warehouse/returnRecord.jsp">退货登记</a>
                                    </li>
                                    <li>
                                        <a href="./warehouse/subReturnOut.jsp">分站退货出库</a>
                                    </li>
                                    <li>
                                        <a href="./warehouse/centerReturnIn.jsp">中心库房退货入库</a>
                                    </li>
                                    <li class="highlight-item">
                                        <a href="./warehouse/centerReturnOut.jsp">中心库房退货出库</a>
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
			<div id="page-wrapper" style="background: #f2f2f2;display: flex;">
				<form action="./warehouseReturnManageServlet?action=searchCenterReturnOrder" method="post">
					<div class="page-navigation">中心退货出库  / Center return </div>
					<div class="searchblock2" style="height: 200px;">
						<p1>搜索中心退货单</p1>
						<p>退货单号</p>
						<input  type="number" name="orderId" id="ipt1" style="width: 200px;" required="required">
						<div class="buttonblock">
							<button type="submit" style="margin-top: 20px;" onclick="checkform()">搜索</button>
						</div>
					</div>
				</form>
				<div class="resultblock" style="height: 350px;">
					<div class="title" style="margin-left: 80px;">
						<p3>退货单号</p3>
						<p3>商品名称</p3>
						<p3>商品编号</p3>
						<p3>商品数量</p3>
						<p3>计量单位</p3>
					</div>
					<form action="./warehouseReturnManageServlet?action=centerReturnOut" method="post" style="flex: 1;display: flex;flex-direction: column;">
					<div class="result-content" >
						<%
							List l = (List<SubReturnRecord>)request.getAttribute("orderList");
							if(!l.isEmpty()){
						%>
						<c:forEach items="${orderList}" var="order">
							<div class="result-item">
								
								<p3 style="margin-left: 80px;width: 80px;">${order.orderId}</p3>
							<input name="orderId" value="${order.orderId}" style="display: none;" />
							<input name="productId" value="${order.productId}" style="display: none;" />
							<p3 style="margin-left: 10px;width: 100px;">${order.productName}</p3>
							<p3 style="margin-left: 20px;width: 70px;">${order.productCode}</p3>
							<p3 style="margin-left: 50px;width: 50px;">${order.productQuantity}</p3>
							<input name="productQuantity" value="${order.productQuantity}" style="display: none;" />
							<p3 style="margin-left: 65px;">${order.productUnit}</p3>
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
						<%
							if(!l.isEmpty()){
							%>
						<div class="operate-button" style="margin: 0 auto;">
							<button class="b" type="submit">确认出库</button>
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