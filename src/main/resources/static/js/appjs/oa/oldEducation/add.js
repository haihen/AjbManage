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
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var content_sn = $("#content_sn").summernote('code');
	if(content_sn=='<br>'||content_sn=='<p><br></p>'||content_sn==null||content_sn==''){
		parent.layer.alert("请填写文章内容");
		return;
	}
	var coverImg = $("#coverImgFile").val();
	var type = $("#type").val();
	if((coverImg==null||coverImg=='')&&type.indexOf('资讯中心')<0){
		parent.layer.alert("请选择缩略图");
		return;
	}
	$("#context").val(content_sn);
	var formData = new FormData($('#signupForm')[0]);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/oldEducation/save",
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
			title : "required",
			context : "required"
		},
		messages : {
			title : "请填写文章标题",
			context : "请填写文章内容"
		}
	})
}
