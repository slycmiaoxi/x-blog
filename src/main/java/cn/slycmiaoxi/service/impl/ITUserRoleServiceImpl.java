package cn.slycmiaoxi.service.impl;

import cn.slycmiaoxi.entity.TUserRole;
import cn.slycmiaoxi.dao.TUserRoleMapper;
import cn.slycmiaoxi.service.ITUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
@Service
public class ITUserRoleServiceImpl extends ServiceImpl<TUserRoleMapper, TUserRole> implements ITUserRoleService {
    @Autowired
    private TUserRoleMapper tUserRoleMapper;


    @Override
    public void updateByUserAndRoleId(int userId, int roleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleId", roleId);
        tUserRoleMapper.updateByUserAndRoleId(map);
    }
}
