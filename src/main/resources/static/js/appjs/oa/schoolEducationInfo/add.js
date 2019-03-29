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
    $("#fkTypeId1").change(function (e) {
        var fkTypeId1 = $("#fkTypeId1 option:selected").attr("value");
        var html = "";
        var params = {
                'level' : 2,
                'pid' : fkTypeId1
            };
    	$.ajax({
    		url : '/oa/schoolEducation/getListByType',
    		data : params,
    		success : function(data) {
    			//加载数据
    			for (var i = 0; i < data.length; i++) {
    				html += '<option value="' + data[i].id + '">' + data[i].type + '</option>';
    			}
    			$("#fkTypeId2").append(html);
    			$("#fkTypeId2").chosen({
    				maxHeight : 200
    			});
    			//点击事件
    			$('#fkTypeId2').on('change', function(e, params) {
    				console.log(params.selected);
    				var opt = {
    					query : {
    						type : params.selected,
    					}
    				}
    				$('#exampleTable').bootstrapTable('refresh', opt);
    			});
    		}
    	});
    });
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function save() {
	var fkTypeId2;
	fkTypeId2 = $("#fkTypeId2").val();
	if(!fkTypeId2){
		parent.layer.alert("请填写学历教育二级类别");
		return;
	}
	var content_sn = $("#content_sn").summernote('code');
	if(content_sn=='<br>'||content_sn=='<p><br></p>'||content_sn==null||content_sn==''){
		parent.layer.alert("请填写学历教育内容");
		return;
	}
	$("#context").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/schoolEducationInfo/save",
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
			fkTypeId1 : "required",
			fkTypeId2 : "required",
			context : "required"
		},
		messages : {
			title : "请填写学历教育标题",
			fkTypeId1 : "请选择学历教育一级分类",
			fkTypeId2 : "请选择学历教育二级分类",
			context : "请填写学历教育内容"
		}
	})
}
