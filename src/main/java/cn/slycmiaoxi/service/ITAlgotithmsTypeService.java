package cn.slycmiaoxi.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.dto.CommonDto;
import cn.slycmiaoxi.entity.TAlgotithmsType;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public interface ITAlgotithmsTypeService extends IService<TAlgotithmsType> {
    
    /**
     * 添加算法类型
     * 
     * @param algotithmsTypeName 算法类型名称
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    void add(String algotithmsTypeName);
    
    /**
     * 校验重名
     * 
     * @param algotithmsTypeName 算法类型名称
     * @return 校验结果
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    boolean checkRepeat(String algotithmsTypeName);
    
    /**
     * 初始化算法类型dto(po -> dto)
     * 
     * @return CommonDto集合
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    List<CommonDto> listAlgoTypeDtos();
    
    /**
     * 通过类型主键获得类型名称
     * 
     * @param typeId 算法类型主键
     * @return 算法类型名称
     * @author slycmiaoxi
     * @since 2019-06-18
     */
    String getTypeNameById(Integer typeId);
    
    /**
     * 获得所有有效的po
     * 
     * @return po集合
     * @author slycmiaoxi
     * @since 2019-06-18
     */
    List<TAlgotithmsType> listPojo();
}
