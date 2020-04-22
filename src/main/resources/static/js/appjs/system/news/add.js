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
	if(content_sn=='<br>'||content_sn=='<p><br></p>'||content_sn==null||content_sn==''){
		parent.layer.alert("请填写新闻中心（内容）");
		return;
	}
	$("#content").val(content_sn);
	var coverImg = $("#imageFile").val();
	var type = $("#type").val();
	if((coverImg==null||coverImg=='') && type=='安居要闻'){
		parent.layer.alert("请选择新闻中心图");
		return;
	}
	var formData = new FormData($('#signupForm')[0]);
	$.ajax({
		cache : true,
		type : "POST",
		url :"/system/news/save",
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
			title : "请填写新闻中心标题",
			memo : "请填写新闻中心描述",
			type : "请填写新闻中心类型"
		}
	})
}