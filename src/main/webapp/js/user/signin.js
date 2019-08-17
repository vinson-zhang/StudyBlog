/**
 * 也可实现，页面加载完就执行
 * $(document).ready(function(){
	
});*/
$(function(){
	bindEvent();

});

/**
 * 绑定相关事件
 */
function bindEvent(){
	//绑定选择头像事件
	/**
	 * jquery 1.9 之后live换成了on
	 * 说明见以下网址：
	 * http://yishouce.com/article/43.html
	 */
	$("#h-photo").on('change',function(){
		var path = getObjectURL(this.files[0]);
		changeImg(path);
	});
//	$("#h-photo").on('change',changeImg());
//	$("#headphoto").change(changeImg());
//	$("#headphoto").select(changeImg);
	
	/**
	 * 绑定验证表单事件
	 */
	$("#btn-signin").click(function(){
		verifyForm();
	});
	
	/**
	 * 绑定重置事件
	 */
	$("#btn-reset").click(function(){
		resetForm();
	});
	
	/**
	 * 更换验证码
	 */
	changeVerifyCodeImage();
	
}

/**
 * 验证表单
 * @returns {Boolean}
 */
function verifyForm(){
	var nickname = $("#nickname").val();
	//将jquery对象转换成dom对象  
	//链接：http://www.cnblogs.com/conquer/archive/2009/07/24/1529979.html
	var file = $("#h-photo").get(0).files[0];
	var path = null;
	//var path = getObjectURL($("#h-photo").get(0).files[0]);
//	var path = getObjectURL(document.getElementsByName("h-photo").files[0]);
//	var oFiles = document.querySelector("#h-photo");
	var email = $("#email").val();
	var password = $("#password").val();
	var confirmPassword = $("#confirmPassword").val();
	if(nickname==null || nickname == ""){
		showNoticeInfo("请输入昵称");
		return false;
	}
	hideNoticeInfo();
	if(email==null || email == ""){
		showNoticeInfo("请输入邮箱");
		return false;
	}
	hideNoticeInfo();
	if(password==null || password == ""){
		showNoticeInfo("请输入密码");
		return false;
	}
	hideNoticeInfo();
	if(confirmPassword==null || confirmPassword == ""){
		showNoticeInfo("请输入确认密码");
		return false;
	}
	hideNoticeInfo();
	if(password != confirmPassword){
		showNoticeInfo("两次输入的密码不一致");
		return false;
	}
	//验证邮箱是否重复
	$.ajax({
		url:"/StudyBlog/user/usernameIsAvailable",
		type:"post",
		data:{
			email:email
		},
		success:function(data){
			if(data==true){
				hideNoticeInfo();
				if(file == undefined){
					if(!confirm("您还没有选择头像，是否确定使用默认头像？")){
						return false;
					}else{
						path = null; 
					}
				}else{
					path = getObjectURL(file);
				}
				//提交是否真的有选择文件
				alert($("#h-photo").val());
				//将用户名传递给上传图片的form
				$("#path").val($("#h-photo").val());
				var user = {};
				user.headPortrait = path;
				user.nickname = nickname;
				user.email = email;
				user.password = password;
				var verifyCode = $("#verifyCode").val();
				$.post("/StudyBlog/user/verifyCodeIsCorrect",
						{
							verifyCode:verifyCode
						},
						function(data){
							if(data == true || data == "true"){
								submitUserInfo(user);
							}else{
								showNoticeInfo("验证码输入有误，请重新输入");
								$("#verifyCode").val(null);
								d = new Date();
								$(".verify-code img").attr("src","/StudyBlog/user/getVerifyCodeImage?date="+d.getTime());
							}
						});
				
			}else{
				showNoticeInfo("邮箱被占用，请更换邮箱或者找回密码");
				return false;
			}
		}
	});
	
}

/**
 * 重置表单
 */
function resetForm(){
	d = new Date();
	$(".head-photo img").attr("src", "/StudyBlog/user/headPhoto?"+d.getTime());
	hideNoticeInfo();
	$("#signin-form")[0].reset();
}




/**
 * 
 * @param user
 */
function submitUserInfo(user){
//	$("#head-photo-form").submit();
//	$.ajax({
//		url:"signin",
//		type:"post",
//		data:JSON.stringify(user),
//		dataType:"json",
//		contentType:"application/json"
//	});
	$("#signin-form").submit();
	
}

/**
 * 显示提示信息
 * @param notice
 */
function showNoticeInfo(notice){
	$(".notice-info span").text(notice);
}

/**
 * 清除提示信息
 */
function hideNoticeInfo(){
	$(".notice-info span").text("");
}


/**
 * 获取input file 中的文件
 * @param {Object} file
 */
 function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) {
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) {
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
};

/**
 * 更改src 的属性图片
 */
function changeImg(path){
	if(path=="" || path==null){
		return false;
	}
	$(".head-photo img").attr("src",path);
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


