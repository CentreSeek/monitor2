package com.yjjk.monitor.entity.log;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description: 操作日志
 * @author: CentreS
 * @create: 2020-05-18 09:25:50
 **/
@Data
@Accessors(chain = true)
public class ManageLog {

    private Integer managerId;
    private String action;
    private String time;

    public ManageLog(Integer managerId, String action, String time) {
        this.managerId = managerId;
        this.action = action;
        this.time = time;
    }

}
