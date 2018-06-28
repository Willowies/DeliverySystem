<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
 <%@ page import="java.util.List"%>
  <%@ page import="com.neuedu.model.po.FirstClass"%>
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
			window.onload = function(){
				$("#side-menu li").removeClass("active");
				$("#side-menu li ul").removeClass("in");
				$("#open-menu").addClass("active");
				$("#open-menu ul").addClass("in");
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
			<div id="page-wrapper" style="background: #f2f2f2;display: flex;">
				<form action="./dispatchManageServlet?action=searchOrder" method="post">
					<div class="page-navigation">调度商品 / Dispatch goods</div>
					<div class="searchblock2">
						<p1>搜索订单</p1>
						<p>生成日期</p>
						<input type="date" name="createDate" id="createDate">
						<p>要求完成日期</p>
						<input type="date" name="requireDate" id="requireDate">
						<p>订单类型</p>
						<select name="orderType">
							<option value="1">新订</option>
							<option value="2">退货</option>
						</select>
						<div style="margin: 5px 0;display: flex;">
							<input type="checkbox" name="dispatchOrNot" style="width: 15px;margin: 0 10px;">
							<p2 style="margin: 3px;font-size: 12px;">只显示未调度</p2>
						</div>
						<div class="buttonblock">
							<button type="submit">搜索</button>
						</div>
					</div>
				</form>
				<div class="searchResult" style = "width : 1000px;" >
						<div class="searchResultNav" style = "width : 950px;">
						   
							<p>商品代码</p>
							<p>商品名称</p>
							<p>一级分类</p>
							<p>二级分类</p>
							<p>计量单位</p>
							<p>原价</p>
							<p>折扣</p>
							<p>成本价</p>
							<p>型号</p>
							<p>供应商</p>
							<p>厂商</p>
							<p>保质期</p>
							<p>可否退货</p>
							<p>可否换货</p>
						</div>
							
							<c:forEach items="${productresultlist}" var="product" >
							<div class="searchItem" style = "width : 750px;">
							
							<p class="p60">${sup.supName}</p>
							<p class="p60">${sup.supAddress}</p>
							<p class="p60">${sup.supLinkman}</p>
							<p class="p90">${sup.supPhone}</p>
							<p class="p60">${sup.supBankName}</p>
							<p class="p60">${sup.supBankAccount}</p>
							<p class="p60">${sup.supFax}</p>
							<p class="p60">${sup.supPostcode}</p>
							<p class="p60">${sup.supLegalPerson}</p>
							<p class="p60">${sup.supRemark}</p>
						
							</div>	
							</c:forEach>		
						<button type="submit" onclick="window.location.href='firstclass/firstClassSerachProducts.jsp'">返回搜索</button>
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