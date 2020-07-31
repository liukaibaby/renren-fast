package io.renren.modules.penfen.service.impl;

import io.renren.modules.penfen.dao.TPfAgentProductDao;
import io.renren.modules.penfen.entity.TPfAgentProductEntity;
import io.renren.modules.penfen.service.TPfAgentProductService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("tPfAgentProductService")
public class TPfAgentProductServiceImpl extends ServiceImpl<TPfAgentProductDao, TPfAgentProductEntity> implements TPfAgentProductService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TPfAgentProductEntity> page = this.page(
                new Query<TPfAgentProductEntity>().getPage(params),
                new QueryWrapper<TPfAgentProductEntity>()
        );

        return new PageUtils(page);
    }






}