package com.zrn.assistant.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author Jiaman
 * @Date 2023/1/30 22:24
 * @Desc
 */
@Data
public class AnswerItemDTO {
    private Long id;

    private Long questionId;

    private String content;

    private Boolean doRight;

    private Integer score;

    private List<String> contentArray;

    private Boolean completed;

    private Integer itemOrder;
}
