package cn.slycmiaoxi.service;

import cn.slycmiaoxi.entity.TCritiquerecordInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  访问量服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-01
 */
public interface ITCritiquerecordInfoService extends IService<TCritiquerecordInfo> {

    /**
     * 校验重名
     * @param blogId 帖子主键
     * @param ipAddress ip地址
     * @return 校验结果 （true-重复，false-不重复）
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    boolean checkRepeat(long blogId, String ipAddress);
}
