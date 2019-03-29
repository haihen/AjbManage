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
public class NoticeInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//通知公告类型ID
	private Integer fkTypeId;
	private String fkType;
	//通知公告标题
	private String title;
	//通知公告内容
	private String context;
	//通知公告创建时间
	private Date createTime;
	//通知公告创建人
	private String createUser;
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
	 * 设置：通知公告类型ID
	 */
	public void setFkTypeId(Integer fkTypeId) {
		this.fkTypeId = fkTypeId;
	}
	/**
	 * 获取：通知公告类型ID
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
	 * 设置：通知公告标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：通知公告标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：通知公告内容
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * 获取：通知公告内容
	 */
	public String getContext() {
		return context;
	}
	/**
	 * 设置：通知公告创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：通知公告创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：通知公告创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：通知公告创建人
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
}
