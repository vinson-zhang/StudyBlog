/**
 * 也可实现，页面加载完就执行
 * $(document).ready(function(){
	
});*/
$(function(){
	//绑定事件
	//$("#login-btn").click(login());
	/**
	 * 更换验证码
	 */
	changeVerifyCodeImage();
	
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
function isEmpty(username,password,verifyCode){
	/**
	 * 验证之前先清除之前的提示
	 */
	$("#username").tooltip("hide");
	$("#password").tooltip("hide");
	$("#verifyCode").tooltip("hide");
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
	if(verifyCode == "" || verifyCode == null){
		alert("请输入验证码");
//		var operations ={
//				placement:"left",
//				title:"验证码不能为空",
//				trigger:"manual"
//		};
//		$("#verifyCode").tooltip(operations);
//		$("#verifyCode").tooltip("show");
		return false;
	}
//	$("#verifyCode").tooltip("hide");
	return true;
}

/**
 * 向后台传递信息
 * @returns
 */
function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	var verifyCode = $("#verifyCode").val();
	if(isEmpty(username, password,verifyCode)){
		$.post("/StudyBlog/user/verifyCodeIsCorrect",
				{
					verifyCode:verifyCode
				},
				function(data){
					if(data == true || data == "true"){
						$("#userinfo").submit();
					}else{
						$("#messageLogin").text("验证码输入有误，请重新输入")
						d = new Date();
						$(".verify-code img").attr("src","/StudyBlog/user/getVerifyCodeImage?date="+d.getTime());
					}
				});
//		$.post();
//		$("#userinfo").submit();
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

/**
 * 更换验证码
 */
function changeVerifyCodeImage(){
	$(".verify-code img").on("click",function(){
		d = new Date();
		$(".verify-code img").attr("src","/StudyBlog/user/getVerifyCodeImage?date="+d.getTime());
	});
}


