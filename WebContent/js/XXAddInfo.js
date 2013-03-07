$(function() {
	$('form').submit(
			function() {
				var filepath = $("input[name='attachment']").val();
				if (filepath != "") {
					var extStart = filepath.lastIndexOf(".");
					var ext = filepath.substring(extStart, filepath.length)
							.toUpperCase();
					if (ext != ".RAR" && ext != ".ZIP") {
						alert("请把文件打包成rar或zip格式上传");
						return false;
					}
				}
				return true;
			});
	// 添加通知
	$("#addInfoBtn").bind("click", function() {
		var infoTitle = $.trim($("#infoTitle").val());
		/*
		 * var infoContent = CKEDITOR.instances.infoContent.getData(); var
		 * infoAuthor = $.trim($("#infoAuthor").val()); var attachment = "";
		 */
		if (infoTitle == "") {
			alert("请输入标题");
			return;
		}
		$('form').submit();

		/*
		 * $.post("XXInfo",{ infoTitle:infoTitle, infoContent:infoContent,
		 * infoAuthor:infoAuthor, attachment:attachment, action:"add"
		 * },function(data){ if(data.result=="1"){ attachment = $.trim($("form
		 * input[name=attachment]").val()); if(attachment!=""){
		 * $('form').get(0).submit(); } alert("添加成功");
		 * $('form').get(0).submit(); $("#resetBtn").click(); } else
		 * if(data.result=="0"){ alert("添加失败"); } else if(data.result=="2"){
		 * alert("标题重复"); } },'json');
		 */
	});

	$("#resetBtn").bind("click", function() {
		$("#infoTitle").val("");
		$("#infoAuthor").val("");
		CKEDITOR.instances.infoContent.setData("");
	});
});