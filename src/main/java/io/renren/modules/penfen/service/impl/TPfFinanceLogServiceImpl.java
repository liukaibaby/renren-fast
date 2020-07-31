

package io.renren.modules.penfen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.penfen.dao.TPfFinanceLogDao;
import io.renren.modules.penfen.entity.TPfRechargeLogEntity;
import io.renren.modules.penfen.service.TPfFinanceLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 用户充值记录
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("TPfFinanceLogService")
public class TPfFinanceLogServiceImpl extends ServiceImpl<TPfFinanceLogDao, TPfRechargeLogEntity> implements TPfFinanceLogService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String epName = (String) params.get("epName");
        Long createUserId = (Long) params.get("user_id");
        IPage<TPfRechargeLogEntity> page = this.page(
                new Query<TPfRechargeLogEntity>().getPage(params),
                new QueryWrapper<TPfRechargeLogEntity>()
                        .like(StringUtils.isNotBlank(epName), "ep_name", epName)
                        .eq(createUserId != null, "user_id", createUserId)
        );
        return new PageUtils(page);
    }

    @Override
    public void rechargeInsertLog(TPfRechargeLogEntity rechargeLogEntity) {
        this.save(rechargeLogEntity);
    }
}