<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<%
			String path =request.getContextPath();
	      	String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	     %>
	    <base href=<%=basePath%>>
	
		<title>库房中心|中心库房购货入库</title>
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
		<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<link href="css/font-awesome.css" rel="stylesheet">
		<link href="css/warehouse.css" rel="stylesheet">
		<link href="css/custom.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
		<script src="js/metisMenu.min.js"></script>
		<script src="js/custom.js"></script>
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
							<li  class="highlight-item">
								<a href="warehouse/centerIn.jsp"><i class="fa fa-download nav_icon"></i>中心库房购货入库</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-upload nav_icon"></i>中心库房调拨出库<span class="fa arrow"></span>
								</a>
							    <ul class="nav nav-second-level">
							    	<li>
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
					<strong>中心库房购货入库 / Central warehouse purchases goods</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: column;align-items:flex-start;">
					<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<form action="warehouseManageServlet?action=selectPurchaseIn" method="post" role="form">
							
								<p style="float:left; margin: 17px 20px 5px 80px;width= 20px;font-size: 15px;">购货单号</p>
								<div style="float:left;margin: 11px 20px 0 40px; width:254px;">
									<input type="text" name="purchaseInId" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
								<button type="submit" onclick="" class="buttonB" style=" margin:10px 0 0 40px; float:left">搜索</button>
							
						</form>
						
					</div>
				<!--/# 搜索 -->
					<!-- 搜索结果，隐藏 -->
					<div class="searchResult">
						<div class="searchResultNav">
							<p class="p70">购货单号</p>
							<p class="p90">商品名称</p>
							<p class="p90">应入库数量</p>
							<p class="p90">实入库数量</p>
							<p class="p90">金额</p>
							<p class="p90">实入库日期</p>
							<p class="p70">备注</p>
							<p class="p70"></p>
						</div>
						<form action="warehouseManageServlet?action=editCenter" method="post">
						<div class="searchItem">
						    <p class="p70">${purchaseId}</p>
							<p class="p90">${productName}</p>
							<p class="p90">${expectedQuantity}</p>
							<p class="p90">${acturalQuantity}</p>
							<p class="p90">${productAmount}</p>
							<p class="p90" name="remark">${inDate}</p>
							<p class="p70" name="remark">${remark}</p>
							<p class="p70"><button type="submit" style=" background: #aaddee;color: #31708f;box-shadow: none;border: none;border-radius: 5px;">编辑</button></td></p>
						</div>
						</form>
					</div>
				<form action="warehouseManageServlet?action=centerIn" method="post" >
					<button type="submit" onclick="" class="buttonB" style=" margin:90px 0 0 580px; float:left">入库</button>
				</form>
				</div>
				
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
			<!-- /#page-wrapper -->
		</div>
		
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		
		<!-- Metis Menu Plugin JavaScript -->
		
			
	</body>

</html>