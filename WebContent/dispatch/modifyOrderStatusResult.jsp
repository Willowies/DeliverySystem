<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
 <%@ page import="java.util.List"%>
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
			
			function modify(){
				var chks =  document.getElementsByName("check");
				var flag = false;
				for(var i=0;i<chks.length;i++){
					if(chks[i].checked==true){
						flag = true;
						break;
					}
				}
				if(flag){
					//提交请求
					document.forms["result-form"].submit();
				}else{
					//提示
					alert("请至少选择一个订单进行修改");
				}
			}
		</script>
		<!-- Bootstrap Core CSS -->
		<link href="./css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="./css/style.css" rel='stylesheet' type='text/css' />
		<link href="./css/font-awesome.css" rel="stylesheet">
		<link href="./css/dispatch-center.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="./js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="./js/bootstrap.min.js"></script>
		<!--
			弹窗的js
        -->
		<script src="./js/jumpout.js"></script>
		<!--
        	下拉框的滚轮和回收js
        -->
		<script src="./js/autocomplete.js"></script>
		<link href="./css/autocomplete.css" rel='stylesheet' type='text/css' />
		<script type="text/javascript">
			var proposals = ['at', 'boat', 'bear', 'chief', 'dog', 'drink', 'elephant', 'fruit', 'grave', 'hotel', 'illness', 'London', 'motorbike', 'xw素材网官网', 'xw素材网之家', 'xw素材网下载'];
			$(document).ready(function() {
				$('#search-form').autocomplete({
					hints: proposals,
					width: 300,
					height: 30,
					onSubmit: function(text) {
						$('#message').val(text);
					}
				});
			});
			
			window.onload = function(){
				if($("#productName").height() >=30){
					$("#productName").css('marginTop',-10);
				}
				
			}
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
						<a href=" "onclick="window.location.href='exit'" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="./images/exit.png" alt="" /></a>
					</li>
				</ul>
				<!--左侧导航栏-->
				<div class="navbar-default sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li >
								<a href="./dispatch/dispatchHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="./dispatch/dispatchGoods.jsp"><i class="fa fa-exchange nav_icon"></i>调度商品</a>
								<!-- /.nav-second-level -->
							</li>
							<li  class="highlight-item">
								<a href="./dispatch/modifyOrderStatus.jsp"><i class="fa fa-edit nav_icon"></i>修改订单状态</span>
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="./dispatch/searchWorkOrder.jsp"><i class="fa fa-search nav_icon"></i>查询任务单</span>
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
				<form action="./dispatchManageServlet?action=searchLackOrder" method="post">
					<div class="page-navigation">修改订单状态  / Modify status</div>
					<div class="searchblock2" style="height: 250px;">
						<p1>搜索缺货订单</p1>
						<p>生成日期</p>
						<input type="date" name="createDate" id="createDate">
						<p>要求完成日期</p>
						<input type="date" name="requireDate" id="requireDate">
						<div style="margin: 5px 0;display: flex;">
							<input type="checkbox" name="enoughOrNot" style="width: 15px;margin: 0 10px;">
							<p2 style="margin: 3px;font-size: 12px;">只显示已到货</p2>
						</div>
						<div class="buttonblock">
							<button type="submit">搜索</button>
						</div>
					</div>
				</form>
				<div class="resultblock">
					<div class="title">
						<p3>订单号</p3>
						<p3>商品名称</p3>
						<p3>商品数量</p3>
						<p3>生成日期</p3>
						<p3>要求完成日期</p3>
						<p3>到货状态</p3>
					</div>
					<div class="result-content">
						<form action="./dispatchManageServlet?action=modify" method="post" name="result-form">
						<%
							List l = (List)request.getAttribute("orderList");
							if(!l.isEmpty()){
						%>
						<c:forEach items="${orderList}" var="order">
							<div class="result-item">
								<input type="radio" name="check" style="float: left;width: 20px;<c:if test="${!order.enoughOrNot}">visibility: hidden;</c:if>" value="${order.orderId}"/>
								<p3 style="margin-left: 10px;width: 80px;">${order.orderId}</p3>
								<p3 style="margin-left: 10px;width: 100px;"  id="productName">${order.product.productName}</p3>
								<p3 style="width: 60px;margin-left: 30px;">${order.productQuantity}</p3>
								<p3 style="margin-left: 30px;width: 80px;">${order.generateDate}</p3>
								<p3 style="margin-left: 50px;">${order.requireDate}</p3>
								<c:if test="${order.enoughOrNot}">
									<p3 style="margin-left: 65px;">已到货</p3>
								</c:if>
								<c:if test="${!order.enoughOrNot}">
									<p3 style="margin-left: 65px;">未到货</p3>
								</c:if>
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
						
						</form>
					</div>
					<div class="result-foot">
						<div class="page-turner">
							<ul class="pagination pagination-lg">
								<li <c:if test="${page eq 1}">class="disabled "</c:if>>
									<a href="<c:if test="${page != 1}">./dispatchManageServlet?action=searchLackOrderByPage&page=${page-1}</c:if>"><i class="fa fa-angle-left "></i></a>
								</li>
								<c:forEach begin="1" end="${modifyPageNum}" var="p"  >
									<c:if test="${p==page}">
										<li class="active ">
											<a href=" ">${p}</a>
										</li>
									</c:if>
									<c:if test="${p!=page}">
										<li>
											<a href="./dispatchManageServlet?action=searchLackOrderByPage&page=${p}"  >${p}</a>
										</li>
									</c:if>
									&nbsp;&nbsp;
								</c:forEach>
								<li <c:if test="${page eq modifyPageNum}">class="disabled "</c:if>>
									<a href="<c:if test="${page < modifyPageNum}">./dispatchManageServlet?action=searchLackOrderByPage&page=${page+1}</c:if>"><i class="fa fa-angle-right "></i></a>
								</li>
							</ul>
						</div>
						<div class="operate-button">
							<button class="b" onclick="modify()">修改状态</button>
						</div>
					</div>
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