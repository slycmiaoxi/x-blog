package cn.slycmiaoxi.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.slycmiaoxi.dto.RoleDto;
import cn.slycmiaoxi.entity.TRole;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public interface TRoleMapper extends BaseMapper<TRole> {
    
    /**
     * 查询指定用户下所有角色
     * 
     * @param nickName 用户名
     * @return 所有角色集合
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    Set<TRole> findRolesByNickName(@RequestParam("nickName") String nickName);
    
    /**
     * 获得记录总数
     *
     * @param map 存放参数
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    List<RoleDto> queryList(Map<String, Object> map);
    
    /**
     * 获得分页数据
     *
     * @param map 存放参数
     * @return PageModal对象
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    Long queryTotal(Map<String, Object> map);
    
}
