package com.yjjk.monitor.entity.VO.repeater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-11-30 15:16:28
 **/
@Data
@Accessors(chain = true)
@ApiModel("分配路由-获取房间信息")
public class RoomsRepeaterVORooms {
    private Integer roomId;
    private String roomName;
    @ApiModelProperty(value = "当前房间已绑定路由数")
    private Integer repeaterCount;
}
