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

import java.util.HashMap;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 温度静态变量
 * @create 2019/9/24
 */
public class MonitorConstant {
    /**
     * 启用设备类型
     */
    public static final int TEMPERATURE = 0;
    public static final int HEART_RESPIRATORY_RATE = 1;
    public static final int BLOOD_PI = 2;
    public static Map<Integer, String> sleepingTimesMap = new HashMap<>();
}
