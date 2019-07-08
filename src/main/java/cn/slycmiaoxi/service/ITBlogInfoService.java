package cn.slycmiaoxi.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.dto.ArticleDto;
import cn.slycmiaoxi.entity.TBlogInfo;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;

/**
 * <p>
 * 帖子服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
public interface ITBlogInfoService extends IService<TBlogInfo> {
    
    /**
     * 获得记录总数
     *
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    long queryTotal();
    
    /**
     * 获得分页数据
     *
     * @param page 分页对象
     * @param map 存放参数
     * @return PageModal对象
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    PageModal queryListbyPage(Page page, Map<String, Object> map);
    
    /**
     * 根据帖子类型获得所有帖子集合
     *
     * @param states 帖子类型 {@link cn.slycmiaoxi.enumerate.BlogStatusEnum}
     * @return 所有帖子
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    List<ArticleDto> listDtoByState(List<Integer> states);
    
    /**
     * 通过主键获得dto对象
     *
     * @param blogId 帖子Id
     * @return dto对象
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    ArticleDto getDtoById(long blogId);
    
    /**
     * 获得热门贴集合
     *
     * @return 热门贴集合
     * @author slycmiaoxi
     * @since 2019-06-29
     */
    List<ArticleDto> listHotDtos();
    
    /**
     * 删除帖子
     * 
     * @param blogId 帖子主键
     * @author slycmiaoxi
     * @since 2019-06-29
     */
    void delete(long blogId);
}
