<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
<script type="text/javascript" src="js/firstclass.js"></script>
<!DOCTYPE HTML>
<html>

	<head>
	<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
	
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
				$("#open-menu").addClass("active");
				$("#open-menu ul").addClass("in");
			}
		</script>
		<!-- Bootstrap Core CSS -->
		
		<link href="<%=basePath%>/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="<%=basePath%>/css/style.css" rel='stylesheet' type='text/css' />
		<link href="<%=basePath%>/css/font-awesome.css" rel="stylesheet">
		<link href="<%=basePath%>/css/dispatch-center.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="<%=basePath%>/js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="<%=basePath%>/js/bootstrap.min.js"></script>
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
						<a href="" onclick="window.location.href='../exit'" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="../images/exit.png" alt="" /></a>
					</li>
				</ul>
				<!--左侧导航栏-->
				<div class="navbar-default sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li  class="highlight-item">
								<a href="delivery/deliveryHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li id="open-menu">
								<a href=""><i class="fa fa-download nav_icon"></i>商品管理<span class="fa arrow"></span></a>
								 <ul class="nav nav-second-level">
							    	
                                    <li>
                                        <a href="firstclass/firstClassHome.jsp">商品一级分类管理</a>
                                    </li>
                                    <li>
                                        <a href="secondclass/secondClassHome.jsp">商品二级分类管理</a>
                                    </li>
                                    <li>
                                        <a href="clientService/productRegister.jsp">商品管理</a>
                                    </li>
                                </ul>  
							</li>
							<li id="open-menu">
								<a href="#"><i class="fa fa-upload nav_icon"></i>库房管理<span class="fa arrow"></span></a>
							   <ul class="nav nav-second-level">
							   	
							    	<li>
                                        <a href="warehouse1/setWarehouse.jsp">库房设置</a>
                                    </li>
                                    <li>
                                        <a href="delivery/warehouseProductSetting.jsp">库房储备设置</a>
                                    </li>
                                    <li>
                                        <a href="delivery/warehouseValue.jsp">库房量查询</a>
                                    </li>
                                    <li>
                                        <a href="delivery/warehouseOrderSearch.jsp">出入单查询</a>
                                    </li>
                                </ul>    	

								<!-- /.nav-second-level -->
							</li>
							<li id="open-menu">
								<a href=""><i class="fa fa-download nav_icon"></i>供应商管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level">
							   	
							    	<li>
                                        <a href="supService/supRegister.jsp">注册供应商</a>
                                    </li>
							    	<li>
                                        <a href="supService/supSelectResult.jsp">查询供应商</a>
                                    </li>
                                    
                                </ul>    	
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="delivery/stockManage.jsp"><i class="fa fa-briefcase nav_icon"></i>进货管理
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="delivery/returnManage.jsp"><i class="fa fa-sign-out nav_icon"></i>退货管理</span>
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="delivery/statistics.jsp"><i class="fa fa-sign-out nav_icon"></i>统计</span>
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
			<div id="page-wrapper" style="background: #f2f2f2;min-width: 800px;">
				<div class="page-navigation">商品一级分类管理</div>
				<div style="display: flex;flex-direction: row;">
					<div class="homeblock1">
						<div style="display: flex;margin: 40px 10px;">
							<img src="<%=basePath%>/images/profile.png" />
							<p style="margin: 30px 0 0 10px;">姓名：张三</p>
						</div>
						<hr style="border-top: 2px solid #999; width: 220px;" />
						<p style="margin: 40px 0 0 20px;">职位：配送中心管理员</p>
					</div>
					<div style="display: flex;flex-direction: column;">
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 20px;font-family: '微软雅黑';">添加商品一级分类</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px;">添加商品的一级分类和相应描述</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='firstclass/AddfirstClass.jsp'">前往</button>
							</div>
						</div>
						<div class="homeblock2">
							<p style="margin: 15px;font-size: 20px;font-family: '微软雅黑';">编辑商品一分类</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px;">进行商品一级分类的查询，修改和删除</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='firstclass/ChangefirstClass.jsp'">前往</button>
							</div>
						</div>
					</div>
					<div class="homeblock2">
							
							<p style="margin: 15px;font-size: 20px;font-family: '微软雅黑';">查询分类下辖商品</p>
							<p style="border-top: 2px solid #999; width: 220px;margin:10px 0 8px 15px;height: 1px" />
							<p style="margin: 0 0 0 15px;">用于查询一级分类下辖的所有商品</p>
							<div class="buttonblock">
								<button type="submit" onclick="window.location.href='firstclass/firstClassSerachProducts.jsp'">前往</button>
							</div>
						</div>
				</div>

			</div>
			
		<!-- /#wrapper -->
		<!-- Nav CSS -->S
		<link href="<%=basePath%>/css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="<%=basePath%>/js/metisMenu.min.js"></script>
		<script src="<%=basePath%>/js/custom.js"></script>
	</body>

</html>