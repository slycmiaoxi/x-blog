package cn.slycmiaoxi.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.entity.TCommentInfo;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;

/**
 * <p>
 * 评论服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
public interface ITCommentInfoService extends IService<TCommentInfo> {
    
    /**
     * 获得记录总数
     *
     * @param map 存放参数
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    long queryTotal(Map<String, Object> map);
    
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
     * 删除评论
     * 
     * @param map 参数存值
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    void delete(Map<String, Object> map);
}
