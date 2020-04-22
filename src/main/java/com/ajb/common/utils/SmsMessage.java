package com.ajb.common.utils;

public class SmsMessage {

	private String Message;
	private String RequestId;
	private String BizId;
	private String Code;
	private String smsCode;
	
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getRequestId() {
		return RequestId;
	}
	public void setRequestId(String requestId) {
		RequestId = requestId;
	}
	public String getBizId() {
		return BizId;
	}
	public void setBizId(String bizId) {
		BizId = bizId;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
}
