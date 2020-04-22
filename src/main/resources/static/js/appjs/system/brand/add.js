$().ready(function() {
	$('.summernote').summernote({
		height : '400px',
		lang : 'zh-CN',
        callbacks: {
            onImageUpload: function(files, editor, $editable) {
                console.log("onImageUpload");
                sendFile(files);
            }
        }
    });
	var content = $("#content").val();
	$('#content_sn').summernote('code', content);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var content_sn = $("#content_sn").summernote('code');
	$("#content").val(content_sn);
	var coverImg = $("#preview").attr('src');
	var type = $("#type").val();
	if((coverImg==null||coverImg=='') && type!='品牌故事'){
		parent.layer.alert("请选择品牌文化图");
		return;
	}
	var formData = new FormData($('#signupForm')[0]);
	$.ajax({
		cache : true,
		type : "POST",
		url :"/system/brand/save",
		data : formData,// 你的formid
		async : false,
		processData : false,
        contentType: false,
		error : function(request) {
			laryer.alert("Connection error");
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
			title : "required",
			memo : "required",
			type : "required"
		},
		messages : {
			title : "请填写品牌文化标题",
			memo : "请填写品牌文化描述",
			type : "请填写品牌文化类型"
		}
	})
}