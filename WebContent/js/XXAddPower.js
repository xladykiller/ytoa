$(function(){
	//重置信息
	$("#resetBtn").bind("click",function(){
		$("table input[type=text]").val("");
		$("table input[type=checkbox]").prop("checked",false);
	});
	
	//添加角色
	$("#addPowerBtn").bind("click",function(){
		//读取角色名
		var rolename = $.trim($("#rolename").val());
		if(rolename == ""){
			alert("请填写角色名");
			return;
		}
		//计算权限值
		var permission = 0;
		var checked = $(":checked");
		checked.each(function(index){
//			var i = 1;
			permission += Math.pow(2, $(this).val());// i << $(this).val();		
		});
		$.post("XXAddPower",{
			rolename:rolename,
			permission:permission
		},function(data){
			if(data.result=="1"){
				alert("添加成功");
			}
			else if(data.result=="0"){
				alert("添加失败");
			}
			else if(data.result=="2"){
				alert("角色名重复");
			}
		},'json');
	});
	
});