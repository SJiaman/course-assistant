package com.zrn.assistant.service;

import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.dto.LoginDTO;
import com.zrn.assistant.dto.UserDTO;
import com.zrn.assistant.entity.UserEntity;

/**
 * 用户表
 *
 * @author Zhang Ruina
 * @since  2022-11-27
 */
public interface UserService extends CrudService<UserEntity, UserDTO> {

    /**
     * 登录服务
     * @param dto
     * @return
     */
    UserDTO login(LoginDTO dto);

    /**
     * 退出登录
     */
    void logout();
}