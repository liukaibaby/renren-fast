package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.penfen.entity.TPfProductDetailsEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
public interface TPfProductDetailsService extends IService<TPfProductDetailsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

