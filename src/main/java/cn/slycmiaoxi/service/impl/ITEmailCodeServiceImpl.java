package cn.slycmiaoxi.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TEmailCodeMapper;
import cn.slycmiaoxi.entity.TEmailCode;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.service.ITEmailCodeService;

/**
 * <p>
 * 验证码服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
@Service
public class ITEmailCodeServiceImpl extends ServiceImpl<TEmailCodeMapper, TEmailCode> implements ITEmailCodeService {
    @Autowired
    private TEmailCodeMapper tEmailCodeMapper;
    
    @Override
    public TEmailCode getOneByEmail(String eMail) {
        if (StringUtils.isEmpty(eMail)) {
            return null;
        }
        
        EntityWrapper<TEmailCode> wrapper = new EntityWrapper<>();
        wrapper.eq("email_address", eMail).eq("delete_flag", Enable.YES.getValue());
        List<TEmailCode> list = tEmailCodeMapper.selectList(wrapper);
        
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public void add(String eMail, String code) {
        TEmailCode po = generatorPojo(eMail, code);
        tEmailCodeMapper.insert(po);
    }
    
    @Override
    public boolean checkEmail(String eMail) {
        TEmailCode emailCode = tEmailCodeMapper.getOneByUserEmail(eMail);
        return null != emailCode;
    }
    
    /**
     * 生成po实体
     *
     * @param eMail 邮箱地址
     * @param code 验证码
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    private TEmailCode generatorPojo(String eMail, String code) {
        TEmailCode emailCode = new TEmailCode();
        Date now = new Date();
        emailCode.setEmailAddress(eMail);
        emailCode.setEmailCode(code);
        emailCode.setGmtCreate(now);
        emailCode.setDeleteFlag(Enable.YES.getValue());
        return emailCode;
    }
}
