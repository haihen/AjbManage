$().ready(function() {
	var html = "";
    var params = {};
	$.ajax({
		url : '/system/party/item/getListItem',
		data : params,
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
			}
			$("#subType").append(html);
			$("#subType").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#subType').on('change', function(e, params) {
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
	$("#type").change(function (e) {
		
        var type = $("#type option:selected").attr("value");
        if(type!='主题教育'){
        	$("#partyItem").hide();
        	if(type=='党建经验'){
        		$("#pfile").show();
        		$("#pimg").hide();
        	} else {
        		$("#pfile").hide();
        		$("#pimg").hide();
        	}
        } else {
        	$("#partyItem").show();
        	$("#pimg").show();
        	$("#pfile").hide();
        	var html = "";
            var params = {};
        	$.ajax({
        		url : '/system/party/item/getListItem',
        		data : params,
        		success : function(data) {
        			//加载数据
        			for (var i = 0; i < data.length; i++) {
        				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        			}
        			$("#subType").append(html);
        			$("#subType").chosen({
        				maxHeight : 200
        			});
        			//点击事件
        			$('#subType').on('change', function(e, params) {
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
        }
        
    });
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
		parent.layer.alert("请填写党的建设（内容）");
		return;
	}
	$("#content").val(content_sn);

/*	var type = $("#type").val();
	if(type=='主题教育'){
		var coverImg = $("#imageFile").val();
		if(coverImg==null||coverImg==''){
			parent.layer.alert("请选择党的建设图");
			return;
		}
	}
	if(type=='党建经验'){
		var coverFile = $("#partyFile").val();
		var fileUrl = $("#fileUrl").val();
		if((coverFile==null||coverFile=='')&&(fileUrl==null||fileUrl=='')){
			parent.layer.alert("请选择党的建设文件");
			return;
		}
	}*/
	
	var formData = new FormData($('#signupForm')[0]);
	$.ajax({
		cache : true,
		type : "POST",
		url :"/system/party/update",
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
			title : "required",
			memo : "required",
			type : "required"
		},
		messages : {
			title : "请填写党的建设标题",
			memo : "请填写党的建设描述",
			type : "请填写党的建设类型"
		}
	})
}