package io.renren.modules.appium.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.appium.entity.TPfAppiumEntity;
import io.renren.modules.appium.overall.StaticConstant;
import io.renren.modules.appium.service.TPfAppiumService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.annotation.PostConstruct;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-02 17:19:10
 */
@RestController
@RequestMapping("/appium/tpfappium")
public class TPfAppiumController {
    @Autowired
    private TPfAppiumService tPfAppiumService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("appium:tpfappium:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tPfAppiumService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:tpfappium:info")
    public R info(@PathVariable("id") String id) {
        TPfAppiumEntity tPfAppium = tPfAppiumService.getById(id);

        return R.ok().put("tPfAppium", tPfAppium);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("generator:tpfappium:save")
    public R save(@RequestBody TPfAppiumEntity tPfAppium) {
        // tPfAppiumService.save(tPfAppium);
        System.out.println(StaticConstant.UDID);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:tpfappium:update")
    public R update(@RequestBody TPfAppiumEntity tPfAppium) {
        tPfAppiumService.updateById(tPfAppium);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:tpfappium:delete")
    public R delete(@RequestBody String[] ids) {
        tPfAppiumService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 功能点赞/评论
     */
    @RequestMapping("/clickOnComments")
//    @RequiresPermissions("generator:tpfappium:save")
    public R clickOnComments(@RequestBody TPfAppiumEntity tPfAppium) {
        //  tPfAppiumService.clik(tPfAppium);
        tPfAppiumService.clickOnComments(tPfAppium);
        return R.ok();
    }

    /**
     * 手机设备重新启动
     */
    @RequestMapping("/reboot")
//    @RequiresPermissions("generator:tpfappium:save")
    public R join(@RequestBody TPfAppiumEntity tPfAppium) {

        tPfAppiumService.reboot(tPfAppium);

        return R.ok();
    }


}
