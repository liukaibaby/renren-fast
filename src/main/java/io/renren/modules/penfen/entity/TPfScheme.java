package io.renren.modules.penfen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : liukai
 * @date : 2020-07-11 17:44
 **/
@Data
@TableName("t_pf_scheme")
public class TPfScheme {
    /**
     * ID
     */
    @TableField
    private Long id;
    /**
     * 名称
     */
    private String name;

    /**
     * 套餐所能绑定的账号数量
     */
    private Integer accountNum;

    /**
     * 套餐描述
     */
    private String schemeDescription;

    /**
     * 金额
     */
    private int money;
}
