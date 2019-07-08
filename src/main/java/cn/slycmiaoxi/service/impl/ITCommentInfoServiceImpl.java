package cn.slycmiaoxi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TCommentInfoMapper;
import cn.slycmiaoxi.dto.CommentDto;
import cn.slycmiaoxi.entity.TCommentInfo;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.pagehelper.PageUtil;
import cn.slycmiaoxi.service.ITCommentInfoService;
import cn.slycmiaoxi.service.ITFloorInfoService;
import cn.slycmiaoxi.utils.CodecUtils;

/**
 * <p>
 * 评论服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
@Service
public class ITCommentInfoServiceImpl extends ServiceImpl<TCommentInfoMapper, TCommentInfo>
    implements ITCommentInfoService {
    @Autowired
    private TCommentInfoMapper tCommentInfoMapper;
    
    @Autowired
    private ITFloorInfoService itFloorInfoService;
    
    @Override
    public long queryTotal(Map<String, Object> map) {
        return tCommentInfoMapper.queryTotal(map);
    }
    
    @Override
    public PageModal queryListbyPage(Page page, Map<String, Object> map) {
        page = PageUtil.createPage(page, tCommentInfoMapper.queryTotal(map).intValue());
        map.put("beginIndex", page.getBeginIndex());
        map.put("everyPage", page.getEveryPage());
        List<CommentDto> pageList = tCommentInfoMapper.queryList(map);
        
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
        tCommentInfoMapper.delete(map);
        itFloorInfoService.delete(map);
    }
}
