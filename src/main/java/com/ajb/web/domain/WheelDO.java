package com.ajb.web.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * InnoDB free: 9216 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-05 09:17:50
 */
public class WheelDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//轮播图ID
	private Integer id;
	//轮播图标题
	private String title;
	//轮播图链接
	private String wheelUrl;
	//轮播图地址
	private String imgUrl;
	//是否删除
	private Integer isDeleted;

	/**
	 * 设置：轮播图ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：轮播图ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：轮播图标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：轮播图标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：轮播图地址
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：轮播图地址
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：是否删除
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public String getWheelUrl() {
		return wheelUrl;
	}
	public void setWheelUrl(String wheelUrl) {
		this.wheelUrl = wheelUrl;
	}
	
}
