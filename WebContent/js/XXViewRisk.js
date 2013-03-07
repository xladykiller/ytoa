var bindFunction = {};

// 查看
bindFunction.viewRisk = function() {
	$("a[name=proxyid]").bind("click",function(){
		// 读取id
		var id = $(this).attr("id");
		// 根据id读取记录
		var o = $(window).data("" + id);
		// 赋值记录值
		$("#complaindate").html("");
		$("#complainant").html("");
		$("#complainway").html("");
		$("#phone").html("");
		$("#proxyid").html("");
		$("#undertaker").html("");
		$("#content").html("");
		$("#result").html("");
		
		$("#complaindate").html(replace(o.complaindate));
		$("#complainant").html(o.complainant);
		$("#complainway").html(o.complainway);
		$("#phone").html(o.phone);
		$("#proxyid").html(o.proxyid);
		$("#undertaker").html(o.undertaker);
		$("#content").html(o.content);
		$("#result").html(o.result);
		// 隐藏搜索，显示更新
		$("#searchRisk").hide();
		$("#viewRisk").show();
	});
};


// 显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td>'
			+ replace(o.complaindate)
			+ '</td><td>'
			+ o.complainant
			+ '</td><td><a name="proxyid" href="#" id="'+o.id+'">'
			+ o.proxyid
			+ '</a></td><td>'
			+ o.undertaker			
			+'</td></tr>');
	$("#searchRiskTable tbody").append($row);
};


$(function(){
	//点击查询
	$("#searchRiskBtn").bind("click", function(event,num) {
		// 清空列表和分页
		$("#searchRiskTable tbody tr").remove();
//		$("#page a").remove();
		// 读取查询字段
		var searchYear = $.trim($("#searchYear").val());
		var searchComplainant = $.trim($("#searchComplainant").val());
		var searchProxyid = $.trim($("#searchProxyid").val());
		var searchUndertaker = $.trim($("#searchUndertaker").val());
		// 返回查询结果
		$.post("XXRisk", {
			searchYear : searchYear,
			searchComplainant : searchComplainant,
			searchProxyid : searchProxyid,
			searchUndertaker : searchUndertaker,
			action:"search"
		}, function(data) {
			if(data.length == 1){
				if(data[0].ret != null && data[0].ret != undefined){
					window.location.href="result/noPermission.jsp";
					return;
				}
			}
			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXRisk", {
				searchYear : searchYear,
				searchComplainant : searchComplainant,
				searchProxyid : searchProxyid,
				searchUndertaker : searchUndertaker,
				action:"searchByPage"
			}, function() {
				bindFunction.viewRisk();
			}, function(data) {
				$("#searchRiskTable tbody tr").remove();
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
			bindFunction.viewRisk();
			//查看后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}
		}, 'json');
	});// end查询============================================================================
	
	$("#searchRiskBtn").click();
	
	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchRisk").show();
		$("#viewRisk").hide();
	});

});