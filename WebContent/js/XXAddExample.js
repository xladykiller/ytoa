$(function(){
	//添加案例
	$("#addExampleBtn").bind("click",function(){
		var exampleTitle = $.trim($("#exampleTitle").val());
		var exampleContent = CKEDITOR.instances.exampleContent.getData();
		var exampleAuthor = $.trim($("#exampleAuthor").val());
//		alert(CKEDITOR.instances.regulationContent.getData());
		if(exampleTitle == ""){
			alert("请输入标题");
			return;
		}
		
		$.post("XXExample",{
			exampleTitle:exampleTitle,
			exampleContent:exampleContent,
			exampleAuthor:exampleAuthor,
			action:"add"
		},function(data){
			if(data.ret=="1"){
				alert("添加成功");
				$("#resetBtn").click();
			}
			else if(data.ret=="0"){
				alert("添加失败");
			}
		/*	else if(data.result=="2"){
				alert("标题重复");
			}*/
			else if(data.ret == "noPermission"){
				window.location.href="result/noPermission.jsp";
				return;
			}
		},'json');
	});
	
	$("#resetBtn").bind("click",function(){
		$("#exampleTitle").val("");
		$("#exampleAuthor").val("");
		CKEDITOR.instances.exampleContent.setData("");
	});
});