package cn.slycmiaoxi.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.entity.TMessageInfo;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;

/**
 * <p>
 * 留言服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-04
 */
public interface ITMessageInfoService extends IService<TMessageInfo> {
    /**
     * 获得记录总数
     *
     * @param map 存放参数
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-07-01
     */
    long queryTotal(Map<String, Object> map);
    
    /**
     * 获得分页数据
     * 
     * @param page 分页对象
     * @param map 存放参数
     * @return Pagemodal对象
     * @author slycmiaoxi
     * @since 2019-07-01
     */
    PageModal queryListbyPage(Page page, Map<String, Object> map);
    
    /**
     * 删除留言
     *
     * @param map 存放参数
     * @author slycmiaoxi
     * @since 2019-07-06
     */
    void delete(Map<String, Object> map);
}
