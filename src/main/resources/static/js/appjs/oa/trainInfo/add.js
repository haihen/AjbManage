$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var video1,video2;
	video1 = $("#trainVideo").val();
	video2 = $("#trainVideoUrl").val();
	if(!video1 && !video2){
		parent.layer.alert("请选择技能培训视频");
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/trainInfo/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
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
			title : "required",
			coverImg : "required"
		},
		messages : {
			fkTypeId : "请选择技能培训类型",
			title : "请填写技能培训标题",
			coverImg : "请选择技能培训封面图片"
		}
	})
}