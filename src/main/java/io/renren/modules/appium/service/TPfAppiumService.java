package io.renren.modules.appium.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.appium.entity.TPfAppiumEntity;

import java.util.Map;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-02 17:19:10
 */
public interface TPfAppiumService extends IService<TPfAppiumEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void clickOnComments(TPfAppiumEntity tPfAppiumEntity);

    void reboot(TPfAppiumEntity tPfAppiumEntity);


}

