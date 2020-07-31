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
 * @date 2020-06-23 11:21:29
 */
@Data
@TableName("t_pf_agent_grade")
public class TPfAgentGradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long agentId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private Long roleId;

}
