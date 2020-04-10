/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: Temperature
 * Author:   CentreS
 * Date:     2019/9/24 11:04
 * Description: 温度静态变量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.constant;

import com.yjjk.monitor.utility.MathUtils;

import java.math.BigDecimal;

/**
 * @author CentreS
 * @Description: 温度静态变量
 * @create 2019/9/24
 */
public class TemperatureConstant {
    /**
     * 高低温状态
     */
    public static final String LOW_TEMPERATURE = "BLUE";
    public static final String NORMAL_TEMPERATURE = "GREEN";
    public static final String HIGHER_TEMPERATURE = "ORANGE";
    public static final String HIGH_TEMPERATURE = "RED";

    /**
     * 体温预警
     */
    public static final Integer TEMPERATURE_ALERT_NORMAL = 0;
    public static final Integer TEMPERATURE_ALERT_LOW = 1;
    public static final Integer TEMPERATURE_ALERT_HIGH = 2;

    public static final Double[] TEMPERATURE_LIST = {33.0, 33.1, 33.2, 33.3, 33.4, 33.5, 33.6, 33.7, 33.8, 33.9, 34.0, 34.1, 34.2, 34.3, 34.4, 34.5
            , 34.6, 34.7, 34.8, 34.9, 35.0, 35.1, 35.2, 35.3, 35.4, 35.5, 35.6, 35.7, 35.8, 35.9, 36.0, 36.1, 36.2, 36.3, 36.4, 36.5, 36.6, 36.7,
            36.8, 36.9, 37.0, 37.1, 37.2, 37.3, 37.4, 37.5, 37.6, 37.7, 37.8, 37.9, 38.0, 38.1, 38.2, 38.3, 38.4, 38.5, 38.6, 38.7, 38.8, 38.9,
            39.0, 39.1, 39.2, 39.3, 39.4, 39.5, 39.6, 39.7, 39.8, 39.9, 40.0, 40.1, 40.2, 40.3, 40.4, 40.5, 40.6, 40.7, 40.8, 40.9, 41.0, 41.1,
            41.2, 41.3, 41.4, 41.5, 41.6, 41.7, 41.8, 41.9, 42.0};
    public static final Double[][] FAHRENHEIT_LIST = {{91.4, 33.0}, {91.5, 33.1}, {91.7, 33.2}, {91.9, 33.3}, {92.1, 33.4}, {92.3, 33.5}, {92.4, 33.6},
            {92.6, 33.7}, {92.8, 33.8}, {93.0, 33.9}, {93.2, 34.0}, {93.3, 34.1}, {93.5, 34.2}, {93.7, 34.3}, {93.9, 34.4}, {94.0, 34.5}, {94.2, 34.6},
            {94.4, 34.7}, {94.6, 34.8}, {94.8, 34.9}, {95.0, 35.0}, {95.1, 35.1}, {95.3, 35.2}, {95.5, 35.3}, {95.7, 35.4}, {95.9, 35.5}, {96.0, 35.6},
            {96.2, 35.7}, {96.4, 35.8}, {96.6, 35.9}, {96.7, 36.0}, {96.9, 36.1}, {97.1, 36.2}, {97.3, 36.3}, {97.5, 36.4}, {97.7, 36.5}, {97.8, 36.6},
            {98.0, 36.7}, {98.2, 36.8}, {98.4, 36.9}, {98.6, 37.0}, {98.7, 37.1}, {98.9, 37.2}, {99.1, 37.3}, {99.3, 37.4}, {99.5, 37.5}, {99.6, 37.6},
            {99.8, 37.7}, {100.0, 37.8}, {100.2, 37.9}, {100.4, 38.0}, {100.5, 38.1}, {100.7, 38.2}, {100.9, 38.3}, {101.1, 38.4}, {101.2, 38.5},
            {101.4, 38.6}, {101.6, 38.7}, {101.8, 38.8}, {102.0, 38.9}, {102.2, 39.0}, {102.3, 39.1}, {102.5, 39.2}, {102.7, 39.3}, {102.9, 39.4},
            {103.1, 39.5}, {103.2, 39.6}, {103.4, 39.7}, {103.6, 39.8}, {103.8, 39.9}, {104.0, 40.0}, {104.1, 40.1}, {104.3, 40.2}, {104.5, 40.3},
            {104.7, 40.4}, {104.9, 40.5}, {105.0, 40.6}, {105.2, 40.7}, {105.4, 40.8}, {105.6, 40.9}, {105.7, 41.0}, {105.9, 41.1}, {106.1, 41.2},
            {106.3, 41.3}, {106.5, 41.4}, {106.7, 41.5}, {106.8, 41.6}, {107.0, 41.7}, {107.2, 41.8}, {107.4, 41.9}, {107.6, 42.0}};


    public static void main(String[] args) {

        for (int i = 0; i < TEMPERATURE_LIST.length; i++) {
            System.out.print("{" + MathUtils.centigrade2Fahrenheit(TEMPERATURE_LIST[i]) + "," + TEMPERATURE_LIST[i] + "},");

        }
    }

    public static final String ALERT_TYPE_DEFAULT = "default";
    public static final String ALERT_TYPE_DEPARTMENT = "department";
    public static final String ALERT_TYPE_FAHRENHEIT = "fahrenheit";
    /**
     * 体温预开关
     */
    public static final Integer ALERT_STATUS_START = 0;
    public static final Integer ALERT_STATUS_CLOSE = 1;

    /**
     * 体温预警默认科室id
     */
    public static final Integer DEFAULT_DEPARTMENT_ID = -1;

    public static double add(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
