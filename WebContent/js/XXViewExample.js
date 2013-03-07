var bindFunction = {};

// 查看典型案例
bindFunction.viewExample = function() {
	$("a[name=exampleTitle]").bind("click",function(){
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
		$("#searchExample").hide();
		$("#viewExample").show();
	});
};


// 显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td style="text-align:left;text-indent:2em;"><a name="exampleTitle" href="#" id="'+o.id+'">'
			+ o.title
			+ '</a></td><td>'
			+ replace(o.addtime)
			+ '</td><td>'
			+ replaceNull(o.author)
			+'</td></tr>');
	$("#searchExampleTable tbody").append($row);
};


$(function(){
	//点击查询
	$("#searchExampleBtn").bind("click", function(event,num) {
		// 清空列表和分页
		$("#searchExampleTable tbody tr").remove();
		$("#page a").remove();
		// 读取查询字段
		var searchExampleTitle = $.trim($("#searchExampleTitle").val());
		// 返回查询结果
		$.post("XXExample", {
			searchExampleTitle : searchExampleTitle,
			action:"search"
		}, function(data) {
			if(data.length == 1){
				if(data[0].ret != null && data[0].ret != undefined){
					window.location.href="result/noPermission.jsp";
					return;
				}
			}
			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXExample", {
				searchExampleTitle : searchExampleTitle,
				action:"searchByPage"
			}, function() {
				bindFunction.viewExample();
			}, function(data) {
				$("#searchExampleTable tbody tr").remove();
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
			bindFunction.viewExample();
			//查看后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}
		}, 'json');
	});// end查询============================================================================
	
	$("#searchExampleBtn").click();
	
	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchExample").show();
		$("#viewExample").hide();
	});

});