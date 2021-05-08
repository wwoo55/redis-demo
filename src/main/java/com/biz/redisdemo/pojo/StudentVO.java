package com.biz.redisdemo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @projectName: redis-demo
 * @className: StudentVO
 * @description:
 * @author: xy
 * @time: 2021/5/7 12:12
 */
@Data
public class StudentVO {
    private String id;
    private String name;
    private Date birthday;
    private String description;
    private Integer avgScore;
    /**
     * 生日字符串
     */
    private String birthdayStr;

    private String oldName;
    private String oldDescription;
    private Integer oldAvgScore;
    private String oldBirthdayStr;

}
