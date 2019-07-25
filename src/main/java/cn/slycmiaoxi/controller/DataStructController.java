package cn.slycmiaoxi.controller;

import java.util.List;

import cn.slycmiaoxi.object.ArCompress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.slycmiaoxi.core.algotithms.base.DataStructService;
import cn.slycmiaoxi.object.Compress;
import cn.slycmiaoxi.object.HuffmNode;
import cn.slycmiaoxi.utils.FileUtils;
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
        dataStructService.mSort(datas, datas, 0, datas.length - 1);
        String result = listStrByParseInt(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 归并排序
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("/mSort")
    @ResponseBody
    public String mSort(String yourInput) {
        boolean checked = yourInput.matches("((\\d)+\\s)+(\\d+)$");
        if (!checked) {
            return JSON.toJSONString("error");
        }
        int[] datas = listIntByParseStr(yourInput);
        dataStructService.shellSort(datas);
        String result = listStrByParseInt(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 哈夫曼树之压缩
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("compress")
    @ResponseBody
    public String Compress(String yourInput) {
        synchronized (this) {
            String result = "";
            boolean checked = yourInput.matches("\\w+");
            if (!checked) {
                return JSON.toJSONString("error");
            }
            // 1. 创建压缩对象
            Compress compress = dataStructService.getCompress();
            FileUtils ds = null;
            // 2. 统计文件中0-255出现的次数
            try {
                ds = new FileUtils();
                ds.writeStringToFile(yourInput, FileUtils.FILE_ROOT_PATH + "compress.txt");
                compress.countTimes(FileUtils.FILE_ROOT_PATH + "compress.txt");
                // 3. 构造哈夫曼树，并得到根节点
                HuffmNode root = compress.createTree();
                // 4. 得到哈夫曼编码
                compress.getHuffmCode(root, "");
                // 5. 压缩文件
                compress.compress(FileUtils.FILE_ROOT_PATH + "compress.txt",
                    FileUtils.FILE_ROOT_PATH + "hufferCode.txt");
                String code = compress.getStr();
                int total = ((code.length()) / 8) + 1;
                String strcodes = ds.readStringToFile(FileUtils.FILE_ROOT_PATH + "hufferCode.txt");
                result = "压缩后的哈夫曼编码是" + code + " " + " 压缩后的空间是" + total + "" + "字节" + " 压缩后的gbk码是 :" + strcodes;
            }
            catch (Exception e) {
                return JSON.toJSONString("error");
            }
            
            return JSON.toJSONString(result);
        }
    }

    /**
     * 哈夫曼树之含有解密信息的压缩
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("ArCompress")
    @ResponseBody
    public  String ArCompress(String yourInput){
        synchronized (this) {
            String result="";
            boolean checked = yourInput.matches("\\w+");
            if (!checked) {
                return JSON.toJSONString("error");
            }
            //创建压缩对象
            ArCompress compress = dataStructService.getArCompress();
            //统计文件中0-255出现的次数
            FileUtils ds;
            try {
                ds=new FileUtils();
                ds.writeStringToFile(yourInput,FileUtils.FILE_ROOT_PATH+"ArCompress.txt");
                compress.countTimes(FileUtils.FILE_ROOT_PATH+"ArCompress.txt");
                //构造哈夫曼树，并得到根节点
                HuffmNode root=compress.createTree();
                //得到哈夫曼编码
                compress.getHuffmCode(root, "");
                //压缩文件
                compress.compress(FileUtils.FILE_ROOT_PATH+"ArCompress.txt",
                        FileUtils.FILE_ROOT_PATH+"huffer.txt");
                String strcodes=ds.readFileByBytes(FileUtils.FILE_ROOT_PATH+"huffer.txt");
                int strlen=strcodes.length();
                result="压缩后含解密信息的总长度为"+strlen+"字节  压缩后的二进制码是"+strcodes;
            } catch (Exception e) {
                return JSON.toJSONString("error");
            }
            return JSON.toJSONString(result);
        }
    }

    /**
     * 哈夫曼树之解压
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19 17:53
     */
    @RequestMapping("Decompress")
    @ResponseBody
    public String Decompress(String yourInput){
        String result="";
        boolean checked = yourInput.matches("\\w+");
        if (!checked) {
            return JSON.toJSONString("error");
        }
        //创建压缩对象
        ArCompress compress = dataStructService.getArCompress();
        //统计文件中0-255出现的次数
        FileUtils ds=null;
        try {
            ds=new FileUtils();
            ds.writeStringToFile(yourInput, FileUtils.FILE_ROOT_PATH+"ArCompressTemp.txt");
            compress.countTimes( FileUtils.FILE_ROOT_PATH+"ArCompressTemp.txt");
            //构造哈夫曼树，并得到根节点
            HuffmNode root=compress.createTree();
            //得到哈夫曼编码
            compress.getHuffmCode(root, "");
            //压缩文件
            compress.compress( FileUtils.FILE_ROOT_PATH+"ArCompressTemp.txt",
                    FileUtils.FILE_ROOT_PATH+"huffers.txt");
            dataStructService.getDecompress().decompress(FileUtils.FILE_ROOT_PATH+"huffers.txt",
                    FileUtils.FILE_ROOT_PATH+"mytes.txt");
            String strcodes=ds.readStringToFile(FileUtils.FILE_ROOT_PATH+"mytes.txt");
            result="解压后的原码是  "+strcodes;
        } catch (Exception e) {
            return JSON.toJSONString("error");
        }
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
