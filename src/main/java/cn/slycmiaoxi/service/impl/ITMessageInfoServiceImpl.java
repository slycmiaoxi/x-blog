package cn.slycmiaoxi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.common.Const;
import cn.slycmiaoxi.dao.TMessageInfoMapper;
import cn.slycmiaoxi.dto.MessageDto;
import cn.slycmiaoxi.entity.TMessageInfo;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.pagehelper.PageUtil;
import cn.slycmiaoxi.service.ITMessageInfoService;
import cn.slycmiaoxi.utils.DateUtil;

/**
 * <p>
 * 留言服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-04
 */
@Service
public class ITMessageInfoServiceImpl extends ServiceImpl<TMessageInfoMapper, TMessageInfo>
    implements ITMessageInfoService {
    @Autowired
    private TMessageInfoMapper tMessageInfoMapper;
    
    @Override
    public long queryTotal(Map<String, Object> map) {
        return tMessageInfoMapper.queryTotal(map);
    }
    
    @Override
    public PageModal queryListbyPage(Page page, Map<String, Object> map) {
        
        // 1.分页处理
        page = PageUtil.createPage(page, tMessageInfoMapper.queryTotal(map).intValue());
        map.put("beginIndex", page.getBeginIndex());
        map.put("everyPage", page.getEveryPage());
        PageModal pg = new PageModal();
        pg.setPage(page);
        
        // 2. 查询所有有效留言记录
        List<MessageDto> pageList = tMessageInfoMapper.queryList(map);
        if (CollectionUtils.isEmpty(pageList)) {
            pg.setList(pageList);
            return pg;
        }
        
        // 3. 留言数据结构分类（父-子)
        final List<MessageDto> parent = new ArrayList<>();
        final List<MessageDto> child = new ArrayList<>();
        
        pageList.stream().filter(x -> x != null).forEach(dto -> {
            // 3.1 格式化日期
            dto.setTime(DateUtil.formatDate(dto.getGmtCreate(), DateUtil.fullPattern));
            dto.setGmtCreate(null);
            if (dto.getParentNode().equals(String.valueOf(Const.ZERO))) {
                parent.add(dto);
            }
            else {
                child.add(dto);
            }
        });
        
        if (CollectionUtils.isEmpty(child)) {
            pg.setList(parent);
            return pg;
        }
        
        // 4.子节点归类
        child.stream().filter(x -> x != null).forEach(x -> {
            MessageDto parDto = parent.get(parent.indexOf(new MessageDto(x.getParentNode())));
            if (CollectionUtils.isEmpty(parDto.getChild())) {
                parDto.setChild(new ArrayList<>());
            }
            parDto.getChild().add(x);
        });
        pg.setList(parent);
        return pg;
    }
}
