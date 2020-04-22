package com.ajb.party.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public class PartyItemDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//功能模块ID
	private Integer id;
	//模块名称
	private String name;
	//所属菜单
	private String type;
	//模块排序
	private String orderNum;
	//创建时间
	private String createTime;
	//是否展示
	private Integer isShow;
	//主题内容
	private List<PartyDO> partyList = new ArrayList<PartyDO>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<PartyDO> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<PartyDO> partyList) {
		this.partyList = partyList;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
}
