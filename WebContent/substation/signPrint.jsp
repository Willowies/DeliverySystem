<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>分站管理|打印商品签收单</title>
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
		<link href="../css/substation-center.css" rel="stylesheet">
		<link href="../css/bootstrap-select.min.css" rel="stylesheet">
		<!-- jQuery -->
		<script src="../js/jquery.min.js"></script>
		<script src="../js/bootstrap-select.min.js"></script>
		<!-- Custom JS -->
		<script src="../js/popup.js"></script>
		<!----webfonts--->
		<!---//webfonts--->
		<!-- Bootstrap Core JavaScript -->
		<script src="../js/bootstrap.min.js"></script>
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
					<a class="navbar-brand" href="">分站中心</a>
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
							<li>
								<a href="substationHome.jsp"><i class="fa fa-home nav_icon"></i>主页</a>
							</li>
							<li>
								<a href="searchWorkOrder.jsp"><i class="fa fa-search nav_icon"></i>查询任务单
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="workAssign.jsp"><i class="fa fa-tasks nav_icon"></i>任务分配</a>
								<!-- /.nav-second-level -->
							</li>
							<li class="highlight-item">
								<a href="signPrint.jsp"><i class="fa fa-print nav_icon"></i>打印商品签收单
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="feedbackRecord.jsp"><i class="fa fa-edit nav_icon"></i>回执录入
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="paymentQuery.jsp"><i class="fa fa-money nav_icon"></i>缴款查询
								</a>
								<!-- /.nav-second-level -->
							</li>
							<li>
								<a href="">
									<i class="fa fa-tag nav_icon"></i>发票管理<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-second-level collapse" aria-expanded="false" style="height:0px;">
									<li><a href=".jsp">发票领用</a></li>
									<li><a href=".jsp">发票作废</a></li>
									<li><a href=".jsp">发票查询</a></li>
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
					<strong>打印商品签收单 / SignPrint</strong>
				</div>
				<!-- 搜索 -->
				<div style="display: flex;flex-direction: row;align-items:flex-start;">
					<div class="homeblock3" style="margin-left:auto; margin-right:auto;">
						<p style="margin: 15px;font-size: 18px;font-family: '微软雅黑';">搜索任务单</p>
						
						<form action="" method="post" role="form">
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">配送员代号</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="text" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
							</div>
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">要求完成日期</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<input type="date" class="form-control1" style="height: 34px;border-radius: 4px; line-height:18px;" placeholder="">
								</div>
							</div>
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">任务类型</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<select class="selectpicker" style="width: 254px;" name="selectType" id="site_id">
										<option value="">送货收款</option>
										<option>送货</option>
										<option>收款</option>
										<option>换货</option>
										<option>退货</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<p style="margin: 15px 0 5px 15px;font-size: 15px; width:254px;">任务状态</p>
								<div style="margin: 0 0 0 15px; width:254px;">
									<select class="selectpicker" style="width: 254px;" name="selectStatus" id="site_id">
										<option>已调度</option>
										<option>可分配</option>
										<option>已分配</option>
										<option>已领货</option>
										<option>已完成</option>
										<option>失败</option>
									</select>
								</div>
							</div>
							<div class="buttonblock">
								<button type="submit" onclick="">搜索</button>
							</div>
						</form>
						
					</div>
				<!--/# 搜索 -->
					<!-- 搜索结果，隐藏 -->
					<div class="searchResult" style="height:475px;">
						<div class="searchResultNav">
							<p>任务单号</p>
							<p>任务类型</p>
							<p>任务状态</p>
							<p>要求完成日期</p>
							<p>分站代号</p>
						</div>
						<div class="searchItem">
							<button class="workButton" onClick="showWorkOrder()">1</button>
							<div id="showDivW" style="display:none; position: fixed; top: 0; right: 0; bottom: 0; left: 0; z-index:1050; overflow-y: auto !important;">
								<div class="homeblock5" style="margin-left:auto; margin-right:auto;">
									<div style="padding:30px 45px 10px 45px;">
										<button type="button" class="close" onClick="closebuttonW()">×</button>
										<p class="title">详细信息</p>
									</div>
									<div class="bodyStyle" style="padding-top:0;">
										<div class="infoBlock">
											<p style="width:510px; font-weight:400; margin-left:33.75px;">任务信息</p>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>任务单号</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>任务类型</p>
													<span class="infoBorder"></span>
												</div>
											</div>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>任务状态</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>要求完成日期</p>
													<span class="infoBorder"></span>
												</div>
											</div>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>服务资金信息</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>备注</p>
													<span class="infoBorder"></span>
												</div>
											</div>
										</div>
										<div class="infoBlock">
											<p style="width:510px; font-weight:400; margin-left:33.75px;">服务员信息</p>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>分站代号</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>配送员代号</p>
													<span class="infoBorder"></span>
												</div>
											</div>
										</div>
										<div class="infoBlock">
											<p style="width:510px; font-weight:400; margin-left:33.75px;">服务对象信息</p>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>姓名</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>电话</p>
													<span class="infoBorder"></span>
												</div>
											</div>
											<div class="infoRowColumnlong">
												<p>地址</p>
												<span class="infoBorderlong"></span>
											</div>
										</div>
										<div class="infoBlock">
											<p style="width:510px; font-weight:400; margin-left:33.75px;">服务内容信息</p>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>商品代码</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>商品数量</p>
													<span class="infoBorder"></span>
												</div>
											</div>
											<div class="infoRowColumnlong">
												<p>计量单位</p>
												<span class="infoBorder"></span>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" onClick="closebuttonW()" style="width: 76px; margin-right:30px;">关闭</button>
									</div>
								</div>
							</div>
							<p class="p60">送货收款</p>
							<p class="p60">已分配</p>
							<p class="p90">2018/6/25</p>
							<button class="workButton" onClick="printSign()">打印</button>
							<div id="cover" style="background: #000; position:absolute; left: 0px; top: 0px; width:100%; filter: alpha(opacity=50); opacity: 0.5; display:none; z-index:1000;"></div>
							<div id="showDiv" style="display:none; position: fixed; top: 0; right: 0; bottom: 0; left: 0; z-index:1050; overflow-y: auto !important;">
								<div class="homeblock5" style="margin-left:auto; margin-right:auto;">
									<div style="padding:30px 45px 10px 45px;">
										<button type="button" class="close" onClick="closebutton()">×</button>
										<p class="title">签收单</p>
									</div>
									<div class="bodyStyle" style="padding-top:0;">
										<div class="infoBlock">
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>签收单号</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>任务单号</p>
													<span class="infoBorder"></span>
												</div>
											</div>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>客户姓名</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>联系电话</p>
													<span class="infoBorder"></span>
												</div>
											</div>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>邮编</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>送货日期</p>
													<span class="infoBorder"></span>
												</div>
											</div>
											<div class="infoRowColumnlong">
												<p>任务类型</p>
												<span class="infoBorder"></span>
											</div>
										</div>
										<div class="infoBlock">
											<div class="infoRowColumnlong">
												<p>地址</p>
												<span class="infoBorderlong"></span>
											</div>
											<div class="infoRowColumnlong">
												<p>送货要求</p>
												<span class="infoBorderlong"></span>
											</div>
										</div>
										<div class="infoBlock">
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>送货分站代号</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>分站地址</p>
													<span class="infoBorder"></span>
												</div>
											</div>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>分站电话</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumn">
													<p>是否需要发票</p>
													<span class="infoBorder"></span>
												</div>
											</div>
										</div>
										<div class="infoBlock">
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>商品名称</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumnshort">
													<p>单价</p>
													<span class="infoBordershort"></span>
												</div>
												<div class="infoRowColumnshort">
													<p>商品数量</p>
													<span class="infoBordershort"></span>
												</div>
											</div>
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>商品总价</p>
													<span class="infoBorder"></span>
												</div>
												<div class="infoRowColumnmedium">
													<p>备注</p>
													<span class="infoBordermedium"></span>
												</div>
											</div>
										</div>
										<div class="infoBlock">
											<div class="infoRow">
												<div class="infoRowColumn">
													<p>客户反馈</p>
													<span class="infoBorder" style="overflow-y:scroll;"></span>
												</div>
												<div class="infoRowColumn">
													<p>客户签名</p>
													<span class="infoBorder"></span>
												</div>
											</div>
										</div>
									</div>
									<div class="signFooter">
										<button type="button" class="btn btn-default" data-dismiss="modal" style="width: 86px;">确认打印</button>
									</div>
									

								</div>
								<!-- 浮窗 -->
							</div>
						</div>
						<div class="searchItem">
							<button class="workButton" data-toggle="modal" data-target="#myModal">2</button>
							<p class="p60">送货收款</p>
							<p class="p60">已分配</p>
							<p class="p90">2018/6/25</p>
							<button class="workButton" data-toggle="" data-target="">打印</button>
						</div>
						<div class="searchItem">
							<button class="workButton" data-toggle="modal" data-target="#myModal">3</button>
							<p class="p60">送货收款</p>
							<p class="p60">已分配</p>
							<p class="p90">2018/6/25</p>
							<button class="workButton" data-toggle="" data-target="">打印</button>
						</div>
						<div class="searchItem">
							<button class="workButton" data-toggle="modal" data-target="#myModal">4</button>
							<p class="p60">送货收款</p>
							<p class="p60">已分配</p>
							<p class="p90">2018/6/25</p>
							<button class="workButton" data-toggle="" data-target="">打印</button>
						</div>
						<div class="searchItem">
							<button class="workButton" data-toggle="modal" data-target="#myModal">5</button>
							<p class="p60">送货收款</p>
							<p class="p60">已分配</p>
							<p class="p90">2018/6/25</p>
							<button class="workButton" data-toggle="" data-target="">打印</button>
						</div>
						<div class="pageFoot" style="margin:8px 10px 5px 10px;">
							<ul class="pagination" style="margin:10px 0;">
						        <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
						        <li><a href="#">1</a></li>
						        <li><a href="#">2</a></li>
						        <li><a href="#">3</a></li>
						        <li><a href="#">4</a></li>
						        <li><a href="#">5</a></li>
						        <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
						      </ul>
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