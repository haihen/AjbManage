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
public class ActivityInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//特色活动类型ID
	private Integer fkTypeId;
	private String fkType;
	//特色活动标题
	private String title;
	//特色活动内容
	private String context;
	//特色活动创建时间
	private Date createTime;
	//特色活动创建人
	private String createUser;
	//
	private Integer isDeleted;

	private String beforeId ;
	private String beforeTitle;
	private String afterId;
	private String afterTitle;
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
	 * 设置：特色活动类型ID
	 */
	public void setFkTypeId(Integer fkTypeId) {
		this.fkTypeId = fkTypeId;
	}
	/**
	 * 获取：特色活动类型ID
	 */
	public Integer getFkTypeId() {
		return fkTypeId;
	}
	
	public String getFkType() {
		return fkType;
	}
	public void setFkType(String fkType) {
		this.fkType = fkType;
	}
	/**
	 * 设置：特色活动标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：特色活动标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：特色活动内容
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * 获取：特色活动内容
	 */
	public String getContext() {
		return context;
	}
	/**
	 * 设置：特色活动创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：特色活动创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：特色活动创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：特色活动创建人
	 */
	public String getCreateUser() {
		return createUser;
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
	public String getBeforeId() {
		return beforeId;
	}
	public void setBeforeId(String beforeId) {
		this.beforeId = beforeId;
	}
	public String getBeforeTitle() {
		return beforeTitle;
	}
	public void setBeforeTitle(String beforeTitle) {
		this.beforeTitle = beforeTitle;
	}
	public String getAfterId() {
		return afterId;
	}
	public void setAfterId(String afterId) {
		this.afterId = afterId;
	}
	public String getAfterTitle() {
		return afterTitle;
	}
	public void setAfterTitle(String afterTitle) {
		this.afterTitle = afterTitle;
	}
	
}
