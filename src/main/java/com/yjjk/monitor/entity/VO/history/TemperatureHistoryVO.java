package com.yjjk.monitor.entity.VO.history;

import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.VO.monitor.MachinesInfoVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: monitor2
 * @description: 历史记录
 * @author: CentreS
 * @create: 2020-05-11 16:54:39
 **/
@Data
@Accessors(chain = true)
@ApiModel("历史记录")
public class TemperatureHistoryVO {

    @ApiModelProperty(value = "monitor：监控信息列表")
    private List<MonitorBaseVO> monitorVOList;
}

