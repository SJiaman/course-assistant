package com.zrn.assistant.service;

import com.zrn.assistant.dto.TeacherDashboardDTO;
import org.springframework.stereotype.Service;

/**
 * @Author Jiaman
 * @Date 2023/2/1 21:10
 * @Desc
 */

public interface IndexService {
    TeacherDashboardDTO getTeacherDashboardById(Long id);
}
