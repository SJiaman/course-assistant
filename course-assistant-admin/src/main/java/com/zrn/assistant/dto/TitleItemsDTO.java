package com.zrn.assistant.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Zhang Ruina
 * @Date 2023/1/30 9:21
 * @Desc
 */
@Data
public class TitleItemsDTO {
    private Integer type;

    private List<QuestionDTO> questionItems;
}
