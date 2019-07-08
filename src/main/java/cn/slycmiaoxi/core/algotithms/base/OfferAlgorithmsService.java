package cn.slycmiaoxi.core.algotithms.base;

import java.util.ArrayList;
import java.util.List;

import cn.slycmiaoxi.object.ListNode;
import cn.slycmiaoxi.object.TreeNode;

/**
 * <p>
 * 剑指Offer
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public interface OfferAlgorithmsService {
    
    /**
     * 清空数据
     * 
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    void clean();
    
    /**
     * 从尾到头打印链表
     * 
     * @param listNode 链表
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ArrayList<String> printListFromTailToHead(ListNode<String> listNode);
    
    /**
     * 替换空格
     * 
     * @param str 目标字符串
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    String replaceSpace(StringBuffer str);
    
    /**
     * 二进制中1的个数
     * 
     * @param n 十进制整数
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int NumberOf1(int n);
    
    /**
     * 数值的整数次方
     * 
     * @param base 底数
     * @param exponent 幂
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    double Power(double base, int exponent);
    
    /**
     * 调整数组顺序使奇数位于偶数前面
     * 
     * @param array 待排序数组
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    void reOrderArray(int[] array);
    
    /**
     * 链表中倒数第k个结点
     * 
     * @param head 头节点
     * @param k 倒数第k个位置
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ListNode<String> FindKthToTail(ListNode head, int k);
    
    /**
     * 合并两个排序的链表
     * 
     * @param list1 第一个排序链表
     * @param list2 第二个排序链表
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ListNode<Integer> Merge(ListNode<Integer> list1, ListNode<Integer> list2);
    
    /**
     * 二叉树的镜像
     * 
     * @param root 二叉树根结点
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    void Mirror(TreeNode root);
    
    /**
     * 从上往下打印二叉树
     * 
     * @param root 二叉树根结点
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ArrayList<String> PrintFromTopToBottom(TreeNode<String> root);
    
    /**
     * 二叉树的深度
     * 
     * @param root 二叉树根结点
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int TreeDepth(TreeNode root);
    
    /**
     * 平衡二叉树
     * 
     * @param root 二叉树根结点
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    boolean IsBalanced_Solution(TreeNode root);
    
    /**
     * 序列化二叉树
     * 
     * @param root 二叉树根结点
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    String Serialize(TreeNode root);
    
    /**
     * 反序列化二叉树
     * 
     * @param str 二叉树序列化后的字符串
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    TreeNode Deserialize(String str);
    
    /**
     * 先序遍历
     * 
     * @param node 二叉树根结点
     * @param list 结果集合
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    void preOrder(TreeNode<String> node, List<String> list);
    
    /**
     * 约瑟夫环
     * 
     * @param total 总人数
     * @param n 每次需要处死的第n位置人
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int CountnQuit(int total, int n);
    
    /**
     * 栈的压入弹出序列
     * 
     * @param pushA 原栈
     * @param popA 待校验栈
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    boolean IsPopOrder(int[] pushA, int[] popA);
    
    /**
     * 最小的K个数
     * 
     * @param input 输入
     * @param k 最小的数的个数
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k);
    
    /**
     * 把数组排成最小的数
     * 
     * @param numbers 输入
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    String PrintMinNumber(int[] numbers);
    
    /**
     * 数组中出现次数超过一半的数字
     * 
     * @param array 输入
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int MoreThanHalfNum_Solution(int[] array);
    
    /**
     * 第一个只出现一次的字符
     * 
     * @param str 输入
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int FirstNotRepeatingChar(String str);
    
    /**
     * 连续子数组的最大和
     * 
     * @param array 输入
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int FindGreatestSumOfSubArray(int[] array);
    
    /**
     * 左旋转字符串
     * 
     * @param str 输入字符串
     * @param n 连接的位置
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    String LeftRotateString(String str, int n);
    
    /**
     * 滑动窗口的最大值
     * 
     * @param num 输入
     * @param size 滑动大小
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ArrayList<Integer> maxInWindows(int[] num, int size);
}
