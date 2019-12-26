/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ZsMachineInfo
 * Author:   CentreS
 * Date:     2019/7/17 13:50
 * Description: 智能温度贴
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.entity;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 智能温度贴
 * @author CentreS
 * @create 2019/7/17
 */
@Data
@Accessors(chain = true)
public class ZsMachineInfo  {
    private Integer machineId;
    @ApiParam(value = "设备名称", required = true)
    private String name;
    @ApiParam(value = "设备型号", required = true)
    private String machineModel;
    @ApiParam(value = "SN序列号", required = true)
    private String machineNum;
    @ApiParam(value = "科室id", required = true)
    private Integer departmentId;
    private Integer usageState;
    private String remark;
    private String createTime;
    private Integer status;
    @ApiParam(value = "设备编号", required = true)
    private String machineNo;
    private Integer machineTypeId;

    /** 批量导入：设备编号 */
    private List<String> machineNums;
    /** 分页信息 */
    private Integer startLine;
    private Integer pageSize;
    private String departmentName;

    private String unUsedStatus;
    private String normalStatus;
    private String deleteStatus;


}