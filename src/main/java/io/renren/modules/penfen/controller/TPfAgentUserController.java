package io.renren.modules.penfen.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.penfen.overall.Constant;
import io.renren.modules.penfen.service.TPfAgentUserService;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.catalina.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import static io.renren.common.utils.ShiroUtils.getUserId;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@RestController
@RequestMapping("/penfen/agentuser")
public class TPfAgentUserController {
    @Autowired
    private TPfAgentUserService tPfAgentUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("penfen:agentuser:list")
    public R list(@RequestParam Map<String, Object> params) {
        if (getUserId() != io.renren.common.utils.Constant.SUPER_AGENT_ADMIN) {
            params.put("createUserId", getUserId());
        }
        PageUtils page = tPfAgentUserService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("penfen:agentuser:info")
    public R info(@PathVariable("id") Long id) {

        SysUserEntity user = tPfAgentUserService.queryOrUpdate(id);
        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(id);
        if (roleIdList != null) {
            user.setRoleIdList(roleIdList);
        }
        return R.ok().put("user", user);

    }

    /**
     * 保存校验余额是否足够
     */
    public int saveCheck(SysUserEntity user) {
        int check = 0;
        if (getUserId() != io.renren.common.utils.Constant.SUPER_AGENT_ADMIN) {
            SysUserEntity sysUserEntity = sysUserService.getById(getUserId());
            TPfAgentUserEntity agentUserEntity = tPfAgentUserService.getById(sysUserEntity.getAgentId());
            if (agentUserEntity != null && user.getMoney() != null) {
                // 余额 减掉 产品金额
                check = agentUserEntity.getEcharge() - user.getMoney();

            } else {
                check = -1;
            }
        }
        return check;
    }

    /**
     * 修改校验余额是否足够
     */
    public R updateCheck(@RequestBody SysUserEntity user) {
        int check = 0;
        if (getUserId() != io.renren.common.utils.Constant.SUPER_AGENT_ADMIN) {
            SysUserEntity sysUserEntity = sysUserService.getById(getUserId());
            TPfAgentUserEntity agentUserEntity = tPfAgentUserService.getById(sysUserEntity.getAgentId());
            if (agentUserEntity != null && user.getMoney() != null) {
                // 余额 减掉 产品金额
                check = agentUserEntity.getEcharge() - user.getMoney();

            } else {
                check = -1;
            }
        }
        return R.ok().put("check", check);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("penfen:agentuser:save")
    public R save(@RequestBody SysUserEntity user) {
        int check = saveCheck(user);
        if (check >= 0) {
            user.setPassword(user.getUsername());
            // ValidatorUtils.validateEntity(user, AddGroup.class);
            user.setCreateUserId(getUserId());
            TPfAgentUserEntity tPfAgentUserEntity = user.getPfAgentUser();
            tPfAgentUserEntity.setCreateAgentId(getUserId());
            user.setPfAgentUser(tPfAgentUserEntity);
            tPfAgentUserService.saveUser(user);
        }
        return R.ok().put("check", check);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("penfen:agentuser:update")
    public R update(@RequestBody SysUserEntity user) {
        //   ValidatorUtils.validateEntity(user, UpdateGroup.class);
        user.setCreateUserId(getUserId());
        TPfAgentUserEntity tPfAgentUserEntity = user.getPfAgentUser();
        tPfAgentUserEntity.setCreateAgentId(getUserId());
        user.setPfAgentUser(tPfAgentUserEntity);
        tPfAgentUserService.agentUpdate(user);
        return R.ok().put("check", 1);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("penfen:agentuser:delete")
    public R delete(@RequestBody Integer[] ids) {
        tPfAgentUserService.removeByIds(Arrays.asList(ids));
        HashMap map = new HashMap();
        for (int i = 0; i < ids.length; i++) {
            map.put("agent_id", ids[i]);
            sysUserService.removeByMap(map);
        }
        return R.ok();
    }

    /**
     * 代理用户角色列表
     */
    @GetMapping("/agentSelect/{grade}")
    public R agentSelect(@PathVariable("grade") Long grade) {
        Map<String, Object> map = new HashMap<>();
        map.put("role_id", grade);
        List<SysRoleEntity> list = (List<SysRoleEntity>) sysRoleService.listByMap(map);
        return R.ok().put("list", list);
    }

}
