$(function() {
	$("#line2 input[type=button]").click(function() {
		var keyword = $("#line2 input[type=text]").val();
		// alert("keyword:" + keyword);
		if (keyword != "") {
			// var url = "/searchProd.action";
			// $.get(url,{"keyword":keyword});
			// $.ajax({
			// url : "/searchProd.action",
			// data : {
			// "keyword" : keyword
			// },
			// success:function(){
			// location.href="prod_list.jsp";
			// },
			// error:function(){
			// alert("search defeat");
			//				}
			//			});
			location.href="/searchProd.action?keyword="+keyword;
		}
	});
});