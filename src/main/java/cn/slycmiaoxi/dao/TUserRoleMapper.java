package cn.slycmiaoxi.dao;

import cn.slycmiaoxi.entity.TUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public interface TUserRoleMapper extends BaseMapper<TUserRole> {

    /**
     * 更新用户角色关系
     * @param userId 用户id
     * @param roleId 角色id
     * @author slycmiaoxi
     * @since 2019-06-27
     */
    void updateByUserAndRoleId(Map<String, Object> map);
}
