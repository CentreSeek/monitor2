package com.yjjk.monitor.entity.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "zs_repeater_info")
@Data
@Accessors(chain = true)
public class ZsRepeaterInfo {
    @Id
    private Integer id;

    /**
     * 设备MAC
     */
    @Column(name = "MAC")
    private String mac;

    /**
     * 设备IP
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 链接状态 0：在线 1：不在线 2：停用
     */
    @Column(name = "linkStatus")
    private Integer linkStatus;

    /**
     * 设备类型id
     */
    @Column(name = "machine_type_id")
    private Integer machineTypeId;

    /**
     * 部署科室
     */
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * 所属房间号
     */
    @Column(name = "room_id")
    private Integer roomId;

    /**
     * 备注(项目重启时根据此项决定是否重启)
     */
    private String remark;

    /**
     * 0：正常 1：删除
     */
    private Integer status;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 故障次数
     */
    @Column(name = "fail_count")
    private Integer failCount;

    /**
     * 分页信息
     */
    private Integer startLine;
    private Integer pageSize;
    /**
     * 添加字段
     */
    private String repeaterName;
    private String repeaterNum;
    private String departmentName;
    private String roomName;
    private Integer order;
    @ApiModelProperty(value = "关联状态 0:已关联 1：未关联房间 2：未关联科室")
    private Integer bindStatus;
}