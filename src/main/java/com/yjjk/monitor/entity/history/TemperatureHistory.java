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

import java.util.List;

/**
 * @author CentreS
 * @Description: 体温历史数据
 * @create 2019/7/19
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "体温历史数据")
public class TemperatureHistory {

    @ApiModelProperty(value = "history")
    private List<List<TemperatureHistoryData>> history;

}
