package com.zrn.assistant.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author Jiaman
 * @Date 2023/1/30 22:28
 * @Desc
 */

@Data
public class AnswerDTO {
    private Long id;

    private Integer doTime;

    private Long studentId;

    private Integer score;

    private List<AnswerItemDTO> answerItems;
}
