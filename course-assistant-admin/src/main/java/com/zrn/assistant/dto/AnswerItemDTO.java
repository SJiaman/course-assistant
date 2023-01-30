package com.zrn.assistant.dto;

import java.util.List;

/**
 * @Author Jiaman
 * @Date 2023/1/30 22:24
 * @Desc
 */

public class AnswerItem {
    private Long questionId;

    private String content;

    private List<String> contentArray;

    private Boolean completed;

    private Integer itemOrder;
}
