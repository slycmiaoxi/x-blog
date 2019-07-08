package cn.slycmiaoxi.controller;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageListUtil;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.service.ITRoleService;
import cn.slycmiaoxi.service.ITUserRoleService;
import cn.slycmiaoxi.utils.CodecUtils;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 角色前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
@Controller
@RequestMapping("/core/v1/tRole")
@Slf4j
public class TRoleController {
    @Autowired
    private ITRoleService itRoleService;
    
    @Autowired
    private ITUserRoleService itUserRoleService;
    
    /**
     * 返回角色首页
     * 
     * @param currentPage 当前页
     * @param mv ModelAndView对象
     * @return 角色首页
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    @RequestMapping("/show/{currentPage}")
    public ModelAndView showVisitInfoView(@PathVariable int currentPage, ModelAndView mv) {
        Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
        PageModal pg = itRoleService.queryListbyPage(page, new HashMap<>());
        long total = itRoleService.queryTotal();
        new PageListUtil().pageModelList(total, mv, pg);
        mv.setViewName("sys/sysAuthorized");
        return mv;
    }
    
    /**
     * 更新用户角色关系
     * 
     * @param userId 用户id
     * @param roleId 角色id
     * @return 结果
     * @author slycmiaoxi
     * @since 2019-06-27
     */
    @RequestMapping("/updateAuthorized")
    @ResponseBody
    public R edit(@RequestParam("userId") String userId, @RequestParam("roleId") String roleId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleId)) {
            return R.fail().put("msg", "更新失败");
        }
        
        try {
            itUserRoleService.updateByUserAndRoleId(Integer.parseInt(CodecUtils.decodeData(userId)),
                Integer.parseInt(roleId));
        }
        catch (NumberFormatException e) {
            return R.fail().put("msg", "更新失败");
        }
        return R.ok().put("msg", "更新成功");
    }
    
}
