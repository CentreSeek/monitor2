package com.yjjk.monitor.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2.0
 * @description: 统计设备
 * @author: CentreS
 * @create: 2019-12-20 14:04:33
 **/
@Data
@Accessors(chain = true)
@ApiModel("统计设备")
public class StaticsRecordVO {
    private String startTime;
    private String endTime;
    private String departmentName;
}
