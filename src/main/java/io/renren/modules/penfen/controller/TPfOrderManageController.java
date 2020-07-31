package io.renren.modules.penfen.controller;

import java.util.*;

import io.renren.modules.penfen.entity.TPfOrderManageEntity;
import io.renren.modules.penfen.service.TPfOrderManageService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import static io.renren.common.utils.ShiroUtils.getUserId;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-23 17:09:40
 */
@RestController
@RequestMapping("/penfen/tpfordermanage")
public class TPfOrderManageController extends AbstractController {
    @Autowired
    private TPfOrderManageService tPfOrderManageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("penfen:tpfordermanage:list")
    public R list(@RequestParam Map<String, Object> params) {
        if (getUserId() != io.renren.common.utils.Constant.SUPER_AGENT_ADMIN) {
            params.put("createUserId", getUserId());
        }

        PageUtils page = tPfOrderManageService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("penfen:tpfordermanage:info")
    public R info(@PathVariable("id") Long id) {
        TPfOrderManageEntity tPfOrderManage = tPfOrderManageService.getById(id);

        return R.ok().put("tPfOrderManage", tPfOrderManage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("penfen:tpfordermanage:save")
    public R save(@RequestBody TPfOrderManageEntity tPfOrderManage) {
        tPfOrderManage.setTradeTime(new Date());
        tPfOrderManage.setCreateUserId(getUserId());
        boolean check = tPfOrderManageService.add(tPfOrderManage, getUser());
        return R.ok().put("check", check);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("penfen:tpfordermanage:update")
    public R update(@RequestBody TPfOrderManageEntity tPfOrderManage) {
        tPfOrderManageService.updateById(tPfOrderManage);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("penfen:tpfordermanage:delete")
    public R delete(@RequestBody Long[] ids) {
        tPfOrderManageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
