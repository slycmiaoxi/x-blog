package cn.slycmiaoxi.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.slycmiaoxi.dto.ArticleDto;
import cn.slycmiaoxi.entity.TBlogInfo;

/**
 * <p>
 * 帖子Mapper 接口
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
public interface TBlogInfoMapper extends BaseMapper<TBlogInfo> {
    /**
     * 获得记录总数
     *
     * @param map 存放参数
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    Long queryTotal(Map<String, Object> map);
    
    /**
     * 获得分页数据
     *
     * @param map 存放参数
     * @return Pagemodal对象
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    List<ArticleDto> queryList(Map<String, Object> map);
    
    /**
     * 根据帖子类型获得所有帖子集合
     *
     * @param states 帖子类型
     * @return 所有帖子
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    List<ArticleDto> listDtoByState(List<Integer> states);
}
