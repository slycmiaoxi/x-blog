package cn.slycmiaoxi.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.dto.UserDto;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;

/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public interface ITUserService extends IService<TUser> {
    
    /**
     * 根据用户名查询用户所有信息
     * 
     * @param nickName 用户名
     * @return 用户所有信息
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    TUser getUserByName(String nickName);
    
    /**
     * 根据用户名和密码查询用户所有信息
     *
     * @param nickName 用户名
     * @param pwd 密码
     * @return 用户所有信息
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    TUser getUserByNameAndPwd(String nickName, String pwd);
    
    /**
     * 注册用户
     *
     * @param dto 用户dto
     * @return 添加结果 true成功 false失败
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    boolean add(UserDto dto);
    
    /**
     * 拉黑用户
     * 
     * @param user 用户实体
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    void updateInfoById(TUser user);
    
    /**
     * 获得记录总数
     * 
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    long queryTotal();
    
    /**
     * 获得分页数据
     * 
     * @param page 分页对象
     * @param map 存放参数
     * @return Pagemodal对象
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    PageModal queryListbyPage(Page page, Map<String, Object> map);
}
