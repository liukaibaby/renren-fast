package io.renren.modules.penfen.service.impl;

import io.renren.modules.penfen.dao.TPfAgentUserDao;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.penfen.entity.TPfRechargeLogEntity;
import io.renren.modules.penfen.entity.TPfScheme;
import io.renren.modules.penfen.overall.Constant;
import io.renren.modules.penfen.service.TPfAgentUserService;
import io.renren.modules.penfen.service.TPfProductService;
import io.renren.modules.penfen.service.TPfRechargeLogService;
import io.renren.modules.penfen.service.TPfSchemeService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;


@Service("tPfAgentUserService")
public class TPfAgentUserServiceImpl extends ServiceImpl<TPfAgentUserDao, TPfAgentUserEntity> implements TPfAgentUserService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private TPfProductService productService;

    @Autowired
    private TPfRechargeLogService tPfRechargeLogService;

    @Autowired
    private TPfSchemeService tPfSchemeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        params = PageLogic(params);
        List<SysUserEntity> list = baseMapper.queryAgentPerms(params); //查询记录数
        int total = baseMapper.total(params);//查询总页数
        PageUtils pageUtils = new PageUtils(list, total, list.size(), 0);
        return pageUtils;

    }


    @Override
    public void saveUser(SysUserEntity user) {
        //sha256加密
        user.setCreateTime(new Date());
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        //保存代理商表
        this.save(user.getPfAgentUser());

        user.setAgentId(user.getPfAgentUser().getId());
        //保存用户表
        sysUserService.save(user);
        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
        //保存代理商与产品关系
        productService.insetProduct(user.getPfAgentUser());
        // 如果是管理员 不执行操作
        if (user.getCreateUserId() != io.renren.common.utils.Constant.SUPER_AGENT_ADMIN) {
            // 添加成功扣除金额
            agentMoneyDeduction(user);
        }


    }

    @Override
    public SysUserEntity queryOrUpdate(Long userId) {
        return baseMapper.queryOrUpdate(userId);
    }

    @Override
    public SysUserEntity initGetUser(Long userId) {
        return baseMapper.initGetUser(userId);
    }


    @Override
    public void agentUpdate(SysUserEntity user) {
        //sha256加密
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        //修改代理商表
        this.updateById(user.getPfAgentUser());
        //修改用户表
        sysUserService.updateById(user);
        //修改之前删除之前数据
        productService.updateDelProduct(user.getPfAgentUser().getId());
        //保存代理商与产品关系
        productService.insetProduct(user.getPfAgentUser());

        // 如果是管理员 不执行操作
//        if (user.getCreateUserId() != io.renren.common.utils.Constant.SUPER_AGENT_ADMIN) {
//            // 添加成功扣除金额
//            agentMoneyDeduction(user);
//        }

    }


    public void userRecharge(TPfAgentUserEntity agentUserEntity, SysUserEntity sysUserEntity) {
        TPfAgentUserEntity agentUserEntity1 = this.getById(agentUserEntity.getId());
        agentUserEntity1.setEcharge(agentUserEntity1.getEcharge() + agentUserEntity.getEcharge());
        this.updateById(agentUserEntity1);
        // 如果上级ID不为null 表示 存在上级ID则扣除上级ID金额
        if (agentUserEntity.getUpAgentId() != null) {
            TPfAgentUserEntity agentUserEntity2 = this.getById(agentUserEntity.getUpAgentId());
            agentUserEntity2.setEcharge(agentUserEntity2.getEcharge() - agentUserEntity.getEcharge());
            this.updateById(agentUserEntity2);
        }
        // 插入产品
        productService.insetProduct(agentUserEntity);
        TPfRechargeLogEntity rechargeLogEntity = new TPfRechargeLogEntity();
        rechargeLogEntity.setCreateTime(new Date());
        BeanUtils.copyProperties(agentUserEntity, rechargeLogEntity);
        rechargeLogEntity.setUserId(sysUserEntity.getUserId());
        rechargeLogEntity.setCurrentId(agentUserEntity.getId());
        rechargeLogEntity.setUserName(sysUserEntity.getUsername());
        rechargeLogEntity.setAgentId(sysUserEntity.getAgentId());
        rechargeLogEntity.setExplai(agentUserEntity.getEcharge() < 0 ? Constant.REFUND : Constant.RECHARGE);
        if (agentUserEntity.getProduct() != null && agentUserEntity.getProduct().size() > 0) {
            rechargeLogEntity.setOpenProduct(agentUserEntity.getProduct().get(0));
        }
        // 充值成功插入日志
        tPfRechargeLogService.rechargeInsertLog(rechargeLogEntity);

    }

    @Override
    public void agentDelete(Long userId) {
        SysUserEntity sysUserEntity = sysUserService.getById(userId);
        sysUserService.removeById(userId);
        this.removeById(sysUserEntity.getAgentId());
    }

    /**
     * 分页方法
     *
     * @param params
     * @return
     */
    public Map<String, Object> PageLogic(Map<String, Object> params) {
        String page = String.valueOf(params.get("page"));//页数
        String limit = String.valueOf(params.get("limit"));//数量
        int sum = (Integer.parseInt(page) - 1) * Integer.parseInt(limit);
        int sum1 = Integer.parseInt(limit);
        params.put("page", sum);
        params.put("limit", sum1);
        return params;
    }


    /**
     * 扣除 代理商开通的 产品金额
     *
     * @param
     * @return
     */
    public void agentMoneyDeduction(SysUserEntity user) {
        // 获取当前登录用户
        SysUserEntity sysUserEntity = sysUserService.getById(user.getCreateUserId());
        // 获取当前登录用户的代理商用户
        TPfAgentUserEntity tPfAgentUserEntity = this.getById(sysUserEntity.getAgentId());
        if (tPfAgentUserEntity != null) {
            // 获取 开通产品的价格
            TPfScheme tPfScheme = tPfSchemeService.getById(user.getSchemeId());
            if (tPfScheme != null && tPfAgentUserEntity.getEcharge() != null) {
                // 登录代理商用户 余额 减去 产品价格
                tPfAgentUserEntity.setEcharge(tPfAgentUserEntity.getEcharge() - tPfScheme.getMoney());
                // 保存 代理商用户
                this.updateById(tPfAgentUserEntity);
            }
        }
    }
}