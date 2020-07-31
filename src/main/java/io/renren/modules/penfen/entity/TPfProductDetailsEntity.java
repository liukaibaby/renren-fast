package io.renren.modules.penfen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 18:12:08
 */
@Data
@TableName("t_pf_product_details")
public class TPfProductDetailsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 项目名称
	 */
	private String proName;
	/**
	 * 服务内容
	 */
	private String content;
	/**
	 * 价格
	 */
	private Integer price;
	/**
	 * 工作详情
	 */
	private String details;
	/**
	 * 套餐Id 一对多关系
	 */
	private Long pId;

}
