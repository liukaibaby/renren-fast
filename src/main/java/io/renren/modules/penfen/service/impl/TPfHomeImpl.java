package io.renren.modules.penfen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.penfen.dao.TPfHomeUserDao;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.penfen.entity.TPfRechargeRecordEntity;
import io.renren.modules.penfen.overall.DateUtils;
import io.renren.modules.penfen.service.TPfHomeService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("tPfHomeService")
public class TPfHomeImpl extends ServiceImpl<TPfHomeUserDao, TPfAgentUserEntity> implements TPfHomeService {


    @Override
    public SysUserEntity initGetUser(Long userId) {
        return baseMapper.initGetUser(userId);
    }

    @Override
    public Map<String, List> initChartLine(Long userId) {
        List<TPfRechargeRecordEntity> tPfRechargeRecordEntities = baseMapper.initChartLine(userId);
        // 获取当前年
        String currentDate = DateUtils.getCurrentDate("yyyy-MM");
        String firstDayOfCurrentMonth = DateUtils.firstDayOfCurrentMonth();
        List<TPfRechargeRecordEntity> listMap = new ArrayList<>();
        if (!tPfRechargeRecordEntities.isEmpty()) {
            for (int i = 0; i <= 11; i++) {
                TPfRechargeRecordEntity tPfRechargeRecord = new TPfRechargeRecordEntity();
                tPfRechargeRecord.setCurrentDate(firstDayOfCurrentMonth);
                firstDayOfCurrentMonth = DateUtils.monthAddFrist(firstDayOfCurrentMonth);
                for (TPfRechargeRecordEntity tPfRechargeRecordEntity : tPfRechargeRecordEntities) {
                    if (firstDayOfCurrentMonth.equals(tPfRechargeRecordEntity.getCurrentDate())) {
                        tPfRechargeRecord.setRecharge(tPfRechargeRecordEntity.getRecharge());
                        tPfRechargeRecord.setRefund(tPfRechargeRecordEntity.getRefund());
                    }
                }
                listMap.add(tPfRechargeRecord);
            }
        }
        Map<String, List> reslt = dataProcessing(listMap);
        return reslt;
    }

    public Map<String, List> dataProcessing(List<TPfRechargeRecordEntity> list) {
        Map map = new HashMap();
        List recharge = new ArrayList();
        List refund = new ArrayList();
        for (TPfRechargeRecordEntity tPfRechargeRecordEntity : list) {
            recharge.add(tPfRechargeRecordEntity.getRecharge());
            refund.add(tPfRechargeRecordEntity.getRefund());
        }
        map.put("recharge", recharge);
        map.put("refund", refund);
        return map;
    }


}