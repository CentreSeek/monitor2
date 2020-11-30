package com.yjjk.monitor.entity.BO.hospital;

import com.yjjk.monitor.entity.hospital.Room;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-08-31 14:16:13
 **/
@Data
@Accessors(chain = true)
public class AddRoomBO {
    private Room room;
    private Integer departmentId;
}
