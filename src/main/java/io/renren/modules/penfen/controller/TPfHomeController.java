package io.renren.modules.penfen.controller;

import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.modules.penfen.service.TPfHomeService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import static io.renren.common.utils.ShiroUtils.getUserId;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@RestController
@RequestMapping("/penfen/home")
public class TPfHomeController {
    @Autowired
    private TPfHomeService tPfHomeService;

    /**
     * 首页初始化获取代理商信息
     */
    @RequestMapping("/info")
    public R info() {
        SysUserEntity user = tPfHomeService.initGetUser(getUserId());
        return R.ok().put("user", user);
    }

    /**
     * 首页 获取 当前登录用户充值退款记录
     */
    @RequestMapping("/initChartLine")
    public R initChartLine() {
        Long userId = null;
        if (getUserId() != Constant.SUPER_AGENT_ADMIN) {
            userId = getUserId();
        }
        Map<String, List> tPfRechargeRecordEntities = tPfHomeService.initChartLine(userId);
        return R.ok().put("rec", tPfRechargeRecordEntities);
    }
}
