$("input[type='password'],input[type='text'],input[type='email'],textarea").focus(function() {
	$(this).removeClass("wrong").addClass("onfocus");
}).blur(function() {
	$(this).removeClass("onfocus");
});

$("#username").blur(function(){//当username失去焦点时，发生触发事件
	checkUsernameOnly();
	checkUsername();
});
$("#email").blur(function(){
	checkEmail();
});
$("#password").blur(function(){
	checkPassword();
});
$("#password2").blur(function(){
	checkPassword2();
});

var usernameIsOnly = false;
var usernameIsOk = false;
var emailIsOk = false;
var passwordIsOk = false;
var passwordIsOk2 = false;

//用户名验证
function checkUsername() {
	var userName = $("#username");
	var prompt = $("#username_prompt");
	if(userName.val() == ""){//获取元素值
		userName.addClass("wrong"); //在该元素上添加值为wrong的class属性
		prompt.text("用户名不能为空");
		usernameIsOk = false;
		return;
	}
	if(userName.val().length < 5 || userName.val().length > 20) {
		userName.addClass("wrong");
		prompt.text("用户名长度应在5~20字符之间");
		usernameIsOk = false;
	} else {
		userName.removeClass("wrong");
		prompt.text("");
		usernameIsOk = true;
	}
}
//电子邮箱验证
function checkEmail() {
	var email = $("#email");
	var prompt = $("#email_prompt");
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(email.val() == ""){
		email.addClass("wrong");
		prompt.text("电子邮箱不能为空");
		emailIsOk = false;
		return;
	}
	if(!reg.test(email.val())) {
		email.addClass("wrong");
		prompt.text("电子邮箱格式不正确");
		emailIsOk = false;
	} else {
		email.removeClass("wrong");
		prompt.text("");
		emailIsOk = true;
	}
}
//密码验证
function checkPassword(){
	var password1 = $("#password");
	var prompt = $("#password_prompt");
	if(password1.val() == "") {
		password1.addClass("wrong");
		prompt.text("密码不能为空");
		passwordIsOk = false;
	}else {
		password1.removeClass("wrong");
		prompt.text("");
		passwordIsOk = true;
	}
	checkPassword2();
}
//验证两次密码是否一致
function checkPassword2(){
	var password1 = $("#password");
	var password2 = $("#password2");
	var prompt = $("#password2_prompt");
	if(password1.val() != password2.val()){
		password2.addClass("wrong");
		prompt.text("两次密码不相同");
		passwordIsOk2 = false;
	}else {
		password2.removeClass("wrong");
		prompt.text("");
		passwordIsOk2 = true;
	}
}

function checkUsernameOnly() {
	$.ajax({
		type: "post",
		url: "CheckUsernameServlet",
		data: "username=" + $("#username").val(),
		success: function(data) {
			if(data === "false"){
				$("#username").addClass("wrong");
				$("#username_prompt").text("用户已存在");
				usernameIsOnly = false;
			}else{
				usernameIsOnly = true;
			}
		},error: function (error) {
			alert(error);
		}
	});
}

$(function(){
	$("#cancelRegist").click(function(){
		if(window.confirm("你确定要取消注册吗？")){
			window.location.href = "index.jsp";
		}
	});
});

$(function(){
	$("#doRegist").click(function(){
		checkUsername();
		checkEmail();
		checkPassword();
		checkPassword2();
		checkUsernameOnly();
/*		alert("进入AJAX1")
		alert(usernameIsOk)
		alert(emailIsOk)
		alert(passwordIsOk)
		alert(passwordIsOk2)
		alert(usernameIsOk)*/
		if(usernameIsOk && emailIsOk && passwordIsOk && passwordIsOk2 && usernameIsOnly){
			console.log($("#regist").serialize());
			//alert("进入AJAX2")
			$.ajax({
				   type: "POST",
				   url: "RegistServlet",
				   data: $("#regist").serialize() ,
				   success: function(data){
						//alert("进入AJAX3")
		               $("#register-dialog").dialog("close");
		               window.location.href = "registSuccess.jsp";
				   },error: function (error) {
						alert(error);
					}
				});
		}
	});
});
