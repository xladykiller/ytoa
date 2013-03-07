$(function() {
	$("#evaldate").datepicker({
		changeMonth : true,
		changeYear : true
	});

	$("#addSelfevaluationBtn").bind("click", function() {
		var evaldate = $.trim($("#evaldate").val());
		var result = $.trim($("#result").val());
		var content = $.trim($("#content").val());
		var checked = $("table input:checked");
		var params = [];
		checked.each(function(index) {
			params.push($(this).val());
		});
		if(evaldate == ""){
			alert("请填写日期");
			return;
		}
		$.post('XXSelfevaluation', {
			"params[]" : params,
			"evaldate" : evaldate,
			"result" : result,
			"content" : content,
			"action" : "add"
		}, function(data) {
			if(data.ret=="1"){
				alert("提交成功");
				$("#resetBtn").click();
			}
			else if(data.ret=="0"){
				alert("提交失败");
			}
			else if(data.ret=="2"){
				alert(evaldate+"的自评考核已填写");
			}
		},'json');
	});
	
	$("#resetBtn").bind("click",function(){
		$("input[type=checkbox]").attr("checked",false);
		$("input[type=text]").val("");
		$("textarea").val("");
	});
});