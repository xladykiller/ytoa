//2

var bindFunction = {};

// 删除规章制度
bindFunction.delRegulation = function() {
	$("input[name=del]").bind(
			"click",
			function() {
				if (window.confirm("确定删除？")) {
					// 读取id
					var id = $(this).attr('delRegulation');
					$.post("XXDelRegulation", {
						id : id
					}, function(data) {
						if (data == 1) {
							alert("删除成功");
							// 移除window数据
							$(window).data("" + id, null);
							// 刷新
							$("#searchRegulationBtn").trigger('click',
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
bindFunction.showUpdateRegulation = function() {
	$("input[name=update]").bind("click", function() {
		// 读取id
		var id = $(this).attr("updateRegulation");
		// 根据id读取记录
		var o = $(window).data("" + id);
		// 把id赋给updateRegulationBtn（这个按钮就可以根据id操作数据库）
		$("#updateRegulationBtn").attr("regulationid", id);
		// 赋值记录值
		$("#updateRegulationTitle").val(o.title);
		$("#updateRegulationAuthor").val(o.author);
		CKEDITOR.instances.updateRegulationContent.setData(o.content);
		// 隐藏搜索，显示更新
		$("#searchRegulation").hide();
		$("#updateRegulation").show();
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
			+ '</td><td><input type="button"  name="del" value="删除" delRegulation="'
			+ o.id
			+ '"></td><td><input type="button" name="update" value="修改" updateRegulation="'
			+ o.id + '"></td></tr>');
	$("#searchRegulationTable tbody").append($row);
};

$(function() {
	// 点击查询
	$("#searchRegulationBtn").bind("click", function(event, num) {

		// 清空列表和分页
		$("#searchRegulationTable tbody tr").remove();
//		$("#page a").remove();
		// 读取查询字段
		var searchRegulationTitle = $.trim($("#searchRegulationTitle").val());
		// 返回查询结果
		$.post("XXSearchRegulation", {
			searchRegulationTitle : searchRegulationTitle
		}, function(data) {
			//判断权限
			if(data.length == 1){
				if(data[0].ret != null && data[0].ret != undefined){
					window.location.href="result/noPermission.jsp";
					return;
				}
			}
			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXGetRegulationByPage", {
				searchRegulationTitle : searchRegulationTitle
			}, function() {
				bindFunction.delRegulation();
				bindFunction.showUpdateRegulation();
			}, function(data) {
				$("#searchRegulationTable tbody tr").remove();
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

			bindFunction.delRegulation();
			bindFunction.showUpdateRegulation();
			
			//删除修改后回到当前页
			if (num != null && num != undefined && num != "") {
					page.switchPage(num);				
			}

		}, 'json');// end post("XXSearchRegulation")
	});// end查询============================================================================

	$("#searchRegulationBtn").click();

	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchRegulation").show();
		$("#updateRegulation").hide();
	});

	// 提交修改
	$("#updateRegulationBtn").bind("click", function() {
		// 从updateRegulationBtn读取id
		var id = $(this).attr("regulationid");
		var title = $.trim($("#updateRegulationTitle").val());
		var author = $.trim($("#updateRegulationAuthor").val());
		var content = CKEDITOR.instances.updateRegulationContent.getData();
		var o = $(window).data("" + id);
		if (title == "") {
			alert("请填写标题");
			return;
		}
		if (title == o.title && author == o.author && content == o.content) {
			alert("未修改");
			return;
		}
		$.post("XXUpdateRegulation", {
			id : id,
			title : title,
			author : author,
			content : content,
		}, function(data) {
			if (data == 1) {
				alert("修改成功");
				$(window).data("" + id, null);
				$("#searchRegulationBtn").trigger('click', [ page.pageNow ]);
				$("#returnBtn").click();
			} else if(data == 0){
				alert("修改失败");
			}else if(data == 2){
				window.location.href="result/noPermission.jsp";
			}
		});
	});

});