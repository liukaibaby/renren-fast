package io.renren.modules.penfen.service.impl;

import io.renren.modules.penfen.dao.TPfProductDao;
import io.renren.modules.penfen.entity.*;
import io.renren.modules.penfen.service.TPfAgentProductService;
import io.renren.modules.penfen.service.TPfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;


@Service("tPfProductService")
public class TPfProductServiceImpl extends ServiceImpl<TPfProductDao, TPfProductEntity> implements TPfProductService {


    @Autowired
    private TPfAgentProductService agentProductService;

    @Override
    public void insetProduct(TPfAgentUserEntity agentUserEntity) {
        // 插入产品方法
        if (agentUserEntity!=null && agentUserEntity.getProduct()!=null) {
            for (Integer pId : agentUserEntity.getProduct()) {
                TPfAgentProductEntity agentProductEntity = new TPfAgentProductEntity();
                agentProductEntity.setAgentId(agentUserEntity.getId());
                agentProductEntity.setProductId(Long.parseLong(String.valueOf(pId)));
                agentProductService.save(agentProductEntity);
            }
        }
    }

    @Override
    public void updateDelProduct(Long id) {
        HashMap map = new HashMap();
        map.put("agent_id", id);
        agentProductService.removeByMap(map);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<TPfProductEntity> queryAll() {
        List<TPfProductEntity> list = this.list();
        return list;
    }

    @Override
    public List<TPfProductPackageEntity> getProductPackage(Long id) {
        List<TPfProductPackageEntity> productPackage = baseMapper.getProductPackage(id);
        return productPackage;
    }

    @Override
    public List<TPfProductDetailsEntity> getPackageDetails(Long id) {
        List<TPfProductDetailsEntity> productPackage = baseMapper.getPackageDetails(id);
        return productPackage;
    }

    @Override
    public List<TPfProductEntity> getProduct(Long id) {
        Map map = new HashMap();
        map.put("agent_id", id);
        List<TPfProductEntity> tPfProductEntities = baseMapper.getProduct(id);
        return tPfProductEntities;
    }

}