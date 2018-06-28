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
		<title>分站管理|任务分配</title>
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
		<link href="css/substation-center.css" rel="stylesheet">
		<link href="css/bootstrap-select.min.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap-select.min.js"></script>
		<!-- Custom JS -->
		<script src="js/popup.js"></script>
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
					<a class="navbar-brand" href="substation/substationHome.jsp">分站中心</a>
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
								<a href="substation/substationHome.jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="substation/searchWorkOrder.jsp"><i class="fa fa-search nav_icon"></i>查询任务单
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li class="highlight-item">
								<a href="substation/workAssign.jsp"><i class="fa fa-tasks nav_icon"></i>任务分配
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="substation/signPrint.jsp"><i class="fa fa-print nav_icon"></i>打印商品签收单
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="substation/feedbackRecord.jsp"><i class="fa fa-edit nav_icon"></i>回执录入
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="substation/paymentQuery.jsp"><i class="fa fa-money nav_icon"></i>缴款查询
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="">
									<i class="fa fa-tag nav_icon"></i>发票管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
									<li><a href=".jsp">发票领用</a></li>
									<li><a href=".jsp">发票作废</a></li>
									<li><a href=".jsp">发票查询</a></li>
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
					<strong>任务分配 / WorkAssign</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: row;align-items:flex-start;">
					<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">搜索任务单</p>
						
						<form action="substationServlet?action=assign" method="post" role="form">
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">要求完成日期</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="date" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" name="requireDate">
								</div>
							</div>
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">任务类型</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<select class="selectpicker" style="width: 254px;" name="workType" id="site_id">
										<option value="0"></option>
										<option value="1">送货收款</option>
										<option value="2">送货</option>
										<option value="3">收款</option>
										<option value="4">退货</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">任务状态</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<select class="selectpicker" style="width: 254px;" name="workStatus" id="site_id">
										<option value="0"></option>
										<option value="1">已调度</option>
										<option value="2">可分配</option>
										<option value="3">已分配</option>
										<option value="4">已领货</option>
										<option value="5">已完成</option>
									</select>
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
						<div class="searchResultNav">
							<p>任务单号</p>
							<p>任务类型</p>
							<p>任务状态</p>
							<p>要求完成日期</p>
							<p>任务分配</p>
						</div>
						<div id="cover" style="background: #000; position:absolute; left: 0px; top: 0px; width:100%; filter: alpha(opacity=50); opacity: 0.5; display:none; z-index:1000;"></div>
						
					<% 
						List<WorkOrder> list = (ArrayList<WorkOrder>)request.getAttribute("resultList");
						if(list!=null){
							WorkOrder workOrder = null;
							for(int i=0;i<list.size();i++) {
								workOrder = list.get(i);
					%>
						<div class="searchItem">
							<button class="workButton" onClick="showWorkOrder<%=i%>()"><%=workOrder.getWorkId() %></button>
							<div id="showDivW<%=i%>" style="display:none; position: fixed; top: 0; right: 0; bottom: 0; left: 0; z-index:1050; overflow-y: auto !important;">
								<div class="homeblock5" style="margin-left:auto; margin-right:auto;">
									<div style="padding:30px 45px 10px 45px;">
										<button type="button" class="close" onClick="closebuttonW<%=i%>()">×</button>
										<p class="title">详细信息</p>
									</div>
									<div class="bodyStyle" style="padding-top:0;">
										<div class="infoBlock">
											<p style="width:510px; font-weight:400; margin-left:33.75px;">任务信息</p>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>任务单号</p>
													<span class="infoBorder"><%=workOrder.getWorkId() %></span>
												</div>
												<div class="infoRowColumn">
													<p>任务类型</p>
													<span class="infoBorder"><%= workOrder.getWorkType() %></span>
												</div>
											</div>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>任务状态</p>
													<span class="infoBorder"><%= workOrder.getWorkStatus() %></span>
												</div>
												<div class="infoRowColumn">
													<p>要求完成日期</p>
													<span class="infoBorder"><%= workOrder.getRequireDate() %></span>
												</div>
											</div>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>服务资金信息</p>
													<span class="infoBorder"><%= workOrder.getTotalAmount() %></span>
												</div>
												<div class="infoRowColumn">
													<p>备注</p>
													<span class="infoBorder"><%= workOrder.getRemark() %></span>
												</div>
											</div>
										</div>
										<div class="infoBlock">
											<p style="width:510px; font-weight:400; margin-left:33.75px;">服务员信息</p>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>分站名称</p>
													<span class="infoBorder"><%= workOrder.getWarehouseName() %></span>
												</div>
												<div class="infoRowColumn">
													<p>配送员代号</p>
													<span class="infoBorder"><%= workOrder.getDeliveryStaffId() %></span>
												</div>
											</div>
										</div>
										<div class="infoBlock">
											<p style="width:510px; font-weight:400; margin-left:33.75px;">服务对象信息</p>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>姓名</p>
													<span class="infoBorder"><%=workOrder.getClientName() %></span>
												</div>
												<div class="infoRowColumn">
													<p>电话</p>
													<span class="infoBorder"><%=workOrder.getClientPhone() %></span>
												</div>
											</div>
											<div class="infoRowColumnlong">
												<p>地址</p>
												<span class="infoBorderlong"><%= workOrder.getClientAddress() %></span>
											</div>
										</div>
										<div class="infoBlock">
											<p style="width:510px; font-weight:400; margin-left:33.75px;">服务内容信息</p>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>商品代码</p>
													<span class="infoBorder"><%=workOrder.getProductCode() %></span>
												</div>
												<div class="infoRowColumn">
													<p>商品数量</p>
													<span class="infoBorder"><%=workOrder.getProductQuantity() %></span>
												</div>
											</div>
											<div class="infoRowColumnlong">
												<p>计量单位</p>
												<span class="infoBorder"><%=workOrder.getProductUnit() %></span>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" onClick="closebuttonW<%=i%>()" style="width: 76px; margin-right:30px;">关闭</button>
									</div>
								</div>
							</div>
							<p class="p60">
								<%
									if(workOrder.getWorkType()==1){
								%>送货收款
								<%} %>
								<%
									if(workOrder.getWorkType()==2){
								%>送货
								<%} %>
								<%
									if(workOrder.getWorkType()==3){
								%>收款
								<%} %>
								<%
									if(workOrder.getWorkType()==4){
								%>退货
								<%} %>
							</p>
							<p class="p60">
								<%
									if(workOrder.getWorkStatus()==1){
								%>已调度
								<%} %>
								<%
									if(workOrder.getWorkStatus()==2){
								%>可分配
								<%} %>
								<%
									if(workOrder.getWorkStatus()==3){
								%>已分配									
								<%} %>
								<%
									if(workOrder.getWorkStatus()==4){
								%>已领货
								<%} %>
								<%
									if(workOrder.getWorkStatus()==5){
								%>已完成
								<%} %>
							</p>
							<p class="p90"><%=workOrder.getRequireDate() %></p>
							<button class="workButton" onClick="assignWork<%=i%>()">分配</button>
							<div id="cover_assign<%=i%>" style="background: #000; position:absolute; left: 0px; top: 0px; width:100%; filter: alpha(opacity=50); opacity: 0.5; display:none; z-index:1000;"></div>
							
							<div id="showDiv<%=i%>" style="width:300px; margin:0 auto; display:none; position: absolute; top:20%; left:42%; z-index:1050; background:#fff; border-radius: 10px;">
								<div class="homeblock4" style="margin-left:auto; margin-right:auto;">
									<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">任务分配</p>
									
									<form action="substationServlet?action=assignWork" method="post" role="form">
										<div class="form-group">
											<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">任务号</p>
											<div style="margin: 0 0 0 15px; width:254px;">
												<input type="text" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;"
												 name="workIdOfW" value="<%=workOrder.getWorkId()%>" readonly="readonly">
											</div>
										</div>
										<div class="form-group">
											<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">任务状态</p>
											<div style="margin: 0 0 0 15px; width:254px;">
												<input type="text" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" 
												name="workStatusAssign" value="<%= workOrder.getWorkStatus()%>" readonly="readonly">
											</div>
										</div>
										<div class="form-group">
											<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">配送员列表</p>
											<div style="margin: 0 0 0 15px; width:254px;">
												<select class="selectpicker" style="width: 254px;" name="deliveryStaff" id="deliveryList<%=i%>">
													
												</select>
											</div>
										</div>
										<div class="buttonblock">
											<button type="submit">确认分配</button>
										</div>
									</form>

								</div>
								<!-- 浮窗 -->
							</div>
						
						</div>
					<%	 }
						}
					%>
						<div class="pageFoot">
							<ul class="pagination" style="margin:10px 0;">
						        <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
						        <c:forEach  begin="1" end="${pageCount}" var="p">
									<c:if test="${p==pageNum}">
										<li><a>${p}</a></li>
									</c:if>
									<c:if test="${p!=pageNum}">
										<li><a href="substationServlet?action=assign&pageNum=${p}" >${p}</a></li>
									</c:if>
								</c:forEach>
						        <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
						      </ul>
						</div>
					</div>
					
				</div>
			</div>
			<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		<link href="css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="js/metisMenu.min.js"></script>
		<script src="js/custom.js"></script>
	</body>
</html>