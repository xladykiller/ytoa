function validate(permission, power) {
	i = 1;
	if ((Math.pow(2,power)& permission) != 0) {
		return true;
	}
	return false;
}

// 显示权限
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td>'
			+ o.rolename
			+ '</td><td><input type="button"  name="del" value="删除角色" delPower='
			+ o.id
			+ '"></td><td><input type="button" name="update" value="修改权限" updatePower="'
			+ o.id + '"></td></tr>');
	$("#powerTable tbody").append($row);
};

function refresh() {
	$.post("XXGetPower", {}, function(data) {
		$("#powerTable tbody tr").remove();
		var length = data.length;
		for ( var i = 0; i < length; i++) {
			var o = data[i];
			addrow(o, i + 1);
			// 保存权限信息（ o.id为int,转换为String）
			$(window).data("" + o.id, o);
		}

		// 删除角色
		$("input[name=del]").bind("click", function() {
			if (window.confirm("确定删除？")) {
				var id = $(this).attr("delPower");
				// $(window).data('delrow',$(this).parent().parent());
				$.post("XXDelPower", {
					id : id
				}, function(data) {
					if (data == 1) {
						alert("删除成功");
						$(window).data(""+id, null);
						// $(window).data('delrow').remove();
						// $(window).data('delrow',undefined);
						refresh();
					} else {
						alert("删除失败");
					}
				});
			}
		});

		// 弹出修改权限页面
		$("input[name=update]").bind("click", function() {
			//读取id和权限值
			var id = $(this).attr("updatePower");
			var o = $(window).data(id);
			var permission = o.permission;
			//显示角色名
			$("#updateRolename").val(o.rolename);
			//把id赋值给提交按钮!!!!
			$("#updatePowerBtn").attr("powerid",id);
			for(var i=1; i<=64; i++){
				$("input[type=checkbox][value="+i+"]").prop("checked",false); 
				if(validate(permission,i)){
					$("input[type=checkbox][value="+i+"]").prop("checked",true);
				}
			}
			$("#powerTable").hide();
			$("#updatePowerTable").show();
		});
		
	}, "json");
}// refresh end



$(function() {

	refresh();

	//提交修改 
	$("#updatePowerBtn").bind("click",function(){
		//从提交按钮读取id!!!!
		var id = $(this).attr("powerid");
		//读取角色名
		var rolename = $.trim($("#updateRolename").val());
		//计算权限值
		var permission = 0;
		var checked=$(":checked");
		checked.each(function(index){
			//var i = 1;
			permission += Math.pow(2, $(this).val());// i << $(this).val();		
		});			
		$.post("XXUpdatePower",{
			id:id,
			rolename:rolename,
			permission:permission
		},function(data){
			if (data == 1) {
				alert("修改成功");	
				refresh();
				$(window).data(""+id,null);
				$("#returnBtn").click();
			} else {
				alert("修改失败");
			}
		});
	});
	
	// 修改返回
	$("#returnBtn").bind("click", function() {
		$("#powerTable").show();
		$("#updatePowerTable").hide();
	});
});