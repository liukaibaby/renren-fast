package io.renren.modules.penfen.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.penfen.entity.TPfProductDetailsEntity;
import io.renren.modules.penfen.service.TPfProductDetailsService;
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
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@RestController
@RequestMapping("/penfen/productdetails")
public class TPfProductDetailsController {
    @Autowired
    private TPfProductDetailsService tPfProductDetailsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("penfen:productdetails:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tPfProductDetailsService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("penfen:productdetails:info")
    public R info(@PathVariable("id") Integer id){
		TPfProductDetailsEntity tPfProductDetails = tPfProductDetailsService.getById(id);

        return R.ok().put("tPfProductDetails", tPfProductDetails);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("penfen:productdetails:save")
    public R save(@RequestBody TPfProductDetailsEntity tPfProductDetails){
		tPfProductDetailsService.save(tPfProductDetails);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("penfen:productdetails:update")
    public R update(@RequestBody TPfProductDetailsEntity tPfProductDetails){
		tPfProductDetailsService.updateById(tPfProductDetails);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("penfen:productdetails:delete")
    public R delete(@RequestBody Integer[] ids){
		tPfProductDetailsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
