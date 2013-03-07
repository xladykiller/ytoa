var bindFunction = {};

// 查看规章制度
bindFunction.viewRegulation = function() {
	$("a[name=regulationTitle]").bind("click",function(){
		// 读取id
		var id = $(this).attr("id");
		// 根据id读取记录
		var o = $(window).data("" + id);
		// 赋值记录值
		$("#title").html("");
		$("#author").html("");
		$("#addtime").html("");
		$("#content").html("");
		$("#title").html(o.title);
		$("#author").html(o.author);
		$("#addtime").html(replace(o.addtime));
		$("#content").html(o.content);
		// 隐藏搜索，显示更新
		$("#searchRegulation").hide();
		$("#viewRegulation").show();
	});
};


// 显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td style="text-align:left;text-indent:2em;" ><a name="regulationTitle" href="#" id="'+o.id+'">'
			+ o.title
			+ '</a></td><td>'
			+ replace(o.addtime)
			+ '</td><td>'
			+ replaceNull(o.author)
			+'</td></tr>');
	$("#searchRegulationTable tbody").append($row);
};


$(function(){
	//点击查询
	$("#searchRegulationBtn").bind("click", function(event,num) {
		// 清空列表和分页
		$("#searchRegulationTable tbody tr").remove();
		$("#page a").remove();
		// 读取查询字段
		var searchRegulationTitle = $.trim($("#searchRegulationTitle").val());
		// 返回查询结果
		$.post("XXSearchRegulation", {
			searchRegulationTitle : searchRegulationTitle
		}, function(data) {
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
				bindFunction.viewRegulation();
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
			//绑定标题链接
			bindFunction.viewRegulation();
			//查看后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}
		}, 'json');
	});// end查询============================================================================
	
	$("#searchRegulationBtn").click();
	
	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchRegulation").show();
		$("#viewRegulation").hide();
	});

});