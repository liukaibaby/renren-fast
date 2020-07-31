package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.penfen.entity.TPfRechargeLogEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
public interface TPfRechargeLogService extends IService<TPfRechargeLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    @Transactional
    void rechargeInsertLog(TPfRechargeLogEntity rechargeLogEntity);
}

