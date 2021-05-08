package com.biz.redisdemo.service.impl;

import com.biz.redisdemo.dao.IStudentDao;
import com.biz.redisdemo.entity.Student;
import com.biz.redisdemo.pojo.*;
import com.biz.redisdemo.service.IStudentService;
import com.biz.redisdemo.util.DateTimeUtils;
import com.biz.redisdemo.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: redis-test
 * @className: StudentServiceImpl
 * @description: student的具体业务逻辑
 * @author: xy
 * @time: 2021/5/7 10:39
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    @Qualifier(value = "studentDaoImpl")
    private IStudentDao iStudentDao;

    @Override
    public PageResponseDTO<StudentDTO> listStudent(PageRequestVo pageRequestVo) {
        PageResponseDTO<StudentDTO> responseDTO = new PageResponseDTO<>();

        // 分页查询
        // 得到list
        List<Student> studentList = this.iStudentDao.selectByPage(pageRequestVo);
        // 得到count
        Integer count = this.iStudentDao.count();

        // List<Student> -> List<StudentDTO>
        if (CollectionUtils.isEmpty(studentList)) {
            responseDTO.setCode(1);
            responseDTO.setMsg("error");
            return responseDTO;
        }
        List<StudentDTO> dtoList = studentList.stream().map(student -> {
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(student, studentDTO);
            studentDTO.setBirthdayStr(DateTimeUtils.formatDateToString(student.getBirthday()));
            return studentDTO;
        }).collect(Collectors.toList());
        responseDTO.setCode(0);
        responseDTO.setData(dtoList);
        responseDTO.setCount(count);
        return responseDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfoDTO insertStudent(StudentVO studentVO) {
        ResultInfoDTO infoDTO = new ResultInfoDTO();
        // 1.studentVO -> student
        Student student = new Student();
        BeanUtils.copyProperties(studentVO, student);
        student.setBirthday(DateTimeUtils.formatStringToDate(studentVO.getBirthdayStr()));
        student.setId(UUIDUtils.getId());
        // 2.在redis中插入
        boolean flag = this.iStudentDao.insertStudent(student);
        // 3.设置返回状态
        infoDTO.setFlag(flag);
        if (!flag) {
            infoDTO.setMsg("插入失败");
            return infoDTO;
        }
        return infoDTO;
    }

    @Override
    public ResultInfoDTO deleteStudent(StudentVO studentVO) {
        ResultInfoDTO infoDTO = new ResultInfoDTO();
        // 1.studentVO -> studentOld
        Student studentOld = new Student();
        studentOld.setId(studentVO.getId());
        studentOld.setName(studentVO.getOldName());
        studentOld.setBirthday(DateTimeUtils.formatStringToDate(studentVO.getOldBirthdayStr()));
        studentOld.setDescription(studentVO.getOldDescription());
        studentOld.setAvgScore(studentVO.getOldAvgScore());

        // 2.redis中删除
        boolean flag = this.iStudentDao.deleteStudent(studentOld);

        // 3.设置返回状态
        infoDTO.setFlag(flag);
        if (!flag) {
            infoDTO.setMsg("删除失败");
            return infoDTO;
        }
        return infoDTO;
    }

    @Override
    public ResultInfoDTO updateStudent(StudentVO studentVO) {
        ResultInfoDTO infoDTO = new ResultInfoDTO();
        // 1.studentVO -> studentOld
        Student studentOld = new Student();
        studentOld.setId(studentVO.getId());
        studentOld.setName(studentVO.getOldName());
        studentOld.setBirthday(DateTimeUtils.formatStringToDate(studentVO.getOldBirthdayStr()));
        studentOld.setDescription(studentVO.getOldDescription());
        studentOld.setAvgScore(studentVO.getOldAvgScore());

        // 2.studentVo -> studentNew
        Student studentNew = new Student();
        BeanUtils.copyProperties(studentVO,studentNew);
        studentNew.setBirthday(DateTimeUtils.formatStringToDate(studentVO.getBirthdayStr()));

        // 3.先删除在添加实现修改
        boolean flagA = this.iStudentDao.deleteStudent(studentOld);
        boolean flagB = this.iStudentDao.insertStudent(studentNew);

        // 4.设置返回状态
        if (flagA&&flagB) {
            infoDTO.setFlag(true);
            return infoDTO;
        }
        infoDTO.setFlag(false);
        infoDTO.setMsg("更新失败");
        return infoDTO;
    }
}
