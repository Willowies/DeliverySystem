function clearSubstation(){
	var chks = document.getElementsByName("chk");
	var flag = false;
	for(var i=0;i<chks.length;i++){
		if(chks[i].checked==true){
			flag=true;
			break;
		}
	}
	if(flag){
		//提交请求
		alert("选择了发票进行结算");
		document.forms[1].action="../clearMoneyServlet?action=clearSubstation";
		document.forms[1].submit();
	}else{
		//提示
		alert("请至少选择一个发票进行结算");
	}
}