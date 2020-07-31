package io.renren.modules.penfen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.penfen.entity.TPfProductDetailsEntity;
import io.renren.modules.penfen.entity.TPfProductEntity;
import io.renren.modules.penfen.entity.TPfProductPackageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@Mapper
public interface TPfProductDao extends BaseMapper<TPfProductEntity> {

    /**
     * 根据选中产品获取套餐
     */
    List<TPfProductPackageEntity> getProductPackage(Long pId);

    /**
     * 根据选中套餐获取服务详细
     */
    List<TPfProductDetailsEntity> getPackageDetails(Long pId);

    /**
     * 获取产品套餐
     */
    List<TPfProductEntity> getProduct(Long Id);

}
