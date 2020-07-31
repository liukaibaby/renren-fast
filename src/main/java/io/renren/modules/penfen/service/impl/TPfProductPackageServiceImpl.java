package io.renren.modules.penfen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.utils.Query;
import io.renren.modules.penfen.dao.TPfProductPackageDao;
import io.renren.modules.penfen.entity.TPfProductPackageEntity;
import io.renren.modules.penfen.service.TPfProductPackageService;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;




@Service("tPfProductPackageService")
public class TPfProductPackageServiceImpl extends ServiceImpl<TPfProductPackageDao, TPfProductPackageEntity> implements TPfProductPackageService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TPfProductPackageEntity> page = this.page(
                new Query<TPfProductPackageEntity>().getPage(params),
                new QueryWrapper<TPfProductPackageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void productInsert(TPfProductPackageEntity productPackageEntity) {

    }

    @Override
    public void productUpdate(TPfProductPackageEntity productPackageEntity) {

    }

    @Override
    public void delete(Long id) {

    }

}