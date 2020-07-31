package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.penfen.entity.TPfRechargeRecordEntity;
import io.renren.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
public interface TPfHomeService extends IService<TPfAgentUserEntity> {


    /**
     * 首页获取用户
     */
    SysUserEntity initGetUser(Long userId);

    Map<String, List> initChartLine(Long userId);


}

