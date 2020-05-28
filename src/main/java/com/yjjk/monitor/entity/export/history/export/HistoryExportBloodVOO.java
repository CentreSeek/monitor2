/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: MachineExportVO
 * Author:   CentreS
 * Date:     2019/9/25 10:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.entity.export.history.export;

import com.yjjk.monitor.entity.export.history.HistoryExportBaseVO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author CentreS
 * @Description:
 * @create 2019/9/25
 */
@Data
@Accessors(chain = true)
public class HistoryExportBloodVOO extends HistoryExportBaseVO{
    private String patientName;
    private String caseNum;
    private String departmentName;
    private String room;
    private String bed;
    private String time;
    private String blood;
    private String pi;
    private String heartRate;

}

