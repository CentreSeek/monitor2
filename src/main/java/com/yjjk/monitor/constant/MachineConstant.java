/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: MachineConstant
 * Author:   CentreS
 * Date:     2019/9/25 9:31
 * Description: 设备常量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.constant;

/**
 * @author CentreS
 * @Description: 设备常量
 * @create 2019/9/25
 */
public class MachineConstant {

    public static final String[] EXPORT = {"编号", "名称", "型号", "SN序列号", "使用科室", "状态", "添加日期", "备注"};
    public static final String[] EXPORT_EU = {"id", "equipment name", "device model", "SN", "Use department", "state", "Add date", "notes"};

    /**
     * 设备使用状态
     */
    public static final int USAGE_STATE_NORMAL = 0;
    public static final int USAGE_STATE_STOP = 1;
    public static final int USAGE_STATE_USED = 2;

    /**
     * 启用设备类型
     */
    public static final int TEMPERATURE = 0;
    public static final int ECG = 1;
    public static final int BLOOD = 2;
    public static final int SLEEPING = 3;

}
