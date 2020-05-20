/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: UseMachine
 * Author:   CentreS
 * Date:     2019/7/19 9:39
 * Description: 启用设备
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.entity.history;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 血氧历史数据
 * @author CentreS
 * @create 2019/7/19
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "血氧历史数据")
public class BloodHistoryData {

    @ApiModelProperty(value = "时间戳")
    private Long timestamp;
    @ApiModelProperty(value = "血氧")
    private Double bloodOxygen;
    @ApiModelProperty(value = "心率")
    private Double heartRate;

}
