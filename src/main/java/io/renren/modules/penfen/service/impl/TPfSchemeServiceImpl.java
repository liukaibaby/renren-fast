package io.renren.modules.penfen.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.penfen.dao.TPfSchemeDao;
import io.renren.modules.penfen.entity.TPfScheme;
import io.renren.modules.penfen.service.TPfSchemeService;
import org.springframework.stereotype.Service;


@Service("TPfSchemeService")
public class TPfSchemeServiceImpl extends ServiceImpl<TPfSchemeDao, TPfScheme> implements TPfSchemeService {


}