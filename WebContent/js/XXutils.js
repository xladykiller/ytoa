
var page = {
	pageSize : 9,
	pageSpan : 5,
	pageCount : 0,
	rowCount : 0,
	pageNow : 0
};

/**
 * 分页显示初始化
 * len 总数据条数
 * otherfunction 数据显示后操作函数， 比如数据上的事件监听器
 */
page.init = function(len,url,params,otherfunction,showdata) {
	page.rowCount = len;
	page.url = url;
	page.params = params;
	page.otherfunction = otherfunction;
	page.showdata = showdata;
	$("#page a").remove();
	$("#paging").show();
	if (page.rowCount == 0) {
		$("#first").hide();
		$("#previous").hide();
		$("#next").hide();
		$("#last").hide();
		page.pageCount = 0;
		//显示页数
		$("#totalPageNum").html('0');
		return;
	}
	// 计算pageCount
	if (page.rowCount % page.pageSize == 0) {
		page.pageCount = page.rowCount / page.pageSize;
	} else {
		page.pageCount = Math.floor(page.rowCount / page.pageSize) + 1;
	}
	// 显示分页(初始化显示从第一页开始)
	var pagenum = 0;
	if (page.pageSpan <= page.pageCount) {
		pagenum = page.pageSpan;
	} else {
		pagenum = page.pageCount;
	}
	
	for ( var i = 1; i <= pagenum; i++) {
		var $page = $('<a href="#" pageNow=' + i + '>' + i + '</a>');
		$("#page").append($page);
		// 第1页a添加css样式
		$("#page a[pageNow=1]").addClass("disable");
		page.pageNow = 1;
	}
    //处理<< < > >>的显示
	$("#first").hide();
	$("#previous").hide();
	if (page.pageCount == 1) {
		// 只有1页
		$("#next").hide();
		$("#last").hide();
	} else {
		// 大于1页
		$("#next").show();
		$("#last").show();
	}
	//显示页数
	$("#totalPageNum").html(page.pageCount);
	//绑定 << < > >>的单击事件
	$("a[name=arrow]").bind("click",function(){
		var id = $(this).attr('id');
		if(id == 'first'){
//			$("a[pageNow=1]").click();
			page.switchPage(1);
		}
		else if(id == 'last'){
			//$("a[pageNow="+page.pageCount+"]").click();
//			showPageByNum(page.pageCount);
			page.switchPage(page.pageCount);
		}
		else if(id == 'previous'){
			var temp = page.pageNow-1;
			$("a[pageNow="+temp+"]").click();
		}
		else if(id == 'next'){
			var temp = page.pageNow+1;
			$("a[pageNow="+temp+"]").click();
		}
	});
	
	//绑定分页监听器
	page.bindPage = function() {
		$("a[pageNow]").bind("click", function() {
			page.pageNow = parseInt($(this).attr("pageNow"));
			page.switchPage(page.pageNow);
		});
	};
	page.bindPage();
};



/**
 * 处理当前页数居中显示
 */
page.changeIndex = function(num) {
	$("#page a").remove();

	var left = Math.floor(page.pageSpan / 2);
	var right = left;

	if (num - left < 1) {
		left = 1;
	} else {
		left = num - left;
	}
	if (num + right > page.pageCount) {
		right = page.pageCount;
	} else {
		right = num + right;
	}

	while (right < page.pageCount && (right - left < page.pageSpan - 1)) {
		right++;
	}
	while (left > 1 && (right - left < page.pageSpan - 1)) {
		left--;
	}

	for ( var i = left; i < num; i++) {
		var $page = $('<a href="#" pageNow=' + i + '>' + i + '</a>');
		$("#page").append($page);
	}
	for ( var i = num; i <= right; i++) {
		var $page = $('<a href="#" pageNow=' + i + '>' + i + '</a>');
		$("#page").append($page);
	}
	$("#page a[pageNow=" + num + "]").addClass("disable");
	page.pageNow = num;
	 //处理<< < > >>的显示
	$("#first").hide();
	$("#previous").hide();
	$("#next").hide();
	$("#last").hide();
	if (page.pageCount == 1) {
	} else if (page.pageNow == 1) {
		$("#next").show();
		$("#last").show();
	} else if (page.pageNow == page.pageCount) {
		$("#first").show();
		$("#previous").show();
	} else {
		$("#first").show();
		$("#previous").show();
		$("#next").show();
		$("#last").show();
	}
};

/**
 * 给定当前页数，显示table和分页
 */
page.switchPage = function(num) {
	//最后一页最后一条记录删除后，num为前一页
	if (num > page.pageCount) {
		num = page.pageCount;
	}
	//查询记录为空
	if(num<=0){
		return;
	}
	page.pageNow = num;
	page.changeIndex(num);
	$.post(/*"XXGetRegulationByPage"*/page.url,$.extend( {
		pageNow : page.pageNow,
		pageSize : page.pageSize/*,
		searchRegulationTitle : $.trim($("#searchRegulationTitle").val())*/
	},page.params), function(data) {
		/*$("#searchRegulationTable tbody tr").remove();
		for ( var i = 0; i < page.pageSize; i++) {
			var o = data[i];
			if (o == null)
				break;
			addrow(o, i + 1);
			$(window).data("" + o.id, o);
		}*/
		//显示列表
		page.showdata(data);
		//绑定分页监听器
		page.bindPage();
		//绑定删除、修改按钮监听器
		page.otherfunction();
//		bindFunction.delRegulation();
//		bindFunction.showUpdateRegulation();
	}, 'json');
};

/*function replace(s) {
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
}*/
function replace(s) {
	if (s != null || s != undefined) {
		var t = [ '十二月', '十一月', '十月', '九月', '八月', '七月', '六月', '五月', '四月', '三月',
				'二月', '一月' ];
		var m = ['Dec','Nov','Oct','Sep','Aug','Jul','Jun','May','Apr','Mar','Feb','Jan'];
		for ( var i = 12; i >= 1; i--) {
			s = s.replace(t[12 - i], m[12-i]);
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
function replaceNull2(s){
	if (s == null || s == undefined) {
		return "";
	}
	return "√";
}