package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.penfen.entity.TPfOrderManageEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-23 17:09:40
 */
public interface TPfOrderManageService extends IService<TPfOrderManageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    @Transactional
    boolean add(TPfOrderManageEntity tPfOrderManage, SysUserEntity User);
}

