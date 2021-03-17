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

import java.util.ArrayList;
import java.util.List;

/**
 * @author CentreS
 * @Description: 心电心率
 * @create 2019/11/13
 */
public class EcgConstant {

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

    private static List<Integer> ECG_NULL_DATA;

    public static List<Integer> getECG_NULL_DATA() {
        if (ECG_NULL_DATA == null) {
            ECG_NULL_DATA = new ArrayList<>();
            for (int i = 0; i < 128; i++) {
                ECG_NULL_DATA.add(8700);
            }
        }
        return ECG_NULL_DATA;
    }
}
