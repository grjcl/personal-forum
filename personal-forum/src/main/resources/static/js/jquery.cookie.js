jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        // CAUTION: Needed to parenthesize options.path and options.domain
        // in the following expressions, otherwise they evaluate to undefined
        // in the packed version for some reason...
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};
$(function() {
	//设置用户名
	var username = $.cookie("username");
	//设置用户头像 无头像返回null
	var headPortrait = $.cookie("headPortrait");
	//console.log("用户名"+username);
	//console.log("头像"+headPortrait);
	if(username!=null){
		$("#yonghu").text(username);
		$("#nicheng").val(username);
	}
	if(headPortrait!=null){
		$("#images").attr("src",headPortrait);
		
	}
	//退出登录
	$("#yonghu").click(function() {
		console.log(username);
		if(username!=null){
			$.ajax({
   				"url" : "user/logout.do",
   				"type" : "GET",
   				"success" : function() {
   				    //获取当前时间
   					var date=new Date();
   					//将date设置为过去的时间
   					date.setTime(date.getTime()-10000);
   					//将cookie删除
   					document.cookie="username=828; expires="+date.toGMTString(); 
   					document.cookie="headPortrait=828; expires="+date.toGMTString();
   					location.href = "index.html";
   				}
   			});
		}else{
			location.href = "login.html";
		}
		
	})
})