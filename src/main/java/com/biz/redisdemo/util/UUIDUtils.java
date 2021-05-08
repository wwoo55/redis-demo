package com.biz.redisdemo.util;

import java.util.UUID;

/**
 * @projectName: redis-demo
 * @className: UUIDUtils
 * @description:
 * @author: xy
 * @time: 2021/5/7 15:35
 */
public class UUIDUtils {

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
