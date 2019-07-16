$(function(){
	$("#del").click(function() {
		if(window.confirm("你确定要删除吗？")){
			var title = document.getElementById("posts_table").rows[0].cells[0];
			
			//alert("真删除呀 好恐怖 可是删不了哎 哈哈哈哈哈");
			var titleTd = document.getElementById("posts_table").rows[0].cells[0];
			var title = titleTd.innerHTML;
			
			alert(title);
				$.ajax({
					type: "POST",
					url: "DelServlet",
					data: {"title":title},
		            dataType: "json",
					success: function(data) {
						if(data === "false"){
							//删除成功
							alert("粗错啦！未删除成功");
						}else {
							alert("成功删除！");
							$(function() {
								var countdown = 5;
								var obj = $("#showtime");
								showTime(obj);

								function showTime(obj) { //进入倒计时
									if(countdown == 0) {
										countdown = 5;
										window.location = "listPosts.jsp";
										return;
									} else {
										obj.html("" + countdown + "秒后跳转个人中心");
										countdown--;
									}
									setTimeout(function() {
										showTime(obj)
									}, 1000);
								}
							});
						}
					},error: function (error) {
						alert(error);
					}
				});
			//alert(title.innerHTML);
		
		}
	});
});