var bindFunction = {};

// 查看通知
bindFunction.viewInfo = function() {
	$("a[name=infoTitle]").bind("click",function(){
		$("#downloadAttachment").hide();
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
		//attachment字段不为空，显示下载附件超链接
		if(o.attachment != "" && o.attachment != null){
			$("#downloadAttachment").show();
			$("#downloadAttachment").attr("href",o.attachment);
		}
		// 隐藏搜索，显示更新
		$("#searchInfo").hide();
		$("#viewInfo").show();
	});
};


// 显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td style="text-align:left;text-indent:2em;"><a name="infoTitle" href="#" id="'+o.id+'">'
			+ o.title
			+ '</a></td><td>'
			+ replace(o.addtime)
			+ '</td><td>'
			+ replaceNull(o.author)
			+'</td></tr>');
	$("#searchInfoTable tbody").append($row);
};


$(function(){
	
	$("#searchInfoBtn").bind("click", function(event,num) {
		// 清空列表和分页
		$("#searchInfoTable tbody tr").remove();
		$("#page a").remove();
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
				bindFunction.viewInfo();
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

			bindFunction.viewInfo();

			//查看后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}

		}, 'json');// end post("XXSearchInfo")
	});// end查询============================================================================
	
	$("#searchInfoBtn").click();
	
	/*$("#downloadAttachment").bind("click", function() {
		var id = $(this).attr("infoid");
//		alert(id);
		$.post("XXInfo",{
			id:id,
			action:"download"
		},function(data){
			
		});
	});*/
	
	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchInfo").show();
		$("#viewInfo").hide();
	});

});