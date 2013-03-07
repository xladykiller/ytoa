$(function() {

	// 初始化主办人，协办人
	$.post('SZCUserHandler', {
		action : "getAllUsers"
	}, function(data) {
		$(data).each(function(index, o) {
			var html = '<option value="' + o.id + '">' + o.uname + '</option>';
			$('#sponsorSelect').append(html);
			$('#coordinatorSelect').append(html);
		});
	}, 'json');

	$('#searchBtn').bind(
			'click',
			function() {
				var yearSelect = $('#yearSelect').val();
				var typeSelect = $('#typeSelect').val();
				var numberBtn = $('#numberBtn').val();
				var sponsorSelect = $('#sponsorSelect').val();
				var coordinatorSelect = $('#coordinatorSelect').val();
				var fuzzyBtn = $('#fuzzyBtn').val();
				$('#th1,#th2').hide();
				$.post('SZCCaseHandler', {
					action : 'getCase',
					yearSelect : yearSelect,
					typeSelect : typeSelect,
					numberBtn : numberBtn,
					sponsorSelect : sponsorSelect,
					coordinatorSelect : coordinatorSelect,
					fuzzyBtn : fuzzyBtn,
					pageNow : 0,
					pageSize : page.pageSize
				}, function(data) {

					page.init(data.length, "SZCCaseHandler", {
						action : 'getCase',
						yearSelect : $('#yearSelect').val(),
						typeSelect : $('#typeSelect').val(),
						numberBtn : $('#numberBtn').val(),
						sponsorSelect : $('#sponsorSelect').val(),
						coordinatorSelect : $('#coordinatorSelect').val(),
						fuzzyBtn : $('#fuzzyBtn').val()
					}, function() {

					}, function(data) {
						$('body tbody tr').remove();
						$(data).each(
								function(index, o) {
									if (index >= page.pageSize) {
										return;
									}
									var sort = index + 1;
									var html = "";
									if ($('#typeSelect').val() == 1) {
										html = '<tr>' + '<td>' + sort + '</td>'
												+ '<td>'
												+ replace(o.acceptdate)
												+ '</td>' + '<td>'
												+ o.consignor + '</td>'
												+ '<td>' + o.commitment
												+ '</td>' + '<td>'
												+ o.consultfee + '</td>'
												+ '<td>'
												+ replace(o.entrustdate)
												+ '</td>' + '<td>' + o.proxyid
												+ '</td>' + '<td>' + o.relate
												+ '</td>' + '<td>' + o.target
												+ '</td>' + '<td>'
												+ o.agencyfee + '</td>'
												+ '<td>' + o.govadmin + '</td>'
												+ '<td>'
												+ replace(o.filingdate)
												+ '</td>' + '<td>' + o.memo
												+ '</td>' + '<td>'
												+ o.coordinator + '</td>'
												+ '<td>' + o.sponsor + '</td>'
												+ '</tr>';
									} else {
										html = '<tr>' + '<td>' + sort + '</td>'
												+ '<td>' + o.number + '</td>'
												+ '<td>'
												+ replace(o.engagedate)
												+ '</td>' + '<td>'
												+ o.hostinstitution + '</td>'
												+ '<td>'
												+ o.legalrepresentative
												+ '</td>' + '<td>' + o.contact
												+ '</td>' + '<td>' + o.charge
												+ '</td>' + '<td>'
												+ o.recruityear + '</td>'
												+ '<td>' + o.recruitfee
												+ '</td>' + '<td>'
												+ replace(o.filingdate)
												+ '</td>' + '<td>' + o.memo
												+ '</td>' + '<td>'
												+ o.coordinator + '</td>'
												+ '<td>' + o.sponsor + '</td>'
												+ '</tr>';
									}
									$('tbody').append(html);
									// addrow($('#case1 tbody'), elem, index +
									// 1);
								});
					});// end page.init

					var typeSelect = $('#typeSelect').val();
					if (typeSelect == 1) {
						$('#th1').show();
					} else {
						$('#th2').show();
					}
					$('body tbody tr').remove();
					$(data).each(
							function(index, o) {
								if (index >= page.pageSize) {
									return;
								}
								var sort = index + 1;
								var html = "";
								if (typeSelect == 1) {
									html = '<tr>' + '<td>' + sort + '</td>'
											+ '<td>' + replace(o.acceptdate)
											+ '</td>' + '<td>' + o.consignor
											+ '</td>' + '<td>' + o.commitment
											+ '</td>' + '<td>' + o.consultfee
											+ '</td>' + '<td>'
											+ replace(o.entrustdate) + '</td>'
											+ '<td>' + o.proxyid + '</td>'
											+ '<td>' + o.relate + '</td>'
											+ '<td>' + o.target + '</td>'
											+ '<td>' + o.agencyfee + '</td>'
											+ '<td>' + o.govadmin + '</td>'
											+ '<td>' + replace(o.filingdate)
											+ '</td>' + '<td>' + o.memo
											+ '</td>' + '<td>' + o.coordinator
											+ '</td>' + '<td>' + o.sponsor
											+ '</td>' + '</tr>';
								} else {
									html = '<tr>' + '<td>' + sort + '</td>'
											+ '<td>' + o.number + '</td>'
											+ '<td>' + replace(o.engagedate)
											+ '</td>' + '<td>'
											+ o.hostinstitution + '</td>'
											+ '<td>' + o.legalrepresentative
											+ '</td>' + '<td>' + o.contact
											+ '</td>' + '<td>' + o.charge
											+ '</td>' + '<td>' + o.recruityear
											+ '</td>' + '<td>' + o.recruitfee
											+ '</td>' + '<td>'
											+ replace(o.filingdate) + '</td>'
											+ '<td>' + o.memo + '</td>'
											+ '<td>' + o.coordinator + '</td>'
											+ '<td>' + o.sponsor + '</td>'
											+ '</tr>';
								}
								$('tbody').append(html);
								// addrow($('#case1 tbody'), elem, index + 1);
							});

				}, 'json');
			});
	$('#searchBtn').trigger("click");
});