package cn.slycmiaoxi.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TCritiquerecordInfoMapper;
import cn.slycmiaoxi.entity.TCritiquerecordInfo;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.service.ITCritiquerecordInfoService;

/**
 * <p>
 * 访问量服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-01
 */
@Service
public class ITCritiquerecordInfoServiceImpl extends ServiceImpl<TCritiquerecordInfoMapper, TCritiquerecordInfo>
    implements ITCritiquerecordInfoService {
    @Autowired
    private TCritiquerecordInfoMapper tCritiquerecordInfoMapper;

    @Override
    public boolean checkRepeat(long blogId, String ipAddress) {
        if (StringUtils.isEmpty(ipAddress)) {
            return true;
        }

        EntityWrapper<TCritiquerecordInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("blog_id", blogId).eq("ip_address", ipAddress).eq("delete_flag", Enable.YES.getValue());
        int count = tCritiquerecordInfoMapper.selectCount(wrapper);

        return count > 0 ? true : false;
    }
}
