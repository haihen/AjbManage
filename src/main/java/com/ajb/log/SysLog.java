/**
 * 
 */
/**
 * @author tjjx.com
 *
 */
package com.ajb.log;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class SysLog implements Serializable {
    private Integer id;
    
    private Integer userId;

    private String userName; //用户名

    private String operation; //操作

    private String method; //方法名

    private String params; //参数

    private String ip; //ip地址

    private Date createTime; //操作时间
}