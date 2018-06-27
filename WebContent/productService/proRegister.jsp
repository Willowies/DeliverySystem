<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>库房管理|增加商品</title>
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
					<a class="navbar-brand" href="">库房管理</a>
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
								<a href=".jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li class="highlight-item">
								<a href="proRegister.jsp"><i class="fa fa-user nav_icon"></i>新增商品
								</a>
								<!-- /.nav-second-level -->
							</li>
								<li>
								<a href="">
									<i class="fa fa-tasks nav_icon"></i>商品管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
								    <li><a href="proSelectResult.jsp">查询商品信息</a></li>
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
					<strong>新增商品 / Add New Product</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: row;align-items:flex-start; 
				margin-left: 25%; " >
				<!--/# 搜索 -->
					<!-- 搜索结果，隐藏 -->
					<div class="searchResult" style =" height:1050px;">
											<!--输入信息 -->
					<div class="panel-body">
						<form role="form" class="form-horizontal" action="supManageServlet?action=registerSup"  method="post" >
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">商品代码</label>
								<div class="col-md-8">
									<div class="input-group">
									<input type="text" name="productCode" class="form-control1" placeholder="必填" required="required"
									style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">商品名称</label>
								<div class="col-md-8">
									<div class="input-group">
										 <input type="text" name="productCode" class="form-control1" placeholder="必填" required="required"
										 style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">商品分类</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									<select name = "proClassId" class="form-control1" style = "width:450px;margin-left:0%" >
									<option value = "1">一级商品</option>
									<option value = "2"> 二级商品</option>
									</select>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">计量单位</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									<input type="text" name="productUnit" class="form-control1" placeholder="可选" required="required"
									style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">原&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
										<input type="number" name="productPrice"class="form-control1" placeholder="必填" required="required"
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">商品折扣</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
										<input type="number" name="productDiscount" class="form-control1" placeholder="必填" required="required"
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">成&nbsp;&nbsp;本&nbsp;&nbsp;价</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
										<input type="number" name="productCost" class="form-control1" placeholder="必填" required="required"
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									
										<input type="text" name="productModel"class="form-control1" placeholder="可选" required="required"
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">供&nbsp;&nbsp;应&nbsp;&nbsp;商</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
										<input type="text" name="manufacturer" class="form-control1" placeholder="可选" required="required"
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">厂&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									
										<input type="text" name="supId"class="form-control1" placeholder="可选" required="required"
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">保&nbsp;&nbsp;质&nbsp;&nbsp;期</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									
										<input type="date" name="expirationDate" class="form-control1" placeholder="可选" required="required" 
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">可否退货</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									
										<input type="checkbox" name="returnAble" 
										style = "margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">可否换货</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									
										<input type="checkbox" name="returnAble"  
										style = "margin-left:0%">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" style = "width:100px ;">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
								<div class="col-md-8">
									<div class="input-group input-icon right">
									
										<input type="text" name="remark"class="form-control1" placeholder="可选" required="required" 
										style = "width:450px;margin-left:0%">
									</div>
								</div>
							</div>
							
							
									<!-- 下方提交模块 -->
										<div class="col-sm-8 col-sm-offset-2" style ="margin-left:30%;">
											<button type ="submit" class="btn-success btn" >Submit</button>
											<button type = "reset" class="btn-default btn">Cancel</button>
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