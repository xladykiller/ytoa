var casetype = 1;
var oid = 0;
function replace(s) {
	if (s != null || s != undefined) {
		var t = [ '十二月', '十一月', '十月', '九月', '八月', '七月', '六月', '五月', '四月', '三月',
				'二月', '一月' ];
		var m = [ 'Dec', 'Nov', 'Oct', 'Sep', 'Aug', 'Jul', 'Jun', 'May',
				'Apr', 'Mar', 'Feb', 'Jan' ];
		for ( var i = 12; i >= 1; i--) {
			s = s.replace(t[12 - i], m[12 - i]);
		}
		var date = new Date(s);
		var month = date.getMonth() + 1;
		return date.getFullYear() + "-" + month + "-" + date.getDate();
	} else {
		return "";
	}
}
function bindRowClick1() {
	$('#case1 tbody tr').unbind('dbclick');
	$('#case1 tbody tr').bind('dblclick', function() {
		var id = $(this).attr('rowid');
		oid = id;
		casetype = 1;
		// alert(id);
		$('#searchContainer').hide();
		$('#detailContainner').show();
	});
}
function bindRowClick2() {
	$('#case2 tbody tr').unbind('dbclick');
	$('#case2 tbody tr').bind('dblclick', function() {
		var id = $(this).attr('rowid');
		// alert(id);
		oid = id;
		casetype = 2;
		$('#searchContainer').hide();
		$('#detailContainner').show();
	});
}
function addrow(JQtarget, o, index) {
	var html = '<tr>' + '<td>' + index + '</td>' + '<td>'
			+ replace(o.acceptdate) + '</td>' + '<td>' + o.consignor + '</td>'
			+ '<td>' + o.commitment + '</td>' + '<td>' + o.consultfee + '</td>'
			+ '<td>' + replace(o.entrustdate) + '</td>' + '<td>' + o.proxyid
			+ '</td>' + '<td>' + o.relate + '</td>' + '<td>' + o.target
			+ '</td>' + '<td>' + o.agencyfee + '</td>' + '<td>' + o.govadmin
			+ '</td>' + '<td>' + replace(o.filingdate) + '</td>' + '<td>'
			+ o.memo + '</td>' + '<td>' + o.coordinator + '</td>' + '<td>'
			+ o.sponsor + '</td>' + '</tr>';
	JQtarget.append(html);
}

