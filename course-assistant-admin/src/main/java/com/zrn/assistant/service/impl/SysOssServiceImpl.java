

package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrn.assistant.common.constant.Constant;
import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.service.impl.BaseServiceImpl;
import com.zrn.assistant.dao.SysOssDao;
import com.zrn.assistant.entity.SysOssEntity;
import com.zrn.assistant.service.SysOssService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class SysOssServiceImpl extends BaseServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

	@Override
	public PageData<SysOssEntity> page(Map<String, Object> params) {
		IPage<SysOssEntity> page = baseDao.selectPage(
			getPage(params, Constant.CREATE_DATE, false),
			new QueryWrapper<>()
		);
		return getPageData(page, SysOssEntity.class);
	}
}
