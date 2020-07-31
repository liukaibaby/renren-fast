package io.renren.modules.penfen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.penfen.entity.TPfProductDetailsEntity;
import io.renren.modules.penfen.entity.TPfProductEntity;
import io.renren.modules.penfen.entity.TPfProductPackageEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
public interface TPfProductService extends IService<TPfProductEntity> {

    /**
     * 充值成功插入产品
     *
     * @param agentUserEntity
     */
    @Transactional
    void insetProduct(TPfAgentUserEntity agentUserEntity);

    /**
     * 修改产品先删除之前产品
     *
     * @param id
     */
    @Transactional
    void updateDelProduct(Long id);

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取所有产品信息
     *
     * @return
     */
    List<TPfProductEntity> queryAll();

    /**
     * 根据选中产品获取套餐
     *
     * @param id
     * @return
     */
    List<TPfProductPackageEntity> getProductPackage(Long id);

    /**
     * 根据选中套餐获取详情
     */
    List<TPfProductDetailsEntity> getPackageDetails(Long id);


    /**
     * 获取当前用户开通产品
     */
    List<TPfProductEntity> getProduct(Long id);
}

