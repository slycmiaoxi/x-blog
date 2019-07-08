package cn.slycmiaoxi.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.slycmiaoxi.dto.AlgotithmsInfoDto;
import cn.slycmiaoxi.dto.CommonDto;
import cn.slycmiaoxi.entity.TAlgotithmsInfo;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.object.ClientObject;
import cn.slycmiaoxi.object.TBean;
import cn.slycmiaoxi.redis.JedisClient;
import cn.slycmiaoxi.service.ITAlgotithmsInfoService;
import cn.slycmiaoxi.service.ITAlgotithmsTypeService;
import cn.slycmiaoxi.utils.CodecUtils;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 算法建模前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Controller
@RequestMapping("/core/v1/tAlgotithmsInfo")
@Slf4j
public class TAlgotithmsInfoController {
    @Autowired
    private ITAlgotithmsInfoService itAlgotithmsInfoService;
    
    @Autowired
    private ITAlgotithmsTypeService itAlgotithmsTypeService;
    
    @Autowired
    private JedisClient jedisClient;
    
    /**
     * 返回算法建模首页
     *
     * @return 算法类型首页
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    @RequestMapping("/show")
    public ModelAndView getAlgoInfoView(ModelAndView mv) {
        mv.setViewName("sys/algotithmsInfo");
        try {
            // 初始化算法类型下拉框
            List<CommonDto> algoTypeDtos = itAlgotithmsTypeService.listAlgoTypeDtos();
            mv.addObject("algoTypeList", algoTypeDtos);
            return mv;
        }
        catch (Exception e) {
            return mv;
        }
    }
    
    /**
     * 添加算法
     * 
     * @param dto 算法建模dto
     * @return 添加结果
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public R add(AlgotithmsInfoDto dto) {
        // 检验参数
        try {
            boolean checked = itAlgotithmsInfoService.add(dto);
            if (!checked) {
                return R.fail();
            }
        }
        catch (Exception e) {
            log.error("添加算法建模异常");
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 算法前端页面展示
     * 
     * @param mv ModelAndView对象
     * @return 算法前端页面
     * @author slycmiaoxi
     * @since 2019-06-18
     */
    @RequestMapping("/frontShow")
    public ModelAndView getFAlgoInfoView(ModelAndView mv) {
        mv.setViewName("algotithms/algorithmsShow");
        try {
            // 初始化算法建模下拉框
            List<TBean<String>> list = itAlgotithmsInfoService.listGroupDto();
            mv.addObject("algotithmsList", list);
            return mv;
        }
        catch (Exception e) {
            return mv;
        }
    }
    
    /**
     * 通过算法建模唯一索引获得dto
     * 
     * @param funcIndex 唯一索引
     * @return ClientObject
     * @author slycmiaoxi
     * @since 2019-06-19
     */
    @RequestMapping("/getAlgotithmsDetail")
    @ResponseBody
    public ClientObject getAlgotithmsDetail(@RequestParam("funcIndex") Long funcIndex) {
        ClientObject<AlgotithmsInfoDto> clientObject = new ClientObject<>();
        if (null == funcIndex) {
            clientObject.setCode(Enable.NO.getValue());
            return clientObject;
        }
        
        try {
            AlgotithmsInfoDto dto = itAlgotithmsInfoService.getDtoByFuncIndex(funcIndex);
            clientObject.setCode(Enable.YES.getValue());
            clientObject.setObj(dto);
        }
        catch (Exception e) {
            clientObject.setCode(Enable.NO.getValue());
            return clientObject;
        }
        return clientObject;
    }
    
    /**
     * 初始化redis算法建模key（这里写死了,只有当key里无数据时才初始化)
     * 
     * @return
     * @author slycmiaoxi
     * @since 2019-06-23
     */
    @RequestMapping("/initRedisDatas")
    @ResponseBody
    public String initRedisDatas() {
        List<String> listRedisDatas = jedisClient.lrange("REDIS_ALGORITHMS_LIST_KEY", 0, -1);
        if (CollectionUtils.isNotEmpty(listRedisDatas)) {
            return null;
        }
        
        // 1.获得po集合
        List<TAlgotithmsInfo> infoList = itAlgotithmsInfoService.selectList(new EntityWrapper<>());
        // 2.po -> dto
        List<AlgotithmsInfoDto> list = new ArrayList<>();
        infoList.stream().forEach(po -> {
            AlgotithmsInfoDto dto = new AlgotithmsInfoDto();
            BeanUtils.copyProperties(po, dto, new String[] {"algotithmsInfoId", "algotithmsTypeId"});
            dto.setAlgotithmsInfoId(CodecUtils.encodeData(String.valueOf(po.getAlgotithmsInfoId())));
            dto.setAlgotithmsTypeId(CodecUtils.encodeData(String.valueOf(po.getAlgotithmsTypeId())));
            list.add(dto);
            // 3.入redis
            jedisClient.lpush("REDIS_ALGORITHMS_LIST_KEY", JSON.toJSONString(dto));
        });
        
        return JSON.toJSONString(list);
    }
    
}
