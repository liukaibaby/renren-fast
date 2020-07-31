package io.renren.modules.penfen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-23 17:09:40
 */
@Data
@TableName("t_pf_order_manage")
public class TPfOrderManageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private String userName;
    /**
     *
     */
    private String packageName;
    /**
     *
     */
    private Integer money;
    /**
     *
     */
    private Date tradeTime;
    /**
     *
     */
    private Integer status;

    /**
     * createUserId
     */
    private Long createUserId;
}
