package io.renren.modules.penfen.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.modules.penfen.entity.TPfProductDetailsEntity;
import io.renren.modules.penfen.entity.TPfProductPackageEntity;
import io.renren.modules.penfen.service.TPfProductDetailsService;
import io.renren.modules.penfen.service.TPfProductPackageService;
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
@RequestMapping("/penfen/productpackage")
public class TPfProductPackageController {
    @Autowired
    private TPfProductPackageService tPfProductPackageService;
    @Autowired
    private TPfProductDetailsService tPfProductDetailsService;

    /**
     * 获取产品套餐信息
     */
    /**
     * 获取当前用户产品信息
     */
    @GetMapping("/list")
    @RequiresPermissions("penfen:productpackage:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tPfProductPackageService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("penfen:productpackage:info")
    public R info(@PathVariable("id") Integer id) {
        TPfProductPackageEntity tPfProductPackage = tPfProductPackageService.getById(id);
        Map map = new HashMap();
        map.put("p_id", id);
        if (tPfProductPackage != null) {
            List<TPfProductDetailsEntity> tPfProductDetailsEntity = (List<TPfProductDetailsEntity>) tPfProductDetailsService.listByMap(map);
            tPfProductPackage.setProductDetailsEntityList(tPfProductDetailsEntity);
        }
        return R.ok().put("package", tPfProductPackage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("penfen:productpackage:save")
    public R save(@RequestBody TPfProductPackageEntity tPfProductPackage) {
        tPfProductPackageService.save(tPfProductPackage);
        for (TPfProductDetailsEntity tPfProductDetailsEntity : tPfProductPackage.getProductDetailsEntityList()) {
            tPfProductDetailsEntity.setPId(tPfProductPackage.getId());
            tPfProductDetailsService.save(tPfProductDetailsEntity);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("penfen:productpackage:update")
    public R update(@RequestBody TPfProductPackageEntity tPfProductPackage) {
        tPfProductPackageService.updateById(tPfProductPackage);
        for (TPfProductDetailsEntity tPfProductDetailsEntity : tPfProductPackage.getProductDetailsEntityList()) {
            tPfProductDetailsEntity.setPId(tPfProductPackage.getId());
            tPfProductDetailsService.saveOrUpdate(tPfProductDetailsEntity);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("penfen:productpackage:delete")
    public R delete(@RequestBody Integer[] ids) {
        tPfProductPackageService.removeByIds(Arrays.asList(ids));
        Map map = new HashMap();
        for (int i = 0; i < ids.length; i++) {
            map.put("p_id", ids[i]);
        }
        tPfProductDetailsService.removeByMap(map);
        return R.ok();
    }

}
