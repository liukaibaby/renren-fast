package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
public interface TPfAgentUserService extends IService<TPfAgentUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SysUserEntity queryOrUpdate(Long userId);


    /**
     * 保存用户
     */
    @Transactional
    void saveUser(SysUserEntity user);

    /**
     * 修改用户
     */
    @Transactional
    void agentUpdate(SysUserEntity user);

    /**
     * 用户充值
     */
    @Transactional
    void userRecharge(TPfAgentUserEntity agentUserEntity, SysUserEntity sysUserEntity);

    /**
     * 删除用户
     */
    void agentDelete(Long userId);

    /**
     * 首页获取用户
     */
    SysUserEntity initGetUser(Long userId);

}

