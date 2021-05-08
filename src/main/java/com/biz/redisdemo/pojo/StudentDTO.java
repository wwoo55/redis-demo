package com.biz.redisdemo.pojo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @projectName: redis-demo
 * @className: StudentDTO
 * @description:
 * @author: xy
 * @time: 2021/5/7 12:12
 */
@Data
public class StudentDTO {
    private String id;
    private String name;
    private Date birthday;
    private String description;
    private Integer avgScore;
    /**
     * 生日字符串
     */
    private String birthdayStr;
}
