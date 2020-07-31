package io.renren.modules.penfen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.penfen.entity.TPfRechargeRecordEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@Mapper
public interface TPfHomeUserDao extends BaseMapper<TPfAgentUserEntity> {


    /**
     * 代理商根据id查询
     */
    SysUserEntity initGetUser(Long userId);

    List<TPfRechargeRecordEntity> initChartLine(Long userId);



}
