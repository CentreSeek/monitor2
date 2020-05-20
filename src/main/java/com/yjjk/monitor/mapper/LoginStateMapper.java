package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.pojo.LoginState;
import com.yjjk.monitor.entity.pojo.ManagerInfo;
import com.yjjk.monitor.my.mapper.MyMapper;

public interface LoginStateMapper extends MyMapper<LoginState> {

    LoginState selectByManagerId(Integer managerId);

    /**
     * 使用token查找departmentId
     *
     * @param token
     * @return
     */
    Integer selectDepartmentIdByToken(String token);

    /**
     * 使用token获取管理员资料
     * @param token
     * @return
     */
    ManagerInfo selectByToken(String token);
}