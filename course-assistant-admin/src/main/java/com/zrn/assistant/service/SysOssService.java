

package com.zrn.assistant.service;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.service.BaseService;
import com.zrn.assistant.entity.SysOssEntity;


import java.util.Map;

/**
 * 文件上传
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysOssService extends BaseService<SysOssEntity> {

	PageData<SysOssEntity> page(Map<String, Object> params);
}
