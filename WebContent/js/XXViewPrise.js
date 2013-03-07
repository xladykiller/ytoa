//显示列表
var addrow = function(o, n) {
	var $row = $('<tr><td>'
			+o.account
			+ '</td><td>'
			+o.uname
			+ '</td><td>'
			+ o.prise/10
			+'</td></tr>');
	$("#priseTable tbody").append($row);
};


$(function() {

	$("#startdate").datepicker({
		changeMonth : true,
		changeYear : true
	});
	$("#enddate").datepicker({
		changeMonth : true,
		changeYear : true
	});

	// 查询
	$("#searchPriseBtn").bind("click", function(event, num) {
		$("#priseTable tbody tr").remove();
		var startdate = $("#startdate").val();
		var enddate = $("#enddate").val();
		var account = $.trim($("#searchAccount").val());
		var uname = $.trim($("#searchUname").val());
		if (startdate == "") {
			alert("请选择开始日期");
			return;
		}
		if (enddate == "") {
			alert("请选择结束日期");
			return;
		}
		$.post("XXSelfevaluation", {
			startdate : startdate,
			enddate : enddate,
			account : account,
			uname : uname,
			action : "getAllPrise"
		}, function(data) {

			// 调用XXutils.js，初始化分页
			page.init(data.length, "XXSelfevaluation", {

				startdate : startdate,
				enddate : enddate,
				account : account,
				uname : uname,
				action : "getAllPriseByPage"
			}, function() {
			}, function(data) {
				$("#priseTable tbody tr").remove();
				for ( var i = 0; i < page.pageSize; i++) {
					var o = data[i];
					if (o == null)
						break;
					addrow(o, i + 1);
//					$(window).data("" + o.unameid, o);
				}
			});// end page.init
			// 显示列表
			for ( var i = 0; i < page.pageSize; i++) {
				var o = data[i];
				if (o == null)
					break;
				addrow(o, i + 1);
//				$(window).data("" + o.unameid, o);
			}
			// 回到当前页
			if (num != null && num != undefined && num != "") {
				page.switchPage(num);
			}
		}, 'json');

		/*
		 * $.post("XXSelfevaluation",{ startdate:startdate, enddate:enddate,
		 * action:"getPrise" },function(data){ $("#prise").html(data/10+"元");
		 * });
		 */

	});

});