package cn.slycmiaoxi.core.algotithms.base.impl;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.slycmiaoxi.core.algotithms.base.OfferAlgorithmsService;
import cn.slycmiaoxi.object.ListNode;
import cn.slycmiaoxi.object.TreeNode;


/**
 * <p>
 * 剑指Offer实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Service
public class OfferAlgorithmsServiceImpl implements OfferAlgorithmsService {
    
    ArrayList<String> tailToHeadList = new ArrayList<>();
    
    @Override
    public void clean() {
        tailToHeadList.clear();
    }
    
    @Override
    public ArrayList<String> printListFromTailToHead(ListNode<String> listNode) {
        if (null != listNode) {
            if (null != listNode.next) {
                printListFromTailToHead(listNode.next);
            }
            tailToHeadList.add(listNode.val);
        }
        return tailToHeadList;
    }
    
    @Override
    public String replaceSpace(StringBuffer str) {
        if (null == str) {
            return null;
        }
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                sb.append("%");
                sb.append("2");
                sb.append("0");
            }
            else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
    
    @Override
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & n - 1;
        }
        return count;
    }
    
    @Override
    public double Power(double base, int exponent) {
        double sum = 0;
        double temp = base;
        if (exponent == 0) {
            return 1;
        }
        
        if (exponent == 1) {
            return temp;
        }
        
        if (exponent > 1) {
            for (int i = 1; i < exponent; i++) {
                sum = temp * base;
                temp = sum;
            }
        }
        else {
            for (int i = -1; i > exponent; i--) {
                sum = temp * base;
                temp = sum;
            }
            sum = 1 / sum;
        }
        return sum;
    }
    
    @Override
    public void reOrderArray(int[] array) {
        if (null == array) {
            return;
        }
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (isEven(array[i])) {
                evenList.add(array[i]);
            }
            else {
                oddList.add(array[i]);
            }
        }
        oddList.addAll(evenList);
        for (int i = 0; i < oddList.size(); i++) {
            array[i] = oddList.get(i);
        }
    }
    
    @Override
    public ListNode<String> FindKthToTail(ListNode head, int k) {
        if (k <= 0 || null == head) {
            return null;
        }
        
        ListNode<String> pre = head;
        for (int i = 0; i < k - 1; i++) {
            if (null == pre.next) {
                return null;
            }
            else {
                pre = pre.next;
            }
        }
        
        ListNode<String> behind = head;
        while (pre.next != null) {
            pre = pre.next;
            behind = behind.next;
        }
        return behind;
    }
    
    @Override
    public ListNode<Integer> Merge(ListNode<Integer> list1, ListNode<Integer> list2) {
        if (null == list1) {
            return list2;
        }
        if (null == list2) {
            return null;
        }
        
        ListNode<Integer> list = null;
        if (list1.val < list2.val) {
            list = list1;
            list.next = Merge(list1.next, list2);
        }
        else {
            list = list2;
            list.next = Merge(list1, list2.next);
        }
        return list;
    }
    
    @Override
    public void Mirror(TreeNode root) {
        if (null == root || (null == root.left && null == root.right)) {
            return;
        }
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null) {
            Mirror(root.left);
        }
        if (root.right != null) {
            Mirror(root.right);
        }
    }
    
    @Override
    public ArrayList<String> PrintFromTopToBottom(TreeNode<String> root) {
        ArrayList<String> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        
        Deque<TreeNode<String>> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode<String> temp = deque.pop();
            list.add(temp.value);
            if (temp.left != null) {
                deque.add(temp.left);
            }
            if (temp.right != null) {
                deque.add(temp.right);
            }
        }
        return list;
    }
    
    @Override
    public int TreeDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }
    
    @Override
    public boolean IsBalanced_Solution(TreeNode root) {
        if (null == root) {
            return true;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int dir = left - right;
        if (dir < -1 || dir > 1) {
            return false;
        }
        
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }
    
    @Override
    public String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (null == root) {
            sb.append("$");
            sb.append(",");
            return sb.toString();
        }
        sb.append(root.value + ",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }
    
    int n = -1;
    
    @Override
    public TreeNode Deserialize(String str) {
        n++;
        if (str.length() <= n) {
            return null;
        }
        
        TreeNode<String> node = null;
        String[] s = str.split(",");
        if (!s[n].equals("$")) {
            node = new TreeNode<>(s[n]);
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }
    
    @Override
    public void preOrder(TreeNode<String> node, List<String> list) {
        if (null != node) {
            list.add(node.value);
            preOrder(node.left, list);
            preOrder(node.right, list);
        }
    }
    
    @Override
    public int CountnQuit(int total, int n) {
        boolean[] arr = new boolean[total];
        for (int i = 0; i < total; i++) {
            arr[i] = true;
        }
        
        int count = 0;
        int index = 0;
        int len = arr.length;
        while (len > 1) {
            if (arr[index] == true) {
                count++;
                if (count == n) {
                    count = 0;
                    arr[index] = false;
                    len--;
                }
            }
            index++;
            if (index == arr.length) {
                index = 0;
            }
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == true) {
                return i + 1;
            }
        }
        return 0;
    }
    
    @Override
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (null == pushA || null == popA || pushA.length != popA.length) {
            return false;
        }
        Stack<Integer> stack = new Stack();
        int index = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[index]) {
                stack.pop();
                index++;
            }
        }
        
        return stack.isEmpty();
    }
    
    @Override
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        final ArrayList<Integer> list = new ArrayList<>();
        if (null == input || input.length == 0 || k > input.length) {
            return list;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < input.length; i++) {
            if (i < k) {
                set.add(input[i]);
            }
            else {
                if (set.last() > input[i]) {
                    set.remove(set.last());
                    set.add(input[i]);
                }
            }
        }
        
        set.stream().forEach(x -> {
            list.add(x);
        });
        return list;
    }
    
    @Override
    public String PrintMinNumber(int[] numbers) {
        if (null == numbers || numbers.length == 0) {
            return "";
        }
        
        String[] str = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(str, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder sb = new StringBuilder();
        for (String s : str) {
            sb.append(s);
        }
        return sb.toString();
    }
    
    @Override
    public int MoreThanHalfNum_Solution(int[] array) {
        if (null == array || array.length == 0) {
            return 0;
        }
        int count = 0;
        int temp = 0;
        for (int j = 0; j < array.length / 2; j++) {
            temp = array[j];
            for (int i = 0; i < array.length; i++) {
                if (temp == array[i]) {
                    count++;
                }
                if (count >= array.length / 2) {
                    return temp;
                }
            }
            count = 0;
        }
        
        return 0;
    }
    
    @Override
    public int FirstNotRepeatingChar(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        char[] ch = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ch.length; i++) {
            if (map.get(ch[i]) != null) {
                map.put(ch[i], map.get(ch[i]) + 1);
            }
            else {
                map.put(ch[i], 1);
            }
        }
        
        for (int i = 0; i < ch.length; i++) {
            if (map.get(ch[i]) == 1) {
                return i + 1;
            }
        }
        return 0;
    }
    
    @Override
    public int FindGreatestSumOfSubArray(int[] array) {
        if (null == array || array.length == 0) {
            return 0;
        }
        
        int total = array[0];
        int max = 0;
        
        for (int i = 1; i < array.length; i++) {
            if (total > 0) {
                total += array[i];
            }
            else {
                total = array[i];
            }
            if (total > max) {
                max = total;
            }
        }
        return max;
    }
    
    @Override
    public String LeftRotateString(String str, int n) {
        if (StringUtils.isEmpty(str) || n > str.length()) {
            return "";
        }
        char[] ch = new char[n];
        char[] th = new char[str.length() - n];
        char[] sn = str.toCharArray();
        for (int i = 0; i < sn.length; i++) {
            if (i < n) {
                ch[i] = sn[i];
            }
            else {
                th[i - n] = sn[i];
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (char t : th) {
            sb.append(t);
        }
        for (char c : ch) {
            sb.append(c);
        }

        return sb.toString();
        
    }
    
    @Override
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || num.length < 0 || num.length < size) {
            return list;
        }
        
        int k = 0;
        while (k + size <= num.length) {
            list.add(maxInWindow(num, k, size));
            k++;
        }
        return list;
    }
    
    private int maxInWindow(int[] num, int k, int size) {
        int top = num[k];
        for (int i = k; i < k + size; i++) {
            if (num[i] > top) {
                top = num[i];
            }
        }
        return top;
    }
    
    private boolean isEven(int n) {
        if ((n & 0x1) == 0) {
            return true;
        }
        return false;
    }
    
}
