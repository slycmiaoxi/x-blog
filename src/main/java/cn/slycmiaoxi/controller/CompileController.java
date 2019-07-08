package cn.slycmiaoxi.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slycmiaoxi.core.compile.base.DynamicCompile;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.object.ClientObject;
import cn.slycmiaoxi.utils.MessageInfoUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 在线编程前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-16
 */
@Slf4j
@Controller
@RequestMapping("/core/v1/dynamic")
public class CompileController {
    
    @Autowired
    private DynamicCompile dynamicCompile;
    
    /**
     * 编译代码
     * 
     * @param code 待编译代码
     * @return 编辑结果
     * @author slycmiaoxi
     * @since 2019-06-16
     */
    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    @ResponseBody
    public ClientObject getCompileResult(String code) {
        ClientObject<String> clientObject = new ClientObject<>();
        if (StringUtils.isBlank(code)) {
            clientObject.setCode(Enable.NO.getValue());
            clientObject.setMsg(MessageInfoUtil.getValue("info.empty"));
            return clientObject;
        }
        try {
            synchronized (this) {
                String result = dynamicCompile.getCompileResult(code);
                clientObject.setCode(Enable.YES.getValue());
                clientObject.setObj(result);
            }
        }
        catch (Exception e) {
            log.error("编译错误");
            clientObject.setCode(Enable.NO.getValue());
            clientObject.setMsg(MessageInfoUtil.getValue("info.error"));
            return clientObject;
        }
        return clientObject;
    }
    
    /**
     * 返回在线编程首页面
     * 
     * @return 在线编程首页面
     * @author slycmiaoxi
     * @since 2019-06-16
     */
    @RequestMapping("/show")
    public ModelAndView getCompileView() {
        return new ModelAndView("compile/programming");
    }
    
}
