package com.yjjk.monitor.entity.BO.history;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-05-21 09:31:24
 **/
@Data
@Accessors(chain = true)
@ApiModel("获取所有历史记录")
public class GetRecordsBO {

    @ApiModelProperty(value = "起始日期")
    private String startDate;

    @ApiModelProperty(value = "终止日期")
    private String endDate;

    @ApiModelProperty(value = "病人姓名(支持模糊查询)")
    private String patientName;

    @ApiModelProperty(value = "设备类型： 0-体温 1-心电 2-血氧 3-离床感应", required = true)
    private Integer type;

    @ApiModelProperty(value = "科室id")
    private Integer departmentId;
}
