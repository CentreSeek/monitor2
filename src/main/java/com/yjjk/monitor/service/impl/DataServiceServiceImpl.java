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

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.entity.transaction.BackgroundResult;
import com.yjjk.monitor.entity.transaction.BackgroundSend;
import com.yjjk.monitor.entity.transaction.XueYaInModel;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.DataServerService;
import com.yjjk.monitor.utility.NetUtils;
import com.yjjk.monitor.utility.ResultUtil;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

/**
 * @author CentreS
 * @Description: 血压
 * @create 2019/7/22
 */
@Service
public class DataServiceServiceImpl extends BaseService implements DataServerService {


    @Override
    public Boolean connectDataServer(String machineId, Integer type, String connectionType, Integer baseId, XueYaInModel xueYaInModel) throws Exception {
        BackgroundSend backgroundSend = new BackgroundSend();
        backgroundSend.setDeviceId(machineId);
        backgroundSend.setData(connectionType);
        backgroundSend.setType(type);
        backgroundSend.setBaseId(baseId);
        backgroundSend.setBigData(JSON.toJSONString(xueYaInModel));
        String response = NetUtils.doPost(connectRepeater.getStart(), backgroundSend);
        logger.info("硬件服务器返回值：     " + response);
        if (response == null || response == "500") {
            throw new ConnectException();
        }
        BackgroundResult backgroundResult = JSON.parseObject(response, BackgroundResult.class);
        if (backgroundResult == null || !"200".equals(backgroundResult.getCode())) {
            throw new ConnectException();
        }
        return true;
    }

    @Override
    public Boolean connectDataServer(String machineId, Integer type, String connectionType, Integer baseId) throws Exception {
        return connectDataServer(machineId, type, connectionType, baseId, null);
    }
}
