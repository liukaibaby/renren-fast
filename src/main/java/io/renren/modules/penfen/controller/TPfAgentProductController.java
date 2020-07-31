package io.renren.modules.penfen.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.penfen.entity.TPfAgentProductEntity;
import io.renren.modules.penfen.service.TPfAgentProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@RestController
@RequestMapping("/penfen/agentproduct")
public class TPfAgentProductController {
    @Autowired
    private TPfAgentProductService tPfAgentProductService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("penfen:agentproduct:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tPfAgentProductService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("penfen:agentproduct:info")
    public R info(@PathVariable("id") Integer id) {
        TPfAgentProductEntity tPfAgentProduct = tPfAgentProductService.getById(id);

        return R.ok().put("tPfAgentProduct", tPfAgentProduct);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("penfen:agentproduct:save")
    public R save(@RequestBody TPfAgentProductEntity tPfAgentProduct) {
        tPfAgentProductService.save(tPfAgentProduct);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("penfen:agentproduct:update")
    public R update(@RequestBody TPfAgentProductEntity tPfAgentProduct) {
        tPfAgentProductService.updateById(tPfAgentProduct);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("penfen:agentproduct:delete")
    public R delete(@RequestBody Integer[] ids) {
        tPfAgentProductService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
