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

import com.yjjk.monitor.entity.pojo.ManagerInfo;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.ManagerService;
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
    public int insertManager(ManagerInfo managerInfo) {
        return super.ManagerInfoMapper.insertSelective(managerInfo);
    }

    @Override
    public int updateManger(ManagerInfo managerInfo) {
        return super.ManagerInfoMapper.updateByPrimaryKeySelective(managerInfo);
    }

    @Override
    public ManagerInfo getManagerInfo(Integer managerId) {
        return super.ManagerInfoMapper.selectByPrimaryKey(managerId);
    }

    @Override
    public List<ManagerInfo> selectNormalList(Map<String, Object> paramMap) {
        return super.ManagerInfoMapper.selectNormalList(paramMap);
    }

    @Override
    public int selectNormalListCount(Map<String, Object> paramMap) {
        return super.ManagerInfoMapper.selectNormalListCount(paramMap);
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
        return super.ManagerInfoMapper.selectByAccount(managerInfo);
    }

    @Override
    public ManagerInfo selectByToken(String token) {
        return super.ManagerInfoMapper.selectByToken(token);
    }
}
