$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var pwd = $("#password").val();
	var pwdRegex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');
	if(!pwdRegex.test(pwd)){
		parent.layer.alert("您的密码安全级别低（密码中必须包含字母、数字、特殊字符），请及时修改密码！");
		$('#password').focus();
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/web/user/save",
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
			loginName : "required",
			password : "required"
		},
		messages : {
			loginName : "请填写用户名",
			password : "请填写密码"
		}
	})
}