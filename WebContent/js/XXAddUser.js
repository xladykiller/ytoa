$(function() {
	//重置信息
	$("#resetBtn").bind("click",function(){
		$("table input[type=text]").val("");
		$("table input[type=password]").val("");
	});
	
	//添加用户
	$("#addUserBtn").bind("click", function() {
		var account = $.trim($("#account").val());
		var psw = $.trim($("#psw").val());
		var repsw =$.trim( $("#repsw").val());
		var uname = $.trim($("#uname").val());
		var powerid = $.trim($("#powerid").val());
		var cellphone = $.trim($("#cellphone").val());
		
		if(account == "" || psw == "" || repsw=="" || uname=="" || powerid=="" || cellphone==""){
			alert("请把信息填写完整");
			return ;
		}
		if(psw!=repsw){
			alert("两次密码不相同");
			return;
		}
		
		$.post("XXAddUser",{
			account:account,
			psw:psw,
			uname:uname,
			powerid:powerid,
			cellphone:cellphone
		},function(data){
			if(data.result=="1"){
				alert("添加成功");
			}
			else if(data.result=="0"){
				alert("添加失败");
			}
			else if(data.result=="2"){
				alert("用户名重复");
			}
			else{
				window.location.href="result/noPermission.jsp";
			}
		},"json");
	});

	//读取角色
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