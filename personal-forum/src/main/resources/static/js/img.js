
$(function() {
	$("#files").change(function() {
		var list = this.files;
		// 清空文件
		$("#list").empty();
		for (var i = 0; i < list.length; i++) {
			var file = list[i];
			var url = window.URL.createObjectURL(file);
			var img = $("<img>").attr("src", url);
			$("#list").append(img);
		}
	});

	$("#jquery-upload").click(function() {
		//console.log("点击了");
		// 找到所有文件
		var files = $("#files")[0].files;
		// 创建FormData对象
		var frm = new FormData();
		if(files.length==0){
			return;
		}
		for (var i = 0; i < files.length; i++) {
			var file = files[i];
			frm.append("images", file);
		}
		
		if(file.size>524288){
			alert("上传头像图片过大");
			return;
		}
		// 利用JQuery的ajax方法发送formData对象
		$.ajax({
			url : "user/upload.do",
			type : "POST",
			dataType : "json",
			data : frm,// ajax方法发送fromData对象
			processData : false,// Jquery不要出来frm数据
			contentType : false,
			"success" : function(obj) {
				if (obj.state == 200) {
					// 提示操作成功
					alert("上传成功");
					$.cookie("headPortrait", obj.data, {
                        expires: 7
                    });
				} else {
					alert(obj.message);
				}

			}
		});
	});

	// 建立一个可存取到该file的url
	function getObjectURL(file) {
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}
	// 获取点击的图片元素
	var cdimg = $('.fileImgs1').children('img');
	// 获取上传图片的 input 标签元素
	var cdfile = $('.fileImgs1').children('input[type="file"]');
	// 设置input 大小和图片一致
	cdfile.css({
		'width' : cdimg.css('width'),
		'height' : cdimg.css('height')
	});
	// input透明度为0,定位，优先级比图片高
	cdfile.css({
		'opacity' : 0,
		'position' : 'absolute',
		'z-index' : 10
	});
	// 判断input的图片文件改变则img的图片也替换为input选择的图片
	cdfile.change(function() {
		if (this.files && this.files[0]) {
			var objUrl = getObjectURL(this.files[0]);
			if (objUrl) {
				$(this).siblings('img').attr("src", objUrl);
			}
		}
	});
})