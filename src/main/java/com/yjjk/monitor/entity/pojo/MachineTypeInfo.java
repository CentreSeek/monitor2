package com.yjjk.monitor.entity.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "machine_type_info")
public class MachineTypeInfo {

    /**
     * 设备id
     */
    @Id
    private Integer id;

    /**
     * 设备名称/型号
     */
    @ApiModelProperty(value = "设备名称/型号")
    private String name;

    /**
     * 设备名称/型号 0：名称 1：型号
     */
    @ApiModelProperty(value = "设备名称/型号 0：名称 1：型号")
    private Integer level;

    /**
     * 设备类型0：体温贴 1：路由器
     */
    @ApiModelProperty(value = "设备类型0：监护设备 1：路由器")
    private Integer type;

    /**
     * 父类id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "create_time")
    private String createTime;

    private Integer status;
    @ApiModelProperty(value = "0-体温 1-心电 2-血氧 3-离床感应 4-血压")
    @Column(name = "type_code")
    private Integer typeCode;

}