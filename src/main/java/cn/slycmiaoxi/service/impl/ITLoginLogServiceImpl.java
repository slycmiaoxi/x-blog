package cn.slycmiaoxi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.dao.TLoginLogMapper;
import cn.slycmiaoxi.entity.TLoginLog;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.pagehelper.PageUtil;
import cn.slycmiaoxi.service.ITLoginLogService;

/**
 * <p>
 * 日志服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-26
 */
@Service("iTLoginLogService")
public class ITLoginLogServiceImpl extends ServiceImpl<TLoginLogMapper, TLoginLog> implements ITLoginLogService {
    @Autowired
    private TLoginLogMapper tLoginLogMapper;
    
    @Override
    public long queryTotal() {
        return tLoginLogMapper.queryTotal(null);
    }
    
    @Override
    public PageModal queryListbyPage(Page page, Map<String, Object> map) {
        page = PageUtil.createPage(page, tLoginLogMapper.queryTotal(map).intValue());
        map.put("beginIndex", page.getBeginIndex());
        map.put("everyPage", page.getEveryPage());
        List<TLoginLog> pageList = tLoginLogMapper.queryList(map);
        PageModal pg = new PageModal();
        pg.setPage(page);
        pg.setList(pageList);
        return pg;
    }
}
