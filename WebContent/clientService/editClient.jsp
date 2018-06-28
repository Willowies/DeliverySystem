<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>客户管理|修改客户信息</title>
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
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="../js/bootstrap.min.js"></script>
	</head>
	<body>
		<div id="wrapper">
		
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
					<strong>修改客户信息 / Edit Client Information</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: row;align-items:flex-start; 
				margin-left: 25%; " >
				<!--/# 搜索 -->
					<!-- 搜索结果，隐藏 -->
					<div class="searchResult" style =" height:650px;">
											<!--输入信息 -->
					<div class="panel-body">
						<form role="form" class="form-horizontal" action="clientManageServlet?action=updateClient"  method="post" >
						
						<input type="hidden" name="clientId" value="${editClient.clientId}"  >
							
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:80px ;">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
								<div class="col-md-8">
									<div class="input-group">
									<input type="text" name="clientName" value="${editClient.clientName}"  class="form-control1" placeholder="必填"
									style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:80px ;">身份证号</label>
								<div class="col-md-8">
									<div class="input-group">
										 <input type="text" name="clientIc" value="${editClient.clientIc}" class="form-control1" placeholder="必填"
										 style = "width:450px;margin-left:0%" maxlength="11">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:80px ;">工作单位</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									<input type="text" name="clientWorkPlace" value="${editClient.clientWorkPlace}" class="form-control1" placeholder="可选"
									style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:80px ;">座机号码</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									<input type="text" name="clientPhoneNumber" value="${editClient.clientPhoneNumber}" class="form-control1" placeholder="可选"
									style = "width:450px;margin-left:0%" maxlength="8">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:80px ;">移动号码</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
										<input type="text" name="clientMobilePhone" value="${editClient.clientMobilePhone}" class="form-control1" placeholder="必填"
										style = "width:450px;margin-left:0%" maxlength="11">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:80px ;">联系地址</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
										<input type="text" name="clientContactAddress" value="${editClient.clientContactAddress}" class="form-control1" placeholder="必填"
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:80px ;">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									
										<input type="number" name="clientPostcode" value="${editClient.clientPostcode}" class="form-control1" placeholder="可选"
										style = "width:450px;margin-left:0%" maxlength="6">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:80px ;">&nbsp;&nbsp;&nbsp;email&nbsp;&nbsp;&nbsp;</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
										<input type="email" name="clientEmail" value="${editClient.clientEmail}" class="form-control1"
											placeholder="可选" style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
									<!-- 下方提交模块 -->
										<div class="col-sm-8 col-sm-offset-2" style ="margin-left:40%;">
											<button type ="submit" class="btn-success btn" >修改</button>
										</div>
						</form>
					</div>
					</div>
					
				</div>
			</div>
			<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->
		<!-- Nav CSS -->
		<link href="../css/custom.css" rel="stylesheet">
		<!-- Metis Menu Plugin JavaScript -->
		<script src="../js/metisMenu.min.js"></script>
		<script src="../js/custom.js"></script>
	</body>
</html>