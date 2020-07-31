package io.renren.modules.penfen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 18:12:08
 */
@Data
@TableName("t_pf_agent_user")
public class TPfAgentUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
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
     * QQ号码
     */
    private String qqNumber;
    /**
     * 代理等级0:管理员8:一级代理9:二级代理10:普通用户
     */
    private String agentGrade;
    /**
     * 用来记录代理用户下的子代理用户
     */
    private Long createAgentId;
    /**
     * 可用余额
     */
    private Integer echarge = 0;
    /**
     * 总资金
     */
    private Integer allAmount;
    /**
     * 退款金额
     */
    private Integer refund;
    /**
     * 开通容量
     */
    private Integer capacity;

    /**
     * 视频是否发布权限 0 禁用 1 正常
     */
    private Integer videoRelease;

    /**
     * 记录上级代理
     */
    @TableField(exist = false)
    private Long upAgentId;

    /**
     * 上级金额
     */
    @TableField(exist = false)
    private int upEcharge;

    /**
     * 开通产品
     */
    @TableField(exist = false)
    private List<Integer> product;

    /**
     * 开通产品名
     */
    @TableField(exist = false)
    private String productName;

}
