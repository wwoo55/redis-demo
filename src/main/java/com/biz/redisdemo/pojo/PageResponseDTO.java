package com.biz.redisdemo.pojo;

import lombok.Data;

import java.util.List;

/**
 * @projectName: Practice
 * @className: PageResponseDTO
 * @description: 返回分页数据对象
 * @author: xy
 * @time: 2021/4/22 11:25
 */
@Data
public class PageResponseDTO<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
}
