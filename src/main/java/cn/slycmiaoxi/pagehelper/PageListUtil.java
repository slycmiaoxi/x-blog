package cn.slycmiaoxi.pagehelper;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 分页返回参数统一
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
@Slf4j
public class PageListUtil {
    
    public ModelAndView pageModelList(long totalRecord, ModelAndView mv, PageModal pg) {
        try {
            List TotalList = pg.getList();
            Page page = pg.getPage();
            mv.addObject("TotalList", TotalList);
            mv.addObject("page", page);
            mv.addObject("totalCurrentRecord", TotalList != null ? TotalList.size() : 0);
            mv.addObject("totalRecord", totalRecord);
        }
        catch (Exception e) {
            log.debug("error", e);
        }
        
        return mv;
    }
    
}
