package cn.slycmiaoxi.service;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.entity.TEmailCode;

/**
 * <p>
 * 验证码服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public interface ITEmailCodeService extends IService<TEmailCode> {
    
    /**
     * 根据邮箱地址获得实体
     * 
     * @param eMail 邮箱地址
     * @return po
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    TEmailCode getOneByEmail(String eMail);
    
    /**
     * 添加
     * 
     * @param eMail 邮箱地址
     * @param code 验证码
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    void add(String eMail, String code);
    
    /**
     * 验证该邮箱是否有用户正在使用（true-注册 false-未被使用)
     * 
     * @param eMail 邮箱地址
     * @return 校验结果（true-注册 false-未被使用)
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    boolean checkEmail(String eMail);
}
