package com.zrn.assistant.dto;

import lombok.Data;

/**
 * @author Zhang Ruina
 * @Date 2022/11/30 19:17
 * @Desc
 */
@Data
public class LoginDTO {
    private String username;

    private String password;

    private Integer role;
}
