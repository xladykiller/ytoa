$(function(){
	//选择时间
	$('#updateComplainDate').datepicker({
		changeMonth: true,
		changeYear: true
	});
	
});



var bindFunction = {};

//删除风险记录
bindFunction.delRisk = function() {
	$("input[name=del]").bind("click", function() {
		if (window.confirm("确定删除？")) {
			// 读取id
			var id = $(this).attr('delRisk');
			$.post("XXRisk", {
				id : id,
				action : "del"
			}, function(data) {
				if (data == 1) {
					alert("删除成功");
					// 移除window数据
					$(window).data("" + id, null);
					// 刷新
					$("#searchRiskBtn").trigger('click', [page.pageNow]);
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
bindFunction.showUpdateRisk = function() {
	$("input[name=update]").bind("click", function() {
		// 读取id
		var id = $(this).attr("updateRisk");
		// 根据id读取记录
		var o = $(window).data("" + id);
		// 把id赋给updateRiskBtn（这个按钮就可以根据id操作数据库）
		$("#updateRiskBtn").attr("riskid", id);
		// 赋值记录值
		$("#updateComplainDate").val(replace(o.complaindate));
		$("#updateComplainant").val(o.complainant);
		$("#updateComplainWay").val(o.complainway);
		$("#updatePhone").val(o.phone);
		$("#updateProxyid").val(o.proxyid);
		$("#updateUndertaker").val(o.undertaker);
		$("#updateContent").val(o.content);
		$("#updateResult").val(o.result);
		// 隐藏搜索，显示更新
		$("#searchRisk").hide();
		$("#updateRiskTable").show();
	});
};

//显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+ n
			+ '</td><td>'
			+ replace(o.complaindate)
			+ '</td><td>'
			+ o.complainant
			+ '</td><td>'
			+ o.proxyid
			+ '</td><td>'
			+ o.undertaker
			+ '</td><td><input type="button"  name="del" value="删除" delRisk="'
			+ o.id
			+ '"></td><td><input type="button" name="update" value="修改" updateRisk="'
			+ o.id + '"></td></tr>');
	$("#searchRiskTable tbody").append($row);
};

$(function() {
	// 点击查询
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
			action : "search"
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
				bindFunction.delRisk();
				bindFunction.showUpdateRisk();
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

			bindFunction.delRisk();
			bindFunction.showUpdateRisk();
			//删除修改后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}

		}, 'json');// end post("XXSearchInfo")
	});// end查询============================================================================

	$("#searchRiskBtn").click();

	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchRisk").show();
		$("#updateRiskTable").hide();
	});

	// 提交修改
	$("#updateRiskBtn").bind("click", function() {
		// 从updateRiskBtn读取id
		var id = $(this).attr("riskid");
		var updateComplainDate = $.trim($("#updateComplainDate").val());
		var updateComplainant = $.trim($("#updateComplainant").val());
		var updateComplainWay = $.trim($("#updateComplainWay").val());
		var updatePhone = $.trim($("#updatePhone").val());
		var updateUndertaker = $.trim($("#updateUndertaker").val());
		var updateContent = $.trim($("#updateContent").val());
		var updateResult = $.trim($("#updateResult").val());
		var o = $(window).data("" + id);
		if(updateComplainDate == "" || updateComplainant == "" || updateComplainWay == "" || updatePhone == "" || updateUndertaker == ""
			|| updateContent == ""){
			alert("请把信息填写完整");
			return;
		}
		if (updateComplainDate == replace(o.complaindate) && updateComplainant == o.complainant 
				&& updateComplainWay == o.complainway &&  updatePhone == o.phone
				&& updateUndertaker == o.undertaker &&  updateContent == o.content
				&& updateResult == o.result ) {
			alert("未修改");
			return;
		}
		$.post("XXRisk", {
			id : id,
			updateComplainDate : updateComplainDate,
			updateComplainant : updateComplainant,
			updateComplainWay : updateComplainWay,
			updatePhone:updatePhone,
			updateUndertaker : updateUndertaker,
			updateContent : updateContent,
			updateResult:updateResult,
			action : "update"
		}, function(data) {
			if (data == 1) {
				alert("修改成功");
				$(window).data("" + id, null);
				$("#searchRiskBtn").trigger('click', [page.pageNow]);
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