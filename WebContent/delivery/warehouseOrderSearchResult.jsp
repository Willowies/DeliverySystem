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
				$("#open-menu").addClass("active");
				$("#open-menu ul").addClass("in");
			}
		</script>
		<!-- Bootstrap Core CSS -->
		<link href="./css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="./css/style.css" rel='stylesheet' type='text/css' />
		<link href="./css/font-awesome.css" rel="stylesheet">
		<link href="./css/delivery-center-stock-return.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="./js/jquery.min.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="./js/bootstrap.min.js"></script>
		<script type="text/javascript">
			function date(){
            var myDate = new Date();  //获取当前时间对象，精确到当前的时、分、秒
            var this_time=$('input[name="start"]').val();//获取用户选择后的时间值

            var this_datetime=new Date(this_time);//获取用户选择的时间，生成时间对象  具体时间为时间8:00:00
            var year = myDate.getFullYear();    //获取当前时间的年份 格式xxxx 如：2016
            var month =myDate.getMonth()+1;     //获取当前时间的月份 格式1-9月为x， 10-12月为xx 如：11
            var date = myDate.getDate();        //获取当前时间的日期 格式同月份 如11
             myDate=new Date(year+'-'+month+'-'+date);  //获取根据上述时间生成的时间对象 具体时间为0:00:00  
            var now=new Date(year+'-'+month+'-'+date+' 8:00:00'); 
            myDate.setDate(now.getDate()-7); //设置now对象相应日期的七天前日期 具体时间为0:00:00
            if(this_datetime>now){    //时间对象可以直接比较大小
                alert('时间需选择今天以前');
                $('input[name="start"]').val('');   
            };
         };
         
         function dateEnd(){
            var myDate = new Date();  //获取当前时间对象，精确到当前的时、分、秒
            var this_time=$('input[name="start"]').val();//获取用户选择后的时间值
            var end_time=$('input[name="end"]').val();
            if(this_time == null || this_time == ""){
            	alert('先选择开始时间');
            	$('input[name="end"]').val('');   
            }else{
            	var this_datetime=new Date(end_time);//获取用户选择的时间，生成时间对象  具体时间为时间8:00:00
          	 	var start_datetime = new Date(this_time);
          	 	
          	 	var year = myDate.getFullYear();    //获取当前时间的年份 格式xxxx 如：2016
            var month =myDate.getMonth()+1;     //获取当前时间的月份 格式1-9月为x， 10-12月为xx 如：11
            var date = myDate.getDate();        //获取当前时间的日期 格式同月份 如11
             myDate=new Date(year+'-'+month+'-'+date);  //获取根据上述时间生成的时间对象 具体时间为0:00:00  
            var now=new Date(year+'-'+month+'-'+date+' 8:00:00'); 
          	 	
           	 if(this_datetime<start_datetime || this_datetime>now){    //时间对象可以直接比较大小
           	     alert('时间需选择开始时间之后,并在当前日期之前');
           	     $('input[name="end"]').val('');   
           	 };
            }

           
         };
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
								<a href=""><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li>
								<a href=""><i class="fa fa-download nav_icon"></i>商品管理<span class="fa arrow"></span></a>
								 <ul class="nav nav-second-level">
							    	
                                    <li>
                                        <a href="">商品一级分类管理</a>
                                    </li>
                                    <li>
                                        <a href="">商品二级分类管理</a>
                                    </li>
                                    <li>
                                        <a href="">商品管理</a>
                                    </li>
                                </ul>  
							</li>
							<li id="open-menu">
								<a href="#"><i class="fa fa-upload nav_icon"></i>库房管理<span class="fa arrow"></span></a>
							   <ul class="nav nav-second-level">
							   	
							    	<li>
                                        <a href="">库房设置</a>
                                    </li>
                                    <li>
                                        <a href="./delivery/warehouseProductSetting.jsp">库房储备设置</a>
                                    </li>
                                    <li>
                                        <a href="./delivery/warehouseValue.jsp">库房量查询</a>
                                    </li>
                                    <li class="highlight-item">
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
                                        <a href="">供应商管理</a>
                                    </li>
                                    <li>
                                        <a href="">注册供应商</a>
                                    </li>
                                    <li>
                                        <a href="">编辑供应商</a>
                                    </li>
                                    
                                </ul>    	
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="./delivery/stockManage.jsp"><i class="fa fa-briefcase nav_icon"></i>进货管理
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
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
				<form action="./deliveryPartServlet?action=searchOrder" method="post">
					<div class="page-navigation">出入单查询  / Warehouse order </div>
					<div class="searchblock4" style="height: 280px;width: 240px;">
						<p1>出入单查询</p1>
						<p>商品名称</p>
						<input  type="text" name="productName" style="width: 200px;">
						<p>开始日期</p>
						<input  type="date" name="start" id="ipt1" style="width: 200px;" required="required" onblur="date()">
						<p>结束日期</p>
						<input  type="date" name="end" id="ipt2" style="width: 200px;" required="required" onblur="dateEnd()">
						<div class="buttonblock">
							<button type="submit" style="margin-top: 20px;" onclick="checkform()">搜索</button>
						</div>
					</div>
				</form>
				<div class="resultblock2">
					<div class="title">
						<p3>类型</p3>
						<p3>单号</p3>
						<p3>商品编号</p3>
						<p3>商品名称</p3>
						<p3>商品单价</p3>
						<p3>商品数量</p3>
						<p3>商品金额</p3>
						<p3>日期</p3>
					</div>
					<div class="result-content">
						<%
							List l = (List)request.getAttribute("orderList");
							if(!l.isEmpty()){
						%>
						<c:forEach items="${orderList}" var="order">
							<div class="result-item">
								<p3 style="margin-left: 35px;width: 80px;">${order.type}</p3>
								<p3 style="margin-left: 10px;width: 60px;">${order.orderId}</p3>
								<p3 style="margin-left: 10px;width: 60px;">${order.productCode}</p3>
								<p3 style="margin-left: 20px;width: 100px;">${order.productName}</p3>
								<p3 style="margin-left: 20px;width: 80px;">${order.price}</p3>
								<p3 style="margin-left: 20px;width: 80px;">${order.quantity}</p3>
								<p3 style="margin-left: 20px;width: 80px;">${order.total}</p3>
								<p3 style="margin-left: 10px;"><fmt:formatDate value="${order.date}" type="date"/></p3>
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
					<div class="result-foot">
						<div class="page-turner">
							<ul class="pagination pagination-lg">
								<li <c:if test="${page eq 1}">class="disabled "</c:if>>
									<a href="<c:if test="${page != 1}">./deliveryPartServlet?action=searchOrderByPage&page=${page-1}</c:if>"><i class="fa fa-angle-left "></i></a>
								</li>
								<c:forEach begin="1" end="${warehouseOrderPageNum}" var="p"  >
									<c:if test="${p==page}">
										<li class="active ">
											<a href=" ">${p}</a>
										</li>
									</c:if>
									<c:if test="${p!=page}">
										<li>
											<a href="./deliveryPartServlet?action=searchOrderByPage&page=${p}"  >${p}</a>
										</li>
									</c:if>
									&nbsp;&nbsp;
								</c:forEach>
								<li <c:if test="${page eq warehouseOrderPageNum}">class="disabled "</c:if>>
									<a href="<c:if test="${page < warehouseOrderPageNum}">./deliveryPartServlet?action=searchOrderByPage&page=${page+1}</c:if>"><i class="fa fa-angle-right "></i></a>
								</li>
							</ul>
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