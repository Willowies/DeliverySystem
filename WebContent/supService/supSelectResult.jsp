<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>供应商管理|注册供应商</title>
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
		<script type="text/javascript" src="../js/deleteSup.js" ></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="../js/bootstrap.min.js"></script>
	<style type="text/css">
        #omit {
            overflow: hidden; /*自动隐藏文字*/
            text-overflow: ellipsis;/*文字隐藏后添加省略号*/
            white-space: nowrap;/*强制不换行*/
            width: 3em;/*不允许出现半汉字截断*/
        }
    </style>
	
	</head>
	<body>
	
	<form action="supSearchServlet" method="post" role="form">
	
		<div id="wrapper">
			<!-- Navigation -->
		<!-- 左侧导航栏 -->
			<!-- Navigation -->
			<nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
				<div class="navbar-header" style="padding-top:1px;">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		                   <span class="sr-only">Toggle navigation</span>
		                   <span class="icon-bar"></span>
		                   <span class="icon-bar"></span>
		                   <span class="icon-bar"></span>
	                </button>
					<a class="navbar-brand" href="">配送中心管理</a>
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
								<a href="supRegister.jsp"><i class="fa fa-user nav_icon"></i>新增供应商
								</a>
								<!-- /.nav-second-level -->
							</li>
								<li>
								<a href="">
									<i class="fa fa-tasks nav_icon"></i>供应商管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
								    <li><a href="supSelectResult.jsp">查询供应商信息</a></li>
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
					<strong>查询供应商信息 / Search Supplier Information</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: row;align-items:flex-start;">
					<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">查询供应商信息</p>
						
						
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">供应商名称</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									
									
									<input type="text" name = "supName" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="" >
								
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
							<p>名称</p>
							<p>地址</p>
							<p>联系人</p>
							<p>联系电话</p>
							<p>开户行</p>
							<p>银行账号</p>
							<p>传真</p>
							<p>邮编</p>
							<p>法人</p>
							<p>备注</p>
							<p>编辑</p>
						</div>
							
							<c:forEach items="${resultList}" var="sup" >
							<div class="searchItem" style = "width : 750px;">
							
							
							<P class = "p60"><input type="checkbox"  value="${sup.supId}" name="chk"  ></P>
							
							<p class="p60" id = "omit" title="${sup.supName}">${sup.supName}</p>
							<p class="p60" id = "omit" title="${sup.supAddress}">${sup.supAddress}</p>
							<p class="p60" id = "omit" title="${sup.supLinkman}">${sup.supLinkman}</p>
							<p class="p90" id = "omit" title="${sup.supPhone}">${sup.supPhone}</p>
							<p class="p60" id = "omit" title="${sup.supBankName}">${sup.supBankName}</p>
							<p class="p60" id = "omit" title="${sup.supBankAccount}">${sup.supBankAccount}</p>
							<p class="p60" id = "omit" title="${sup.supFax}">${sup.supFax}</p>
							<p class="p60" id = "omit" title="${sup.supPostcode}">${sup.supPostcode}</p>
							<p class="p60" id = "omit" title="${sup.supLegalPerson}">${sup.supLegalPerson}</p>
							<p class="p60" id = "omit" title="${sup.supRemark}">${sup.supRemark}</p>
							<p class="p60"><a href="supManageServlet?supId=${sup.supId}&action=editSup" >编辑</a></p>
							
							
							
							</div>	
								</c:forEach>		
						<div class="pageFoot">
							<ul class="pagination" style="margin:10px 0;">
						        <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
						        <c:forEach begin="1" end="${pagecount}" var="p"  >
			                    <c:if test="${p==pageNum}">
				                      <li><a href="#">${p}</a></li>
			                    </c:if>
			                    <c:if test="${p!=pageNum}">
				                <li><a href="supSearchServlet?pageNum=${p}"  >${p}</a></li>
			                    </c:if>
		                        </c:forEach>
						        <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
						      </ul>
						</div>
						
						<div style ="margin-left:80%; margin-top:-13.25%">
						     <ul class="pagination">
						     <li><span class="pagination" style="hight:50px;width:60px;" 
						      onclick="deleteSup()"  >删除</span></li>
						     </ul>
						</div> 
						
					</div>
					
				</div>
			</div>
			<!-- /#page-wrapper -->
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