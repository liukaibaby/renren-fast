package io.renren.modules.penfen.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.modules.penfen.entity.TPfProductDetailsEntity;
import io.renren.modules.penfen.entity.TPfProductEntity;
import io.renren.modules.penfen.entity.TPfProductPackageEntity;
import io.renren.modules.penfen.entity.TPfScheme;
import io.renren.modules.penfen.service.TPfProductService;
import io.renren.modules.penfen.service.TPfSchemeService;
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
@RequestMapping("/penfen/product")
public class TPfProductController {
    @Autowired
    private TPfProductService tPfProductService;

    @Autowired
    private TPfSchemeService tPfSchemeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("penfen:product:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tPfProductService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("penfen:product:info")
    public R info(@PathVariable("id") Integer id) {
        TPfProductEntity tPfProduct = tPfProductService.getById(id);

        return R.ok().put("tPfProduct", tPfProduct);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("penfen:product:save")
    public R save(@RequestBody TPfProductEntity tPfProduct) {
        tPfProductService.save(tPfProduct);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("penfen:product:update")
    public R update(@RequestBody TPfProductEntity tPfProduct) {
        tPfProductService.updateById(tPfProduct);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("penfen:product:delete")
    public R delete(@RequestBody Integer[] ids) {
        tPfProductService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 获取当前用开通产品
     */
    @GetMapping("/getProduct/{id}")
    public R getProduct(@PathVariable("id") Long id) {
        List<TPfProductEntity> user = tPfProductService.getProduct(id);
        return R.ok().put("user", user);
    }

    /**
     * 获取套餐信息
     */
    @GetMapping("/productPackage/{proId}")
    public R productPackage(@PathVariable("proId") Long proId) {
        List<TPfProductPackageEntity> list = tPfProductService.getProductPackage(proId);
        return R.ok().put("product", list);
    }

    /**
     * 获取套餐产品详情
     */
    @GetMapping("/packageDetails/{proId}")
    public R packageDetails(@PathVariable("proId") Long proId) {
        List<TPfProductDetailsEntity> list = tPfProductService.getPackageDetails(proId);
        return R.ok().put("package", list);
    }

    /**
     * 获取 ABC 套餐
     */
    @RequestMapping("/proList")
    public R porList() {
        List<TPfScheme> tPfSchemes = tPfSchemeService.list();

        return R.ok().put("scheme", tPfSchemes);
    }

}
