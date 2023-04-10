package com.zrn.assistant.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author Jiaman
 * @Date 2023/2/1 23:55
 * @Desc
 */
@Data
public class TeacherDashboardDTO {
    private Integer courseCount;

    private Integer examCount;

    private Integer questionCount;

    private Integer recordCount;

//    List<Map<String, Long>> examRecordListCount;
    private List<RecordCountDTO> examRecordListCount;
}

