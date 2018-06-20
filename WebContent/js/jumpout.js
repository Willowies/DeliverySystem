var winWidth = 0;
var winHeight = 0;

function findDimensions() { //函数：获取尺寸  
	//获取窗口宽度  
	if(window.innerWidth) {
		winWidth = window.innerWidth;
	} else if((document.body) && (document.body.clientWidth)) {
		winWidth = document.body.clientWidth;
	}
	//获取窗口高度  
	if(window.innerHeight) {
		winHeight = window.innerHeight;
	} else if((document.body) && (document.body.clientHeight)) {
		winHeight = document.body.clientHeight;
	}
	//通过深入Document内部对body进行检测，获取窗口大小  
	if(document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
	//设置div的具体宽度=窗口的宽度的%  
	if(document.getElementById("jumpout-block")) {
		document.getElementById("jumpout-block").style.height = winHeight + "px";
		document.getElementById("jumpout-block").style.width = winWidth + "px";
	}
}
findDimensions();
window.onload = findDimensions;
window.onresize = findDimensions;

function jumpout(){
	$("#jumpout-block").fadeIn(500);
}

function jumpback(event){
	 if (event.target.id == "jump-actual-block"|| event.target.id =="use-in-jumpout-js"){
	 	 return
	 }else if(event.target.tagName == "P" ||event.target.tagName == "SELECT"||event.target.tagName == "BUTTON"||event.target.tagName == "INPUT" ){
	 	return
	 }else{
		$("#jumpout-block").fadeOut(500);
    }
	
}
