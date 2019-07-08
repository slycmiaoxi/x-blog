package cn.slycmiaoxi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 图灵机器人前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
@Slf4j
@Controller
@RequestMapping("rob")
public class RobbotController {
    
    /**
     * 返回图灵机器人首页
     * 
     * @return 图灵机器人首页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/robbot")
    public String getRobbotView() {
        return "robbot/robbot";
    }
    
}
