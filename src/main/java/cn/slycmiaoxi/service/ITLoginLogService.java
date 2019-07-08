package cn.slycmiaoxi.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.slycmiaoxi.entity.TLoginLog;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-26
 */
public interface ITLoginLogService extends IService<TLoginLog> {
    
    /**
     * 获得记录总数
     * 
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    long queryTotal();
    
    /**
     * 获得分页数据
     * 
     * @param page 分页对象
     * @param map 存放参数
     * @return PageModal对象
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    PageModal queryListbyPage(Page page, Map<String, Object> map);
    
}
