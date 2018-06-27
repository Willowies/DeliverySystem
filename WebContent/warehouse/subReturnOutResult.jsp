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
		<link href="./css/warehouse-center-return.css" rel="stylesheet">
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
								<a href="./warehouse/warehouseHome.jsp"><i class="fa fa-home fa-fw nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="./warehouse/centerIn.jsp"><i class="fa fa-download nav_icon"></i>中心库房购货入库</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-upload nav_icon"></i>中心库房调拨出库<span class="fa arrow"></span>
								</a>
							    <ul class="nav nav-second-level">
							    	<li>
                                        <a href="./warehouse/transferCenterOut.jsp">调拨出库</a>
                                    </li>
                                    <li>
                                        <a href="./warehouse/selectCenterOut.jsp">查询出库信息</a>
                                    </li>
                                    <li>
                                        <a href="./warehouse/selectDistribution">查询分发信息</a>
                                    </li>
                                </ul>    	

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="./warehouse/subWarehouseIn.jsp"><i class="fa fa-download nav_icon"></i>分站库房调拨入库
								</a>

								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="./warehouse/receive.jsp"><i class="fa fa-briefcase nav_icon"></i>领货
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
                                    <li class="highlight-item">
                                        <a href="./warehouse/subReturnOut.jsp">分站退货出库</a>
                                    </li>
                                    <li>
                                        <a href="./warehouse/centerReturnIn.jsp">中心库房退货入库</a>
                                    </li>
                                    <li>
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
				<form action="./warehouseReturnManageServlet?action=searchReturnProduct" method="post">
					<div class="page-navigation">分站退货出库  / Return</div>
					<div class="searchblock2" style="height: 250px;">
						<p1>退货查询</p1>
						<p>开始日期</p>
						<input  type="date" name="start" id="ipt1" style="width: 200px;" required="required" onblur="date()">
						<p>结束日期</p>
						<input  type="date" name="end" id="ipt2" style="width: 200px;" required="required" onblur="dateEnd()">
						<div class="buttonblock">
							<button type="submit" style="margin-top: 20px;" onclick="checkform()">搜索</button>
						</div>
					</div>
				</form>
				<div class="resultblock">
					
					<div class="title" style="margin-left: 80px;">
						<p3>商品名称</p3>
						<p3>商品编号</p3>
						<p3>商品数量</p3>
						<p3>计量单位</p3>
						<p3>退货日期</p3>
					</div>
					<form  action="./warehouseReturnManageServlet?action=subReturnOut" method="post"  style="flex: 1;display: flex;flex-direction: column;">
					<div class="result-content">
						<%
							List l = (List<SubReturnRecord>)request.getAttribute("orderList");
							if(!l.isEmpty()){
						%>
						<c:forEach items="${orderList}" var="order">
							<div class="result-item">
								<input type="radio" name="check" style="float: left;width: 20px;" value="${order.productId}_${order.productQuantity}_<fmt:formatDate value="${order.start}" type="date"/>_<fmt:formatDate value="${order.end}" type="date"/>"/>
								<p3 style="margin-left: 50px;width: 100px;">${order.productName}</p3>
								<p3 style="margin-left: 10px;width: 100px;">${order.productCode}</p3>
								<p3 style="margin-left: 20px;width: 80px;">${order.productQuantity}</p3>
								<p3 style="margin-left: 40px;width: 60px;">${order.productUnit}</p3>
								<p3 style="margin-left: 30px;"><fmt:formatDate value="${order.returnDate}" type="date"/></p3>
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
						<div class="page-turner">
							<ul class="pagination pagination-lg">
								<li <c:if test="${page eq 1}">class="disabled "</c:if>>
									<a href="<c:if test="${page != 1}">./warehouseReturnManageServlet?action=searchReturnProductByPage&page=${page-1}</c:if>"><i class="fa fa-angle-left "></i></a>
								</li>
								<c:forEach begin="1" end="${subReturnPageNum}" var="p"  >
									<c:if test="${p==page}">
										<li class="active ">
											<a href=" ">${p}</a>
										</li>
									</c:if>
									<c:if test="${p!=page}">
										<li>
											<a href="./warehouseReturnManageServlet?action=searchReturnProductByPage&page=${p}"  >${p}</a>
										</li>
									</c:if>
									&nbsp;&nbsp;
								</c:forEach>
								<li <c:if test="${page eq subReturnPageNum}">class="disabled "</c:if>>
									<a href="<c:if test="${page < subReturnPageNum}">./warehouseReturnManageServlet?action=searchReturnProductByPage&page=${page+1}</c:if>"><i class="fa fa-angle-right "></i></a>
								</li>
							</ul>
						</div>
						<div class="operate-button" style="margin: 0 auto;">
							<%
							if(!l.isEmpty()){
							%>
							<button class="b" type="submit">确认分站退货</button>
							<%}%>
						</div>
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