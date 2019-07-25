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
    
    /**
     * 反转链表
     * 
     * @param head 头结点
     * @return 反转后的结点
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ListNode ReverseList(ListNode head);
    
    /**
     * 树的子结构
     * 
     * @param root1 原树
     * @param root2 子树
     * @return 是否是子结构
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    boolean HasSubtree(TreeNode<String> root1, TreeNode<String> root2);
    
    /**
     * 二叉搜索树的后序遍历序列
     * 
     * @param sequence 数组
     * @return 是否是二叉搜索树的后序遍历序列
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    boolean VerifySquenceOfBST(int[] sequence);
    
    /**
     * 字符串的排列
     * 
     * @param str 输入
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ArrayList<String> Permutation(String str);
    
    /**
     *
     * @param node 输入
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    void push(int node);
    
    /**
     *
     * @param node 输入
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    void pushs(int node);
    
    /**
     * 用两个栈实现队列
     * 
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int pop();
    
    /**
     * 包含min函数的栈
     * 
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int min();
    
    /**
     * 旋转数组的最小数字
     * 
     * @param array 输入
     * @return 最小数字
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int minNumberInRotateArray(int[] array);
    
    /**
     * 丑数
     * 
     * @param index 输入
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int GetUglyNumber_Solution(int index);
    
    /**
     * 二维数组中的查找
     * 
     * @param target 目标数值
     * @param array 原数组
     * @return 是否存在该数组中
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    boolean Find(int target, int[][] array);
    
    /**
     * 斐波那契数列
     * 
     * @param n 输入
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int Fibonacci(int n);
    
    /**
     * 跳台阶
     * 
     * @param target 输入
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int JumpFloor(int target);
    
    /**
     * 变态跳台阶
     *
     * @param target 输入
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int JumpFloorII(int target);
    
    /**
     * 矩形覆盖
     *
     * @param target 输入
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int RectCover(int target);
    
    /**
     * 翻转单词顺序列
     * 
     * @param str 输入的单词
     * @return 反转后的单词
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    String ReverseSentence(String str);
    
    /**
     * 扑克牌顺子
     * 
     * @param numbers 输入
     * @return 是否是扑克牌顺子
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    boolean isContinuous(int[] numbers);
    
    /**
     * 不用加减乘除做加法
     * 
     * @param num1 第一个加数
     * @param num2 第二个加数
     * @return 加数
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int Add(int num1, int num2);
    
    /**
     * 正则表达式匹配
     * 
     * @param str
     * @param pattern
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    boolean match(char[] str, char[] pattern);
    
    /**
     * 和为S的连续正数序列
     * 
     * @param sum 输入
     * @return 结果
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum);
    
    /**
     * 和为S的两个数字
     * 
     * @param array
     * @param sum
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    ArrayList<Integer> FindNumbersWithSum(int[] array, int sum);
    
    /**
     * 数字在排序数组中出现的次数
     * 
     * @param array
     * @param k
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    int GetNumberOfK(int[] array, int k);
    
    /**
     * 表示数值的字符串
     * 
     * @param str
     * @return
     * @Auther: slycmiaoxi
     * @Date: 2019/6/20
     */
    boolean isNumeric(char[] str);
}
