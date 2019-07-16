$(function(){
	/*搜索功能*/
	$("#search-btn").click(function(){
		var keyword = $("#search-text").val();
		alert(keyword);
		window.location = "Search?key=" + keyword;
	});

    $("#register-dialog").dialog({
        title: "用户注册",
        modal: true,//表示它是一个模块框
        autoOpen: false, //窗口默认为关闭
        resizable: false,//不能改变大小
        
    });
    $("#btn-register").click(function(){
        $("#register-dialog").dialog("open"); //发生点击才打开
     });

    $("#login-dialog").dialog({
        title: "用户登录",
        modal: true,
        autoOpen:false,
        resizable: false,//不能改变大小
        draggable: false,//不能改变位置
        show: {effct:"slideDown", duration:"fast"},
        hide: {effct:"slideUp", duration:"slow"},
        position:{my:"right top", of: "#btn-login", at: "right bottom"},//固定弹出框位置
    });
    $("#btn-login").click(function(){
        $("#login-dialog").dialog("open"); //发生点击才打开
     });
     $(".avatar").tooltip({
        items:"img",//只显示什么
        content: function(){//显示的东西是什么
            return "<img class='avatar-tooltip' src='" + $(this).attr('src') + "' alt='"+ $(this).attr('alt') + "'>";
        },
        position:{//显示的位置
            my: "left+20 center", at:"right center"
        }
    });
     
     
});
