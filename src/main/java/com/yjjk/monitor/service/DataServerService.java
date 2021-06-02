package com.yjjk.monitor.service;

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.entity.transaction.BackgroundResult;
import com.yjjk.monitor.entity.transaction.XueYaInModel;
import org.springframework.stereotype.Service;

/**
 * @program: monitor2
 * @description: 数据服务器
 * @author: CentreS
 * @create: 2021-06-01 15:55:22
 **/
@Service
public interface DataServerService {

    Boolean connectDataServer(String machineId, Integer type, String connectionType, Integer baseId, XueYaInModel xueYaInModel) throws Exception;

    Boolean connectDataServer(String machineId, Integer type, String connectionType, Integer baseId) throws Exception;
}
