function replace(s) {
	if (s != null || s != undefined) {
		var t = [ '十二月', '十一月', '十月', '九月', '八月', '七月', '六月', '五月', '四月', '三月',
				'二月', '一月' ];
		for ( var i = 12; i >= 1; i--) {
			s = s.replace(t[12 - i], i);
		}
		var date = new Date(s);
		var month = date.getMonth() + 1;
		return date.getFullYear() + "-" + month + "-" + date.getDate();
	} else {
		return "";
	}
}

function replaceNull(s) {
	if (s == null || s == undefined || s == "") {
		return "无";
	}
	return s;
}

var bindFunction = {};
// 删除规章制度
bindFunction.delRegulation = function() {
	$("input[name=del]").bind("click", function() {
		if (window.confirm("确定删除？")) {
			// 读取要删除用户的account
			var id = $(this).attr('delRegulation');
			$.post("XXDelRegulation", {
				id : id
			}, function(data) {
				if (data == 1) {
					alert("删除成功");
					$(window).data("" + id, null);
					// $("#searchRegulationBtn").click();
					refresh();
				} else {
					alert("删除失败");
				}
			});
		}
	});
};

// 弹出修改页面
bindFunction.showUpdateRegulation = function() {
	$("input[name=update]").bind("click", function() {
		// 读取要修改规章的id
		var id = $(this).attr("updateRegulation");
		var o = $(window).data("" + id);
		// 把id赋给updateRegulationBtn
		$("#updateRegulationBtn").attr("regulationid", id);
		$("#updateRegulationTitle").val(o.title);
		$("#updateRegulationAuthor").val(o.author);
		CKEDITOR.instances.updateRegulationContent.setData(o.content);
		$("#searchRegulation").hide();
		$("#updateRegulation").show();
	});
};

//显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td>'
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


function refresh() {
	// $(function(){
	$("#searchRegulationTable tbody tr").remove();
	$("#page a").remove();

	// 分页显示规章列表==================================================================
	$.post("XXSearchRegulation", {
		searchRegulationTitle : ""
	}, function(data) {
		var pageSize = 5;
		var rowCount = data.length;
		var pageCount = 0;
		//计算pageCount
		if (rowCount % pageSize == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = Math.floor(rowCount / pageSize) + 1;
		}
		//显示分页
		for ( var i = 1; i <= pageCount; i++) {
			if (pageCount == 1)
				break;
			var $page = $('<a href="#" pageNow=' + i + '>' + i + '</a>');
			$("#page").append($page);
		}
	    //显示列表
		for ( var i = 0; i < pageSize; i++) {
			var o = data[i];
			if (o == null)
				break;
			addrow(o, i + 1);
			$(window).data("" + o.id, o);
		}

		// 点击分页------------------------------------------------------------------------
		$("a[pageNow]").bind(
				"click",
				function() {
					var pageNow = $(this).attr("pageNow");
					$.post("XXGetRegulationByPage", {
						pageNow : pageNow,
						pageSize : pageSize,
					}, function(data) {
						$("#searchRegulationTable tbody tr").remove();
						for ( var i = 0; i < pageSize; i++) {
							var o = data[i];
							if (o == null)
								break;
							addrow(o, i + 1);
							$(window).data("" + o.id, o);
						}

						bindFunction.delRegulation();
						bindFunction.showUpdateRegulation();

					}, 'json');
				});// end 点击分页---------------------------------------------------------------------------------

		bindFunction.delRegulation();
		bindFunction.showUpdateRegulation();

	}, 'json');// end显示分页=================================================================

	
	// 查询规章制度，显示规章列表==========================================================
	$("#searchRegulationBtn").bind("click", function() {
		var searchRegulationTitle = $.trim($("#searchRegulationTitle").val());
		$.post("XXSearchRegulation", {
			searchRegulationTitle : searchRegulationTitle
		}, function(data) {
			// 显示规章列表
			$("#searchRegulationTable tbody tr").remove();
			$("#page a").remove();
			var length = data.length;
			for ( var i = 0; i < length; i++) {
				var o = data[i];
				if (o == null)
					break;
				addrow(o, i + 1);
				$(window).data("" + o.id, o);
			}

			bindFunction.delRegulation();
			bindFunction.showUpdateRegulation();

		}, 'json');
	});// end查询============================================================================

	// });
}

$(function() {
	refresh();

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
				// $("#searchRegulationBtn").click();
				refresh();
				$("#returnBtn").click();
			} else {
				alert("修改失败");
			}
		});
	});

});