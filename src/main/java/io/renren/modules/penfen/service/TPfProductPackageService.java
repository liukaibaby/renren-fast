package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.penfen.entity.TPfProductPackageEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
public interface TPfProductPackageService extends IService<TPfProductPackageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void productInsert(TPfProductPackageEntity productPackageEntity);


    void productUpdate(TPfProductPackageEntity productPackageEntity);

    void delete(Long id);
}

