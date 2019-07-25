package cn.slycmiaoxi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.slycmiaoxi.core.algotithms.base.OfferAlgorithmsService;
import cn.slycmiaoxi.object.ListNode;
import cn.slycmiaoxi.object.TreeNode;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 剑指Offer前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-16
 */
@Controller
@RequestMapping("/core/v1/algorithms")
@Slf4j
public class OfferAlgorithmsController {
    @Autowired
    private OfferAlgorithmsService offerAlgorithmsService;
    
    /**
     * 从尾到头打印链表
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/printListFromTailToHead")
    @ResponseBody
    public String printListFromTailToHead(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+,)+(\\w)+$")) {
            return JSON.toJSONString("error");
        }
        
        // 创建头结点
        ListNode<String> head = createListNodeHead(yourInput);
        offerAlgorithmsService.clean();
        ArrayList<String> list = offerAlgorithmsService.printListFromTailToHead(head);
        String result = getStrBySplitList(list);
        return JSON.toJSONString(result);
    }
    
    /**
     * 替换空格
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/replaceSpace")
    @ResponseBody
    public String replaceSpace(String yourInput) {
        if (StringUtils.isEmpty(yourInput)) {
            return JSON.toJSONString("error");
        }
        
        StringBuffer sb = new StringBuffer(yourInput);
        String result = offerAlgorithmsService.replaceSpace(sb);
        return JSON.toJSONString(result);
    }
    
    /**
     * 二进制中1的个数
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/numberOf1")
    @ResponseBody
    public String numberOf1(String yourInput) {
        if (!checkInput(yourInput, "[0-9]+")) {
            return JSON.toJSONString("error");
        }
        
        int data = Integer.parseInt(yourInput);
        if (data > Integer.MAX_VALUE || data < Integer.MIN_VALUE) {
            return JSON.toJSONString("error");
        }
        
        int result = offerAlgorithmsService.NumberOf1(data);
        return JSON.toJSONString(result);
    }
    
    /**
     * 数值的整数次方
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/power")
    @ResponseBody
    public String power(String yourInput) {
        String[] str = yourInput.split(",");
        if (null == str || str.length != 2) {
            return JSON.toJSONString("error");
        }
        
        try {
            Double base = Double.parseDouble(str[0]);
            int exponent = Integer.parseInt(str[1]);
            Double result = offerAlgorithmsService.Power(base, exponent);
            return JSON.toJSONString(result);
            
        }
        catch (NumberFormatException e) {
            return JSON.toJSONString("error");
        }
        
    }
    
    /**
     * 调整数组顺序使奇数位于偶数前面
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/reOrderArray")
    @ResponseBody
    public String reOrderArray(String yourInput) {
        if (!checkInput(yourInput, "(([0-9])+,)+([0-9])+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] str = yourInput.split(",");
        int[] datas = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            datas[i] = Integer.parseInt(str[i]);
        }
        
        offerAlgorithmsService.reOrderArray(datas);
        StringBuffer sb = new StringBuffer(2 * str.length);
        for (Integer arr : datas) {
            sb.append(arr);
            sb.append(" ");
        }
        
        return JSON.toJSONString(sb.toString());
        
    }
    
    /**
     * 链表中倒数第k个结点
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/findKthToTail")
    @ResponseBody
    public String findKthToTail(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+,)+\\s(([0-9])+)$")) {
            return JSON.toJSONString("error");
        }
        
        String[] str = yourInput.split(" ");
        if (null == str || str.length != 2) {
            return JSON.toJSONString("error");
        }
        
        String removeLastStrSplit = str[0].substring(0, str[0].length() - 1);
        ListNode<String> head = createListNodeHead(removeLastStrSplit);
        ListNode<String> kNode = offerAlgorithmsService.FindKthToTail(head, Integer.parseInt(str[1]));
        if (null == kNode) {
            return JSON.toJSONString("error");
        }
        
        return JSON.toJSONString(kNode.val);
    }
    
    /**
     * 合并两个排序的链表
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/merge")
    @ResponseBody
    public String merge(String yourInput) {
        String[] strs = yourInput.split(",");
        if (null == strs || strs.length != 2) {
            return JSON.toJSONString("error");
        }
        
        boolean checkFirst = checkInput(strs[0], "((\\d)+\\s)+(\\d+)$");
        boolean checkSecond = checkInput(strs[1], "((\\d)+\\s)+(\\d+)$");
        if (!(checkFirst && checkSecond)) {
            return JSON.toJSONString("error");
        }
        
        int[] listMerge1 = listIntByParseStr(strs[0]);
        int[] listMerge2 = listIntByParseStr(strs[1]);
        Arrays.sort(listMerge1);
        Arrays.sort(listMerge2);
        
        ListNode<Integer> firstNode = createIntListNodeHead(listMerge1);
        ListNode<Integer> secondNode = createIntListNodeHead(listMerge2);
        ListNode<Integer> resultNode = offerAlgorithmsService.Merge(firstNode, secondNode);
        
        ArrayList<String> list = new ArrayList<>();
        while (null != resultNode) {
            list.add(String.valueOf(resultNode.val));
            resultNode = resultNode.next;
        }
        
        String result = getStrBySplitList(list);
        return JSON.toJSONString(result);
    }
    
    /**
     * 二叉树的镜像
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/mirror")
    @ResponseBody
    public String mirror(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+,)+(\\w)+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] strs = yourInput.split(",");
        if (null == strs || (strs.length + 1) % 2 != 0) {
            return JSON.toJSONString("error");
        }
        
        TreeNode<String> root = getTreeHead(strs);
        offerAlgorithmsService.Mirror(root);
        ArrayList<String> nodeList = new ArrayList<>();
        offerAlgorithmsService.preOrder(root, nodeList);
        String result = getStrBySplitList(nodeList);
        
        return JSON.toJSONString(result);
    }
    
    /**
     * 从上往下打印二叉树
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/printFromTopToBottom")
    @ResponseBody
    public String printFromTopToBottom(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+,)+(\\w)+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] strs = yourInput.split(",");
        if (null == strs || (strs.length + 1) % 2 != 0) {
            return JSON.toJSONString("error");
        }
        
        TreeNode<String> root = getTreeHead(strs);
        ArrayList<String> nodeList = offerAlgorithmsService.PrintFromTopToBottom(root);
        String result = getStrBySplitList(nodeList);
        
        return JSON.toJSONString(result);
    }
    
    /**
     * 二叉树的深度
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/treeDepth")
    @ResponseBody
    public String treeDepth(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+,)+(\\w)+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] strs = yourInput.split(",");
        TreeNode<String> root = getTreeHead(strs);
        int result = offerAlgorithmsService.TreeDepth(root);
        return JSON.toJSONString(result);
        
    }
    
    /**
     * 平衡二叉树
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/isBalanced_Solution")
    @ResponseBody
    public String isBalanced_Solution(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+,)+(\\w)+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] strs = yourInput.split(",");
        TreeNode<String> root = getTreeHead(strs);
        boolean result = offerAlgorithmsService.IsBalanced_Solution(root);
        return JSON.toJSONString(result);
    }
    
    /**
     * 序列化二叉树
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/serialize")
    @ResponseBody
    public String serialize(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+,)+(\\w)+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] strs = yourInput.split(",");
        TreeNode<String> root = getTreeHead(strs);
        String result = offerAlgorithmsService.Serialize(root);
        
        return JSON.toJSONString(result);
    }
    
    /**
     * 约瑟夫环
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/countnQuit")
    @ResponseBody
    public String countnQuit(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+,)+(\\d)+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] strs = yourInput.split(",");
        if (null == strs || strs.length != 2) {
            return JSON.toJSONString("error");
        }
        
        int total = Integer.parseInt(strs[0]);
        int n = Integer.parseInt(strs[1]);
        if (n > total) {
            return JSON.toJSONString("error");
        }
        int result = offerAlgorithmsService.CountnQuit(total, n);
        return JSON.toJSONString("被赦免的人是第" + result + "人");
    }
    
    /**
     * 栈的压入弹出序列
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/isPopOrder")
    @ResponseBody
    public String isPopOrder(String yourInput) {
        String[] strs = yourInput.split(",");
        if (null == strs || strs.length != 2) {
            return JSON.toJSONString("error");
        }
        
        boolean checkFirst = checkInput(strs[0], "((\\d)+\\s)+(\\d+)$");
        boolean checkSecond = checkInput(strs[1], "((\\d)+\\s)+(\\d+)$");
        if (!(checkFirst && checkSecond) || strs[0].length() != strs[1].length()) {
            return JSON.toJSONString("error");
        }
        
        int[] pushA = listIntByParseStr(strs[0]);
        int[] popA = listIntByParseStr(strs[1]);
        boolean result = offerAlgorithmsService.IsPopOrder(pushA, popA);
        return JSON.toJSONString(result);
    }
    
    /**
     * 最小的K个数
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/21
     */
    @RequestMapping("/getLeastNumbers_Solution")
    @ResponseBody
    public String getLeastNumbers_Solution(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+,)+\\s(([0-9])+)$")) {
            return JSON.toJSONString("error");
        }
        
        String[] str = yourInput.split(" ");
        if (null == str || str.length != 2) {
            return JSON.toJSONString("error");
        }
        
        String removeLastStrSplit = str[0].substring(0, str[0].length() - 1);
        String[] input = removeLastStrSplit.split(",");
        int[] datas = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            datas[i] = Integer.parseInt(input[i]);
        }
        ArrayList<Integer> list = offerAlgorithmsService.GetLeastNumbers_Solution(datas, Integer.parseInt(str[1]));
        
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i + " ");
        }
        return JSON.toJSONString(sb.toString());
        
    }
    
    /**
     * 把数组排成最小的数
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("/printMinNumber")
    @ResponseBody
    public String printMinNumber(String yourInput) {
        if (!checkInput(yourInput, "(([0-9])+,)+([0-9])+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] str = yourInput.split(",");
        int[] datas = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            datas[i] = Integer.parseInt(str[i]);
        }
        
        String result = offerAlgorithmsService.PrintMinNumber(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 数组中出现次数超过一半的数字
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("/moreThanHalfNum_Solution")
    @ResponseBody
    public String moreThanHalfNum_Solution(String yourInput) {
        if (!checkInput(yourInput, "(([0-9])+,)+([0-9])+$")) {
            return JSON.toJSONString("error");
        }
        
        String[] str = yourInput.split(",");
        int[] datas = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            datas[i] = Integer.parseInt(str[i]);
        }
        
        int result = offerAlgorithmsService.MoreThanHalfNum_Solution(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 第一个只出现一次的字符
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("/firstNotRepeatingChar")
    @ResponseBody
    public String firstNotRepeatingChar(String yourInput) {
        if (!checkInput(yourInput, "(\\w)+\\w$")) {
            return JSON.toJSONString("error");
        }
        
        int result = offerAlgorithmsService.FirstNotRepeatingChar(yourInput);
        return JSON.toJSONString(result);
    }
    
    /**
     * 连续子数组的最大和
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("/findGreatestSumOfSubArray")
    @ResponseBody
    public String findGreatestSumOfSubArray(String yourInput) {
        if (!checkInput(yourInput, "((((\\-|\\+?))\\d)+,)+((\\-|\\+?)\\d+)$")) {
            return JSON.toJSONString("error");
        }
        
        String[] str = yourInput.split(",");
        int[] datas = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            datas[i] = Integer.parseInt(str[i]);
        }
        
        int result = offerAlgorithmsService.FindGreatestSumOfSubArray(datas);
        return JSON.toJSONString(result);
    }
    
    /**
     * 左旋转字符串
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("/leftRotateString")
    @ResponseBody
    public String leftRotateString(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+,)(\\d+)$")) {
            return JSON.toJSONString("error");
        }
        
        String[] strs = yourInput.split(",");
        if (null == strs || strs.length != 2) {
            return JSON.toJSONString("error");
        }
        
        String str = strs[0];
        int n = Integer.parseInt(strs[1]);
        String result = offerAlgorithmsService.LeftRotateString(str, n);
        return JSON.toJSONString(result);
    }
    
    /**
     * 滑动窗口的最大值
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("/maxInWindows")
    @ResponseBody
    public String maxInWindows(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+,)+\\s(([0-9])+)$")) {
            return JSON.toJSONString("error");
        }
        
        String[] str = yourInput.split(" ");
        if (null == str || str.length != 2) {
            return JSON.toJSONString("error");
        }
        
        String removeLastStrSplit = str[0].substring(0, str[0].length() - 1);
        String[] input = removeLastStrSplit.split(",");
        int[] datas = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            datas[i] = Integer.parseInt(input[i]);
        }
        
        ArrayList<Integer> list = offerAlgorithmsService.maxInWindows(datas, Integer.parseInt(str[1]));
        StringBuffer sb = new StringBuffer();
        for (Integer s : list) {
            sb.append(s + " ");
        }
        return JSON.toJSONString(sb.toString());
    }
    
    /**
     * 反转链表
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("reverseList")
    @ResponseBody
    public String ReverseList(String yourInput) {
        if (!checkInput(yourInput, "(\\w,)+\\w$")) {
            return JSON.toJSONString("error");
        }
        
        ListNode<String> head = createListNodeHead(yourInput);
        ListNode<String> node = offerAlgorithmsService.ReverseList(head);
        List<String> list = new ArrayList<String>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return JSON.toJSONString(getStrBySplitList((ArrayList<String>)list));
    }
    
    /**
     * 树的子结构
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("hasSubtree")
    @ResponseBody
    public String HasSubtree(String yourInput) {
        String[] str = yourInput.split(",");
        String[] str1 = str[0].split(" ");
        String[] str2 = str[1].split(" ");
        boolean isRight = str[0].matches("(\\w\\s)+\\w$");
        boolean Right = str[1].matches("(\\w\\s)+\\w$");
        if ((yourInput.length() + 1) % 2 != 0 || str.length == 0 || !isRight || !Right) {
            return JSON.toJSONString("error");
        }
        TreeNode root1 = new TreeNode(str1[0]);
        TreeNode root2 = new TreeNode(str2[0]);
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        for (int i = 1; i < str1.length; i++) {
            list1.add(str1[i]);
        }
        TreeNode head1 = createTree(root1, list1);
        for (int i = 1; i < str2.length; i++) {
            list2.add(str2[i]);
        }
        TreeNode head2 = createTree(root2, list2);
        Boolean flag = offerAlgorithmsService.HasSubtree(head1, head2);
        return JSON.toJSONString(flag);
    }
    
    /**
     * 二叉搜索树的后序遍历序列
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("verifySquenceOfBST")
    @ResponseBody
    public String VerifySquenceOfBST(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+\\s)+(\\d+)$")) {
            return JSON.toJSONString("error");
        }
        String[] str = yourInput.split(" ");
        int[] strs = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            strs[i] = Integer.parseInt(str[i]);
        }
        return JSON.toJSONString(offerAlgorithmsService.VerifySquenceOfBST(strs));
    }
    
    /**
     * 字符串的排列
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("permutation")
    @ResponseBody
    public String Permutation(String yourInput) {
        boolean isRight = yourInput.matches("\\w+");
        if (!isRight || yourInput.length() == 0) {
            return JSON.toJSONString("error");
        }
        List<String> list = offerAlgorithmsService.Permutation(yourInput);
        return JSON.toJSONString(getStrBySplitList((ArrayList<String>)list));
    }
    
    /**
     * 用两个栈实现队列
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("pushAndpop")
    @ResponseBody
    public String pushAndpop(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+\\s)+(\\d+)$")) {
            return JSON.toJSONString("error");
        }
        String[] str = yourInput.split(" ");
        offerAlgorithmsService.clean();
        for (int i = 0; i < str.length; i++) {
            offerAlgorithmsService.push(Integer.parseInt(str[i]));
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append(offerAlgorithmsService.pop() + " ");
        }
        return JSON.toJSONString(sb.toString());
    }
    
    /**
     * 包含min函数的栈
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("min")
    @ResponseBody
    public String min(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+\\s)+(\\d+)$")) {
            return JSON.toJSONString("error");
        }
        String[] str = yourInput.split(" ");
        offerAlgorithmsService.clean();
        for (int i = 0; i < str.length; i++) {
            offerAlgorithmsService.pushs(Integer.parseInt(str[i]));
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append(offerAlgorithmsService.min() + " ");
        }
        return JSON.toJSONString(sb.toString());
    }
    
    /**
     * 旋转数组的最小数字
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("minNumberInRotateArray")
    @ResponseBody
    public String minNumberInRotateArray(String yourInput) {
        boolean isRight = yourInput.matches("((\\d)+\\s)+(\\d+)$");
        String[] str = yourInput.split(" ");
        int[] strs = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            strs[i] = Integer.parseInt(str[i]);
        }
        if (!isRight || !isminNumberInRotateArray(strs)) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.minNumberInRotateArray(strs));
    }
    
    /**
     * 丑数
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("getUglyNumber_Solution")
    @ResponseBody
    public String GetUglyNumber_Solution(String yourInput) {
        int n = Integer.parseInt(yourInput);
        if (!checkInput(yourInput, "(\\d)+")) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.GetUglyNumber_Solution(n));
    }
    
    /**
     * 二维数组中的查找
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("Find")
    @ResponseBody
    public String Find(String yourInput) {
        String[] str = yourInput.split(",");
        int[][] s = new int[str.length - 1][str[0].length() - 1];
        for (int j = 0; j < str.length - 1; j++) {
            String[] strs = str[j].split(" ");
            for (int i = 0; i < str[j].length() - 1; i++) {
                s[j][i] = Integer.parseInt(strs[i]);
            }
        }
        if (!IsFind(s)) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.Find(Integer.parseInt(str[str.length - 1]), s));
    }
    
    /**
     * 斐波那契数列
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("fibonacci")
    @ResponseBody
    public String Fibonacci(String yourInput) {
        int n = Integer.parseInt(yourInput);
        if (!checkInput(yourInput, "(\\d)+")) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.Fibonacci(n));
    }
    
    /**
     * 跳台阶
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("jumpFloor")
    @ResponseBody
    public String JumpFloor(String yourInput) {
        int n = Integer.parseInt(yourInput);
        if (!checkInput(yourInput, "(\\d)+")) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.JumpFloor(n + 1));
    }
    
    /**
     * 变态跳台阶
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("jumpFloorII")
    @ResponseBody
    public String JumpFloorII(String yourInput) {
        int n = Integer.parseInt(yourInput);
        if (!checkInput(yourInput, "(\\d)+")) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.JumpFloorII(n));
    }
    
    /**
     * 矩形覆盖
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("rectCover")
    @ResponseBody
    public String RectCover(String yourInput) {
        int n = Integer.parseInt(yourInput);
        if (!checkInput(yourInput, "(\\d)+")) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.RectCover(n));
    }
    
    /**
     * 翻转单词顺序列
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("reverseSentence")
    @ResponseBody
    public String ReverseSentence(String yourInput) {
        if (!checkInput(yourInput, "((\\w)+\\s)+(\\w+)$")) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.ReverseSentence(yourInput));
    }
    
    /**
     * 扑克牌顺子
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("isContinuous")
    @ResponseBody
    public String isContinuous(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+\\s)+(\\d+)$")) {
            return JSON.toJSONString("error");
        }
        String[] str = yourInput.split(" ");
        int[] count = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            count[i] = Integer.parseInt(str[i]);
        }
        if (!Continuous(count)) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.isContinuous(count));
        
    }
    
    private boolean Continuous(int[] str) {
        String strs = "0,1,2,3,4,5,6,7,8,9,10,11,12,13";
        Arrays.sort(str);
        int count = 0;
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == str[i + 1])
                count++;
        }
        if (count > 1) {
            return false;
        }
        for (int j = 0; j < str.length; j++) {
            if (!strs.contains(String.valueOf(str[j]))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 不用加减乘除做加法
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("add")
    @ResponseBody
    public String Add(String yourInput) {
        if (!checkInput(yourInput, "(\\d)+\\s(\\d)+$")) {
            return JSON.toJSONString("error");
        }
        String[] str = yourInput.split(" ");
        return JSON.toJSONString(offerAlgorithmsService.Add(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
    }
    
    /**
     * 正则表达式匹配
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("matchRegCore")
    @ResponseBody
    public String matchRegCore(String yourInput) {
        if (!checkInput(yourInput, "(\\w)+\\s(.)+$")) {
            return JSON.toJSONString("error");
        }
        String[] str = yourInput.split(" ");
        return JSON.toJSONString(offerAlgorithmsService.match(str[0].toCharArray(), str[1].toCharArray()));
    }
    
    /**
     * 和为S的连续正数序列
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("findContinuousSequence")
    @ResponseBody
    public String FindContinuousSequence(String yourInput) {
        if (!checkInput(yourInput, "(\\d)+")) {
            return JSON.toJSONString("error");
        }
        ArrayList<ArrayList<Integer>> list = offerAlgorithmsService.FindContinuousSequence(Integer.parseInt(yourInput));
        StringBuffer sb = new StringBuffer();
        for (ArrayList<Integer> a : list) {
            for (Integer s : a) {
                sb.append(s + " ");
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return JSON.toJSONString(sb.toString());
    }
    
    /**
     * 和为S的连续正数序列
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("findNumbersWithSum")
    @ResponseBody
    public String FindNumbersWithSum(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+\\s)+(\\d+)$")) {
            return JSON.toJSONString("error");
        }
        String[] str = yourInput.split(" ");
        int[] strs = new int[str.length - 1];
        for (int i = 0; i < str.length - 1; i++)
            strs[i] = Integer.parseInt(str[i]);
        ArrayList<Integer> list =
            offerAlgorithmsService.FindNumbersWithSum(strs, Integer.parseInt(str[str.length - 1]));
        StringBuilder sb = new StringBuilder();
        if (!list.isEmpty()) {
            for (Integer a : list)
                sb.append(a + " ");
        }
        else {
            sb.append("不存在");
        }
        return JSON.toJSONString(sb.toString());
    }
    
    /**
     * 数字在排序数组中出现的次数
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("GetNumberOfK")
    @ResponseBody
    public String GetNumberOfK(String yourInput) {
        if (!checkInput(yourInput, "((\\d)+\\s)+(\\d+)$")) {
            return JSON.toJSONString("error");
        }
        
        String[] str = yourInput.split(" ");
        int[] strs = new int[str.length - 1];
        Integer[] s = new Integer[str.length - 1];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = Integer.parseInt(str[i]);
            s[i] = Integer.parseInt(str[i]);
        }
        if (!this.compare(s))
            return JSON.toJSONString("error");
        Arrays.sort(strs);
        return JSON.toJSONString(offerAlgorithmsService.GetNumberOfK(strs, Integer.parseInt(str[str.length - 1])));
        
    }
    
    /**
     * 表示数值的字符串
     *
     * @param yourInput 输入
     * @return 输出
     * @Auther: slycmiaoxi
     * @Date: 2019/6/22
     */
    @RequestMapping("isNumeric")
    @ResponseBody
    public String isNumeric(String yourInput) {
        if (!checkInput(yourInput, "(.)+$")) {
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString(offerAlgorithmsService.isNumeric(yourInput.toCharArray()));
    }
    
    private boolean compare(Integer[] str) {
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i + 1] < str[i])
                return false;
        }
        return true;
    }
    
    private boolean IsFind(int[][] array) {
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array[0].length - 1; j++) {
                if (array[i][j] > array[i][j + 1])
                    return false;
                if (array[j][i] > array[j + 1][i])
                    return false;
            }
        return true;
    }
    
    private boolean isminNumberInRotateArray(int[] array) {
        int k = 0;
        boolean flag = true;
        for (int i = 1; i < array.length && flag; i++) {
            if (array[i - 1] > array[i]) {
                flag = false;
                k = i;
            }
        }
        if (k != array.length - 1) {
            for (int j = k; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /** 得到二叉树头结点 **/
    private TreeNode<String> getTreeHead(String[] str) {
        TreeNode<String> root = new TreeNode<String>(str[0]);
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < str.length; i++)
            list.add(str[i]);
        TreeNode<String> head = createTree(root, list);
        return head;
    }
    
    /** 根据根节点创建完全二叉树 **/
    private TreeNode createTree(TreeNode root, List<String> list) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty() && count < list.size()) {
            TreeNode lastNode = queue.poll();
            
            if (count < list.size()) {
                TreeNode left = new TreeNode(list.get(count++));
                lastNode.left = left;
                queue.add(left);
            }
            if (count < list.size()) {
                
                TreeNode right = new TreeNode(list.get(count++));
                lastNode.right = right;
                queue.add(right);
            }
        }
        return root;
    }
    
    private int[] listIntByParseStr(String yourInput) {
        String[] str = yourInput.split(" ");
        int[] datas = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            datas[i] = Integer.parseInt(str[i]);
        }
        return datas;
    }
    
    private String getStrBySplitList(ArrayList<String> list) {
        final StringBuffer sb = new StringBuffer();
        if (CollectionUtils.isEmpty(list)) {
            return sb.toString();
        }
        
        list.stream().filter(x -> x != null).forEach(x -> {
            sb.append(x);
            sb.append(" ");
        });
        return sb.toString();
    }
    
    private ListNode<String> createListNodeHead(String yourInput) {
        String[] str = yourInput.split(",");
        if (null == str || str.length == 0) {
            return null;
        }
        
        ListNode<String> head = new ListNode<>(str[0]);
        ListNode<String> top = head;
        ListNode<String> nextNode = null;
        for (int i = 1; i < str.length; i++) {
            nextNode = new ListNode<>(str[i]);
            top.next = nextNode;
            top = top.next;
        }
        return head;
    }
    
    private ListNode<Integer> createIntListNodeHead(int[] str) {
        if (null == str || str.length == 0) {
            return null;
        }
        ListNode<Integer> head = new ListNode<>(str[0]);
        ListNode<Integer> top = head;
        ListNode<Integer> nextNode = null;
        for (int i = 1; i < str.length; i++) {
            nextNode = new ListNode<>(str[i]);
            top.next = nextNode;
            top = top.next;
        }
        return head;
    }
    
    private boolean checkInput(String yourInput, String match) {
        return yourInput.matches(match);
    }
    
}
