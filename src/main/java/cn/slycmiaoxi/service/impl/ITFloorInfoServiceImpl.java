package cn.slycmiaoxi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TFloorInfoMapper;
import cn.slycmiaoxi.dto.FloorDto;
import cn.slycmiaoxi.entity.TFloorInfo;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.pagehelper.PageUtil;
import cn.slycmiaoxi.service.ITFloorInfoService;
import cn.slycmiaoxi.utils.CodecUtils;

/**
 * <p>
 * 楼中楼服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
@Service
public class ITFloorInfoServiceImpl extends ServiceImpl<TFloorInfoMapper, TFloorInfo> implements ITFloorInfoService {
    @Autowired
    private TFloorInfoMapper tFloorInfoMapper;
    
    @Override
    public long queryTotal() {
        return tFloorInfoMapper.queryTotal(null);
    }
    
    @Override
    public PageModal queryListbyPage(Page page, Map<String, Object> map) {
        page = PageUtil.createPage(page, tFloorInfoMapper.queryTotal(map).intValue());
        map.put("beginIndex", page.getBeginIndex());
        map.put("everyPage", page.getEveryPage());
        List<FloorDto> pageList = tFloorInfoMapper.queryList(map);
        
        // 加密
        pageList.stream().filter(x -> x != null).forEach(dto -> {
            dto.setBlogId(CodecUtils.encodeData(String.valueOf(dto.getBlogId())));
            dto.setUserId(CodecUtils.encodeData(String.valueOf(dto.getUserId())));
        });
        PageModal pg = new PageModal();
        pg.setPage(page);
        pg.setList(pageList);
        return pg;
    }
    
    @Override
    public void delete(Map<String, Object> map) {
        tFloorInfoMapper.delete(map);
    }
}
