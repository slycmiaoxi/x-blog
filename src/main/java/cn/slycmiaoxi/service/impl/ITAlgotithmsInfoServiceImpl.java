package cn.slycmiaoxi.service.impl;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TAlgotithmsInfoMapper;
import cn.slycmiaoxi.dto.AlgotithmsInfoDto;
import cn.slycmiaoxi.entity.TAlgotithmsInfo;
import cn.slycmiaoxi.entity.TAlgotithmsType;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.object.TBean;
import cn.slycmiaoxi.redis.JedisClient;
import cn.slycmiaoxi.service.ITAlgotithmsInfoService;
import cn.slycmiaoxi.service.ITAlgotithmsTypeInfoRelService;
import cn.slycmiaoxi.service.ITAlgotithmsTypeService;
import cn.slycmiaoxi.utils.CodecUtils;
import cn.slycmiaoxi.utils.IDUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Service
public class ITAlgotithmsInfoServiceImpl extends ServiceImpl<TAlgotithmsInfoMapper, TAlgotithmsInfo>
    implements ITAlgotithmsInfoService {
    @Autowired
    private TAlgotithmsInfoMapper tAlgotithmsInfoMapper;
    
    @Autowired
    private ITAlgotithmsTypeInfoRelService itAlgotithmsTypeInfoRelService;
    
    @Autowired
    private ITAlgotithmsTypeService itAlgotithmsTypeService;
    
    @Autowired
    private JedisClient jedisClient;
    
    @Value("${REDIS_ALGORITHMS_LIST_KEY}")
    private String REDIS_ALGORITHMS_LIST_KEY;
    
    @Override
    public boolean add(AlgotithmsInfoDto dto) {
        // 1.校验参数
        boolean checkParams = convert(dto);
        if (!checkParams) {
            return false;
        }
        
        // 2.dto -> po
        TAlgotithmsInfo po = getPoByDto(dto);
        
        // 3.入数据库
        tAlgotithmsInfoMapper.insert(po);
        itAlgotithmsTypeInfoRelService.add(po.getAlgotithmsInfoId(), po.getAlgotithmsTypeId());
        
        // 4.入redis(存dto)
        Integer algotithmsInfoId = po.getAlgotithmsInfoId();
        long funcIndex = po.getFuncIndex();
        dto.setAlgotithmsInfoId(CodecUtils.encodeData(String.valueOf(algotithmsInfoId)));
        dto.setFuncIndex(funcIndex);
        // 4.1 算法建模详情（集合数据结构)
        jedisClient.lpush(REDIS_ALGORITHMS_LIST_KEY, JSON.toJSONString(dto));
        
        return true;
    }
    
    @Override
    public List<TBean<String>> listGroupDto() {
        final List<TBean<String>> resultList = new ArrayList<>();
        
        // 1.获得所有算法类型
        List<TAlgotithmsType> typeList = itAlgotithmsTypeService.listPojo();
        if (CollectionUtils.isEmpty(typeList)) {
            return resultList;
        }
        
        // 2.从redis中取出数据
        List<String> list = jedisClient.lrange(REDIS_ALGORITHMS_LIST_KEY, 0, -1);
        if (CollectionUtils.isEmpty(list)) {
            return listDtoByTypeGroupWithLocal(typeList);
        }
        
        // 3.String -> dto
        final List<AlgotithmsInfoDto> dtos = new LinkedList<>();
        try {
            list.stream().filter(x -> x != null).forEach(x -> {
                AlgotithmsInfoDto dto = JSON.parseObject(x, AlgotithmsInfoDto.class);
                dtos.add(dto);
            });
        }
        catch (Exception e) {
            return listDtoByTypeGroupWithLocal(typeList);
        }
        
        // 4.分类组合
        typeList.stream().filter(k -> k != null).forEach(k -> {
            List<AlgotithmsInfoDto> newDtos = new ArrayList<>();
            dtos.stream().filter(dto -> dto != null).forEach(dto -> {
                String encodeTypeId = CodecUtils.encodeData(String.valueOf(k.getAlgotithmsTypeId()));
                if (dto.getAlgotithmsTypeId().equals(encodeTypeId)) {
                    // 排序后的集合
                    newDtos.add(dto);
                }
            });
            resultList.add(new TBean<>(newDtos, k.getAlgotithmsTypeName()));
        });
        
        return resultList;
    }
    
    @Override
    public AlgotithmsInfoDto getDtoByFuncIndex(Long funcIndex) {
        if (null == funcIndex) {
            return null;
        }
        
        List<String> list;
        AlgotithmsInfoDto resultDto = null;
        try {
            // 1.从redis中取出数据
            list = jedisClient.lrange(REDIS_ALGORITHMS_LIST_KEY, 0, -1);
            if (CollectionUtils.isEmpty(list)) {
                return getDtoByFuncIndexWithLocal(funcIndex);
            }
            
            // 2.返回dto
            for (String x : list) {
                AlgotithmsInfoDto dto = JSON.parseObject(x, AlgotithmsInfoDto.class);
                if (funcIndex.equals(dto.getFuncIndex())) {
                    resultDto = dto;
                    // 格式纠正
                    resultDto.setAlgotithmsCode(changeToRightShow(dto.getAlgotithmsCode()));
                    resultDto.setControllerCode(changeToRightShow(dto.getControllerCode()));
                    break;
                }
            }
        }
        catch (Exception e) {
            return getDtoByFuncIndexWithLocal(funcIndex);
        }
        
        return resultDto;
    }
    
    /**
     * 通过函数索引获得dto（从数据库)
     *
     * @param funcIndex 索引
     * @return dto
     * @author slycmiaoxi
     * @since 2019-06-22
     */
    private AlgotithmsInfoDto getDtoByFuncIndexWithLocal(Long funcIndex) {
        Map<String, Object> map = new HashMap<>();
        map.put("func_index", funcIndex);
        map.put("delete_flag", Enable.YES.getValue());
        List<TAlgotithmsInfo> list = tAlgotithmsInfoMapper.selectByMap(map);
        
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        
        TAlgotithmsInfo algotithmsInfo = list.get(0);
        AlgotithmsInfoDto dto = new AlgotithmsInfoDto();
        BeanUtils.copyProperties(algotithmsInfo, dto, new String[] {"algotithmsInfoId", "algotithmsTypeId"});
        // 格式纠正
        dto.setAlgotithmsCode(changeToRightShow(dto.getAlgotithmsCode()));
        dto.setControllerCode(changeToRightShow(dto.getControllerCode()));
        return dto;
    }
    
    /**
     * 从数据库获得前端算法建模dto数据（类型名-算法建模dto）
     *
     * @param typeList 算法类型集合
     * @return List<TBean<String>>
     * @author slycmiaoxi
     * @since 2019-06-18
     */
    private List<TBean<String>> listDtoByTypeGroupWithLocal(List<TAlgotithmsType> typeList) {
        final List<TBean<String>> resultList = new ArrayList<>();
        if (CollectionUtils.isEmpty(typeList)) {
            return resultList;
        }
        
        // 1.获得所有算法建模
        List<TAlgotithmsInfo> infoList = tAlgotithmsInfoMapper
            .selectList(new EntityWrapper<TAlgotithmsInfo>().eq("delete_flag", Enable.YES.getValue()));
        if (CollectionUtils.isEmpty(infoList)) {
            typeList.stream().filter(x -> x != null).forEach(po -> {
                TBean<String> tBean = new TBean<>();
                tBean.setValue(po.getAlgotithmsTypeName());
                resultList.add(tBean);
            });
            return resultList;
        }
        
        // 2.分类组合
        typeList.stream().filter(k -> k != null).forEach(k -> {
            List<AlgotithmsInfoDto> dtos = new ArrayList<>();
            infoList.stream().filter(po -> po != null).forEach(po -> {
                if (po.getAlgotithmsTypeId().equals(k.getAlgotithmsTypeId())) {
                    // po -> dto
                    AlgotithmsInfoDto dto = new AlgotithmsInfoDto();
                    BeanUtils.copyProperties(po, dto, new String[] {"algotithmsInfoId", "algotithmsTypeId"});
                    dto.setAlgotithmsInfoId(CodecUtils.encodeData(String.valueOf(po.getAlgotithmsInfoId())));
                    dto.setAlgotithmsTypeId(CodecUtils.encodeData(String.valueOf(po.getAlgotithmsTypeId())));
                    dtos.add(dto);
                }
            });
            resultList.add(new TBean<>(dtos, k.getAlgotithmsTypeName()));
        });
        return resultList;
    }
    
    /**
     * dto -> po
     *
     * @param dto 算法建模dto
     * @return 算法建模po实体类
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    private TAlgotithmsInfo getPoByDto(AlgotithmsInfoDto dto) {
        TAlgotithmsInfo po = new TAlgotithmsInfo();
        BeanUtils.copyProperties(dto, po, new String[] {"algotithmsInfoId", "algotithmsTypeId", "funcIndex"});
        po.setAlgotithmsTypeId(Integer.parseInt(CodecUtils.decodeData(dto.getAlgotithmsTypeId())));
        po.setFuncIndex(IDUtils.genItemId());
        po.setDeleteFlag(Enable.YES.getValue());
        return po;
    }
    
    /**
     * 校验参数
     *
     * @param dto 算法建模dto
     * @return 校验结果
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    private boolean convert(AlgotithmsInfoDto dto) {
        // 1.算法建模不能为空或者编号不能存在（防止报文）
        if (null == dto || StringUtils.isNotEmpty(dto.getAlgotithmsInfoId())) {
            return false;
        }
        
        // 2.算法类型不为空
        if (StringUtils.isEmpty(dto.getAlgotithmsTypeId())) {
            return false;
        }
        
        // 3.算法类型名称是否有效
        Integer typeId = Integer.parseInt(CodecUtils.decodeData(dto.getAlgotithmsTypeId()));
        String typeName = itAlgotithmsTypeService.getTypeNameById(typeId);
        if (StringUtils.isEmpty(typeName)) {
            return false;
        }
        dto.setAlgotithmsTypeName(typeName);
        
        // 4.函数名称校验
        String funcName = dto.getFuncName();
        if (StringUtils.isEmpty(funcName)) {
            return false;
        }
        int count = tAlgotithmsInfoMapper.selectCount(
            new EntityWrapper<TAlgotithmsInfo>().eq("func_name", funcName).eq("delete_flag", Enable.YES.getValue()));
        if (count > 0) {
            return false;
        }
        return true;
    }
    
    /**
     * 代码块转成正确json格式
     *
     * @param result
     * @return
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    private String changeToRightShow(String result) {
        result = result.replace("\n\r", "<br>  ");
        result = result.replace("\r\n", "<br>  ");
        result = result.replace("\t", "    ");
        result = result.replace(" ", " ");
        result = result.replace("\"", "'");
        return result;
    }
}
