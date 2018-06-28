//分配任务时的小窗口
function assignWork0(){
	var req0;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req0 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req0 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req0){
		//打开到服务器的连接，post，url，同步false
		req0.open("post","substationServlet?action=delivery",false);
		//设置请求头信息
		req0.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req0.onreadystatechange = function(){
			if(req0.readyState == 4){
				if(req0.status == 200){
					var result = req0.responseText;
					var insert = document.getElementById("deliveryList0");
					if(insert != null){
						insert.innerHTML= result;
					}
					
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req0.send();
	}
	
	$('#showDiv0').show();
	$('#cover_assign0').css('display','block');
	$('#cover_assign0').css('height',document.body.clientHeight+'px');
}
function assignWork1(){
	var req1;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req1 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req1 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req1){
		//打开到服务器的连接，post，url，同步false
		req1.open("post","substationServlet?action=delivery",false);
		//设置请求头信息
		req1.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req1.onreadystatechange = function(){
			if(req1.readyState == 4){
				if(req1.status == 200){
					var result = req1.responseText;
					var insert = document.getElementById("deliveryList1");
					if(insert != null){
						insert.innerHTML= result;
					}
					
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req1.send();
	}
	
	$('#showDiv1').show();
	$('#cover_assign1').css('display','block');
	$('#cover_assign1').css('height',document.body.clientHeight+'px');
}
function assignWork2(){
	var req2;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req2 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req2 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req2){
		//打开到服务器的连接，post，url，同步false
		req2.open("post","substationServlet?action=delivery",false);
		//设置请求头信息
		req2.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req2.onreadystatechange = function(){
			if(req2.readyState == 4){
				if(req2.status == 200){
					var result = req2.responseText;
					var insert = document.getElementById("deliveryList2");
					if(insert != null){
						insert.innerHTML= result;
					}
					
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req2.send();
	}
	
	$('#showDiv2').show();
	$('#cover_assign2').css('display','block');
	$('#cover_assign2').css('height',document.body.clientHeight+'px');
}
function assignWork3(){
	var req3;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req3 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req3 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req3){
		//打开到服务器的连接，post，url，同步false
		req3.open("post","substationServlet?action=delivery",false);
		//设置请求头信息
		req3.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req3.onreadystatechange = function(){
			if(req3.readyState == 4){
				if(req3.status == 200){
					var result = req3.responseText;
					var insert = document.getElementById("deliveryList3");
					if(insert != null){
						insert.innerHTML= result;
					}
					
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req3.send();
	}
	
	$('#showDiv3').show();
	$('#cover_assign3').css('display','block');
	$('#cover_assign3').css('height',document.body.clientHeight+'px');
}
function assignWork4(){
	var req4;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req4 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req4 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req4){
		//打开到服务器的连接，post，url，同步false
		req4.open("post","substationServlet?action=delivery",false);
		//设置请求头信息
		req4.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req4.onreadystatechange = function(){
			if(req4.readyState == 4){
				if(req4.status == 200){
					var result = req4.responseText;
					var insert = document.getElementById("deliveryList4");
					if(insert != null){
						insert.innerHTML= result;
					}
					
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req4.send();
	}
	
	$('#showDiv4').show();
	$('#cover_assign4').css('display','block');
	$('#cover_assign4').css('height',document.body.clientHeight+'px');
}

$(document).mousedown(function(e){
	var _con = $('#showDiv0');
	if(!_con.is(e.target) && _con.has(e.target).length === 0){
		$('#showDiv0').hide();
		$('#cover_assign0').css('display','none');
	}
});

$(document).mousedown(function(e){
	var _con = $('#showDiv1');
	if(!_con.is(e.target) && _con.has(e.target).length === 0){
		$('#showDiv1').hide();
		$('#cover_assign1').css('display','none');
	}
});

$(document).mousedown(function(e){
	var _con = $('#showDiv2');
	if(!_con.is(e.target) && _con.has(e.target).length === 0){
		$('#showDiv2').hide();
		$('#cover_assign2').css('display','none');
	}
});

$(document).mousedown(function(e){
	var _con = $('#showDiv3');
	if(!_con.is(e.target) && _con.has(e.target).length === 0){
		$('#showDiv3').hide();
		$('#cover_assign3').css('display','none');
	}
});

$(document).mousedown(function(e){
	var _con = $('#showDiv4');
	if(!_con.is(e.target) && _con.has(e.target).length === 0){
		$('#showDiv4').hide();
		$('#cover_assign4').css('display','none');
	}
});


function printSign0(){
	var req0;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req0 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req0 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req0){
		//打开到服务器的连接，post，url，同步false
		req0.open("post","substationServlet?action=showSign",false);
		//设置请求头信息
		req0.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req0.onreadystatechange = function(){
			if(req0.readyState == 4){
				if(req0.status == 200){
					var result = req0.responseText;
					var insert = document.getElementById("insertSign0");
					if(insert != null){
						insert.innerHTML= result;
					}
					
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req0.send("index=0");
	}
	
	$('#showDiv0').show();
	$('#cover_assign0').css('display','block');
	$('#cover_assign0').css('height',document.body.clientHeight+'px');
}
function printSign1(){
	var req1;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req1 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req1 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req1){
		//打开到服务器的连接，post，url，同步false
		req1.open("post","substationServlet?action=showSign",false);
		//设置请求头信息
		req1.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req1.onreadystatechange = function(){
			if(req1.readyState == 4){
				if(req1.status == 200){
					var result = req1.responseText;
					var insert = document.getElementById("insertSign1");
					if(insert != null){
						insert.innerHTML= result;
					}
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req1.send("index=1");
	}
	
	$('#showDiv1').show();
	$('#cover_assign1').css('display','block');
	$('#cover_assign1').css('height',document.body.clientHeight+'px');
}

function printSign2(){
	var req2;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req2 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req2 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req2){
		//打开到服务器的连接，post，url，同步false
		req2.open("post","substationServlet?action=showSign",false);
		//设置请求头信息
		req2.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req2.onreadystatechange = function(){
			if(req2.readyState == 4){
				if(req2.status == 200){
					var result = req2.responseText;
					var insert = document.getElementById("insertSign2");
					if(insert != null){
						insert.innerHTML= result;
					}
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req2.send("index=2");
	}
	
	$('#showDiv2').show();
	$('#cover_assign2').css('display','block');
	$('#cover_assign2').css('height',document.body.clientHeight+'px');
}

function printSign3(){
	var req3;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req3 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req3 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req3){
		//打开到服务器的连接，post，url，同步false
		req3.open("post","substationServlet?action=showSign",false);
		//设置请求头信息
		req3.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req3.onreadystatechange = function(){
			if(req3.readyState == 4){
				if(req3.status == 200){
					var result = req3.responseText;
					var insert = document.getElementById("insertSign3");
					if(insert != null){
						insert.innerHTML= result;
					}
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req3.send("index=3");
	}
	
	$('#showDiv3').show();
	$('#cover_assign3').css('display','block');
	$('#cover_assign3').css('height',document.body.clientHeight+'px');
}

function printSign4(){
	var req4;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req4 = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req4 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req4){
		//打开到服务器的连接，post，url，同步false
		req4.open("post","substationServlet?action=showSign",false);
		//设置请求头信息
		req4.setRequestHeader("Content-Type","text/plain; charset=UTF-8");
		//回调函数
		req4.onreadystatechange = function(){
			if(req4.readyState == 4){
				if(req4.status == 200){
					var result = req4.responseText;
					var insert = document.getElementById("insertSign4");
					if(insert != null){
						insert.innerHTML= result;
					}
				}else{
					alert("失败啦～～");
				}
			}
		};
		//发送请求
		req4.send("index=4");
	}
	
	$('#showDiv4').show();
	$('#cover_assign4').css('display','block');
	$('#cover_assign4').css('height',document.body.clientHeight+'px');
}

function closebutton0(){
	$('#showDiv0').hide();
	$('#cover_assign0').css('display','none');
}
function closebutton1(){
	$('#showDiv1').hide();
	$('#cover_assign1').css('display','none');
}
function closebutton2(){
	$('#showDiv2').hide();
	$('#cover_assign2').css('display','none');
}
function closebutton3(){
	$('#showDiv3').hide();
	$('#cover_assign3').css('display','none');
}
function closebutton4(){
	$('#showDiv4').hide();
	$('#cover_assign4').css('display','none');
}


//显示任务单详情
function showWorkOrder0(){
	$('#showDivW0').show();
	$('#cover').css('display','block');
	$('#cover').css('height',document.body.clientHeight+'px');
}
function showWorkOrder1(){
	$('#showDivW1').show();
	$('#cover').css('display','block');
	$('#cover').css('height',document.body.clientHeight+'px');
}
function showWorkOrder2(){
	$('#showDivW2').show();
	$('#cover').css('display','block');
	$('#cover').css('height',document.body.clientHeight+'px');
}
function showWorkOrder3(){
	$('#showDivW3').show();
	$('#cover').css('display','block');
	$('#cover').css('height',document.body.clientHeight+'px');
}
function showWorkOrder4(){
	$('#showDivW4').show();
	$('#cover').css('display','block');
	$('#cover').css('height',document.body.clientHeight+'px');
}
//任务单详情的关闭按钮以及x的js
function closebuttonW0(){
	$('#showDivW0').hide();
	$('#cover').css('display','none');
}
function closebuttonW1(){
	$('#showDivW1').hide();
	$('#cover').css('display','none');
}
function closebuttonW2(){
	$('#showDivW2').hide();
	$('#cover').css('display','none');
}
function closebuttonW3(){
	$('#showDivW3').hide();
	$('#cover').css('display','none');
}
function closebuttonW4(){
	$('#showDivW4').hide();
	$('#cover').css('display','none');
}