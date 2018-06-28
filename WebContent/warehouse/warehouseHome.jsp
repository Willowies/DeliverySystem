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
		<title>库房中心|主页</title>
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
							<li class="highlight-item">
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
				<div style="display: flex;flex-direction: row;">
					<div class="homeblock1">
						<div style="display: flex;margin: 40px 10px;">
							<img src="images/profile.png" />
							<p style="margin: 30px 0 0 10px;">姓名：${employee.employeeName}</p>
						</div>
						<hr style="border-top: 2px solid #999; width: 220px;" />
						<p style="margin: 40px 0 0 20px;">职位：库房管理员</p>
					</div>
					<div style="display: flex;flex-direction: column;">
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">中心库房购货入库</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">将供货商送来的货物安排入库。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/centerIn.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">中心库房调拨出库</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">货物到中心库房后，安排出库。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/centerOut.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">查询出库信息</p>
							<p style="margin: 0 0 0 15px; width:254px;"></p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/selectCenterOut.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">查询分发信息</p>
							<p style="margin: 0 0 0 15px; width:254px;"></p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/selectDistribution.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">分站库房调拨入库</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">货物到分站库房安排入库。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/subWarehouseIn.jsp'">前往</button>
							</div>
						</div>
					</div>
					<div style="display: flex;flex-direction: column;">
						<div class="homeblock2" style="margin-left:20px;">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">领货</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">配送员前来分站领货。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/receive.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2" style="margin-left:20px;">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">退货登记</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">退货流：将退货情况进行记录。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/returnRecord.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2" style="margin-left:20px;">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">分站退货出库</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">退货流：货物从分站出库</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/subReturnOut.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2" style="margin-left:20px;">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">中心库房退货入库</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">退货流：货物退入中心库房。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/centerReturnIn.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2" style="margin-left:20px;">
							<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">中心库房退货出库</p>
							<p style="border-top: 2px solid #999; width: 254px;margin:0 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px; width:254px;">退货流：货物从中心库房出库。</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='warehouse/centerReturnOut.jsp'">前往</button>
							</div>
						</div>
						
					</div>
					
				</div>

			</div>
			<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		
		<!-- Metis Menu Plugin JavaScript -->
		
	</body>

</html>