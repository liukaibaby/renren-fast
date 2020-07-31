package io.renren.modules.penfen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 18:12:08
 */
@Data
@TableName("t_pf_agent_product")
public class TPfAgentProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 代理商ID
     */
    private Long agentId;
    /**
     * 产品ID
     */
    private Long productId;

}
