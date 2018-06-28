var req;
function validate(){
	
	/*
	 * XMLHTTPRequest
	 * 1. 创建对象
	 * 2. 建立要连接的URL
	 * 3. 打开到服务器的连接
	 * 4. 设置回调函数
	 * 5. 发送请求
	 */
	if(window.XMLHttpRequest){
		//非IE浏览器
		req = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req){
		//打开到服务器的连接
		req.open("post","supManageServlet?action=validateSupName",false);
		//设定请求头信息
		req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//回调函数
		req.onreadystatechange = updatePage;
		//发送请求
		req.send("supName="+document.getElementById("supName").value);
	}
	
}

function updatePage(){
	if(req.readyState == 4){
		if(req.status == 200){
			var result = req.responseText;
			if(result == "true"){
				//重复
				document.getElementById("res").innerHTML = "已存在";
			}else{
				//不重复
				document.getElementById("res").innerHTML = "可用";
			}
		}
	}
}





