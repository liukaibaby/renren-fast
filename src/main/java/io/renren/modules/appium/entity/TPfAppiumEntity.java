package io.renren.modules.appium.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-02 17:19:10
 */
@Data
@TableName("t_pf_appium")
public class TPfAppiumEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 设备号
     */
    private String number;
    /**
     * 设备名称
     */
    private String ementName;
    /**
     * 设备型号
     */
    private String ementModel;
    /**
     * 手机名称
     */
    private String name;
    /**
     * 设备号
     */
    private String editionNumber;
    /**
     * 设备连接状态
     */
    private String editionStatus;

    /**
     * 运输设备号
     */
    private String transportId;

    /**
     * 用户
     */
    @TableField(exist = false)
    private String user;

    /**
     * 评论内容
     */
    @TableField(exist = false)
    private String comments;
}
