$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var video1,video2,videoType;
	videoType = $("#videoType").val();
	video1 = $("#trainVideoFile").val();
	video2 = $("#trainVideoUrl").val();
	if(videoType){
		if(!video1 && !video2){
			parent.layer.alert("请选择技能培训视频");
			return;
		}
	}
	var formData = new FormData($('#signupForm')[0]);
	console.log($('#signupForm').serialize());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/trainInfo/update",
		data : formData,// 你的formid
		async : false,
		processData : false,
        contentType: false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			fkTypeId : "required",
			title : "required"
		},
		messages : {
			fkTypeId : "请选择技能培训类型",
			title : "请填写技能培训标题"
		}
	})
}