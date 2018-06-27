<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
 <%@ page import="java.util.List"%>
  <%@ page import="com.neuedu.model.po.WorkOrder"%>
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
        	下拉框的滚轮和回收js
        -->
		<script src="./js/autocomplete.js"></script>
		<link href="./css/autocomplete.css" rel='stylesheet' type='text/css' />
		<script type="text/javascript">
			var proposals = ['at', 'boat', 'bear', 'chief', 'dog', 'drink', 'elephant', 'fruit', 'grave', 'hotel', 'illness', 'London', 'motorbike', 'xw素材网官网', 'xw素材网之家', 'xw素材网下载'];
			$(document).ready(function() {
				$('#search-form').autocomplete({
					hints: proposals,
					width: 200,
					height: 20,
					onSubmit: function(text) {
						$('#message').html('Selected: <b>' + text + '</b>');
					}
				});
			});
			
			window.onload = function(){
				if($("#warehouseName").height() >=30){
					$("#warehouseName").css('marginTop',-10);
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
						<a href="" onclick="window.location.href='exit'" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="./images/exit.png" alt="" /></a>
					</li>
				</ul>
				<!--左侧导航栏-->
				<div class="navbar-default sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li>
								<a href="./dispatch/dispatchHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="./dispatch/dispatchGoods.jsp"><i class="fa fa-exchange nav_icon"></i>调度商品</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="./dispatch/modifyOrderStatus.jsp"><i class="fa fa-edit nav_icon"></i>修改订单状态</span>
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li class="highlight-item">
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
				<form action="./dispatchManageServlet?action=searchWorkOrder" method="post">
					<div class="page-navigation">查询任务单 / Search workOrder</div>
					<div class="searchblock4">
						<p1>搜索任务单</p1>
						<p>任务单号</p>
						<input type="number" name="workId" id="workId">
						<p>要求完成日期</p>
						<input type="date" name="requireDate" id="requireDate">
						<p>选择分站</p>
						<div id="search-form"></div>
						<div id="message"></div>
						<p>任务单类型</p>
						<select name="orderType">
							<option value="0"></option>
							<option value="1">新订</option>
							<option value="2">退货</option>
						</select>
						<p>任务单状态</p>
						<select name="orderStatus">
							<option value="0"></option>
							<option value="1">已调度</option>
							<option value="2">可分配</option>
							<option value="3">已分配</option>
							<option value="4">已领货</option>
							<option value="5">已完成</option>
						</select>
						<p>客户姓名</p>
						<input type="text" name="clientName" id="clientName">
						<p>客户电话</p>
						<input type="number" name="clientPhone" id="clientPhone">
						<div class="buttonblock">
							<button type="submit">搜索</button>
						</div>
					</div>
				</form>
				<div class="resultblock2">
					<div class="title">
						<p3 style="margin: 0 40px 0 0;">任务号</p3>
						<p3>任务类型</p3>
						<p3>任务状态</p3>
						<p3>要求完成日期</p3>
						<p3>分站名称</p3>
						<p3>客户姓名</p3>
						<p3>客户电话</p3>
					</div>

					<div class="result-content">
						<%
							List l = (List)request.getAttribute("workOrderList");
							if(!l.isEmpty()){
						%>
							<c:forEach items="${workOrderList}" var="order">
							<div class="result-item">
								<p3 style="margin-left: 10px;width:80px" ">
									${order.workId}
								</p3>
								<p3 style="margin-left: 45px; ">
									<c:if test="${order.workType eq 1}">新订</c:if>
									<c:if test="${order.workType eq 2}">退货</c:if>	
								</p3>
								<p3 style="margin-left: 65px; ">
									<c:if test="${order.workStatus eq 1}">已调度</c:if>
									<c:if test="${order.workStatus eq 2}">可分配</c:if>	
									<c:if test="${order.workStatus eq 3}">已分配</c:if>	
									<c:if test="${order.workStatus eq 4}">已领货</c:if>	
									<c:if test="${order.workStatus eq 5}">已完成</c:if>	
								</p3>
								<p3 style="margin-left: 55px; ">${order.requireDate}</p3>
								<p3 style="margin-left: 35px;width:100px " id="warehouseName">${order.warehouseName}</p3>
								<p3 style="margin-left: 25px; ">${order.clientName}</p3>
								<p3 style="margin-left: 30px; ">${order.clientPhone}</p3>
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
					<div class="result-foot ">
						<div class="page-turner ">
							<ul class="pagination pagination-lg ">
								<li <c:if test="${page eq 1}">class="disabled "</c:if>>
									<a href="<c:if test="${page != 1}">./dispatchManageServlet?action=searchWorkOrderByPage&page=${page-1}</c:if>"><i class="fa fa-angle-left "></i></a>
								</li>
								<c:forEach begin="1" end="${pageNum}" var="p"  >
									<c:if test="${p==page}">
										<li class="active ">
											<a href=" ">${p}</a>
										</li>
									</c:if>
									<c:if test="${p!=page}">
										<li>
											<a href="./dispatchManageServlet?action=searchWorkOrderByPage&page=${p}"  >${p}</a>
										</li>
									</c:if>
									&nbsp;&nbsp;
								</c:forEach>
								<li <c:if test="${page eq pageNum}">class="disabled "</c:if>>
									<a href="<c:if test="${page < pageNum}">./dispatchManageServlet?action=searchWorkOrderByPage&page=${page+1}</c:if>"><i class="fa fa-angle-right "></i></a>
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
		<link href="./css/custom.css " rel="stylesheet ">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="./js/metisMenu.min.js "></script>
		<script src="./js/custom.js "></script>
	</body>

</html>