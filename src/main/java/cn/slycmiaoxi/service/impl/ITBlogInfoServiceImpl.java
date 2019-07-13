package cn.slycmiaoxi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.dao.TBlogInfoMapper;
import cn.slycmiaoxi.dto.ArticleDto;
import cn.slycmiaoxi.elasticsearch.EsUtil;
import cn.slycmiaoxi.entity.TBlogInfo;
import cn.slycmiaoxi.enumerate.BlogStatusEnum;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.object.ResultMap;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.pagehelper.PageUtil;
import cn.slycmiaoxi.service.ITBlogInfoService;
import cn.slycmiaoxi.service.ITCommentInfoService;
import cn.slycmiaoxi.service.ITFloorInfoService;
import cn.slycmiaoxi.utils.CodecUtils;

/**
 * <p>
 * 帖子服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
@Service
public class ITBlogInfoServiceImpl extends ServiceImpl<TBlogInfoMapper, TBlogInfo> implements ITBlogInfoService {
    @Autowired
    private TBlogInfoMapper tBlogInfoMapper;
    
    @Autowired
    private ITCommentInfoService itCommentInfoService;
    
    @Autowired
    private ITFloorInfoService itFloorInfoService;
    
    @Override
    public long queryTotal() {
        return tBlogInfoMapper.queryTotal(null);
    }
    
    @Override
    public PageModal queryListbyPage(Page page, Map<String, Object> map) {
        page = PageUtil.createPage(page, tBlogInfoMapper.queryTotal(map).intValue());
        map.put("beginIndex", page.getBeginIndex());
        map.put("everyPage", page.getEveryPage());
        List<ArticleDto> pageList = tBlogInfoMapper.queryList(map);
        
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
    public List<ArticleDto> listDtoByState(List<Integer> states) {
        if (CollectionUtils.isEmpty(states)) {
            return null;
        }

        List<ArticleDto> dtos = tBlogInfoMapper.listDtoByState(states);
        dtos.stream().filter(x -> x != null).forEach(dto -> {
            dto.setBlogId(CodecUtils.encodeData(String.valueOf(dto.getBlogId())));
            dto.setUserId(CodecUtils.encodeData(String.valueOf(dto.getUserId())));
        });
        return dtos;
    }
    
    @Override
    public ArticleDto getDtoById(long blogId) {
        List<ArticleDto> dtos = tBlogInfoMapper.queryList(new ResultMap("blogId", blogId).toMap());
        if (CollectionUtils.isEmpty(dtos)) {
            return null;
        }
        
        ArticleDto dto = dtos.get(0);
        dto.setBlogId(CodecUtils.encodeData(String.valueOf(dto.getBlogId())));
        dto.setUserId(CodecUtils.encodeData(String.valueOf(dto.getUserId())));
        return dto;
    }
    
    @Override
    public List<ArticleDto> listHotDtos() {
        
        // 1.获得所有热门贴
        List<Integer> stateList = new ArrayList<>();
        stateList.add(BlogStatusEnum.TOP.getCode());
        stateList.add(BlogStatusEnum.TOP_HOT.getCode());
        List<ArticleDto> topDtos = listDtoByState(stateList);
        if (CollectionUtils.isEmpty(topDtos)) {
            return topDtos;
        }
        
        // 2.控制内容子数显示
        topDtos.stream().filter(x -> x != null).forEach(dto -> {
            if (dto.getBlogContent().length() > Constants.HOT_BLOG_COUNT) {
                dto.setBlogContent(dto.getBlogContent().substring(0, Constants.HOT_BLOG_COUNT));
            }
            // 2.1.评论数
            long blogId = Long.parseLong(CodecUtils.decodeData(dto.getBlogId()));
            long total = itCommentInfoService.queryTotal(new ResultMap("blogId", blogId).toMap());
            dto.setCommentAmounts(total);
        });
        
        return topDtos;
    }
    
    @Override
    public void delete(long blogId) {
        // 1.删除帖子主体
        TBlogInfo blogInfo = new TBlogInfo();
        blogInfo.setBlogId(blogId);
        blogInfo.setDeleteFlag(Enable.NO.getValue());
        tBlogInfoMapper.updateById(blogInfo);
        
        // 2.删除帖子下所有评论
        itCommentInfoService.delete(new ResultMap("blogId", blogId).toMap());
        
        // 3.删除帖子下所有楼中楼
        itFloorInfoService.delete(new ResultMap("blogId", blogId).toMap());
        
        // 4.删除es
        TBlogInfo po = tBlogInfoMapper.selectById(blogId);
        EsUtil.delDoc(Constants.BLOG_SEARCH, po.getBlogItem());
    }
}
