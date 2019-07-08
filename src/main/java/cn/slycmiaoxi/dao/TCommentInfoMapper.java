package cn.slycmiaoxi.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.slycmiaoxi.dto.CommentDto;
import cn.slycmiaoxi.entity.TCommentInfo;

/**
 * <p>
 * 评论Mapper 接口
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
public interface TCommentInfoMapper extends BaseMapper<TCommentInfo> {
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
    List<CommentDto> queryList(Map<String, Object> map);
    
    /**
     * 删除评论
     * 
     * @param map 参数存值
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    void delete(Map<String, Object> map);
}
