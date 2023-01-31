

package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_oss")
public class SysOssEntity  {
	private static final long serialVersionUID = 1L;

	/**
	 * URL地址
	 */
	private String url;

}