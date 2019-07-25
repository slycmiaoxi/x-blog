package cn.slycmiaoxi.core.algotithms.base;

import java.util.List;

import cn.slycmiaoxi.object.ArCompress;
import cn.slycmiaoxi.object.Compress;
import cn.slycmiaoxi.object.Decompress;

/**
 * <p>
 * 数据结构
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public interface DataStructService {
    
    /**
     * 模式匹配
     * 
     * @param target 目标串
     * @param pattern 待匹配子串
     * @param begin 起始匹配位置
     * @return 子串匹配到的位置，-1表示不存在
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    int indexOf(String target, String pattern, int begin);
    
    /**
     * KMP
     * 
     * @param target 目标串
     * @param pattern 待匹配子串
     * @param begin 起始匹配位置
     * @return 子串匹配到的位置，-1表示不存在
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    int indexOfKMP(String target, String pattern, int begin);
    
    /**
     * 哈夫曼编码
     * 
     * @param datas 原始数据
     * @param code 编码code，一般为二进制
     * @return 哈夫曼编码
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    List<String> getHuffer(int[] datas, String code);
    
    /**
     * 冒泡排序
     * 
     * @param a 待排序数组
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    void bubbleSort(int[] a);
    
    /**
     * 选择排序
     * 
     * @param a 待排序数组
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    void chooseSort(int[] a);
    
    /**
     * 快速排序
     * 
     * @param a 待排序数组
     * @param low 起始位置
     * @param high 结束位置
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    void qSort(int[] a, int low, int high);
    
    /**
     * 直接插入排序
     *
     * @param a 待排序数组
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    void insertSort(int[] a);
    
    /**
     * 希尔排序
     * 
     * @param a 待排序数组
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    void shellSort(int[] a);
    
    /**
     * 二分查找
     * 
     * @param a 原数组
     * @param target 目标数
     * @return 匹配起始位置，不存在为-1
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    int binarySearch(int[] a, int target);
    
    /**
     * 归并排序
     * 
     * @param b
     * @param a
     * @param i
     * @param j
     * @Auther: slycmiaoxi
     * @Date: 2019/6/19
     */
    void mSort(int[] b, int[] a, int i, int j);
    
    /**
     * 哈夫曼树之压缩
     * 
     * @return 压缩后的数据对象
     * @author slycmiaoxi
     * @since 2019-06-30
     */
    Compress getCompress();
    
    /**
     * 哈夫曼树之含有解密信息的压缩
     * 
     * @return 压缩后的数据对象
     * @author slycmiaoxi
     * @since 2019-06-30
     */
    ArCompress getArCompress();
    
    /**
     * 哈夫曼树之解压
     *
     * @return 压缩后的数据对象
     * @author slycmiaoxi
     * @since 2019-06-30
     */
    Decompress getDecompress();
}
