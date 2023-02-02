package com.zrn.assistant.controller;

import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.TeacherDashboardDTO;
import com.zrn.assistant.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zhang Ruina
 * @Date 2022/11/30 19:15
 * @Desc
 */
@RestController
@Api(tags = "主页管理")
@RequestMapping("index")
public class IndexController {
    @Resource
    private IndexService indexService;

    @ApiOperation("教师")
    @GetMapping("teacher")
    public Result indexForTeacher(@RequestParam Long id){
        TeacherDashboardDTO teacherDashboardDTO = indexService.getTeacherDashboardById(id);
        return Result.success(teacherDashboardDTO);
    }


}
