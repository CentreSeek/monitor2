package com.yjjk.monitor.entity.transaction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@ApiModel("血压计启用参数")
public class XueYaInModel {

    @ApiModelProperty(value = "日间起始")
    private String daytimeStart;
    @ApiModelProperty(value = "日间结束")
    private String daytimeEnd;
    @ApiModelProperty(value = "日间间隔")
    private String daytimeInterval;
    @ApiModelProperty(value = "夜间间隔")
    private String nightInterval;
    @ApiModelProperty(value = "开始检测时间:     yyyy-MM-dd HH:mm:ss")
    private String startTime;
    @ApiModelProperty(value = "检测时间组-日间：     HH:mm")
    private List<String> monitorListDay;
    @ApiModelProperty(value = "检测时间组-夜间：     HH:mm")
    private List<String> monitorListNight;
    @ApiModelProperty(value = "检测时间组-日间2组：     HH:mm")
    private List<String> monitorListDayTwo;

}
