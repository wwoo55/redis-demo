package com.biz.redisdemo.dao;


import com.biz.redisdemo.entity.Student;
import com.biz.redisdemo.pojo.PageRequestVo;

import java.util.List;

public interface IStudentDao {

    /**
     * 在redis中插入
     * @return
     * @param student
     */
    boolean insertStudent(Student student);

    /**
     * 返回分页的List
     * @param pageRequestVo
     * @return
     */
    List<Student> selectByPage(PageRequestVo pageRequestVo);

    /**
     * 得到总页数
     * @return
     */
    Integer count();

    /**
     * 在redis 中删除
     * @param studentOld
     * @return
     */
    boolean deleteStudent(Student studentOld);
}
