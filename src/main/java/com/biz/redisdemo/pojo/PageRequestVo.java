package com.biz.redisdemo.pojo;

import lombok.Data;

/**
 * @projectName: redis-demo
 * @className: PageRequestVo
 * @description:
 * @author: xy
 * @time: 2021/5/7 14:19
 */
@Data
public class PageRequestVo {

    /**
     * 页数
     */
    private Integer page;
    /**
     * 每页条数
     */
    private Integer limit;
    /**
     * 搜索关键字
     */
    private String key;
}
