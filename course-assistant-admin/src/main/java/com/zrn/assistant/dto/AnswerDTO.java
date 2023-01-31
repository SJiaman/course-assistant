package com.zrn.assistant.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Zhang Ruina
 * @since
 */

@Data
public class AnswerDTO {
    private Long id;

    private Integer doTime;

    private Long studentId;

    private Integer score;

    private List<AnswerItemDTO> answerItems;
}
