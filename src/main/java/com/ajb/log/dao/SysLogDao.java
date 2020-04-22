/**
 * @author tjjx.com
 *
 */
package com.ajb.log.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ajb.log.SysLog;

/**
 * 日志表
 * @author yuyang
 * @email 1992lcg@163.com
 * @date 2018-11-19 10:13:29
 */
@Mapper
public interface SysLogDao {
	
	int save(SysLog sysLog);
	
	List<SysLog> list(Map<String,Object> map);
	
}