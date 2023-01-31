package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.CourseDTO;
import com.zrn.assistant.dto.CourseStudentDTO;
import com.zrn.assistant.dto.JoinCourseDTO;
import com.zrn.assistant.dto.UserDTO;
import com.zrn.assistant.service.CourseStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina
 * @since  2022-11-27
 */
@RestController
@RequestMapping("course/student")
@Api(tags="课程-学生关联管理")
public class CourseStudentController {
    @Autowired
    private CourseStudentService courseStudentService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<CourseStudentDTO>> page( @RequestParam Map<String, Object> params){
        PageData<CourseStudentDTO> page = courseStudentService.page(params);
        return Result.success(page);
    }

    @PostMapping("joinCourse")
    public Result joinCourse(@RequestBody JoinCourseDTO dto) {
        courseStudentService.joinCourse(dto);
        return Result.success();
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<CourseStudentDTO> get(@PathVariable("id") Long id){
        CourseStudentDTO data = courseStudentService.get(id);
        return Result.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public Result save(@RequestBody CourseStudentDTO dto){
        courseStudentService.save(dto);
        return  Result.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public Result update(@RequestBody CourseStudentDTO dto){
        courseStudentService.update(dto);
        return  Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        courseStudentService.delete(ids);
        return  Result.success();
    }

    @GetMapping("user")
    @ApiOperation("查询用户的课程")
    public Result getUserCourse(@RequestParam Long id) {
        List<CourseDTO> courses = courseStudentService.getUserCourse(id);
        return Result.success(courses);
    }

    @GetMapping("student")
    @ApiOperation("老师课程学生列表")
    public Result getStudent( @RequestParam Map<String, Object> params) {
        PageData<CourseStudentDTO> studentDTOS = courseStudentService.getStudent(params);
        return Result.success(studentDTOS);
    }

    @GetMapping("byCourseId")
    @ApiOperation("通过课程名查询")
    public Result getStudentByCourseId(@RequestParam String courseName) {
        List<CourseStudentDTO> dtoList = courseStudentService.getStudentByCourseName(courseName);
        return Result.success(dtoList);
    }

    @GetMapping("random")
    @ApiOperation("随机点名")
    public Result randomStudent(@RequestParam Long courseId) {
        UserDTO userDTO = courseStudentService.randomStudent(courseId);
        return Result.success(userDTO);
    }
}