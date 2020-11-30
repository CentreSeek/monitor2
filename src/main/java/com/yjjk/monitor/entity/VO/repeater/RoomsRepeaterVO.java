package com.yjjk.monitor.entity.VO.repeater;

import com.yjjk.monitor.entity.ListVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2019-12-20 14:04:33
 **/
@Data
@Accessors(chain = true)
@ApiModel("分配路由-")
public class RoomsRepeaterVO {
//    private Integer canBindRepeaterCount;
    @ApiModelProperty(value = "房间信息")
    private List<RoomsRepeaterVORooms> rooms;
    private List<ListVO> unBindRepeaterList;
}
