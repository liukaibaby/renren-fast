package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.penfen.entity.TPfRechargeLogEntity;

import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface TPfFinanceLogService extends IService<TPfRechargeLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void rechargeInsertLog(TPfRechargeLogEntity rechargeLogEntity);
}
