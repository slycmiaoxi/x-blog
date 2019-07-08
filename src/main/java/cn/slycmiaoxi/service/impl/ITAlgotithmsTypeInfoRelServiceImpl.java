package cn.slycmiaoxi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TAlgotithmsTypeInfoRelMapper;
import cn.slycmiaoxi.entity.TAlgotithmsTypeInfoRel;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.service.ITAlgotithmsTypeInfoRelService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Service
public class ITAlgotithmsTypeInfoRelServiceImpl extends
    ServiceImpl<TAlgotithmsTypeInfoRelMapper, TAlgotithmsTypeInfoRel> implements ITAlgotithmsTypeInfoRelService {
    @Autowired
    private TAlgotithmsTypeInfoRelMapper tAlgotithmsTypeInfoRelMapper;
    
    @Override
    public void add(Integer algotithmsInfoId, Integer algotithmsTypeId) {
        TAlgotithmsTypeInfoRel po = generatorPojo(algotithmsInfoId, algotithmsTypeId);
        tAlgotithmsTypeInfoRelMapper.insert(po);
    }
    
    /**
     * 生成po实体
     *
     * @param algotithmsInfoId 算法建模主键
     * @param algotithmsTypeId 算法类型主键
     * @return po实体
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    private TAlgotithmsTypeInfoRel generatorPojo(Integer algotithmsInfoId, Integer algotithmsTypeId) {
        TAlgotithmsTypeInfoRel po = new TAlgotithmsTypeInfoRel();
        po.setAlgotithmsInfoId(algotithmsInfoId);
        po.setAlgotithmsTypeId(algotithmsTypeId);
        po.setDeleteFlag(Enable.YES.getValue());
        return po;
    }
}
