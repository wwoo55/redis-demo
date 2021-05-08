package com.biz.redisdemo.controller;

import com.biz.redisdemo.pojo.*;
import com.biz.redisdemo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: redis-test
 * @className: StudentController
 * @description: student的视图控制
 * @author: xy
 * @time: 2021/5/7 10:40
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    /**
     * list
     * @param pageRequestVo
     * @return
     */
    @GetMapping("/listStudent")
    public PageResponseDTO<StudentDTO> listStudent(PageRequestVo pageRequestVo){
        return this.iStudentService.listStudent(pageRequestVo);
    }

    /**
     * insert
     * @param studentVO
     * @return
     */
    @PostMapping("/common")
    public ResultInfoDTO insertStudent(StudentVO studentVO){
        return this.iStudentService.insertStudent(studentVO);
    }

    /**
     * delete
     * @param studentVO
     * @return
     */
    @DeleteMapping("/common")
    public ResultInfoDTO deleteStudent(StudentVO studentVO){
        return this.iStudentService.deleteStudent(studentVO);
    }

    /**
     * update
     * @param studentVO
     * @return
     */
    @PutMapping("/common")
    public ResultInfoDTO updateStudent(StudentVO studentVO){
        return this.iStudentService.updateStudent(studentVO);
    }



}
