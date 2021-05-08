package com.biz.redisdemo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: Week01
 * @className: ResultInfoDTO
 * @description: 用于封装返回前端的结果
 * @author: xy
 * @time: 2021/4/23 11:09
 */
@Data
public class ResultInfoDTO{
    /**
     * flag //后端返回结果正常为true，发生异常返回false
     * data //后端返回结果数据对象
     * Msg //发生消息
     */
    private Boolean flag;
    private Object data;
    private String msg;
}
