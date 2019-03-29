$().ready(function() {
	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
	});
	var content = $("#context").val();
	$('#content_sn').summernote('code', content);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var content_sn = $("#content_sn").summernote('code');
	if(content_sn=='<br>'||content_sn=='<p><br></p>'||content_sn==null||content_sn==''){
		parent.layer.alert("请填写特色活动内容");
		return;
	}
	$("#context").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/activityInfo/update",
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
			title : "required",
			fkTypeId : "required",
			context : "required"
		},
		messages : {
			title : "请填写特色活动标题",
			fkTypeId : "请选择特色活动分类",
			context : "请填写特色活动内容"
		}
	})
}