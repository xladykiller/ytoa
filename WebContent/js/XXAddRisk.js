$(function(){
	//选择时间
	$('#complainDate').datepicker({
		changeMonth: true,
		changeYear: true
	});
	
	//添加公章使用记录
	$("#addRiskBtn").bind("click",function(){
		var complainDate = $.trim($("#complainDate").val());
		var complainant = $.trim($("#complainant").val());
		var complainWay = $.trim($("#complainWay").val());
		var phone = $.trim($("#phone").val());
		var proxyid = $.trim($("#proxyid").val());
		var undertaker = $.trim($("#undertaker").val());
		var content = $.trim($("#content").val());
		var result = $.trim($("#result").val());
		if(complainDate == "" || complainant == "" || complainWay == "" || phone == "" || proxyid == ""
			|| undertaker == ""|| content == ""){
			alert("请把信息填写完整");
			return;
		}
		$.post("XXRisk",{
			complainDate:complainDate,
			complainant:complainant,
			complainWay:complainWay,
			phone:phone,
			proxyid:proxyid,
			undertaker:undertaker,
			content:content,
			result:result,
			action:"add"
		},function(data){
			if(data.ret=="1"){
				alert("操作成功");
				$("#resetBtn").click();
			}
			else if(data.ret=="0"){
				alert("操作失败");
			}
			else if(data.ret=="2"){
				alert("代理案号错误");
			}
			else if(data.ret == "noPermission"){
				window.location.href="result/noPermission.jsp";
				return;
			}
		},'json');
	});
	
	$("#resetBtn").bind("click",function(){
		$("input[type=text]").val("");
		$("textarea").val("");
	});
});




//显示列表
var addrow = function(o) {
	var $row = $('<tr><td>'
			+ o.proxyid
			+ '</td></tr>');
	$("#proxyidList").append($row);
};


	$(function() {

	$('#picker').datepicker({
		changeMonth : true,
		changeYear : true
	});

	//鼠标移出、点击选项
	$(document).on(
			'click',
			function(e) {
				var target = e.target;
				if (target.id != "proxyid"
						&& $('#proxyidList').find(target).length == 0) {
					$('#proxyidList').hide();
				}
				if ($('#proxyidList').find(target).length > 0) {
					$("#proxyid").val($(target).html());
					$('#proxyidList').hide();
				}
			});

	/* 	$(document).on("mouseover",function(e){
			var target = e.target;
			if($("#proxyidList tr").find(target).length!=0){
				$(target).css("background-color","blue");
			}
		}); */

	$('#proxyid').bind("keyup", function() {
		var proxyidText = $.trim($("#proxyid").val());

		$.post("XXRisk", {
			proxyidText : proxyidText,
			action : "getProxyidList"
		}, function(data) {
			$("#proxyidList tr").remove();
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				addrow(o);
			}
			if (proxyidText != "" && data.length != 0) {
				var offset = $("#proxyid").position();
				var height = $("#proxyid").outerHeight();
				$('#proxyidList').position({
					left : offset.left,
					top : offset.top + height
				});
				$('#proxyidList').show();
			} else {
				$('#proxyidList').hide();
			}
		}, "json");
	});
});