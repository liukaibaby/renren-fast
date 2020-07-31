package io.renren.modules.penfen.service.impl;

import io.renren.modules.penfen.dao.TPfRechargeLogDao;
import io.renren.modules.penfen.entity.TPfRechargeLogEntity;
import io.renren.modules.penfen.service.TPfRechargeLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("tPfRechargeLogService")
public class TPfRechargeLogServiceImpl extends ServiceImpl<TPfRechargeLogDao, TPfRechargeLogEntity> implements TPfRechargeLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long createUserId = (Long) params.get("user_id");
        IPage<TPfRechargeLogEntity> page = this.page(
                new Query<TPfRechargeLogEntity>().getPage(params),
                new QueryWrapper<TPfRechargeLogEntity>()
                        .eq(createUserId != null, "user_id", createUserId)
        );

        return new PageUtils(page);
    }


    @Override
    public void rechargeInsertLog(TPfRechargeLogEntity rechargeLogEntity) {
        this.save(rechargeLogEntity);
    }


}