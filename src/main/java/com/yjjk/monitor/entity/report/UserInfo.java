package com.yjjk.monitor.entity.report;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2.0
 * @description: 患者信息
 * @author: CentreS
 * @create: 2020-07-08 16:08:39
 **/

@Data
@Accessors(chain = true)
public class UserInfo {
    /**("姓名")
     * 
     */
    private String name;
    /**("住院号")
     * 
     */
    private String caseNum;
    /**("年龄")
     * 
     */
    private String age;
    /**("性别")
     * 
     */
    private String gender;
    /**("设备编号")
     * 
     */
    private String machineNo;
    /**("开始时间")
     * 
     */
    private String startTime;
    /**("结束时间")
     * 
     */
    private String endTime;
    /**("有效时间")
     * 
     */
    private String useTimes;
}
