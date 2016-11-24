/**
 * 也可实现，页面加载完就执行
 * $(document).ready(function(){
	
});*/
$(function(){
	//绑定事件
	//$("#login-btn").click(login());
	
});

/**
 * 用于显示提示内容
 * @param content
 * @param id

function showToolTip(content,id){
	var operations ={
			placement:"right",
			title:content,
			trigger:"manual"
	};
	$(id).tooltip(operations);
}
 */

/**
 * 判断用户名密码是否为空
 * @param username
 * @param password
 * @returns {Boolean}
 */
function isEmpty(username,password){
	if(username == ""|| username == null){
		var operations ={
				placement:"right",
				title:"用户名不能为空",
				trigger:"manual"
		};
		$("#username").tooltip(operations);
		$("#username").tooltip("show");
		return false;
	}
	$("#username").tooltip("hide");
	if(password == "" || password == null){
		var operations ={
				placement:"right",
				title:"密码不能为空",
				trigger:"manual"
		};
		$("#password").tooltip(operations);
		$("#password").tooltip("show");
		return false;
	}
	$("#password").tooltip("hide");
	return true;
}

/**
 * 向后台传递信息
 * @returns
 */
function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	if(isEmpty(username, password)){
//		$.post();
		$("#userinfo").submit();
//		$.ajax({
//			url:"/StudyBlog/user/login",
//			type:"POST",
//			data:{
//				username:username,
//				password:password
//			}
//		});
	}
}


