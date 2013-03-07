var bindFunction = {};
//查看
bindFunction.viewSelfEval = function() {
	$("a[name=evaldate]").bind("click",function(){
		// 读取id
		var id = $(this).attr("id");
		// 根据id读取记录
		var o = $(window).data("" + id);
		// 赋值记录值
		$("span").html("");
		$("textarea").val("");
		
		if(o.late == "1"){
			$("#late").html(replaceNull2(o.late));
		}if(o.leave == "1"){
			$("#leave").html(replaceNull2(o.leave));
		}if(o.absence == "1"){
			$("#absence").html(replaceNull2(o.absence));
		}if(o.sick == "1"){
			$("#sick").html(replaceNull2(o.sick));
		}if(o.happy == "1"){
			$("#happy").html(replaceNull2(o.happy));
		}if(o.tidy == "1"){
			$("#tidy").html(replaceNull2(o.tidy));
		}if(o.speedy == "1"){
			$("#speedy").html(replaceNull2(o.speedy));
		}if(o.mistake == "1"){
			$("#mistake").html(replaceNull2(o.mistake));
		}if(o.leaveseat == "1"){
			$("#leaveseat").html(replaceNull2(o.leaveseat));
		}if(o.absentwork == "1"){
			$("#absentwork").html(replaceNull2(o.absentwork));
		}if(o.playgames == "1"){
			$("#playgames").html(replaceNull2(o.playgames));
		}
		$("#evaldate").html(replace(o.evaldate));
		$("#result").val(o.result);
		$("#content").val(o.content);
		// 隐藏搜索，显示更新
		$("#searchSelfEval").hide();
		$("#viewSelfEval").show();
	});
};


//显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td><a name="evaldate" href="#" id="'+o.id+'">'
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
			+ o.result.substr(0,10)
			+ '</td><td>'
			+ o.content.substr(0,10)
			+'</td></tr>');
	$("#searchSelfEvalTable tbody").append($row);
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
	//-----------------------
	/*$('#viewSelfEval input[type=checkbox]').attr('disabled',true);*/
	$('#viewSelfEval textarea').attr('readonly',true);
	//-----------------------
	$("#searchSelfEvalBtn").bind("click",function(){
		$("#searchSelfEvalTable tbody tr").remove();
		var startdate = $("#startdate").val();
		var enddate = $("#enddate").val();
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
			action:"search"
		},function(data){

			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXSelfevaluation", {
				
				startdate : startdate,
				enddate : enddate,
				action:"searchByPage"
			}, function() {
				bindFunction.viewSelfEval();
			}, function(data) {
				$("#searchSelfEvalTable tbody tr").remove();
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
			bindFunction.viewSelfEval();
			//查看后回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}		
		},'json');
		
		$.post("XXSelfevaluation",{
			startdate:startdate,
			enddate:enddate,
			action:"getPrise"
		},function(data){
			$("#prise").html(data/10+"元");
		});
		
	});
	
	
	
	// 返回
	$("#returnBtn").bind("click", function() {
		$("#searchSelfEval").show();
		$("#viewSelfEval").hide();
	});
});