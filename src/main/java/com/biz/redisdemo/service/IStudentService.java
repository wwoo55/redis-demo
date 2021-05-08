package com.biz.redisdemo.service;

import com.biz.redisdemo.pojo.*;

public interface IStudentService {

    /**
     * 返回student list
     * @param pageRequestVo
     * @return
     */
    PageResponseDTO<StudentDTO> listStudent(PageRequestVo pageRequestVo);

    /**
     * 插入student
     * @param studentVO
     * @return
     */
    ResultInfoDTO insertStudent(StudentVO studentVO);

    /**
     * 在redis 中根据对象删除
     * @param studentVO
     * @return
     */
    ResultInfoDTO deleteStudent(StudentVO studentVO);

    /**
     * 在redis 中根据对象更新
     * @param studentVO
     * @return
     */
    ResultInfoDTO updateStudent(StudentVO studentVO);
}
