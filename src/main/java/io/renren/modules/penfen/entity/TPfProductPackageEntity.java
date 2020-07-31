package io.renren.modules.penfen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 18:12:08
 */
@Data
@TableName("t_pf_product_package")
public class TPfProductPackageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主ID
	 */
	@TableId
	private Long id;
	/**
	 * 套餐名称
	 */
	private String name;
	/**
	 * 套餐金额
	 */
	private Integer money;
	/**
	 * 套餐类型 //1 标准套餐 2.自定义套餐
	 */
	private Integer type;
	/**
	 * 产品id
	 */
	private Long productId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 服务详情
	 */
	@TableField(exist = false)
	private List<TPfProductDetailsEntity> productDetailsEntityList = new ArrayList<>();

}
