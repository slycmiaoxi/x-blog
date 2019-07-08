package cn.slycmiaoxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.slycmiaoxi.core.algotithms.base.DataStructService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 数据结果前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-16
 */
@Controller
@RequestMapping("/core/v1/algorithms")
@Slf4j
public class DataStructController {
    @Autowired
    private DataStructService dataStructService;
    
    /**
     * 模式匹配
     * 
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/indexOf")
    @ResponseBody
    public String indexOf(String yourInput) {
        String[] str = yourInput.split(",");
        // 目标串，子串，开始下标
        if (str.length != 3) {
            return JSON.toJSONString("error");
        }
        
        boolean checkStr3 = str[2].matches("[0-9]+");
        if (!checkStr3) {
            return JSON.toJSONString("error");
        }
        
        int begin = Integer.parseInt(str[2]);
        if (begin > str[0].length()) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(dataStructService.indexOf(str[0], str[1], begin));
    }
    
    /**
     * KMP
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/indexOfKMP")
    @ResponseBody
    public String indexOfKMP(String yourInput) {
        String[] str = yourInput.split(",");
        // 目标串，子串，开始下标
        if (str.length != 3) {
            return JSON.toJSONString("error");
        }
        
        boolean checkStr3 = str[2].matches("[0-9]+");
        if (!checkStr3) {
            return JSON.toJSONString("error");
        }
        
        int begin = Integer.parseInt(str[2]);
        if (begin > str[0].length()) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(dataStructService.indexOfKMP(str[0], str[1], begin));
    }
    
    /**
     * 哈夫曼编码
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/getHufferCode")
    @ResponseBody
    public String getHufferCode(String yourInput) {
        boolean checked = yourInput.matches("((\\d)+\\s)+(\\d+)$");
        if (!checked) {
            return JSON.toJSONString("error");
        }
        
        int[] datas = listIntByParseStr(yourInput);
        List<String> list = dataStructService.getHuffer(datas, "");
        String result = getStrBySplitList(list);
        
        return JSON.toJSONString(result);
    }
    
    /**
     * 快速排序
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/qSort")
    @ResponseBody
    public String qSort(String yourInput) {
        boolean checked = yourInput.matches("((\\d)+\\s)+(\\d+)$");
        if (!checked) {
            return JSON.toJSONString("error");
        }
        int[] datas = listIntByParseStr(yourInput);
        dataStructService.qSort(datas, 0, datas.length - 1);
        String result = listStrByParseInt(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 冒泡排序
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/bubbleSort")
    @ResponseBody
    public String bubbleSort(String yourInput) {
        boolean checked = yourInput.matches("((\\d)+\\s)+(\\d+)$");
        if (!checked) {
            return JSON.toJSONString("error");
        }
        int[] datas = listIntByParseStr(yourInput);
        dataStructService.bubbleSort(datas);
        String result = listStrByParseInt(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 选择排序
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/chooseSort")
    @ResponseBody
    public String chooseSort(String yourInput) {
        boolean checked = yourInput.matches("((\\d)+\\s)+(\\d+)$");
        if (!checked) {
            return JSON.toJSONString("error");
        }
        int[] datas = listIntByParseStr(yourInput);
        dataStructService.chooseSort(datas);
        String result = listStrByParseInt(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 直接插入
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/insertSort")
    @ResponseBody
    public String insertSort(String yourInput) {
        boolean checked = yourInput.matches("((\\d)+\\s)+(\\d+)$");
        if (!checked) {
            return JSON.toJSONString("error");
        }
        int[] datas = listIntByParseStr(yourInput);
        dataStructService.insertSort(datas);
        String result = listStrByParseInt(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 希尔排序
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/shellSort")
    @ResponseBody
    public String shellSort(String yourInput) {
        boolean checked = yourInput.matches("((\\d)+\\s)+(\\d+)$");
        if (!checked) {
            return JSON.toJSONString("error");
        }
        int[] datas = listIntByParseStr(yourInput);
        dataStructService.shellSort(datas);
        String result = listStrByParseInt(datas);
        return JSON.toJSONString(result);
    }
    
    private String listStrByParseInt(int[] datas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            sb.append(String.valueOf(datas[i] + " "));
        }
        return sb.toString();
    }
    
    private String getStrBySplitList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String s : list) {
            sb.append(s);
            if (count < list.size() - 1) {
                sb.append(",");
            }
            count++;
        }
        return sb.toString();
    }
    
    private int[] listIntByParseStr(String yourInput) {
        String[] str = yourInput.split(" ");
        int[] datas = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            datas[i] = Integer.parseInt(str[i]);
        }
        return datas;
    }
    
}
