$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var formData = new FormData($('#signupForm')[0]);
	$.ajax({
		cache : true,
		type : "POST",
		url :"/system/ajb/type/update",
		data : formData,// 你的formid
		async : false,
		processData : false,
        contentType: false,
		error : function(request) {
			layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("保存成功");
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
			name : "required",
			type : "required"
		},
		messages : {
			name : "请填写安居办模块",
			type : "请填写安居办菜单"
		}
	})
}