package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
public class TrainTypeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//技能培训类型
	private String type;
	//
	private Integer isDeleted;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：技能培训类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：技能培训类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
}
