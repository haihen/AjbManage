package com.ajb.system.config;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="公共返回结果实体类",description="此返回结果实体类包含（状态、信息、结果集Map）")
public class ResultMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7240989833242804136L;
	@ApiModelProperty(value="结果状态（成功（SUCCESS）：0，失败（FAIL）：1）")
	private String status;
	@ApiModelProperty(value="结果信息")
	private String message;
	@ApiModelProperty(value="结果集")
	private Map<String,Object> resultMap;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	@Override
	public String toString() {
		return "ResultMessage [status=" + status + ", message=" + message + "]";
	}
	public static final String NOT_FOUND = "not_found";//没找到数据
	public static final String INVALID_INPUT = "invalid_input";//错误的输入项
	public static final String SUCCESS = "Success";//操作成功
	public static final String INTERNAL_ERROR = "internal_error";//服务器内部错误，失败
	public static final String SESSION_TIMEOUT = "session_timeout";//该请求的seesion已失效，需要重新登录。
	public static final String INFO_EXIST = "info_exist";//信息已存在
	public static final String INVALID_FILETYPE = "invalid_filetype";//错误的文件类型
	public static final String INVALID_IMGSIZE = "invalid_imgsize";//图片最大SIZE
	public static final String NOT_PERMISSION = "not_permission";//没找到权限
}
