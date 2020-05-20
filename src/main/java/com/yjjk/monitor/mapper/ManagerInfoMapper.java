package com.yjjk.monitor.mapper;
import com.yjjk.monitor.entity.*;
import com.yjjk.monitor.entity.pojo.ManagerInfo;
import com.yjjk.monitor.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface ManagerInfoMapper extends MyMapper<ManagerInfo> {

    /**
     * select---查询所有正常管理员
     * @return
     */
    List<ManagerInfo>  selectNormalList(Map<String, Object> paramMap);

    /**
     * select---查询管理员数量
     * @param paramMap
     * @return
     */
    int selectNormalListCount(Map<String, Object> paramMap);

    /**
     * select---使用账户查询用户信息
     * @param managerInfo
     * @return
     */
    ManagerInfo selectByAccount(ManagerInfo managerInfo);

    /**
     * 使用token查询管理员信息
     * @param Token
     * @return
     */
    ManagerInfo selectByToken(String Token);

}