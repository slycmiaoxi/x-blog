package cn.slycmiaoxi.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.slycmiaoxi.dto.MessageDto;
import cn.slycmiaoxi.entity.TMessageInfo;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-04
 */
public interface TMessageInfoMapper extends BaseMapper<TMessageInfo> {
    
    /**
     * 获得记录总数
     *
     * @param map 存放参数
     * @return 记录总数
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    List<MessageDto> queryList(Map<String, Object> map);
    
    /**
     * 获得分页数据
     *
     * @param map 存放参数
     * @return Pagemodal对象
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    Long queryTotal(Map<String, Object> map);
}
