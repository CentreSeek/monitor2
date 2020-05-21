/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: RecordHistory
 * Author:   CentreS
 * Date:     2019/7/23 10:54
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.entity.VO.history;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author CentreS
 * @Description: 历史记录
 * @create 2019/7/23
 */
@Data
@Accessors(chain = true)
@ApiModel("历史记录")
public class RecordsHistory {

    @ApiModelProperty(value = "id")
    private Integer recordId;
    private String patientName;
    private String patientCaseNum;
    private String departmentName;
    private String roomAndBed;
//    private String machineName;
    private String startTime;
    private String endTime;
    @ApiModelProperty(value = "使用状态 0-使用中 1-结束")
    private Integer recordStatus;


}
