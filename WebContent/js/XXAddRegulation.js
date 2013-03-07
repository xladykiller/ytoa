$(function(){
	//添加规章制度
	$("#addRegulationBtn").bind("click",function(){
		var regulationTitle = $.trim($("#regulationTitle").val());
		var regulationContent = CKEDITOR.instances.regulationContent.getData();
		var regulationAuthor = $.trim($("#regulationAuthor").val());
//		alert(CKEDITOR.instances.regulationContent.getData());
		if(regulationTitle == ""){
			alert("请输入标题");
			return;
		}
		
		$.post("XXAddRegulation",{
			regulationTitle:regulationTitle,
			regulationContent:regulationContent,
			regulationAuthor:regulationAuthor,
		},function(data){
			if(data.ret=="1"){
				alert("添加成功");
				$("#resetBtn").click();
			}
			else if(data.ret=="0"){
				alert("添加失败");
			}
			else if(data.ret == "noPermission"){
				window.location.href="result/noPermission.jsp";
			}
		/*	else if(data.result=="2"){
				alert("标题重复");
			}*/
		},'json');
	});
	
	$("#resetBtn").bind("click",function(){
		$("#regulationTitle").val("");
		$("#regulationAuthor").val("");
		CKEDITOR.instances.regulationContent.setData("");
	});
});