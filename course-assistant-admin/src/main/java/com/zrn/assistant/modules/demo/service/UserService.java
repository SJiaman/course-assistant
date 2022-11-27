package com.zrn.assistant.modules.demo.service;

import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.modules.demo.dto.UserDTO;
import com.zrn.assistant.modules.demo.entity.UserEntity;

/**
 * 用户表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
public interface UserService extends CrudService<UserEntity, UserDTO> {

}