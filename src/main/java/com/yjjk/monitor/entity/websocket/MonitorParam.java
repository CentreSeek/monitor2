package com.yjjk.monitor.entity.websocket;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2021-05-27 10:36:54
 **/
@Data
@Accessors(chain = true)
public class MonitorParam {

    private Integer departmentId;
    private Integer start;
    private Integer end;

}
