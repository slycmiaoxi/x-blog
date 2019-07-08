package cn.slycmiaoxi.service;

import cn.slycmiaoxi.entity.TUserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public interface ITUserRoleService extends IService<TUserRole> {

    /**
     * 更新用户角色关系
     * @param userId 用户id
     * @param roleId 角色id
     * @author slycmiaoxi
     * @since 2019-06-27
     */
    void updateByUserAndRoleId(int userId, int roleId);
}
