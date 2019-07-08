package cn.slycmiaoxi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageListUtil;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.service.ITLoginLogService;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 日志前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-26
 */
@Controller
@RequestMapping("/core/v1/tLoginLog")
@Slf4j
public class TLoginLogController {
    @Autowired
    private ITLoginLogService itLoginLogService;
    
    /**
     * 返回日志统计首页
     * 
     * @param currentPage 当前页
     * @param mv ModelAndView对象
     * @return 日志统计首页
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    @RequestMapping("/show/{currentPage}")
    public ModelAndView showVisitInfoView(@PathVariable int currentPage, ModelAndView mv) {
        Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
        PageModal pg = itLoginLogService.queryListbyPage(page, new HashMap<>());
        long total = itLoginLogService.queryTotal();
        new PageListUtil().pageModelList(total, mv, pg);
        mv.setViewName("sys/sysLoggerInfo");
        return mv;
    }
    
    /**
     * 删除单个记录
     * 
     * @param pkId 日志主键
     * @return 删除结果
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public R delete(@RequestParam("pkId") String pkId) {
        if (StringUtils.isEmpty(pkId)) {
            return R.fail();
        }
        
        try {
            itLoginLogService.deleteById(Integer.parseInt(pkId));
        }
        catch (Exception e) {
            log.error("delete fail");
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 批量删除日志
     * 
     * @param ids 主键集合
     * @return 结果
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.DELETE)
    @ResponseBody
    public R batchDelete(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return R.fail();
        }
        
        try {
            List<Integer> list = new ArrayList<Integer>();
            String[] idList = ids.split(",");
            for (String id : idList) {
                list.add(Integer.parseInt(id));
            }
            itLoginLogService.deleteBatchIds(list);
        }
        catch (Exception e) {
            log.error("batchDelete fail");
            return R.fail();
        }
        return R.ok();
    }
    
}
