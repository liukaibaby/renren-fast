package io.renren.modules.penfen.service.impl;

import io.renren.modules.penfen.dao.TPfProductDetailsDao;
import io.renren.modules.penfen.entity.TPfProductDetailsEntity;
import io.renren.modules.penfen.service.TPfProductDetailsService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("tPfProductDetailsService")
public class TPfProductDetailsServiceImpl extends ServiceImpl<TPfProductDetailsDao, TPfProductDetailsEntity> implements TPfProductDetailsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TPfProductDetailsEntity> page = this.page(
                new Query<TPfProductDetailsEntity>().getPage(params),
                new QueryWrapper<TPfProductDetailsEntity>()
        );

        return new PageUtils(page);
    }

}