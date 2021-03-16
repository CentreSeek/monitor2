/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: EcgConstant
 * Author:   CentreS
 * Date:     2019/11/13 10:12
 * Description: 心电心率
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.constant;

import com.google.common.primitives.Ints;

import java.util.List;

/**
 * @author CentreS
 * @Description: 心电心率
 * @create 2019/11/13
 */
public class EcgConstant {
    static {
//    if (ECG_NULL_DATA == null){
//
//    }
    }

    /**
     * time
     */
    public static final long FIVE_MINUTES_LONG = 1000 * 60 * 5;

    /**
     * heart rate
     */
    public static final int HEART_RATE_LOW = 50;
    public static final int HEART_RATE_HEIGHT = 100;
    /**
     * breath rate
     */
    public static final double RESPIRATORY_RATE_LOW = 12;
    public static final double RESPIRATORY_RATE_HEIGHT = 24;
    public static final List<Integer> ECG_NULL_DATA = Ints.asList(new int[128]);
}
