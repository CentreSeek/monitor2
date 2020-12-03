package com.yjjk.monitor.entity.BO.machine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-11-16 13:28:11
 **/
@Data
@Accessors(chain = true)
@ApiModel("分配设备")
public class UpdateDepartmentOfMachines {

    @ApiModelProperty(value = "id")
    private Integer departmentId;
    private List<Integer> machineIds;
}
