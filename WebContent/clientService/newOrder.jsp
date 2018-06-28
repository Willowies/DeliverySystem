<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
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
		<link href="<%=basePath%>/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="<%=basePath%>/css/style.css" rel='stylesheet' type='text/css' />
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
								<a href="<%=basePath%>/clientService/OrderManageHome.jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li class="highlight-item">
								<a href="<%=basePath%>/clientService/generateOrder.jsp"><i class="fa fa-edit nav_icon"></i>生成订单</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="">
									<i class="fa fa-tasks nav_icon"></i>客户管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
								    <li><a href="<%=basePath%>/clientService/registerClient.jsp">新增客户</a></li>
								    <li><a href="<%=basePath%>/clientService/selectResult.jsp">查询客户信息</a></li>
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
			<div id="page-wrapper" style="background: #f2f2f2;min-width: 800px;">
				<div class="container-fluid">
					<div class="row">
						<div class="page-navigation">生成订单/ generateOrder</div>
					</div>

					<div class="row">
						<!--  第一列卡片  -->
						<div class="col-md-3">
							<form action="../DeliverySystem/OrderServlet?action=locateClient" method="post" role="form">
								<div class="generateblock1">
									<p>重新定位客户</p>
									<div class="form-group">
										<label class="hidden"></label>
										<p>客户名</p>
										<input type="text" class="form-control" placeholder="客户名" name="clientName">
									</div>
									<div class="form-group">
										<label class="hidden"></label>
										<p>客户手机</p>
										<input type="text" class="form-control" placeholder="输入11位手机号" name="clientMobilephone">
									</div>
									<div class="form-group">
										<label class="hidden"></label>
										<p>客户座机</p>
										<input type="text" class="form-control" placeholder="输入8位座机号" name="clientPhonenumber">
									</div>
									<div class="form-group">
										<label class="hidden"></label>
										<p>客户身份证</p>
										<input type="text" class="form-control" placeholder="输入16位身份证号" name="clientIc">
									</div>

									<div class="form-group">
										<label class="hidden"></label>
										<button type="submit" class="form-control">搜索  </button>
									</div>
								</div>
							</form>

							<div class="row">
								<div class="col-md-12 margin-top">
									<div class="generateblock2">
										<p>客户名:${Client.clientName}</p>
										<p>客户手机:${Client.clientMobilePhone}</p>
										<p>客户座机:${Client.clientPhoneNumber}</p>
										<p>客户地址:${Client.clientContactAddress}</p>
									</div>
								</div>
							</div>
						</div>
						<!--  第一列卡片  -->

						<!--  第二列卡片  -->
						<div class="col-md-4">
							<form action="../DeliverySystem/OrderServlet?action=newOrder" method="post" role="form">
								<div class="generateblock3">
									<p>订单信息</p>
									<div class="form-group">
										<p>客户：</p>
										<div class="input-group">
											<input type="text" class="form-control" placeholder="${Client.clientName}" transparent autofocus x-webkit-speech disabled>
											</span>
										</div>
									</div>
									<div class="form-group">
										<p>收货人：</p>
										<div class="input-group">
											<input type="text" class="form-control" placeholder="${Client.clientName}" value="${Client.clientName}" id="receiverName1"  transparent autofocus x-webkit-speech disabled>
											<span class="input-group-addon btn btn-default">  
                                 			<span class="glyphicon glyphicon-option-vertical"></span>
											</span>
										</div>
									</div>
										<input type="hidden" name = "receiverName" class="form-control" value="${Client.clientName}" id="receiverName">
										<input type="hidden" name = "receiverMobilePhone" class="form-control" value="${Client.clientMobilePhone}" id="receiverMobilePhone">
										<input type="hidden" name = "receiverAddress" class="form-control"  	value="${Client.clientContactAddress}" id="receiverAddress">
										<input type="hidden" name = "receiverPostCode" class="form-control"  	value="${Client.clientPostCode}" id="receiverPostCode">
									
									<div class="form-group">
										<label class="hidden"></label>
										<p>商品信息：</p>
										<div class="input-group">
											<textarea class="form-control" rows="4" disabled="disabled" id="productInfo">
											</textarea>
											<span class="input-group-addon btn btn-default">  
                                 			<span class="glyphicon glyphicon-menu-hamburger"></span>
											</span>
										</div>
										<input type="hidden" name = "productId" value=""  id ="productId">
									</div>
									<div class="form-group">
										<label class="hidden"></label>
										<p>商品数量：</p>
										<input type="text" name = "productQuantity" placeholder="请输入商品数量"  value="" class="form-control" id ="productQuantity">
										<input type="Date" name = "requireDate"  value="" class="form-control" id ="requireDate">
										<input type="checkbox" name = "whetherInvoice" onclick="choose" value="1">是否索要发票
									</div>

									<div class="form-group">
										<label class="hidden"></label>
										<p>配送需求</p>
										<textarea class="form-control" name = "deliverRequest" rows="4" transparent autofocus x-webkit-speech></textarea>
									</div>
									<div class="form-group">
										<label class="hidden"></label>
										<p>备注</p>
										<textarea class="form-control" name = "newOrderRemark" rows="4" transparent autofocus x-webkit-speech></textarea>
									</div>

									<div class="form-group">
										<label class="hidden"></label>
										<button type="submit" class="form-control">提交订单</button>
									</div>
								</div>
							</form>
						</div>
						<!--  第二列卡片  -->

						<!--  第三列卡片  -->
						<div class="col-md-4">
							<div class="row">
								<div class="col-md-4">
									<div class="generateblock4 ">
										<div class="row">
											<div class ="col-md-12">
											<table class="table table-hover  table-condensed">
												<thead>
													<tr class="active">
														<th>商品一级分类</th>
														<th>商品二级分类</th>
														<th>商品名称</th>
														<th>商品可购买数</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${resultList}" var="product" >
														<tr class="" id="a${product.productId}"  onClick="selectProduct(this)">
															<th hidden="hidden">${product.productId}</th>
															<th>${product.firstClassId} </th>
															<th>${product.secondClassId}</th>
															<th>${product.productName}</th>
															<th>${product.allocatableQuantity}</th>
															<th hidden="hidden">${product.remark}</th>
														</tr>
													</c:forEach>
												</tbody>

											</table>
											</div>
										</div>
										
										<div class="row">
											<div class ="col-md-12">
											<form action="OrderServlet?action=selectProduct" method="post">
												<input type="text" name ="firstClassId" class="form-control" placeholder="一级分类" transparent autofocus x-webkit-speech>
												<input type="text" name ="secondClassId" class="form-control" placeholder="二级分类" transparent autofocus x-webkit-speech>
												<input type="text" name ="productName" class="form-control" placeholder="商品名称" transparent autofocus x-webkit-speech>
												<button type="submit" class="form-control">查询</button>
											</form>
											</div>
										</div>
										
									</div>
								</div>
							</div>
							
							<div class="generateblock5 margin-top">
								<div class="row">
										<div class="col-md-5" id="receiverInfo1" >
											<p>收货人：${Client.clientName}</p>
											<p>手机：${Client.clientMobilePhone}</p>
											<p>地址：${Client.clientContactAddress}</p>
											<p>邮编：${Client.clientPostCode}</p>
										</div>
										<div class="col-md-1">
											<hr style="width:2px;height:100px; "></hr>
										</div>
										<div class="col-md-6 form-group" >
											<input type="text" class ="form-control" placeholder="新联系人" id="newReceiverName"  transparent autofocus x-webkit-speech>
											<input type="text" class ="form-control" placeholder="新电话" id="newReceiverPhone"  transparent autofocus x-webkit-speech>
											<input type="text" class ="form-control" placeholder="新地址" id="newReceiverAddress" transparent autofocus x-webkit-speech>
											<input type="text" class ="form-control" placeholder="新邮编" id="newReceiverPostcode" transparent autofocus x-webkit-speech>
											<button type="submit" class="btn btn-info form-control" onclick="modifyReceiver()">修改</button>
										</div>
								</div>

							</div>

						</div>
						<!--  第三列卡片  -->
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->

	</body>
</html>