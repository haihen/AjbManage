package com.ajb.news.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public class NewsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//新闻中心ID
	private Integer id;
	//新闻中心模块标题
	private String title;
	//新闻中心模块副标题
	private String subtitle;
	//新闻中心模块主体
	private String content;
	//新闻中心模块描述
	private String memo;
	//新闻中心模块链接
	private String url;
	//新闻中心模块图片链接
	private String imageUrl;
	//新闻中心模块类型
	private String type;
	//排序
	private Integer orderNum;
	//创建日期
	private String createDate;
	//创建时间
	private Date createTime;
	//创建人
	private String createUser;
	//置顶时间
	private Date topTime;
	//是否删除
	private Integer isDeleted;
	//是否置顶
	private Integer isTop;
	//是否展示
	private Integer isShow;
	//浏览次数
	private Integer browseNum;
	
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
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getTopTime() {
		return topTime;
	}
	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public Integer getBrowseNum() {
		return browseNum;
	}
	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
