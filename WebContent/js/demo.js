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


//js函数代码：字符串转换为时间戳
function getDateTimeStamp(dateStr) {
  return Date.parse(dateStr.replace(/-/g,"/"));
} 

//计算时差
function getDateDiff(dateTimeStamp) {
  var minute = 1000 * 60; 
  var hour = minute * 60;
  var day = hour * 24;
  var halfamonth = day * 15;
  var month = day * 30;
  var now = new Date().getTime();
  var diffValue = now - dateTimeStamp;
  if (diffValue < 0) {
      //若日期不符则弹窗口告之,结束日期不能小于开始日期！
  }
  var monthC = diffValue / month;
  var weekC = diffValue / (7 * day);
  var dayC = diffValue / day;
  var hourC = diffValue / hour;
  var minC = diffValue / minute;
  if (monthC >= 1) {
      result = "发表于" + parseInt(monthC) + "个月前";
  }else if (weekC >= 1) {
      result = "发表于" + parseInt(weekC) + "周前";
  }else if (dayC >= 1) {
      result = "发表于" + parseInt(dayC) + "天前";
  }else if (hourC >= 1) {
      result = "发表于" + parseInt(hourC) + "个小时前";
  }else if (minC >= 1) {
      result = "发表于" + parseInt(minC) + "分钟前";
  } else
      result = "刚刚发表";
  return result;
}
//中间界面左边部分显示的帖子
$(function(){
	 $.ajax({
	        type: 'POST',
	        url: 'ShowPosts', 
	        dataType: 'json', 
	        data: { },
	        success: function(data){ 
	       	 var posthtml;
	       	 $("#posts").html("");
	       	 $.each(data,function(idx,obj)
	       			{  
	       				//console.log(obj.title);
	       				//console.log(obj.postingtime) 
	       				//console.log(obj.replycount)
	       				var FormatDate = getDateTimeStamp(obj.postingtime);
	       				var ReleaseTime = getDateDiff(FormatDate);
	       				var lastReply = "";
	       				$.ajax({
	       					type: 'POST',
					         url: 'FindLastComment', 
					         dataType: 'json', 
					         data: {"postid":obj.postid},
					         success: function (data_1){
					        	 if(data_1.username!="不存在该用户"){
					        		 lastReply="最后回复   来自于   "+ data_1.username;
					        	 }
					        	 else{
					        	 }
			       				 posthtml=' <div class="post clearfix">'+
			        	 			'<div class="avatar">'+
		                             '<a href="' + "ReadUserPosts.jsp?username=" + obj.username +'">'+
			        	 				'<img src="'+obj.photo+'" alt=""></a>'+
			                       ' </div>'+
			                        '<div class="post-title">'+
				                        '<div class="post-title-text">'+
				                        '<a href="detailpost.jsp?postid='+obj.postid+'" title="'+obj.title+'">'+obj.title+'</a>'+
				                       ' </div>'+
				                       '<div class="post-info clearfix">'+
				                        	'<div class="post-info-text">'+obj.username+ '---'+ ReleaseTime+' '+lastReply+'</div>'+
				                        	'<div class="reply-count">'+obj.replycount+'</div>'+
				                        '</div>'+
				                    '</div>'+        
			                    '</div>'+''
		       				$("#posts").append(posthtml)
					         }
	       				});

	       			}
	       	 );
	        },
	        error: function (XMLHttpRequest, textStatus, errorThown) {
	            alert("左边部分帖子，操作失败！");
	        }
	    });
});

//加载热帖
$(function(){
	$.ajax({
        type: 'POST',
        url: 'ShowHot', 
        dataType: 'json', 
        data: { },
        success: function(data){
        	var hotposthtml;
	       	 $("#hot-posts").html("");
	       	 $.each(data,function(idx,obj)
		       			{  
		       				
		       				 posthtml=
		       					 '<div class="hot-post clearfix">'+
                             '<div class="hot-avatar">'+
                             '<img src="'+obj.photo+'" alt="">'+
                         '</div>'+
                         '<div class="hot-post-text">'+
                         '<a href="detailpost.jsp?postid='+obj.postid+'" title="'+obj.title+'">'+obj.title+'</a>'+
             '</div>'+''
		       				$("#hot-posts").append(posthtml)

		       			}
		       	 );
        },
        error: function (XMLHttpRequest, textStatus, errorThown) {
            alert("排列热帖操作失败！");
        }
        });
});
//论坛信息统计
$(function(){
	$.ajax({
        type: 'POST',
        url: 'ShowCount', 
        dataType: 'json', 
        data: { },
        success: function(data){
        	var hotposthtml;
	       	 $("#count").html("");
	       	 if(data!=null){
	       		posthtml=
   					'<div id="statistcs">'+
                    '<div class="statistics-item clearfix">'+
                        '<div class="statistics-name">'+"注册会员:"+'</div>'+
                        '<div class="statistics-value">'+data.userCount+'</div>'+
                    '</div>'+
                '</div>'+
                '<div id="statistcs">'+
                        '<div class="statistics-item clearfix">'+
                            '<div class="statistics-name">'+"主题:"+'</div>'+
                            '<div class="statistics-value">'+data.postCount+'</div>'+
                        '</div>'+
                    '</div>'+
                    '<div id="statistcs">'+
                            '<div class="statistics-item clearfix">'+
                                '<div class="statistics-name">'+"回复:"+'</div>'+
                                '<div class="statistics-value">'+data.commentCount+'</div>'+
                            '</div>'+
                        '</div>'+''
   				$("#count").append(posthtml)
	       	 }else{
					alert("获取总数为空，出错啦！");
	       	 }

        },
        error: function (XMLHttpRequest, textStatus, errorThown) {
            alert("所有总数，操作失败！");
        }
        });
});


