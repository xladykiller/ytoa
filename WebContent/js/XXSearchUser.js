var bindFunction = {};

// 删除用户
bindFunction.delUser = function() {
	$("input[name=del]").bind("click", function() {
		if (window.confirm("确定删除？")) {
			// 读取id
			var id = $(this).attr('delAccount');
			$.post("XXDelUser", {
				id : id
			}, function(data) {
				if (data == 1) {
					alert("删除成功");
//					if (window.confirm("确定删除该用户的自评考核记录?")) {
//						$.post("XXSelfevaluation",{
//							"unameid":id,
//							"action":"delByUnameid"
//						},function(data){
//							if(data == 0)
//						});
//					}
					$(window).data(""+id, null);
					$("#searchUserBtn").trigger('click',
							[ page.pageNow ]);
				} else if(data == 0){
					alert("删除失败");
				} else if(data == 2){
					window.location.href="result/noPermission.jsp";
				}
			});
		}
	});
};

// 弹出修改页面
bindFunction.showUpdateUser = function() {
	$("input[name=update]")
			.bind(
					"click",
					function() {
						// 读取id
						var id = $(this).attr("updateAccount");
						// 根据id读取记录
						var o = $(window).data(""+id);
						// 把id赋给updateUserBtn（这个按钮就可以根据id操作数据库）
						$("#updateUserBtn").attr("userid", id);
						// 赋值记录值
						$("#updateAccount").val(o.account);
						$("#updateUname").val(o.uname);
						$("#updateCellphone").val(o.cellphone);
						$("#updatePsw").val("");
						$("#updateRepsw").val("");
						$("#updatePowerid option[value=" + o.powerid + "]")
								.get(0).selected = true;
						// 隐藏搜索，显示更新
						$("#searchUser").hide();
						$("#updateUserTable").show();
					});
};

// 显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td>'
			+ o.account
			+ '</td><td>'
			+ o.uname
			+ '</td><td>'
			+ o.cellphone
			+ '</td><td>'
			+ o.rolename
			+ '</td><td><input type="button"  name="del" value="删除" delAccount="'
			+ o.id
			+ '"></td><td><input type="button" name="update" value="修改" updateAccount="'
			+ o.id + '"></td></tr>');
	$("#searchUserTable tbody").append($row);
};

$(function() {
	// 查询用户，显示用户信息列表
	$("#searchUserBtn").bind("click", function(event, num) {
		// 清空列表
		$("#searchUserTable tbody tr").remove();
//		$("#page a").remove();
		// 读取查询字段
		var account = $.trim($("#account").val());
		var uname = $.trim($("#uname").val());
		var cellphone = $.trim($("#cellphone").val());
		var powerid = $.trim($("#powerid").val());
		// 返回查询结果
		$.post("XXSearchUser", {
			account : account,
			uname : uname,
			cellphone : cellphone,
			powerid : powerid
		}, function(data) {
			if(data.length == 1){
				if(data[0].ret != null && data[0].ret != undefined){
					window.location.href="result/noPermission.jsp";
					return;
				}
			}
			
			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXGetUserByPage", {
				account : account,
				uname : uname,
				cellphone : cellphone,
				powerid : powerid
			}, function() {
				bindFunction.delUser();
				bindFunction.showUpdateUser();
			}, function(data) {
				$("#searchUserTable tbody tr").remove();
				for ( var i = 0; i < page.pageSize; i++) {
					var o = data[i];
					if (o == null)
						break;
					addrow(o, i + 1);
					$(window).data(""+o.id, o);
				}
			});// end page.init
			
			// 显示列表
			for ( var i = 0; i < page.pageSize; i++) {
				var o = data[i];
				if (o == null)
					break;
				addrow(o, i + 1);
				$(window).data("" + o.id, o);
			}

			bindFunction.delUser();
			bindFunction.showUpdateUser();
			
			//删除修改后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}

		}, "json");
	});

	$("#searchUserBtn").click();

	// 提交修改
	$("#updateUserBtn").bind("click", function() {
		// 从updateUserBtn读取id
		var id = $(this).attr("userid");
		var account = $("#updateAccount").val();
		var uname = $("#updateUname").val();
		var cellphone = $("#updateCellphone").val();
		var powerid = $("#updatePowerid").val();
		var psw = $("#updatePsw").val();
		var repsw = $("#updateRepsw").val();
		if (psw != null || repsw != null) {
			if (psw != repsw) {
				alert("两次密码不相同");
				return;
			}
		}
		$.post("XXUpdateUser", {
			id:id,
			account : account,
			uname : uname,
			cellphone : cellphone,
			powerid : powerid,
			psw : psw
		}, function(data) {
			if (data == 1) {
				alert("修改成功");
				$(window).data("" + id, null);
				$("#searchUserBtn").trigger('click',
						[ page.pageNow ]);
				$("#returnBtn").click();
			} else  if(data == 0){
				alert("修改失败");
			}else if(data == 2){
				window.location.href="result/noPermission.jsp";
			}
		});
	});

	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchUser").show();
		$("#updateUserTable").hide();
	});

	// 读取角色
	$.post("xxReadRole", {}, function(data) {
		var size = data.result.length;
		// var rt = $.parseJSON(data);
		for ( var i = 0; i < size; i++) {
			var s = '<option value="' + data.result[i].id + '">'
					+ data.result[i].rolename + '</option>';
			$('select').append($(s));
		}
	}, 'json');
});