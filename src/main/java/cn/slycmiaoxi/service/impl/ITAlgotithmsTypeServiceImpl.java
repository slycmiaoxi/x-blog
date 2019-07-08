package cn.slycmiaoxi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TAlgotithmsTypeMapper;
import cn.slycmiaoxi.dto.CommonDto;
import cn.slycmiaoxi.entity.TAlgotithmsType;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.service.ITAlgotithmsTypeService;
import cn.slycmiaoxi.utils.CodecUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Service
public class ITAlgotithmsTypeServiceImpl extends ServiceImpl<TAlgotithmsTypeMapper, TAlgotithmsType>
    implements ITAlgotithmsTypeService {
    @Autowired
    private TAlgotithmsTypeMapper algotithmsTypeMapper;
    
    @Override
    public void add(String algotithmsTypeName) {
        TAlgotithmsType algotithmsType = generatorPojo(algotithmsTypeName);
        algotithmsTypeMapper.insert(algotithmsType);
    }
    
    @Override
    public boolean checkRepeat(String algotithmsTypeName) {
        if (StringUtils.isEmpty(algotithmsTypeName)) {
            return false;
        }
        
        EntityWrapper<TAlgotithmsType> wrapper = new EntityWrapper<>();
        wrapper.eq("algotithms_type_name", algotithmsTypeName).eq("delete_flag", Enable.YES.getValue());
        int count = algotithmsTypeMapper.selectCount(wrapper);
        return count > 0 ? true : false;
    }
    
    @Override
    public List<CommonDto> listAlgoTypeDtos() {
        List<CommonDto> dtos = new ArrayList<>();
        EntityWrapper<TAlgotithmsType> wrapper = new EntityWrapper<>();
        wrapper.eq("delete_flag", Enable.YES.getValue());
        
        // 1.查询所有有效的记录
        List<TAlgotithmsType> list = algotithmsTypeMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            return dtos;
        }
        
        // 2.返回数据处理
        list.stream().filter(x -> StringUtils.isNotEmpty(x.getAlgotithmsTypeName())).forEach(po -> {
            String decode = CodecUtils.encodeData(String.valueOf(po.getAlgotithmsTypeId()));
            dtos.add(new CommonDto(decode, po.getAlgotithmsTypeName()));
        });
        return dtos;
    }
    
    @Override
    public String getTypeNameById(Integer typeId) {
        TAlgotithmsType algotithmsType = algotithmsTypeMapper.selectById(typeId);
        if (null == algotithmsType || algotithmsType.getDeleteFlag() == Enable.NO.getValue()) {
            return null;
        }
        return algotithmsType.getAlgotithmsTypeName();
    }
    
    @Override
    public List<TAlgotithmsType> listPojo() {
        EntityWrapper<TAlgotithmsType> wrapper = new EntityWrapper<>();
        wrapper.eq("delete_flag", Enable.YES.getValue());
        List<TAlgotithmsType> list = algotithmsTypeMapper.selectList(wrapper);
        return list;
    }
    
    /**
     * 生成po实体
     * 
     * @param algotithmsTypeName 算法类型名称
     * @return po实体
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    private TAlgotithmsType generatorPojo(String algotithmsTypeName) {
        TAlgotithmsType algotithmsType = new TAlgotithmsType();
        algotithmsType.setAlgotithmsTypeName(algotithmsTypeName);
        algotithmsType.setAddTime(new Date());
        algotithmsType.setAddUserid(1);
        algotithmsType.setDeleteFlag(Enable.YES.getValue());
        return algotithmsType;
    }
}
