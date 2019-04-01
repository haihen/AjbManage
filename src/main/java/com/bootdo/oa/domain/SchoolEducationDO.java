package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
public class SchoolEducationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//学历教育分类
	private String type;
	//学历教育分类等级
	private Integer level;
	//学历教育分类父级
	private Integer pid;
	private String pType;
	//是否删除
	private Integer isDeleted;
	
	private String sfxz;
	
	private List<SchoolEducationDO> childList = new ArrayList<SchoolEducationDO>();

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
	 * 设置：学历教育分类
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：学历教育分类
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：学历教育分类等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：学历教育分类等级
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：学历教育分类父级
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：学历教育分类父级
	 */
	public Integer getPid() {
		return pid;
	}
	
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
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
	public String getSfxz() {
		return sfxz;
	}
	public void setSfxz(String sfxz) {
		this.sfxz = sfxz;
	}
	public List<SchoolEducationDO> getChildList() {
		return childList;
	}
	public void setChildList(List<SchoolEducationDO> childList) {
		this.childList = childList;
	}
}
