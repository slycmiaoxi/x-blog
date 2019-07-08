package cn.slycmiaoxi.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.dto.AlgotithmsInfoDto;
import cn.slycmiaoxi.entity.TAlgotithmsInfo;
import cn.slycmiaoxi.object.TBean;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public interface ITAlgotithmsInfoService extends IService<TAlgotithmsInfo> {
    
    /**
     * 添加算法建模
     * 
     * @param dto 算法建模dto
     * @return 添加成功或失败
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    boolean add(AlgotithmsInfoDto dto);
    
    /**
     * 获得前端算法建模dto数据（类型名-算法建模dto）
     * 
     * @return List<TBean<String>>
     * @author slycmiaoxi
     * @since 2019-06-18
     */
    List<TBean<String>> listGroupDto();
    
    /**
     * 通过算法建模唯一索引获得dto
     * 
     * @param funcIndex 唯一索引
     * @return dto
     * @author slycmiaoxi
     * @since 2019-06-19
     */
    AlgotithmsInfoDto getDtoByFuncIndex(Long funcIndex);
}
