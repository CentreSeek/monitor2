package com.yjjk.monitor.entity.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Table(name = "zs_machine_info")
@ApiModel("设备信息")
public class ZsMachineInfo {
    /**
     * 设备id
     */
    @Id
    @Column(name = "machine_id")
    private Integer machineId;

    /**
     * 设备MAC
     */
    @ApiModelProperty(value = "mac")
    @Column(name = "machine_mac")
    private String machineMac;


    /**
     * SN序列号
     */
    @ApiModelProperty(value = "sn")
    @Column(name = "machine_num")
    private String machineNum;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "编号")
    @Column(name = "machine_no")
    private String machineNo;

    /**
     * 科室id
     */
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * 设备状态 0：正常 1：停用 2：使用中
     */
    @Column(name = "usage_state")
    private Integer usageState;

    /**
     * 备注
     */
    private String remark;
    private String battery;
    @Column(name = "box_battery")
    private String boxBattery;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 0：正常 1：删除
     */
    private Integer status;

    /**
     * (machine_type)外键
     */
    @Column(name = "machine_type_id")
    private Integer machineTypeId;
    private Integer machineTypeIdChild;


    /**
     * 分页信息
     */
    private Integer startLine;
    private Integer pageSize;
    private String departmentName;

    private String unUsedStatus;
    private String normalStatus;
    private String deleteStatus;

    private Integer machineNameId;
    private String machineModel;
    private String machineName;
    private String typeCode;
    /**
     * 获取设备id
     *
     * @return machine_id - 设备id
     */
}