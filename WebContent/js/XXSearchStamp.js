$(function(){
	//选择时间
	$('#updateUseDate').datepicker({
		changeMonth: true,
		changeYear: true
	});
	
});



var bindFunction = {};

//删除公章使用记录
bindFunction.delStamp = function() {
	$("input[name=del]").bind("click", function() {
		if (window.confirm("确定删除？")) {
			// 读取id
			var id = $(this).attr('delStamp');
			$.post("XXStamp", {
				id : id,
				action : "del"
			}, function(data) {
				if (data == 1) {
					alert("删除成功");
					// 移除window数据
					$(window).data("" + id, null);
					// 刷新
					$("#searchStampBtn").trigger('click', [page.pageNow]);
				} else if(data == 0){
					alert("删除失败");
				}else if(data == 2){
					window.location.href="result/noPermission.jsp";
					return;
				}
			});
		}
	});
};

//弹出修改页面
bindFunction.showUpdateStamp = function() {
	$("input[name=update]").bind("click", function() {
		// 读取id
		var id = $(this).attr("updateStamp");
		// 根据id读取记录
		var o = $(window).data("" + id);
		// 把id赋给updateStampBtn（这个按钮就可以根据id操作数据库）
		$("#updateStampBtn").attr("stampid", id);
		// 赋值记录值
		$("#updateUseDate").val(replace(o.usedate));
		$("#updatePetitioner").val(o.petitioner);
		$("#updateUname").val(o.uname);
		$("#updateFileNum").val(o.filenum);
		$("#updateUseReason").val(o.usereason);
		$("#updateMemo").val(o.memo);
		// 隐藏搜索，显示更新
		$("#searchStamp").hide();
		$("#updateStampTable").show();
	});
};

//显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td>'
			+ replace(o.usedate)
			+ '</td><td>'
			+ o.petitioner
			+ '</td><td>'
			+ o.uname
			+ '</td><td>'
			+ o.filenum
			+ '</td><td style="text-align:left;text-indent:2em;">'
			+ o.usereason.substr(0,28)
			+ '</td><td><input type="button"  name="del" value="删除" delStamp="'
			+ o.id
			+ '"></td><td><input type="button" name="update" value="修改" updateStamp="'
			+ o.id + '"></td></tr>');
	$("#searchStampTable tbody").append($row);
};

$(function() {
	// 点击查询
	$("#searchStampBtn").bind("click", function(event,num) {
		// 清空列表和分页
		$("#searchStampTable tbody tr").remove();
//		$("#page a").remove();
		// 读取查询字段
		var searchYear = $.trim($("#searchYear").val());
		var searchPetitioner = $.trim($("#searchPetitioner").val());
		var searchUname = $.trim($("#searchUname").val());
		var searchUseReason = $.trim($("#searchUseReason").val());
		// 返回查询结果
		$.post("XXStamp", {
			searchYear : searchYear,
			searchPetitioner : searchPetitioner,
			searchUname : searchUname,
			searchUseReason : searchUseReason,
			action : "search"
		}, function(data) {
			if(data.length == 1){
				if(data[0].ret != null && data[0].ret != undefined){
					window.location.href="result/noPermission.jsp";
					return;
				}
			}
			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXStamp", {
				searchYear : searchYear,
				searchPetitioner : searchPetitioner,
				searchUname : searchUname,
				searchUseReason : searchUseReason,
				action:"searchByPage"
			}, function() {
				bindFunction.delStamp();
				bindFunction.showUpdateStamp();
			}, function(data) {
				$("#searchStampTable tbody tr").remove();
				for ( var i = 0; i < page.pageSize; i++) {
					var o = data[i];
					if (o == null)
						break;
					addrow(o, i + 1);
					$(window).data("" + o.id, o);
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

			bindFunction.delStamp();
			bindFunction.showUpdateStamp();
			//删除修改后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}

		}, 'json');// end post("XXSearchInfo")
	});// end查询============================================================================

	$("#searchStampBtn").click();

	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchStamp").show();
		$("#updateStampTable").hide();
	});

	// 提交修改
	$("#updateStampBtn").bind("click", function() {
		// 从updateStampBtn读取id
		var id = $(this).attr("stampid");
		var updateUseDate = $.trim($("#updateUseDate").val());
		var updateFileNum = $.trim($("#updateFileNum").val());
		var updateUseReason = $.trim($("#updateUseReason").val());
		var updateMemo = $.trim($("#updateMemo").val());
		var o = $(window).data("" + id);
		if (updateUseDate == "" || updateFileNum == "" || updateUseReason == "") {
			alert("请把信息填写完整");
			return;
		}
		if (updateUseDate == replace(o.usedate) && updateFileNum == o.filenum && updateUseReason == o.usereason &&  updateMemo == o.memo) {
			alert("未修改");
			return;
		}
		$.post("XXStamp", {
			id : id,
			updateUseDate : updateUseDate,
			updateFileNum : updateFileNum,
			updateUseReason : updateUseReason,
			updateMemo:updateMemo,
			action : "update"
		}, function(data) {
			if (data == 1) {
				alert("修改成功");
				$(window).data("" + id, null);
				$("#searchStampBtn").trigger('click', [page.pageNow]);
				$("#returnBtn").click();
			} else if(data == 0){
				alert("修改失败");
			}else if(data == 2){
				window.location.href="result/noPermission.jsp";
				return;
			}
		});
	});

});