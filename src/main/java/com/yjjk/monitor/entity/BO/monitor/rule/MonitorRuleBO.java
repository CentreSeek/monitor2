package com.yjjk.monitor.entity.BO.monitor.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@ApiModel("设置规则：数据")
public class MonitorRuleBO {

    @NotNull
    @ApiModelProperty(value = "0：体温 1：心率 2：呼吸率 3：血氧 4：舒张压 5：收缩压")
    private Integer type;

    @NotNull
    @ApiModelProperty(value = "参数1")
    private Double paramOne;
    @NotNull
    @ApiModelProperty(value = "参数2")
    private Double paramTwo;
    @ApiModelProperty(value = "参数3")
    private Double paramThree;

//    @ApiModelProperty(value = "高低预警：0 开，1 关")
//    private Integer alertFlag;
//
//    @ApiModelProperty(value = "偏低预警阈值")
//    private Double lowAlert;
//
//    @ApiModelProperty(value = "偏高预警阈值")
//    private Double highAlert;

}