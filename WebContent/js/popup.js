function assignWork(){
	$('#showDiv').show();
	$('#cover').css('display','block');
	$('#cover').css('height',document.body.clientHeight+'px');
}

$(document).mousedown(function(e){
	var _con = $('#showDiv');
	if(!_con.is(e.target) && _con.has(e.target).length === 0){
		$('#showDiv').hide();
		$('#cover').css('display','none');
	}
});

function printSign(){
	$('#showDiv').show();
	$('#cover').css('display','block');
	$('#cover').css('height',document.body.clientHeight+'px');
}

function closebutton(){
	$('#showDiv').hide();
	$('#cover').css('display','none');
}