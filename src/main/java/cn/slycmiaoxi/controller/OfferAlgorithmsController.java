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