$(function() {

	// 单击保存案号
	$('#saveProxyBtn').bind('click', function() {
		$.post("SZCCaseHandler", {
			"action" : "saveProxyid",
			"id" : oid,
			"casetype" : casetype,
			"proxyid" : $('#setProxyidBoxText').val()
		}, function(data) {
			if (data.result == 1) {
				alert("添加成功");
			} else {
				alert('添加失败');
			}
		}, "json");
	});
	// 单击修改
	$('#updateBtn').bind('click', function() {
		if(casetype==1){
			var o = $(window).data(oid+"");
			$('#facceptdate').val(replace(o.acceptdate));
			$('#fconsignor').val(o.consignor);
			
			$('#fcommitment').val(o.commitment);
			$('#fconsultfee').val(o.consultfee);
			$('#fentrustdate').val(o.entrustdate);
			$('#fproxyid').val(o.proxyid);
			$('#frelate').val(o.relate);
			$('#ftarget').val(o.target);
			$('#fagencyfee').val(o.agencyfee);
			$('#fgovadmin').val(o.govadmin);
			$('#ffilingdate').val(replace(o.filingdate));
			$('#fmemo').val(o.memo);
			$('#fcoordinator').val(o.coordinator);
			$('#fsponsor').val(o.sponsor);
			$('#case1table').show();
			$('#case2table').hide();
		}else{
			var o = $(window).data(oid+"");
			$('#number').val(o.number);
			
			$('#engagedate').val(o.engagedate);
			$('#hostinstitution').val(o.hostinstitution);
			$('#legalrepresentative').val(o.legalrepresentative);
			$('#contact').val(o.contact);
			$('#charge').val(o.charge);
			$('#recruityear').val(o.recruityear);
			$('#recruitfee').val(o.recruitfee);
			$('#filingdate').val(replace(o.filingdate));
			$('#memo').val(o.memo);
			$('#coordinator').val(o.coordinator);
			$('#sponsor').val(o.sponsor);

			$('#case1table').hide();
			$('#case2table').show();
		}
		$('#setProxyidBox').hide();
		$('#updateBox').show();
		$('#setNumBtn').css("background-color","gray");
		$('#updateBtn').css("background-color","#ffffff");
		
	});

	// 设置案号
	$('#setNumBtn').bind('click', function() {
		$('#updateBox').hide();
		var o = $(window).data(oid + "");
		if(casetype==1){
			$('#setProxyidBoxText').val(o.proxyid);
		}else{
			$('#setProxyidBoxText').val(o.number);
		}
		//alert($('#setNumBtn').css("background-color"));
		$('#setNumBtn').css("background-color","#ffffff");
		$('#updateBtn').css("background-color","#EEEEEE");
		$('#setProxyidBox').show();

	});
	// 点击案号，显示在案号输入框中
	$('#setProxyidBox div a').on('click', function(e) {
		$('#setProxyidBoxText').val($(this).html());
	});

	$('#backBtn').bind('click', function() {
		
		$('#searchContainer').show();
		$('#detailContainner').hide();
		$('#updateBox').hide();
		$('#setProxyidBox').hide();
		$('#searchBtn').trigger("click");
	});
	/**
	 * 年份查询
	 */
	$('#searchBtn').bind(
			'click',
			function(e) {
				var year = $('select').val();
				$('#case1 tbody tr').remove();
				$('#case2 tbody tr').remove();
				$.post('SZCSearchCaseByYear', {
					year : year,
					type : "1"
				}, function(data) {
					$(data).each(
							function(index, o) {
								var sort = index + 1;
								var html = '<tr rowid="' + o.id + '">' + '<td>'
										+ sort + '</td>' + '<td>'
										+ replace(o.acceptdate) + '</td>'
										+ '<td>' + o.consignor + '</td>'
										+ '<td>' + o.commitment + '</td>'
										+ '<td>' + o.consultfee + '</td>'
										+ '<td>' + replace(o.entrustdate)
										+ '</td>' + '<td>' + o.proxyid
										+ '</td>' + '<td>' + o.relate + '</td>'
										+ '<td>' + o.target + '</td>' + '<td>'
										+ o.agencyfee + '</td>' + '<td>'
										+ o.govadmin + '</td>' + '<td>'
										+ replace(o.filingdate) + '</td>'
										+ '<td>' + o.memo + '</td>' + '<td>'
										+ o.coordinator + '</td>' + '<td>'
										+ o.sponsor + '</td>' + '</tr>';
								$(window).data(o.id + "", o);
								$('#case1 tbody').append(html);
								// addrow($('#case1 tbody'), elem, index + 1);
							});
					bindRowClick1();
				}, 'json');
				$.post('SZCSearchCaseByYear', {
					year : year,
					type : "2"
				}, function(data) {
					$(data).each(
							function(index, o) {
								var sort = index + 1;
								var html = '<tr rowid="' + o.id + '">' + '<td>'
										+ sort + '</td>' + '<td>' + o.number
										+ '</td>' + '<td>'
										+ replace(o.engagedate) + '</td>'
										+ '<td>' + o.hostinstitution + '</td>'
										+ '<td>' + o.legalrepresentative
										+ '</td>' + '<td>' + o.contact
										+ '</td>' + '<td>' + o.charge + '</td>'
										+ '<td>' + o.recruityear + '</td>'
										+ '<td>' + o.recruitfee + '</td>'
										+ '<td>' + replace(o.filingdate)
										+ '</td>' + '<td>' + o.memo + '</td>'
										+ '<td>' + o.coordinator + '</td>'
										+ '<td>' + o.sponsor + '</td>'
										+ '</tr>';
								$(window).data(o.id + "", o);
								$('#case2 tbody').append(html);
								// addrow($('#case2 tbody'), elem, index + 1);
							});
					bindRowClick2();
				}, 'json');
			});
});