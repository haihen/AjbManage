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
public class TrainInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//技能培训ID
	private Integer fkTypeId;
	private String fkType;
	//技能培训标题
	private String title;
	//技能培训封面
	private String coverImg;
	//技能培训视频
	private String trainVideo;
	private String trainVideoUrl;
	private String trainVideoShow;
	//创建时间
	private Date createTime;
	//创建人
	private String createUser;
	//浏览量
	private Integer browseCount;
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
	 * 设置：技能培训ID
	 */
	public void setFkTypeId(Integer fkTypeId) {
		this.fkTypeId = fkTypeId;
	}
	/**
	 * 获取：技能培训ID
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
	 * 设置：技能培训标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：技能培训标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：技能培训封面
	 */
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	/**
	 * 获取：技能培训封面
	 */
	public String getCoverImg() {
		return coverImg;
	}
	/**
	 * 设置：技能培训视频
	 */
	public void setTrainVideo(String trainVideo) {
		this.trainVideo = trainVideo;
	}
	/**
	 * 获取：技能培训视频
	 */
	public String getTrainVideo() {
		return trainVideo;
	}
	
	public String getTrainVideoUrl() {
		return trainVideoUrl;
	}
	public void setTrainVideoUrl(String trainVideoUrl) {
		this.trainVideoUrl = trainVideoUrl;
	}
	
	public String getTrainVideoShow() {
		return trainVideoShow;
	}
	public void setTrainVideoShow(String trainVideoShow) {
		this.trainVideoShow = trainVideoShow;
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
	 * 设置：浏览量
	 */
	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}
	/**
	 * 获取：浏览量
	 */
	public Integer getBrowseCount() {
		return browseCount;
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
