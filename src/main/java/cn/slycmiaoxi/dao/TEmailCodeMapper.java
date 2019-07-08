package cn.slycmiaoxi.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.slycmiaoxi.entity.TEmailCode;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public interface TEmailCodeMapper extends BaseMapper<TEmailCode> {
    
    /**
     * 获得已注册的用户信息
     * 
     * @param eMail 邮箱地址
     * @return po
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    TEmailCode getOneByUserEmail(String eMail);
}
