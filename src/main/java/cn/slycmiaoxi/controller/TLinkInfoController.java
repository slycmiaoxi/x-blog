package cn.slycmiaoxi.controller;

import cn.slycmiaoxi.dto.ArticleDto;
import cn.slycmiaoxi.entity.TLinkInfo;
import cn.slycmiaoxi.service.ITBlogInfoService;
import cn.slycmiaoxi.service.ITLinkInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
@Controller
@RequestMapping("/tLinkInfo")
public class TLinkInfoController {
    @Autowired
    private ITLinkInfoService itLinkInfoService;
    @Autowired
    private ITBlogInfoService itBlogInfoService;
    
    @RequestMapping("portal")
    public String getDoorView(Model model) {
        
        // 1.加载链接栏信息
        List<TLinkInfo> linkList = itLinkInfoService.selectList(new EntityWrapper<TLinkInfo>());
        model.addAttribute("linkList", linkList);

        // 2.加载热门贴
        List<ArticleDto> hotDtos = itBlogInfoService.listHotDtos();
        model.addAttribute("hotBlog", hotDtos);

        return "portal/index";
    }
    
}
