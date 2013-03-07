$(function() {
	// 修改密码
	$("#updatePswBtn").bind("click", function() {
		var oldpsw = $.trim($("#oldPsw").val());
		var newpsw = $.trim($("#updatePsw").val());
		var repsw = $.trim($("#updateRepsw").val());
		if(oldpsw == "" || newpsw == "" || repsw == ""){
			alert("密码不能为空");
			return;
		}
		if (newpsw != repsw) {
			alert("两次密码不相同");
			return;
		}
		$.post("XXUpdatePsw", {
			oldpsw:oldpsw,
			newpsw : newpsw
		}, function(data) {
			if (data.status == "falseOldpsw") {
				alert("旧密码错误");
			}
			else if (data.status == "failedNewpsw") {
				alert("新密码修改失败");
			} 
			else if (data.status == "success") {
				alert("密码修改成功");
				$("#resetBtn").click();
			} 
			else if(data.status == "noPermission"){
				window.location.href="result/noPermission.jsp";
			}
		},"json");
	});
	
	//重置信息
	$("#resetBtn").bind("click",function(){
		$("table input[type=password]").val("");
	});
});