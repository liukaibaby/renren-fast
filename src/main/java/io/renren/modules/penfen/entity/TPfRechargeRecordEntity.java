package io.renren.modules.penfen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 18:12:08
 */
@Data
@TableName("t_pf_recharge_log")
public class TPfRechargeRecordEntity {

    /**
     * 充值
     */
    private Integer recharge = 0;
    /**
     * 退款
     */
    private Integer refund = 0;

    /**
     * 时间
     */
    private String currentDate;

}
