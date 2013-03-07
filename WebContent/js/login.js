$(function() {
	$('#submitbtn')
			.bind(
					'click',
					function(e) {
						var account = $.trim($('#account').val()), psw = $
								.trim($('#psw').val()), safecode = $.trim($(
								'#safecode').val());
						if (account == "") {
							alert('登陆名称不能为空!');
							return;
						}
						if (psw == "") {
							alert('登陆密码不能为空!');
							return;
						}
						if (safecode == "") {
							alert('验证码不能为空!');
							return;
						}
						// var sear = $('form').serializeArray();

						$.post('Login', {
							account : account,
							psw : psw,
							safecode : safecode
						}, function(data) {
							// var ret = $.parseJSON(data);
							// alert(ret);
							if (data.status == 'ok') {
								window.location.href=data.lastReq;
							} else {
								refreshSafeCode();
								$('#tdinfo').show();

							}
							// alert(data.status);
						}, 'json');

					});
});