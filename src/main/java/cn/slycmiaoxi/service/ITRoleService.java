package cn.slycmiaoxi.service;

import java.util.Map;
import java.util.Set;

import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;
import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.entity.TRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public interface ITRoleService extends IService<TRole> {
    
    /**
     * 查询指定用户下所有角色
     * 
     * @param nickName 用户名
     * @return 所有角色集合
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    Set<TRole> findRolesByNickName(String nickName);

    /**
     * 获得记录总数
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    long queryTotal();

    /**
     * 获得分页数据
     * @param page 分页对象
     * @param map 存放参数
     * @return Pagemodal对象
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    PageModal queryListbyPage(Page page, Map<String, Object> map);
}
