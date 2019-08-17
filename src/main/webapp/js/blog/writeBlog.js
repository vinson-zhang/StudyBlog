
$(function(){
	
});

/**
 * 提交blog
 */
function addBlog(){
	//获取要提交的blog内容
	var name = $("#blog-name").val();
	var content = $("#blog-content").val();
	var isVisible = $("#is-visible").val();
	if(name == null || name == ""){
		var operations ={
				placement:"right",
				title:"请输入博客标题",
				trigger:"manual"
		};
		$("#blog-name").tooltip(operations);
		$("#blog-name").tooltip("show");
		return false;
	}
	$("#blog-name").tooltip("hide");
	if(content == null || content == ""){
		var operations ={
				placement:"right",
				title:"请输入博客内容",
				trigger:"manual"
		};
		$("#blog-content").tooltip(operations);
		$("#blog-content").tooltip("show");
		return false;
	}
	if(isVisible ==null || isVisible == ""){
		alert("请勿随意修改代码，合理使用网站！");
		return false;
	}
	$("#blog-content").tooltip("hide");
	var blog = {};
	blog.name = name;
	blog.content = content;
	blog.isVisible = isVisible;
	
	//使用ajax
	$.ajax({
		url:"/StudyBlog/blog/addBlog",
		data:JSON.stringify(blog),
//		data:{
//			name:"hello",
//			content:"hello"
//		},
		//data:blog,
		type:"POST",
		//dataType:"json",
		//headers: {'Content-type': 'application/json;charset=UTF-8'},
		contentType:"application/json; charset=UTF-8",
		success:function(data){
			alert(data);
		}
	},"json");
}

/**
 * 是否清空书写blog的面板
 */
function isReset(){
	var r=confirm("确认清空？");
	if (r==true){
		$("#blog")[0].reset();
		/*
		$("#blog-name").val("");
		$("#blog-content").val("");
		$("#is-visable").selectedIndex=0;
		*/
		return true;
	}else{
	    return false;
	}	
}