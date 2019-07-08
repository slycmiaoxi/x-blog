package cn.slycmiaoxi.service;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.entity.TAlgotithmsTypeInfoRel;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public interface ITAlgotithmsTypeInfoRelService extends IService<TAlgotithmsTypeInfoRel> {
    
    /**
     * 添加算法建模—类型关系
     * 
     * @param algotithmsInfoId 算法建模主键
     * @param algotithmsTypeId 算法类型主键
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    void add(Integer algotithmsInfoId, Integer algotithmsTypeId);
}
