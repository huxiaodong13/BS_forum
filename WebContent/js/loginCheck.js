$("#name").blur(function(){//当username失去焦点时，发生触发事件
	checkUsername();
});

$("#pwd").blur(function(){
	checkPassword();
});

var nameIsOk = false;
var pwdIsOk = false;
function checkUser() {
	var username_prompt = $("#name_prompt");
	var username_val = $("#name").val();
	if(username_val.trim() == "") {
		username_prompt.text("用户名不能为空");
	} else {
		username_prompt.text("");
		nameIsOk  = true;
	}
	return;
}

function checkPass() {
	var password_prompt = $("#pwd_prompt");
	var password_val = $("#pwd").val();
	if(password_val.trim() == "") {
		password_prompt.text("密码不能为空");
	} else {
		password_prompt.text("");
		pwdIsOk =  true;
	}
	return;
}


$(function(){
	$("#cancelLogin").click(function(){
		if(window.confirm("你确定要取消登录吗？")){
			window.location.href = "index.jsp";
		}
	});
	$("#doLogin").click(function(){
		checkUser();
		checkPass();
		//alert(nameIsOk);
		//alert(pwdIsOk);
		if(nameIsOk && pwdIsOk){
			$.ajax({
				type: "POST",
				url: "LoginServlet",
				data: $("#login").serialize(),
				success: function(data) {
					if(data === "no-such-user"){
						var username_prompt = $("#name_prompt");
						username_prompt.text("用户不存在");
						$("#pwd").val("");
						$("#name").focus();
					}else if(data === "wrong-password"){
						var password_prompt = $("#pwd_prompt");
						password_prompt.text("密码错误");
						$("#pwd").val("");
						$("#pwd").focus();
					}else if(data === "login-success") {
						window.location.href = "loginSuccessful.jsp";
					}else {
						alert("粗错啦！");
					}
				}
			});	
		}
	});
});


