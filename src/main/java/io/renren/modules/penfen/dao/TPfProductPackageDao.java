package io.renren.modules.penfen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.penfen.entity.TPfProductPackageEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@Mapper
public interface TPfProductPackageDao extends BaseMapper<TPfProductPackageEntity> {

    TPfProductPackageEntity  productPackageinfo();

	
}
