/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ManagerController
 * Author:   CentreS
 * Date:     2019/7/17 13:58
 * Description: 管理员模块
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.controller;

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.entity.ZsManagerInfo;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.PasswordUtils;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 管理员模块
 * @create 2019/7/17
 */
@RestController
@RequestMapping("/manage")
public class ManagerController extends BaseController {

    /**
     * 新增管理员
     *
     * @param managerInfo
     * @param request
     * @param response
     */
    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public CommonResult addManager(ZsManagerInfo managerInfo, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        if (StringUtils.isNullorEmpty(managerInfo.getAccount()) || StringUtils.isNullorEmpty(managerInfo.getPassword())
                || StringUtils.isNullorEmpty(managerInfo.getName()) || StringUtils.isNullorEmpty(managerInfo.getPhone())
                || managerInfo.getSex() == null || StringUtils.isNullorEmpty(managerInfo.getDepartmentId())) {
            return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
        }
        ZsManagerInfo temp = super.managerService.selectByAccount(managerInfo);
        if (!StringUtils.isNullorEmpty(temp)) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_EXIST_ERROR);
        }
        // 密码加密
        managerInfo.setPassword(PasswordUtils.generate(managerInfo.getPassword()));
        managerInfo.setRole(2);
        managerInfo.setStatus(0);
        int i = super.managerService.insertManager(managerInfo);
        if (i == 0) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_INSERT_ERROR);
        }
        return ResultUtil.returnSuccess(i);
    }

    /**
     * 更新管理员信息
     *
     * @param managerInfo
     * @param request
     * @param response
     */
    @RequestMapping(value = "/manager", method = RequestMethod.PUT)
    public CommonResult updateManager(ZsManagerInfo managerInfo, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        if (StringUtils.isNullorEmpty(managerInfo.getManagerId())) {
            return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
        }

        // 密码加密
        managerInfo.setPassword(PasswordUtils.generate(managerInfo.getPassword()));
        int i = super.managerService.updateManger(managerInfo);
        if (i == 0) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_UPDATE_ERROR);
        }
        return ResultUtil.returnSuccess(i);
    }

    /**
     * 删除管理员
     *
     * @param managerId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/manager", method = RequestMethod.DELETE)
    public CommonResult updateManager(@RequestParam(value = "managerId") Integer managerId,
                                      HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        ZsManagerInfo managerInfo = new ZsManagerInfo();
        managerInfo.setManagerId(managerId);
        managerInfo.setStatus(1);

        int i = super.managerService.updateManger(managerInfo);
        if (i == 0) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_UPDATE_ERROR);
        }
        return ResultUtil.returnSuccess(i);
    }

    /**
     * 获取管理员信息
     *
     * @param managerId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public CommonResult getManagerInfo(@RequestParam(value = "managerId", required = false) Integer managerId,
                                       @RequestParam(value = "role", required = false) Integer role,
                                       @RequestParam(value = "departmentId", required = false) Integer departmentId,
                                       @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                       HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        if (managerId == null && (currentPage == null || currentPage <= 0 || pageSize == null || pageSize <= 0)) {
            return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
        }

        List<ZsManagerInfo> zsManagerInfos;
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> reqMap = new HashMap<>();
        if (StringUtils.isNullorEmpty(managerId)) {
            // 超管查询管理员+科室管理员账号, 管理员查看科室管理员账号
            if (role == 0) {
                paramMap.put("roles", "get");
            } else if (role == 1) {
                paramMap.put("role", 2);
            } else {
                return ResultUtil.returnError(ErrorCodeEnum.MANAGER_ROLE_ERROR);
            }
            if (departmentId != null) {
                paramMap.put("departmentId", departmentId);
            }
            int totalCount = super.managerService.selectNormalListCount(paramMap);
            int startLine = (currentPage - 1) * (pageSize);
            int totalPage = (totalCount + pageSize - 1) / pageSize;
            paramMap.put("startLine", startLine);
            paramMap.put("pageSize", pageSize);
            reqMap.put("totalPage", totalPage);
            reqMap.put("currentPage", currentPage);
        } else {
            paramMap.put("managerId", managerId);
        }
        zsManagerInfos = super.managerService.selectNormalList(paramMap);
//        if (StringUtils.isNullorEmpty(zsManagerInfos)) {
//            message = "获取失败";
//            returnResult(startTime, request, response, resultCode, message, "");
//            return;
//        }
        reqMap.put("list", zsManagerInfos);
        return ResultUtil.returnSuccess(reqMap);
    }


    /**
     * 管理员登录
     *
     * @param account
     * @param password
     * @param request
     * @param response
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult managerLogin(@RequestParam(value = "account") String account,
                                     @RequestParam(value = "password") String password,
                                     HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        ZsManagerInfo param = new ZsManagerInfo();
        param.setAccount(account);

        ZsManagerInfo managerInfo = super.managerService.selectByAccount(param);
        if (managerInfo == null) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_NOT_EXIST);
        }
        boolean login = super.managerService.login(param.setPassword(password), managerInfo.getPassword());
        if (!login) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_PASSWORD_ERROR);
        }
        super.managerService.updateManger(managerInfo.setLoginTime(DateUtil.getCurrentTime()));
        String token = super.loginStateService.login(request, managerInfo.getManagerId());
        List<Integer> posts = new ArrayList<>();
        switch (managerInfo.getRole()) {
            case 0:
                posts.add(PLATEFORM);
                posts.add(HISTORY_RECORD);
                posts.add(BED_MANAGE);
                posts.add(MACHINE_MANAGE);
                posts.add(ACCOUNT_MANAGE);
                posts.add(REPEATER_MANAGE);
                managerInfo.setPosts(posts);
            case 1:
                posts.add(PLATEFORM);
                posts.add(HISTORY_RECORD);
                posts.add(BED_MANAGE);
                posts.add(MACHINE_MANAGE);
                posts.add(ACCOUNT_MANAGE);
                posts.add(REPEATER_MANAGE);
                managerInfo.setPosts(posts);
            case 2:
                posts.add(PLATEFORM);
                posts.add(HISTORY_RECORD);
        }
        managerInfo.setPosts(posts);
        managerInfo.setToken(token);
        managerInfo.setPassword(null);
        return ResultUtil.returnSuccess(managerInfo);
    }

    /**
     * 用户登出
     *
     * @param token
     * @param request
     * @param response
     */
    @RequestMapping(value = "/login", method = RequestMethod.DELETE)
    public CommonResult managerLoginOut(@RequestParam(value = "token") String token,
                                        HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        int i = super.loginStateService.loginOut(token);

        if (i == 0) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_LOGIN_OUT_ERROR);
        }
        return ResultUtil.returnSuccess(i);
    }

    /**
     * 权限验证
     *
     * @param managerId
     * @param model
     * @param request
     * @param response
     */
    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public CommonResult managerLogin(@RequestParam(value = "managerId") Integer managerId,
                                     @RequestParam(value = "model") Integer model,
                                     HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/

        ZsManagerInfo managerInfo = super.managerService.getManagerInfo(managerId);
        if (managerInfo == null) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_NOT_EXIST);
        }
        switch (managerInfo.getRole()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                if (!model.equals(PLATEFORM) && !model.equals(HISTORY_RECORD)) {
                    return ResultUtil.returnError(ErrorCodeEnum.MANAGER_REFUSE);
                }
        }
        managerInfo.setPassword(null);
        return ResultUtil.returnSuccess(managerInfo);
    }
}
