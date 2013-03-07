var bindFunction = {};

// 删除通知
bindFunction.delInfo = function() {
	$("input[name=del]").bind("click", function() {
		if (window.confirm("确定删除？")) {
			// 读取id
			var id = $(this).attr('delInfo');
			$.post("XXInfo", {
				id : id,
				action : "del"
			}, function(data) {
				if (data == 1) {
					alert("删除成功");
					// 移除window数据
					$(window).data("" + id, null);
					// 刷新
					$("#searchInfoBtn").trigger('click', [page.pageNow]);
				} else if(data == 0){
					alert("删除失败");
				}else if(data == 2){
					window.location.href="result/noPermission.jsp";
				}
			});
		}
	});
};

// 弹出修改页面
bindFunction.showUpdateInfo = function() {
	$("input[name=update]").bind("click", function() {
		// 读取id
		var id = $(this).attr("updateInfo");
		// 根据id读取记录
		var o = $(window).data("" + id);
		// 把id赋给updateRegulationBtn（这个按钮就可以根据id操作数据库）
		$("#updateInfoBtn").attr("infoid", id);
		// 赋值记录值
		$("#updateInfoTitle").val(o.title);
		$("#updateInfoAuthor").val(o.author);
		CKEDITOR.instances.updateInfoContent.setData(o.content);
		// 隐藏搜索，显示更新
		$("#searchInfo").hide();
		$("#updateInfo").show();
	});
};

// 显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td style="text-align:left;text-indent:2em;">'
			+ o.title
			+ '</td><td>'
			+ replace(o.addtime)
			+ '</td><td>'
			+ replaceNull(o.author)
			+ '</td><td><input type="button"  name="del" value="删除" delInfo="'
			+ o.id
			+ '"></td><td><input type="button" name="update" value="修改" updateInfo="'
			+ o.id + '"></td></tr>');
	$("#searchInfoTable tbody").append($row);
};

$(function() {
	// 点击查询
	$("#searchInfoBtn").bind("click", function(event,num) {
		// 清空列表和分页
		$("#searchInfoTable tbody tr").remove();
//		$("#page a").remove();
		// 读取查询字段
		var searchInfoTitle = $.trim($("#searchInfoTitle").val());
		// 返回查询结果
		$.post("XXInfo", {
			searchInfoTitle : searchInfoTitle,
			action : "search"
		}, function(data) {
			//判断查询权限
			if(data.length == 1){
				if(data[0].ret != null && data[0].ret != undefined){
					window.location.href="result/noPermission.jsp";
					return;
				}
			}
			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXInfo", {
				searchInfoTitle : searchInfoTitle,
				action:"searchByPage"
			}, function() {
				bindFunction.delInfo();
				bindFunction.showUpdateInfo();
			}, function(data) {
				$("#searchInfoTable tbody tr").remove();
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

			bindFunction.delInfo();
			bindFunction.showUpdateInfo();
			//删除修改后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}

		}, 'json');// end post("XXSearchInfo")
	});// end查询============================================================================

	$("#searchInfoBtn").click();

	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchInfo").show();
		$("#updateInfo").hide();
	});

	// 提交修改
	$("#updateInfoBtn").bind("click", function() {
		// 从updateRegulationBtn读取id
		var id = $(this).attr("infoid");
		var title = $.trim($("#updateInfoTitle").val());
		var author = $.trim($("#updateInfoAuthor").val());
		var content = CKEDITOR.instances.updateInfoContent.getData();
		var o = $(window).data("" + id);
		if (title == "") {
			alert("请填写标题");
			return;
		}
		if (title == o.title && author == o.author && content == o.content) {
			alert("未修改");
			return;
		}
		$.post("XXInfo", {
			id : id,
			title : title,
			author : author,
			content : content,
			action : "update"
		}, function(data) {
			if (data == 1) {
				alert("修改成功");
				$(window).data("" + id, null);
				$("#searchInfoBtn").trigger('click', [page.pageNow]);
				$("#returnBtn").click();
			} else if (data == 0){
				alert("修改失败");
			}else if(data == 2){
				window.location.href="result/noPermission.jsp";
			}
		});
	});

});