/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientRecordServiceImpl
 * Author:   CentreS
 * Date:     2019/7/22 11:41
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.yjjk.monitor.constant.MachineConstant;
import com.yjjk.monitor.entity.BO.PageBO;
import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.PagedGridResult;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CentreS
 * @Description: 监控模块
 * @create 2019/7/22
 */
@Service
public class HistoryServiceImpl extends BaseService implements HistoryService {

    @Override
    public PagedGridResult getHistory(PageBO pageBO, GetRecordsBO bo) {
        PageHelper.startPage(pageBO.getPage(), pageBO.getPageSize(), false);
        switch (bo.getType()) {
            case MachineConstant.TEMPERATURE:
                List<RecordsHistory> list = super.recordTemperatureMapper.getHistoryRecords(bo);
                return setterPagedGrid(list, pageBO.getPage());
            case MachineConstant.ECG:
                List<RecordsHistory> historyRecords = super.recordEcgMapper.getHistoryRecords(bo);
                return setterPagedGrid(historyRecords, pageBO.getPage());
            case MachineConstant.BLOOD:
                List<RecordsHistory> historyRecords1 = super.recordBloodMapper.getHistoryRecords(bo);
                return setterPagedGrid(historyRecords1, pageBO.getPage());
            case MachineConstant.SLEEPING:
                List<RecordsHistory> historyRecords2 = super.recordSleepingMapper.getHistoryRecords(bo);
                return setterPagedGrid(historyRecords2, pageBO.getPage());
            default:
                return setterPagedGrid(new ArrayList<>(), pageBO.getPage());
        }
    }
}
