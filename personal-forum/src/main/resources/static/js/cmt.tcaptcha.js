window.objSubmit = $("#inpId").parent("form").find(":submit");
objSubmit.data("original", objSubmit.val())

zbp.plugin.on("comment.postsuccess", "tverify", function (i) {
    recoverySubmit();
});

zbp.plugin.on("comment.verifydata", "tverify", function (i,j) {
    if (i.code == 0 && !zbp.cookie.get('ticket')){
        lcp.tcaptcha(function(){
            objSubmit.click();
        },function(){
            recoverySubmit();
        });
        throw new Error('请进行图形验证');
    }
});

function recoverySubmit(){
    objSubmit.removeClass("loading").removeAttr("disabled");
    if (objSubmit.data("original")) {
        objSubmit.val(objSubmit.data("original"))
    }
}