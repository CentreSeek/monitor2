package com.yjjk.monitor.entity.VO.monitor;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * @program: monitor2.0
 * @description: 科室使用人数
 * @author: CentreS
 * @create: 2019-12-20 14:04:33
 **/
@Data
@Accessors(chain = true)
@ApiModel("科室使用人数")
public class StaticsRecordDepartmentVO implements Comparable<StaticsRecordDepartmentVO> {
    private Integer rank;
    private String departmentName;
    private Integer equipmentQuantity;

    @Override
    public int compareTo(@NotNull StaticsRecordDepartmentVO o) {
        return o.getEquipmentQuantity() - this.getEquipmentQuantity();
    }
}
