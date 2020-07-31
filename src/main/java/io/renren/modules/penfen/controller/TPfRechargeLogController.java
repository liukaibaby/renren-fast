package io.renren.modules.penfen.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.Constant;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.penfen.entity.TPfRechargeLogEntity;
import io.renren.modules.penfen.service.TPfAgentUserService;
import io.renren.modules.penfen.service.TPfFinanceLogService;
import io.renren.modules.penfen.service.TPfRechargeLogService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@RestController
@RequestMapping("/penfen/rechargelog")
public class TPfRechargeLogController extends AbstractController {
    @Autowired
    private TPfRechargeLogService tPfRechargeLogService;

    @Autowired
    private TPfAgentUserService agentUserService;
    @Autowired
    private TPfFinanceLogService financeLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("penfen:rechargelog:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tPfRechargeLogService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("penfen:rechargelog:info")
    public R info(@PathVariable("id") Integer id) {
        TPfRechargeLogEntity tPfRechargeLog = tPfRechargeLogService.getById(id);

        return R.ok().put("tPfRechargeLog", tPfRechargeLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("penfen:rechargelog:save")
    public R save(@RequestBody TPfRechargeLogEntity tPfRechargeLog) {
        tPfRechargeLogService.save(tPfRechargeLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("penfen:rechargelog:update")
    public R update(@RequestBody TPfRechargeLogEntity tPfRechargeLog) {
        tPfRechargeLogService.updateById(tPfRechargeLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("penfen:rechargelog:delete")
    public R delete(@RequestBody Integer[] ids) {
        tPfRechargeLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 查看代理用户充值日志
     */
    @SysLog("用户充值日志")
    @GetMapping("/userRechargeLog")
    public R agentList(@RequestParam Map<String, Object> params) {
        // 如果不是超级管理员 只能看到自己的数据
        if (getUserId() != Constant.SUPER_AGENT_ADMIN) {
            params.put("user_id", getUserId());
        }
        PageUtils page = financeLogService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 用户充值
     */
    @SysLog("用户充值")
    @PostMapping("/userRecharge")
    public R userRecharge(@RequestBody TPfAgentUserEntity agentUserEntity) {
        agentUserService.userRecharge(agentUserEntity, getUser());
        return R.ok();
    }


}
