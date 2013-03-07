var bindFunction = {};

// 查看
bindFunction.viewStamp = function() {
	$("a[name=useReason]").bind("click",function(){
		// 读取id
		var id = $(this).attr("id");
		// 根据id读取记录
		var o = $(window).data("" + id);
		// 赋值记录值
		$("#usedate").html("");
		$("#usereason").html("");
		$("#petitioner").html("");
		$("#uname").html("");
		$("#filenum").html("");
		$("#memo").html("");
		
		$("#usedate").html(replace(o.usedate));
		$("#usereason").html(o.usereason);
		$("#petitioner").html(o.petitioner);
		$("#uname").html(o.uname);
		$("#filenum").html(o.filenum);
		$("#memo").html(o.memo);
		// 隐藏搜索，显示更新
		$("#searchStamp").hide();
		$("#viewStamp").show();
	});
};


// 显示列表
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
			+ '</td><td style="text-align:left;text-indent:2em;"><a name="useReason" href="#" id="'+o.id+'">'
			+ o.usereason.substr(0,45)
			+'</a></td></tr>');
	$("#searchStampTable tbody").append($row);
};


$(function(){
	//点击查询
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
			action:"search"
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
				bindFunction.viewStamp();
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
			//绑定标题链接
			bindFunction.viewStamp();
			//查看后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}
		}, 'json');
	});// end查询============================================================================
	
	$("#searchStampBtn").click();
	
	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchStamp").show();
		$("#viewStamp").hide();
	});

});