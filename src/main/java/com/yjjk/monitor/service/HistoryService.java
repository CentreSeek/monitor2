/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientRecordService
 * Author:   CentreS
 * Date:     2019/7/22 11:41
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.entity.BO.PageBO;
import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.PagedGridResult;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorVO;
import com.yjjk.monitor.entity.log.ManageLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CentreS
 * @Description: 历史记录
 * @create 2019/7/22
 */
@Service
public interface HistoryService {

    /**
     * records-获取历史记录
     * @param pageBO
     * @param bo
     * @return
     */
    PagedGridResult getHistory(PageBO pageBO, GetRecordsBO bo);

}
