package io.renren.modules.penfen.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.penfen.entity.TPfAgentGradeEntity;
import io.renren.modules.penfen.service.TPfAgentGradeService;
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
 * @date 2020-06-23 11:21:29
 */
@RestController
@RequestMapping("/penfen/agentgrade")
public class TPfAgentGradeController {
    @Autowired
    private TPfAgentGradeService tPfAgentGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("penfen:agentgrade:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tPfAgentGradeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("penfen:agentgrade:info")
    public R getInfo(@PathVariable("id") Long id) {
        TPfAgentGradeEntity tPfAgentGrade = tPfAgentGradeService.getById(id);

        return R.ok().put("tPfAgentGrade", tPfAgentGrade);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("penfen:tpfagentgrade:save")
    public R save(@RequestBody TPfAgentGradeEntity tPfAgentGrade) {
        tPfAgentGradeService.save(tPfAgentGrade);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("penfen:tpfagentgrade:update")
    public R update(@RequestBody TPfAgentGradeEntity tPfAgentGrade) {
        tPfAgentGradeService.updateById(tPfAgentGrade);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("penfen:tpfagentgrade:delete")
    public R delete(@RequestBody Long[] ids) {
        tPfAgentGradeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
