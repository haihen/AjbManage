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
public class ActivityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//教育活动ID
	private Integer id;
	//教育活动标题
	private String title;
	//教育活动链接
	private String url;
	//教育活动是否显示
	private Integer isPlay;
	//教育活动是否显示名称
	private String isPlayName;
	//教育活动起始时间
	private String startTime;
	//教育活动结束时间
	private String endTime;
	//是否删除
	private Integer isDeleted;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsPlay() {
		return isPlay;
	}
	public void setIsPlay(Integer isPlay) {
		this.isPlay = isPlay;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getIsPlayName() {
		return isPlayName;
	}
	public void setIsPlayName(String isPlayName) {
		this.isPlayName = isPlayName;
	}

}
