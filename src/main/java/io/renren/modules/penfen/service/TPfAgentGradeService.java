package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.penfen.entity.TPfAgentGradeEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-23 11:21:29
 */
public interface TPfAgentGradeService extends IService<TPfAgentGradeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

