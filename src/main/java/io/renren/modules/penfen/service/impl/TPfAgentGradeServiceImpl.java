package io.renren.modules.penfen.service.impl;

import io.renren.modules.penfen.dao.TPfAgentGradeDao;
import io.renren.modules.penfen.entity.TPfAgentGradeEntity;
import io.renren.modules.penfen.service.TPfAgentGradeService;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("tPfAgentGradeService")
public class TPfAgentGradeServiceImpl extends ServiceImpl<TPfAgentGradeDao, TPfAgentGradeEntity> implements TPfAgentGradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TPfAgentGradeEntity> page = this.page(
                new Query<TPfAgentGradeEntity>().getPage(params),
                new QueryWrapper<TPfAgentGradeEntity>()
        );

        return new PageUtils(page);
    }

}