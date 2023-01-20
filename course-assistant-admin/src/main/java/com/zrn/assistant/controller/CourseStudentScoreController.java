package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.CourseStudentScoreDTO;
import com.zrn.assistant.service.CourseStudentScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 课程得分
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@RestController
@RequestMapping("demo/coursestudentscore")
@Api(tags="课程得分")
public class CourseStudentScoreController {
    @Autowired
    private CourseStudentScoreService courseStudentScoreService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<CourseStudentScoreDTO>> page( @RequestParam Map<String, Object> params){
        PageData<CourseStudentScoreDTO> page = courseStudentScoreService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<CourseStudentScoreDTO> get(@PathVariable("id") Long id){
        CourseStudentScoreDTO data = courseStudentScoreService.get(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody CourseStudentScoreDTO dto){
        courseStudentScoreService.save(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody CourseStudentScoreDTO dto){
        courseStudentScoreService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        courseStudentScoreService.delete(ids);
        return Result.success();
    }
}