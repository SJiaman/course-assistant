package com.zrn.assistant.modules.demo.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.modules.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {
	
}