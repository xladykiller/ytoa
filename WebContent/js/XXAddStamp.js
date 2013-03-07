$(function(){
	//选择时间
	$('#useDate').datepicker({
		changeMonth: true,
		changeYear: true
	});
	
	//添加公章使用记录
	$("#addStampBtn").bind("click",function(){
		var useDate = $.trim($("#useDate").val());
		var petitioner = $.trim($("#petitioner").val());
		var psw = $.trim($("#psw").val());
		var fileNum = $.trim($("#fileNum").val());
		var useReason = $.trim($("#useReason").val());
		var memo = $.trim($("#memo").val());
		if(useDate == "" || petitioner == "" || psw == "" || fileNum == "" || useReason == ""){
			alert("请把信息填写完整");
			return;
		}
		var r = /^\+?[1-9][0-9]*$/;	    
		if(!r.test(fileNum)){				     
			alert("文件数量填写错误");
			return;
		}
		$.post("XXStamp",{
			useDate:useDate,
			petitioner:petitioner,
			psw:psw,
			fileNum:fileNum,
			useReason:useReason,
			memo:memo,
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
				alert("用户名或账号错误");
			}
			else if(data.ret =="noPermission"){
				window.location.href="result/noPermission.jsp";
				return;
			}
		},'json');
	});
	
	$("#resetBtn").bind("click",function(){
		$("#useDate").val("");
		$("#petitioner").val("");
		$("#psw").val("");
		$("#fileNum").val("");
		$("#useReason").val("");
		$("#memo").val("");
	});
});