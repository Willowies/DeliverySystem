<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>客户中心|查询客户信息</title>
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
		<link href="../css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom CSS -->
		<link href="../css/style.css" rel='stylesheet' type='text/css' />
		<link href="../css/font-awesome.css" rel="stylesheet">
		<link href="../css/client-supplier-center.css" rel="stylesheet">
		<link href="../css/bootstrap-select.min.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="../js/jquery.min.js"></script>
		<script src="../js/bootstrap-select.min.js"></script>
		<script type="text/javascript" src="../js/deleteClient.js" ></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="../js/bootstrap.min.js"></script>
		
		<!-- 省略较长的输出 -->
		<style type="text/css">
        #omit {
            overflow: hidden; /*自动隐藏文字*/
            text-overflow: ellipsis;/*文字隐藏后添加省略号*/
            white-space: nowrap;/*强制不换行*/
            width: 5em;/*不允许出现半汉字截断*/
        }
    </style>
		
		
	</head>
	<body>
	
	<form action="clientSearchServlet" method="post" role="form">
	
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
								<a href="substationHome.jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li class="highlight-item">
								<a href="modifyOrderStatus.jsp"><i class="fa fa-edit nav_icon"></i>生成订单</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="">
									<i class="fa fa-tasks nav_icon"></i>客户管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
								    <li><a href="registerClient.jsp">新增客户</a></li>
								    <li><a href="selectResult.jsp">查询客户信息</a></li>
								</ul>
								
								<!-- /.nav-second-level -->
							</li>
							

						</ul>
					</div>
					<!-- /.sidebar-collapse -->
				</div>
				<!-- /.navbar-static-side -->
			</nav>
			<!-- #左侧导航栏 -->
			
			
			<!--页面-->
			<div id="page-wrapper" style="background: #f2f2f2;min-width: 800px;  min-height: 299px;">
				<div class="page-navigation">
					<strong>查询客户信息 / Search Client Information</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: row;align-items:flex-start;">
					<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">查询客户信息</p>
						
						
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="text" name = "clientName" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
							</div>
							
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">身份证号</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="text" name = "clientIc" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
							</div>
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">移动电话</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="text" name = "clientMobilePhone" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
							</div>
		
						
						<div class="buttonblock">
							<button type="submit" onclick="">搜索</button>
						</div>
					</div>
				<!--/# 搜索 -->
					<!-- 搜索结果，隐藏 -->
					<div class="searchResult" style = "width : 800px;" >
						<div class="searchResultNav" style = "width : 750px;">
						    <p style="width:30px ;margin-left:0%">选择</p>
							<p style="width:30px ;margin-left:0%">姓名</p>
							<p style="width:60px ;margin-left:5%">身份证号</p>
							<p>工作单位</p>
							<p>座机号码</p>
							<p>移动电话</p>
							<p>联系地址</p>
							<p>邮编</p>
							<p>Email</p>
							<p>编辑</p>
						</div>
							
							<c:forEach items="${resultList}" var="client" >
							<div class="searchItem" style = "width : 750px;">
							
							
							<P class = "p60"><input type="checkbox"  value="${client.clientId}" name="chk"  ></P>
							
							<p class="p60" id = "omit" title="${client.clientName}">${client.clientName}</p>
							<p class="p60" id = "omit" title="${client.clientIc}">${client.clientIc}</p>
							<p class="p60" id = "omit" title="${client.clientWorkPlace}">${client.clientWorkPlace}</p>
							<p class="p90" id = "omit" title="${client.clientPhoneNumber}">${client.clientPhoneNumber}</p>
							<p class="p60" id = "omit" title="${client.clientPhoneNumber}">${client.clientMobilePhone}</p>
							<p class="p60" id = "omit" title="${client.clientPhoneNumber}">${client.clientContactAddress}</p>
							<p class="p60" id = "omit" title="${client.clientPhoneNumber}">${client.clientPostcode}</p>
							<p class="p60" id = "omit" title="${client.clientEmail}">${client.clientEmail}</p>
							<p class="p60">
							<a href="clientManageServlet?clientId=${client.clientId}&action=editClient" >编辑</a></p>

							</div>	
								</c:forEach>	
						<!--  
						<div class="pageFoot">
							<ul class="pagination" style="margin:10px 0;">
						        <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
						        <c:forEach  begin="1" end="${pageCount}" var="p">
									<c:if test="${p==pageNum}">
										<li><a href="#">${p}</a></li>
									</c:if>
									<c:if test="${p!=pageNum}">
										<li><a href="clientSearchServlet?pageNum=${p}" >${p}</a></li>
									</c:if>
								</c:forEach>
						        <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
						      </ul>
						</div>
						-->			
						<div class="pageFoot" >
							<ul class="pagination" style="margin:10px 0;">
						        <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
						        <c:forEach begin="1" end="${pagecount}" var="p"  >
			                    <c:if test="${p==pageNum}">
				                      <li><a href="#">${p}</a></li>
			                    </c:if>
			                    <c:if test="${p!=pageNum}">
				                <li><a href="clientSearchServlet?pageNum=${p}" >${p}</a></li>
			                    </c:if>
		                        </c:forEach>
						        <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
						      </ul>
						</div>
						 
						<!--按钮注释  
						<div style ="margin-left:50%; margin-top:-10.5%" >
						<button  class="pagination" style="hight:50px;width:50px;"
						      type="button" onclick="deleteClient()"  >删除</button>
						</div>    
						-->
						
						<div style ="margin-left:80%; margin-top:-13.25%">
						     <ul class="pagination">
						     <li><span class="pagination" style="hight:50px;width:60px;" 
						      onclick="deleteClient()"  >删除</span></li>
						     </ul>
						</div>  
					
					
				</div>
			</div>
			<!-- /#page-wrapper -->
		</div>
		</div>
		
</form>		
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		<link href="../css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="../js/metisMenu.min.js"></script>
		<script src="../js/custom.js"></script>
	</body>
</html>