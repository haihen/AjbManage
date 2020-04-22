package com.ajb.work.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public class WorkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//首页ID
	private Integer id;
	//首页模块标题
	private String title;
	//首页模块副标题
	private String subtitle;
	//首页模块主体
	private String content;
	//首页模块描述
	private String memo;
	//首页模块链接
	private String url;
	//首页模块图片链接
	private String imageUrl;
	//首页模块类型
	private String type;
	//排序
	private Integer orderNum;
	//创建时间
	private Date createTime;
	//置顶时间
	private Date topTime;
	//是否删除
	private Integer isDeleted;
	//是否置顶
	private Integer isTop;
	//是否展示
	private Integer isShow;

	/**
	 * 设置：首页ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：首页ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：首页模块标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：首页模块标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：首页模块副标题
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * 获取：首页模块副标题
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * 设置：首页模块主体
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：首页模块主体
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：首页模块描述
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：首页模块描述
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * 设置：首页模块链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：首页模块链接
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：首页模块图片链接
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 获取：首页模块图片链接
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * 设置：首页模块类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：首页模块类型
	 */
	public String getType() {
		return type;
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
	 * 设置：置顶时间
	 */
	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
	/**
	 * 获取：置顶时间
	 */
	public Date getTopTime() {
		return topTime;
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
	/**
	 * 设置：是否置顶
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	/**
	 * 获取：是否置顶
	 */
	public Integer getIsTop() {
		return isTop;
	}
	/**
	 * 设置：是否展示
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	/**
	 * 获取：是否展示
	 */
	public Integer getIsShow() {
		return isShow;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
}
