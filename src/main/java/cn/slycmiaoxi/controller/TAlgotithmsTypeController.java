package cn.slycmiaoxi.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slycmiaoxi.service.ITAlgotithmsTypeService;
import cn.slycmiaoxi.utils.MessageInfoUtil;
import cn.slycmiaoxi.utils.R;

/**
 * <p>
 * 算法类型前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Controller
@RequestMapping("/core/v1/tAlgotithmsType")
public class TAlgotithmsTypeController {
    @Autowired
    private ITAlgotithmsTypeService itAlgotithmsTypeService;
    
    /**
     * 返回算法类型首页
     * 
     * @return 算法类型首页
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    @RequestMapping("/show")
    public ModelAndView getAlgoTypeView() {
        return new ModelAndView("sys/algotithmsType");
    }
    
    /**
     * 添加算法类型
     * 
     * @param algotithmsTypeName 算法类型名称
     * @return R
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public R add(@RequestParam("algotithmsTypeName") String algotithmsTypeName) {
        // 1.不能为空
        if (StringUtils.isEmpty(algotithmsTypeName)) {
            return R.fail().put("msg", MessageInfoUtil.getValue("info.empty"));
        }
        // 2.重名校验
        boolean isRepeated = itAlgotithmsTypeService.checkRepeat(algotithmsTypeName);
        if (isRepeated) {
            return R.fail().put("msg", MessageInfoUtil.getValue("info.repeat"));
        }
        
        // 3.添加算法类型
        itAlgotithmsTypeService.add(algotithmsTypeName);
        return R.ok().put("msg", MessageInfoUtil.getValue("info.success"));
    }
}
