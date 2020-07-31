package io.renren.modules.penfen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.penfen.entity.TPfAgentUserEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-22 15:25:37
 */
@Mapper
public interface TPfAgentUserDao extends BaseMapper<TPfAgentUserEntity> {

    /**
     * 查询代理商用户
     */
    List<SysUserEntity> queryAgentPerms(Map<String, Object> map);

    /**
     * 查询总记录数
     */
    int total(Map<String, Object> map);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);


    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);

    /**
     * 代理商根据id查询
     */
    SysUserEntity queryOrUpdate(Long userId);

    /**
     * 代理商根据id查询
     */
    SysUserEntity initGetUser(Long userId);


	
}
