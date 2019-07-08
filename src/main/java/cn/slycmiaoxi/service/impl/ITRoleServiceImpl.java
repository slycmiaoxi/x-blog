package cn.slycmiaoxi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TRoleMapper;
import cn.slycmiaoxi.dto.RoleDto;
import cn.slycmiaoxi.entity.TRole;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageUtil;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.service.ITRoleService;
import cn.slycmiaoxi.service.ITUserService;
import cn.slycmiaoxi.utils.CodecUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
@Service
public class ITRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements ITRoleService {
    
    @Autowired
    private TRoleMapper tRoleMapper;
    
    @Autowired
    private ITUserService itUserService;
    
    @Override
    public Set<TRole> findRolesByNickName(String nickName) {
        if (StringUtils.isEmpty(nickName)) {
            return null;
        }
        return tRoleMapper.findRolesByNickName(nickName);
    }
    
    @Override
    public long queryTotal() {
        return itUserService.queryTotal();
    }
    
    @Override
    public PageModal queryListbyPage(Page page, Map<String, Object> map) {
        page = PageUtil.createPage(page, (int)itUserService.queryTotal());
        map.put("beginIndex", page.getBeginIndex());
        map.put("everyPage", page.getEveryPage());
        List<RoleDto> pageList = tRoleMapper.queryList(map);
        // 加密
        pageList.stream().forEach(x -> {
            x.setUserId(CodecUtils.encodeData(x.getUserId()));
        });
        PageModal pg = new PageModal();
        pg.setPage(page);
        pg.setList(pageList);
        return pg;
    }
}
