package com.zrn.assistant.controller;

import com.zrn.assistant.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhang Ruina
 * @Date 2022/11/30 19:15
 * @Desc
 */
@RestController
@Api(tags = "主页管理")
@RequestMapping("index")
public class IndexController {


    @ApiOperation("教师")
    @GetMapping("teacher")
    public Result indexForTeacher(){
        return Result.success();
    }


}
