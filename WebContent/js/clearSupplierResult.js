function clearSupplier(){
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
		alert("选择了订单进行结算");
		document.forms[2].action="../clearMoneyServlet?action=clearSupplier";
		document.forms[2].submit();
	}else{
		//提示
		alert("请至少选择一个订单进行结算");
	}
}