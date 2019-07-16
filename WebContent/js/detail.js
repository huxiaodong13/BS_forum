var building_owner=""
var idpost=GetQueryString("postid")
var titleTag=""
var contentTag=""
function editme() {	
	document.getElementById('modify').style.display = 'block';
	document.getElementById('modify_the_post').style.display = 'block';
}

function closeme() {
	document.getElementById('modify').style.display = 'none';
	document.getElementById('modify_the_post').style.display = 'none';
}

$(function(){
	var postid=GetQueryString("postid")
	 $.ajax({
             type: "post",
             url: "ShowOnePost",
             data: {"postid":postid},
             dataType: "json",
             success: function(data){ 
                        $("#title").children().html(data.title)
                        $("#owner").html('<div class="clear"><div class="left">'+
		                        		'<p>楼主</p>'+
		            					'<img src="'+data.photo+'"/>'+
		            					'<p>'+data.username+'</p>'+
			            				'</div>'+
			            				'<div class="right">'+
			            					'<p>'+data.context+'</p></div></div>'+
			            					'<div class="time">'+data.postingtime+
			            					'  <span>1楼<span></div>')
                        building_owner=data.username
                         titleTag=data.title
                        contentTag=data.context
                        //是否可以修改帖子
                        isEdit()
             },
             error: function (XMLHttpRequest, textStatus, errorThown) {
	             console.log(textStatus);
	             console.log(errorThown);
	         }
      });
	
	 var floor=2;//楼层显示
	 $.ajax({
		 type: "post",
         url: "ShowAllComment",
         data: {"postid":postid},
         dataType: "json",
         success:function(data){
        	 $.each(data,function(idx,obj){
	                var ownerTag=""
		                if(obj.username==building_owner){//楼主的回复
		                	ownerTag='<p>楼主</p>'
		                }
		               	 var huifu='<div class="people">'+
						   				'<div class="clear">'+
						   					'<div class="left">'+
						   						ownerTag+
						   						'<img src="'+obj.photo+'" />'+
						   						'<p>'+obj.username+'</p>'+
						   					'</div>'+
						   						'<div class="right">'+
						   						'<p>'+obj.context+'</p>'	+
						   					'</div>'+
						   				'</div>'+
						   				'<div class="time">'+obj.commenttime+' <span>'+floor+'楼<span></div>'+
						   			'</div>'
		             
		                   $("#content").append(huifu)
		                   floor+=1
        	 });
         },error:function(error){
             console.log(error);
         }
	 });
		
	  var send = document.getElementById('send');
	  send.addEventListener('click', function() {
	  	$.ajax({
	  		type: "post",
	  		url: "UpDatePost",
	  		data: {"postid":idpost,"title":$("#titleUpdate").val(),"context":$("#contentUpdate").val()},
	  		success: function(data2) {
	  			if(data2 === "修改成功"){
	  				alert("修改成功")
	  				window.location = "detailpost.jsp?postid="+idpost;
	  			}else {
	  				alert("粗错啦！"); 
	  			}
	  		},
	  		error: function (XMLHttpRequest, textStatus, errorThown) {
	             console.log(textStatus);
	             console.log(errorThown);
	             
	         }
	  	});
	  	closeme();
	  });
	
});
 

function GetQueryString(name)
{ 
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  decodeURI(r[2]); return null;
}

function alertlogin(){
	alert("您暂未登录请先登录")
}
function insertcomment(){
	    /*console.log("可以回复了")
	    console.log("楼主为"+building_owner)
	    console.log("idpost为"+idpost)
	     console.log("username"+$("#publish").attr("username"))
	      console.log("context为"+$("#commenttext").val())*/
		$.ajax({ 
			type: "post",
			url: "InsertComment",
			data: {"postid":idpost,"username":$("#publish").attr("username"),"context":$("#commenttext").val()},
			success: function(data) {
				console.log("data为"+data)
				if(data === "插入成功"){
					window.location = "detailpost.jsp?postid="+idpost;
					alert("插入成功")
				}else { 
					console.log("插入粗错啦 data"+data)
					alert("粗错啦！");
				}
			},error: function (error) {
				console.log("error"+error)
			}
		});
	    
}

//可修改帖子
function isEdit(){
	console.log("楼主为"+building_owner)
	console.log("username"+$("#publish").attr("username"))
	var username=$("#publish").attr("username")
	//登陆了且为楼主的时候 
	var edithtml='<div id="edit" onClick="editme()">修改发帖</div>'
	if(username!=null&&username==building_owner){
		 console.log("可以修改帖子")
		 //把原来的标题和内容添加上去
		console.log(titleTag)
	    console.log(contentTag)
	   console.log("title为"+$("#titleUpdate").val())
	   console.log("context为"+$("#contentUpdate").val())
	   $("#titleUpdate").val(titleTag)
	   $("#contentUpdate").val(contentTag)

		 
		 $("#owner").append(edithtml)
		  
	}
	else{
		console.log("不显示修改发帖功能")
	}

}


