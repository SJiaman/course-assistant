package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.CourseResourceDTO;
import com.zrn.assistant.service.CourseResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 课程资源表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@RestController
@RequestMapping("course/resource")
@Api(tags="课程资源表")
public class CourseResourceController {
    @Autowired
    private CourseResourceService courseResourceService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<CourseResourceDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CourseResourceDTO> page = courseResourceService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<CourseResourceDTO> get(@PathVariable("id") Long id){
        CourseResourceDTO data = courseResourceService.get(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody CourseResourceDTO dto){
        courseResourceService.save(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody CourseResourceDTO dto){
        courseResourceService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        courseResourceService.delete(ids);
        return Result.success();
    }
}