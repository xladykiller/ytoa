var bindFunction = {};

//显示更新页面
bindFunction.viewSelfEval = function() {
	$("input[name=update]").bind("click",function(){
		// 读取id
		var id = $(this).attr("updateEval");
		// 根据id读取记录
		var o = $(window).data("" + id);
		//把id赋值给updateEvalBtn
		$("#updateEvalBtn").attr("evalid",id);
		//清空
		$("span").html("");
		$("textarea").val("");
		$("input[type=checkbox]").attr("checked",false);
		// 赋值记录值
		if(o.late == "1"){
			$("#late").prop("checked",true);
		}if(o.leave == "1"){
			$("#leave").prop("checked",true);
		}if(o.absence == "1"){
			$("#absence").prop("checked",true);
		}if(o.sick == "1"){
			$("#sick").prop("checked",true);
		}if(o.happy == "1"){
			$("#happy").prop("checked",true);
		}if(o.tidy == "1"){
			$("#tidy").prop("checked",true);
		}if(o.speedy == "1"){
			$("#speedy").prop("checked",true);
		}if(o.mistake == "1"){
			$("#mistake").prop("checked",true);
		}if(o.leaveseat == "1"){
			$("#leaveseat").prop("checked",true);
		}if(o.absentwork == "1"){
			$("#absentwork").prop("checked",true);
		}if(o.playgames == "1"){
			$("#playgames").prop("checked",true);
		}
		$("#updateEvaldate").val(replace(o.evaldate));
		$("#account").html(o.account);
		$("#uname").html(o.uname);
		$("#result").val(o.result);
		$("#content").val(o.content);
		// 隐藏搜索，显示更新
		$("#searchEval").hide();
		$("#viewSelfEval").show();
	});
};


//删除
bindFunction.delSelfEval = function() {
	$("input[name=del]").bind("click", function() {
		if (window.confirm("确定删除？")) {
			// 读取id
			var id = $(this).attr('delEval');
			$.post("XXSelfevaluation", {
				id : id,
				action : "del"
			}, function(data) {
				if (data == 1) {
					alert("删除成功");
					// 移除window数据
					$(window).data("" + id, null);
					// 刷新
					$("#searchEvalBtn").trigger('click', [page.pageNow]);
				} else {
					alert("删除失败");
				}
			});
		}
	});
};

//显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+o.account
			+ '</td><td>'
			+o.uname
			+ '</td><td>'
			+  replace(o.evaldate)
			+ '</a></td><td>'
			+ replaceNull2(o.late)
			+ '</td><td>'
			+ replaceNull2(o.leave)
			+ '</td><td>'
			+ replaceNull2(o.absence)
			+ '</td><td>'
			+ replaceNull2(o.sick)
			+ '</td><td>'
			+ replaceNull2(o.happy)
			+ '</td><td>'
			+ replaceNull2(o.tidy)
			+ '</td><td>'
			+ replaceNull2(o.speedy)	
			+ '</td><td>'
			+ replaceNull2(o.mistake)	
			+ '</td><td>'
			+ replaceNull2(o.leaveseat)	
			+ '</td><td>'
			+ replaceNull2(o.absentwork)	
			+ '</td><td>'
			+ replaceNull2(o.playgames)	
			+ '</td><td>'
			+ o.result.substr(0,8)
			+ '</td><td>'
			+ o.content.substr(0,8)
			+ '</td><td><input type="button"  name="del" value="删除" delEval="'
			+ o.id
			+ '"></td><td><input type="button" name="update" value="修改" updateEval="'
			+ o.id + '"></td></tr>');
			
	$("#searchEvalTable tbody").append($row);
};


$(function(){
	$("#startdate").datepicker({
		changeMonth:true,
		changeYear:true
	});
	$("#enddate").datepicker({
		changeMonth:true,
		changeYear:true
	});
	$("#updateEvaldate").datepicker({
		changeMonth:true,
		changeYear:true
	});
	//查询
	$("#searchEvalBtn").bind("click",function(event,num){
		$("#searchEvalTable tbody tr").remove();
		var startdate = $("#startdate").val();
		var enddate = $("#enddate").val();
		var account =$.trim( $("#searchAccount").val());
		var uname =$.trim( $("#searchUname").val());
		if(startdate == "" ){
			alert("请选择开始日期");
			return;
		}
		if(enddate == "" ){
			alert("请选择结束日期");
			return;
		}
		$.post("XXSelfevaluation",{
			startdate:startdate,
			enddate:enddate,
			account:account,
			uname:uname,
			action:"searchAll"
		},function(data){

			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXSelfevaluation", {
				
				startdate : startdate,
				enddate : enddate,
				account:account,
				uname:uname,
				action:"searchAllByPage"
			}, function() {
				bindFunction.viewSelfEval();
				bindFunction.delSelfEval();
			}, function(data) {
				$("#searchEvalTable tbody tr").remove();
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
			//绑定
			bindFunction.viewSelfEval();
			bindFunction.delSelfEval();
			//回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}		
		},'json');
		
		/*$.post("XXSelfevaluation",{
			startdate:startdate,
			enddate:enddate,
			action:"getPrise"
		},function(data){
			$("#prise").html(data/10+"元");
		});*/
		
	});
	
	//提交更新
	$("#updateEvalBtn").bind("click", function() {
		var id = $(this).attr("evalid");
		var o = $(window).data("" + id);
		var uname =o.uname;
		var unameid = o.unameid;
		var updateEvaldate = $.trim($("#updateEvaldate").val());
		var result = $.trim($("#result").val());
		var content = $.trim($("#content").val());
		var checked = $("table input:checked");
		var params = [];
		checked.each(function(index) {
			params.push($(this).val());
		});
		$.post('XXSelfevaluation', {
			"id":id,
			"params[]" : params,
			"evaldate" : updateEvaldate,
			"uname":uname,
			"unameid":unameid,
			"result" : result,
			"content" : content,
			"action" : "update"
		}, function(data) {
			if(data.result=="1"){
				alert("修改成功");
				//刷新，返回
				$("#searchEvalBtn").trigger('click', [page.pageNow]);
				$("#returnBtn").click();
			}
			else if(data.result=="0"){
				alert("修改失败");
			}
		},'json');
	});
	
	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchEval").show();
		$("#viewSelfEval").hide();
	});
});