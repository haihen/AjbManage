package com.ajb.ajbtype.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public class AjbTypeImageDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//功能模块ID
	private Integer id;
	//功能图片
	private String imageUrl;
	//所属菜单
	private String type;
	//创建时间
	private String createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
