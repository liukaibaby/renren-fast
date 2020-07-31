package io.renren.modules.penfen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 18:12:08
 */
@Data
@TableName("t_pf_recharge_log")
public class TPfRechargeLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主ID
     */
    @TableId
    private Long id;
    /**
     * 当前充值用户ID
     */
    private Long currentId;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 公司名称
     */
    private String epName;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 开通产品
     */
    private Integer openProduct;
    /**
     * 充值金额
     */
    private Integer echarge;
    /**
     * 退款金额
     */
    private Integer refund;
    /**
     * 时间
     */
    private Date createTime;
    /**
     * 充值用户代理id
     */
    private Long agentId;
    /**
     * 操作用户ID
     */
    private Long userId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 操作说明
     */
    private String explai;

}
