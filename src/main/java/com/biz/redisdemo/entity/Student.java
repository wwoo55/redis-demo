package com.biz.redisdemo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: redis-test
 * @className: Student
 * @description: student的实体类
 * @author: xy
 * @time: 2021/5/7 10:32
 */
@Data
public class Student implements Serializable {
    private String id;
    private String name;
    private Date birthday;
    private String description;
    private Integer avgScore;
}
