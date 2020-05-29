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
import com.yjjk.monitor.entity.pojo.ManagerInfo;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.PasswordUtils;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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
@Api(tags = {"管理员模块"})
public class ManagerController extends BaseController {

    /**
     * 新增管理员
     *
     * @param managerInfo
     * @param request
     * @param response
     */
    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public CommonResult addManager(ManagerInfo managerInfo, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        if (StringUtils.isNullorEmpty(managerInfo.getAccount()) || StringUtils.isNullorEmpty(managerInfo.getPassword())
                || StringUtils.isNullorEmpty(managerInfo.getName()) || StringUtils.isNullorEmpty(managerInfo.getPhone())
                || managerInfo.getSex() == null || StringUtils.isNullorEmpty(managerInfo.getDepartmentId())) {
            return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
        }
        ManagerInfo temp = super.managerService.selectByAccount(managerInfo);
        if (!StringUtils.isNullorEmpty(temp)) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_EXIST_ERROR);
        }
        // 密码加密
        managerInfo.setPassword(PasswordUtils.generate(managerInfo.getPassword()));
        managerInfo.setRole(2);
        managerInfo.setStatus(0);
        managerInfo.setToken(null);
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
    public CommonResult updateManager(ManagerInfo managerInfo, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        if (StringUtils.isNullorEmpty(managerInfo.getId())) {
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
     * @param Id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/manager", method = RequestMethod.DELETE)
    public CommonResult updateManager(@RequestParam(value = "Id") Integer Id,
                                      HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        ManagerInfo managerInfo = new ManagerInfo();
        managerInfo.setId(Id);
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
     * @param Id
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public CommonResult getManagerInfo(@ApiParam(value = "id不为空则按id查询") @RequestParam(value = "Id", required = false) Integer Id,
                                       @RequestParam(value = "role", required = false) Integer role,
                                       @RequestParam(value = "departmentId", required = false) Integer departmentId,
                                       @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        /********************** 参数初始化 **********************/
        if (Id == null && (currentPage == null || currentPage <= 0 || pageSize == null || pageSize <= 0)) {
            return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
        }

        List<ManagerInfo> ManagerInfos;
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> reqMap = new HashMap<>();
        if (StringUtils.isNullorEmpty(Id)) {
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
            paramMap.put("Id", Id);
        }
        ManagerInfos = super.managerService.selectNormalList(paramMap);
//        if (StringUtils.isNullorEmpty(ManagerInfos)) {
//            message = "获取失败";
//            returnResult(startTime, request, response, resultCode, message, "");
//            return;
//        }
        reqMap.put("list", ManagerInfos);
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
        ManagerInfo param = new ManagerInfo();
        param.setAccount(account);

        ManagerInfo managerInfo = super.managerService.selectByAccount(param);
        if (managerInfo == null) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_NOT_EXIST);
        }
        boolean login = super.managerService.login(password, managerInfo.getPassword());
        if (!login) {
            return ResultUtil.returnError(ErrorCodeEnum.MANAGER_PASSWORD_ERROR);
        }
        managerInfo.setLoginTime(DateUtil.getCurrentTime());
        super.managerService.updateManger(managerInfo);
        String token = super.loginStateService.login(request, managerInfo.getId());
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
     * @param Id
     * @param model
     * @param request
     * @param response
     */
    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public CommonResult managerLogin(@RequestParam(value = "Id") Integer Id,
                                     @RequestParam(value = "model") Integer model,
                                     HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/

        ManagerInfo managerInfo = super.managerService.getManagerInfo(Id);
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
