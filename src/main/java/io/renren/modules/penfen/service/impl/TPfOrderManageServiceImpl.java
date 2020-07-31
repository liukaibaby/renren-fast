package io.renren.modules.penfen.service.impl;

import io.renren.modules.penfen.dao.TPfOrderManageDao;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.penfen.entity.TPfOrderManageEntity;
import io.renren.modules.penfen.service.TPfAgentUserService;
import io.renren.modules.penfen.service.TPfOrderManageService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("tPfOrderManageService")
public class TPfOrderManageServiceImpl extends ServiceImpl<TPfOrderManageDao, TPfOrderManageEntity> implements TPfOrderManageService {

    @Autowired
    private TPfAgentUserService tPfAgentUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long createUserId = (Long) params.get("createUserId");
        IPage<TPfOrderManageEntity> page = this.page(
                new Query<TPfOrderManageEntity>().getPage(params),
                new QueryWrapper<TPfOrderManageEntity>()
                        .eq(createUserId != null, "create_user_id", createUserId)
        );

        return new PageUtils(page);
    }

    @Override
    public boolean add(TPfOrderManageEntity tPfOrderManage, SysUserEntity User) {
        // 保存前先查询当前用户数据库中 余额是否足够
        TPfAgentUserEntity tPfAgentUserEntity = tPfAgentUserService.getById(User.getAgentId());
        if (tPfAgentUserEntity != null) {
            if (tPfAgentUserEntity.getEcharge() >= tPfOrderManage.getMoney()) {
                tPfAgentUserEntity.setEcharge(tPfAgentUserEntity.getEcharge() - tPfOrderManage.getMoney());
                tPfAgentUserService.updateById(tPfAgentUserEntity);
                this.save(tPfOrderManage);
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

}