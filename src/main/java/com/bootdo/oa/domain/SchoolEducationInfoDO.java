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
public class SchoolEducationInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//学历教育标题
	private String title;
	//学历教育类型ID1
	private Integer fkTypeId1;
	private String fkType1;
	//学历教育类型ID2
	private Integer fkTypeId2;
	private String fkType2;
	//学历教育内容
	private String context;
	//创建时间
	private Date createTime;
	//创建人
	private String createUser;
	//修改时间
	private Date modifyTime;
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
	 * 设置：学历教育标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：学历教育标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：学历教育类型ID1
	 */
	public void setFkTypeId1(Integer fkTypeId1) {
		this.fkTypeId1 = fkTypeId1;
	}
	/**
	 * 获取：学历教育类型ID1
	 */
	public Integer getFkTypeId1() {
		return fkTypeId1;
	}
	/**
	 * 设置：学历教育类型ID2
	 */
	public void setFkTypeId2(Integer fkTypeId2) {
		this.fkTypeId2 = fkTypeId2;
	}
	/**
	 * 获取：学历教育类型ID2
	 */
	public Integer getFkTypeId2() {
		return fkTypeId2;
	}
	
	public String getFkType1() {
		return fkType1;
	}
	public void setFkType1(String fkType1) {
		this.fkType1 = fkType1;
	}
	public String getFkType2() {
		return fkType2;
	}
	public void setFkType2(String fkType2) {
		this.fkType2 = fkType2;
	}
	/**
	 * 设置：学历教育内容
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * 获取：学历教育内容
	 */
	public String getContext() {
		return context;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModifyTime() {
		return modifyTime;
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
