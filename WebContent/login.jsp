<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
  <head>
    <meta name="robots" content="noindex, nofollow"/>
    <title>登录界面</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=1366, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="css/data.css" type="text/css" rel="stylesheet"/>
    <link href="css/index.css" type="text/css" rel="stylesheet"/>
    <link href="css/others.css" type="text/css" rel="stylesheet"/>
</head>
  <body>
    <div id="base" class="">

      <!-- 背景 (Dynamic Panel) -->
      <div id="u1314" class="ax_default" data-label="背景">
        <div id="u1314_state0" class="panel_state" data-label="State1" style="">
          <div id="u1314_state0_content" class="panel_state_content">
          </div>
        </div>
      </div>

      <!-- 登录面板 (Dynamic Panel) -->
      <div id="u1315" class="ax_default" data-label="登录面板">
        <div id="u1315_state0" class="panel_state" data-label="State1" style="">
          <div id="u1315_state0_content" class="panel_state_content">

            <!-- Unnamed (Rectangle) -->
            <div id="u1316" class="ax_default _默认样式">
              <div id="u1316_div" class=""></div>
            </div>

            <!-- Unnamed (Rectangle) -->
            <div id="u1317" class="ax_default _默认样式">
              <img id="u1317_img" class="img " src="img/u1317.png"/>
              <div id="u1317_text" class="text ">
                <p><span>物流管理系统</span></p>
              </div>
            </div>

            <!-- Unnamed (Rectangle) -->
            <div id="u1318" class="ax_default _默认样式">
              <div id="u1318_div" class=""></div>
            </div>                      
<form action="employeeManageServlet?action=login" method="post">
			<!-- 输入框 (Group) -->
            <div id="u1320" class="ax_default" data-label="输入框" data-left="25" data-top="230" data-width="300" data-height="50">

              <!-- 边框 (Rectangle) -->
              <div id="u1321" class="ax_default _默认样式" data-label="边框">
                <img id="u1321_img" class="img " src="img/u1321.png"/>
                <div id="u1321_text" class="text ">
                  <p><span></span></p>
                </div>
              </div>

              <!-- 密码 (Text Field) -->
              <div id="u1322" class="ax_default _默认样式" data-label="密码">
                <input id="u1322_input" type="password" name="password" maxlength="20" placeholder="请输入您的密码"/>
              </div>
            </div>

            <!-- 输入框 (Group) -->
            <div id="u1324" class="ax_default" data-label="输入框" data-left="25" data-top="160" data-width="300" data-height="50">

              <!-- 边框 (Rectangle) -->
              <div id="u1325" class="ax_default _默认样式" data-label="边框">
                <img id="u1325_img" class="img " src="img/u1321.png"/>
              </div>

              <!-- 用户名 (Text Field) -->
              <div id="u1326" class="ax_default _默认样式" data-label="用户名">
                <input id="u1326_input" type="text" name="account" maxlength="20" placeholder="请输入您的账户"/>
              </div>
            </div>
             
             	
             <!-- 登录按纽 (Rectangle) -->
            <div id="u13191" data-label="登录按纽" selectiongroup="按纽组-9">
              <div id="u13191_text" class="text ">
              	<button type="submit" id="u13191_div">登录</button>                
              </div>
            </div>
	
</form>
			 <!-- 注册按纽 (Rectangle) -->
            <div id="u1319" data-label="登录按纽" selectiongroup="按纽组-9">
              <div id="u1319_text" class="text ">
              	<button id="u1319_div">注册</button>
              </div>
            </div>
<%
	Object message = request.getAttribute("message");
	if(message!=null&&!"".equals(message)){
%>
	<script type="text/javascript">
	alert("<%=message%>");
	</script>
<%
	}
%>

			
            <!-- Unnamed (Shape) -->
            <div id="u1328" class="ax_default _默认样式">
              <img id="u1328_img" class="img " src="img/u1328.png"/>
            </div>

            <!-- Unnamed (Shape) -->
            <div id="u1329" class="ax_default _默认样式">
              <img id="u1329_img" class="img " src="img/u1329.png"/>
            </div>

            <!-- Unnamed (Group) -->
            <div id="u1334" class="ax_default" data-left="145" data-top="32" data-width="60" data-height="66">

              <!-- Unnamed (Shape) -->
              <div id="u1335" class="ax_default _默认样式">
                <img id="u1335_img" class="img " src="img/u1335.png"/>
              </div>

              <!-- Unnamed (Shape) -->
              <div id="u1336" class="ax_default _默认样式">
                <img id="u1336_img" class="img " src="img/u1336.png"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
