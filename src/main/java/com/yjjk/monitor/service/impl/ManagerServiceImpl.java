/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ManagerServiceImpl
 * Author:   CentreS
 * Date:     2019/7/17 14:12
 * Description: 管理员服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.constant.VivaConstant;
import com.yjjk.monitor.entity.pojo.ManagerInfo;
import com.yjjk.monitor.entity.viva.sso.request.Request;
import com.yjjk.monitor.entity.viva.sso.response.Response;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.ManagerService;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.NetUtils;
import com.yjjk.monitor.utility.PasswordUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 管理员服务
 * @create 2019/7/17
 */
@Service
public class ManagerServiceImpl extends BaseService implements ManagerService {

    @Override
    public boolean loginViva(String token) {
        ManagerInfo managerInfo = super.managerInfoMapper.selectByToken(token);
        Integer managerId = managerInfo.getId();
        Request r = new Request();
        r.setCode(managerInfo.getAccount()).setFirst_name(managerInfo.getName()).setLast_name(managerInfo.getName()).setType(400);
        String s = NetUtils.doVcPost(VivaConstant.ssoRequestUrl, JSON.toJSONString(r));
        Response response = JSON.parseObject(s, Response.class);
        VivaConstant.map.put(managerId, new String[]{response.getData().getToken().getToken(), DateUtil.getCurrentTimeLong().toString()});
        return true;
    }

    @Override
    public int insertManager(ManagerInfo managerInfo) {
        return super.managerInfoMapper.insertSelective(managerInfo);
    }

    @Override
    public int updateManger(ManagerInfo managerInfo) {
        return super.managerInfoMapper.updateByPrimaryKeySelective(managerInfo);
    }

    @Override
    public ManagerInfo getManagerInfo(Integer managerId) {
        return super.managerInfoMapper.selectByPrimaryKey(managerId);
    }

    @Override
    public List<ManagerInfo> selectNormalList(Map<String, Object> paramMap) {
        return super.managerInfoMapper.selectNormalList(paramMap);
    }

    @Override
    public int selectNormalListCount(Map<String, Object> paramMap) {
        return super.managerInfoMapper.selectNormalListCount(paramMap);
    }

    @Override
    public boolean login(String password, String md5) {
        return PasswordUtils.verify(password, md5);
    }

    public static void main(String[] args) {
        boolean verify = PasswordUtils.verify("123456", "d071D7009e01d7ce10eF94B989f73136703e3E39F23Ac746");
        System.out.println(verify);
    }

    @Override
    public ManagerInfo selectByAccount(ManagerInfo managerInfo) {
        return super.managerInfoMapper.selectByAccount(managerInfo);
    }

    @Override
    public ManagerInfo selectByToken(String token) {
        return super.managerInfoMapper.selectByToken(token);
    }
}
