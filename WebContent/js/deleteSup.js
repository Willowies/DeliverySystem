function deleteSup(){
	var chks =  document.getElementsByName("chk");
	var flag = false;
	for(var i=0;i<chks.length;i++){
		if(chks[i].checked==true){
			flag = true;
			break;
		}
	}
	if(flag){
		//提交请求
		document.forms[0].action="supManageServlet?action=deleteSup";
		document.forms[0].submit();
	}else{
		//提示
		alert("请至少选择一个供应商进行删除");
	}
}