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
        stack1.clear();
        stack2.clear();
        m_data.clear();
        m_min.clear();
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
    
    @Override
    public ListNode ReverseList(ListNode head) {
        ListNode phead = null;
        ListNode currentNode = head;
        ListNode pre = null;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            if (nextNode == null) {
                phead = currentNode;
            }
            currentNode.next = pre;
            pre = currentNode;
            currentNode = nextNode;
        }
        return phead;
    }
    
    @Override
    public boolean HasSubtree(TreeNode<String> root1, TreeNode<String> root2) {
        boolean flag = false;
        if (root1 != null && root2 != null) {
            if (root1.value.equals(root2.value)) {
                flag = nodesIsEquals(root1, root2);
            }
            if (!flag) {
                flag = HasSubtree(root1.left, root2);
            }
            if (!flag) {
                flag = HasSubtree(root1.right, root2);
            }
        }
        return flag;
    }
    
    @Override
    public boolean VerifySquenceOfBST(int[] sequence) {
        int count = sequence.length;
        if (count == 0)
            return false;
        return isRight(sequence, 0, count - 1);
        
    }
    
    @Override
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if (str == null || str.length() < 0)
            return list;
        list = permitution(list, str.toCharArray(), 0, str.length());
        Collections.sort(list);
        return list;
    }
    
    Stack<Integer> stack1 = new Stack<Integer>();
    
    Stack<Integer> stack2 = new Stack<Integer>();
    
    @Override
    public void push(int node) {
        stack1.push(node);
    }
    
    @Override
    public void pushs(int node) {
        m_data.push(node);
        if (m_min.size() == 0 || node < m_min.peek())
            m_min.push(node);
        else {
            m_min.push(m_min.peek());
        }
    }
    
    Stack<Integer> m_data = new Stack<Integer>();
    
    Stack<Integer> m_min = new Stack<Integer>();
    
    @Override
    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        
        return stack2.pop();
    }
    
    @Override
    public int min() {
        if (m_data.size() > 0 && m_min.size() > 0) {
            return m_min.pop();
        }
        return 0;
    }
    
    @Override
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length <= 0)
            return 0;
        int low = 0;
        int high = array.length - 1;
        int mid = low;
        while (array[low] >= array[high]) {
            if (high - low == 1) {
                return array[high];
            }
            mid = (high + low) / 2;
            if (array[low] == array[high] && array[mid] == array[high]) {
                
            }
            if (array[mid] >= array[low]) {
                low = mid;
            }
            else if (array[mid] <= array[high]) {
                high = mid;
            }
        }
        return array[mid];
    }
    
    @Override
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) {
            return 0;
        }
        int[] ugly = new int[index];
        ugly[0] = 1;
        int count = 1;
        int num2 = 0;
        int num3 = 0;
        int num5 = 0;
        while (count < index) {
            int min = IsUglyNumber(ugly[num2] * 2, ugly[num3] * 3, ugly[num5] * 5);
            ugly[count] = min;
            count++;
            if (min == ugly[num2] * 2) {
                num2++;
            }
            if (min == ugly[num3] * 3) {
                num3++;
            }
            if (min == ugly[num5] * 5) {
                num5++;
            }
        }
        return ugly[index - 1];
    }
    
    @Override
    public boolean Find(int target, int[][] array) {
        boolean found = false;
        int rows = array.length;
        int colums = array[0].length;
        int row = 0;
        int colum = colums - 1;
        while (row < rows && colum >= 0) {
            if (array[row][colum] == target) {
                found = true;
                return found;
            }
            else if (array[row][colum] < target) {
                row++;
            }
            else {
                colum--;
            }
        }
        return found;
    }
    
    @Override
    public int Fibonacci(int n) {
        int result = 0;
        int preOne = 0;
        int preTwo = 1;
        if (n == 0) {
            return preOne;
        }
        if (n == 1) {
            return preTwo;
        }
        for (int i = 1; i <= n; i++) {
            preOne = preTwo;
            preTwo = result;
            result = preOne + preTwo;
        }
        return result;
    }
    
    @Override
    public int JumpFloor(int target) {
        int[] r = {0, 1, 2};
        if (target < 0) {
            return -1;
        }
        if (target <= 2 && target >= 0) {
            return r[target];
        }
        int one = 1;
        int two = 2;
        int result = 0;
        for (int i = 3; i <= target; i++) {
            result = one + two;
            one = two;
            two = result;
        }
        return result;
    }
    
    @Override
    public int JumpFloorII(int target) {
        if (target <= 0) {
            return -1;
        }
        if (target == 1) {
            return 1;
        }
        return 2 * JumpFloorII(target - 1);
    }
    
    @Override
    public int RectCover(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target == 1 || target == 2) {
            return target;
        }
        return RectCover(target - 1) + RectCover(target - 2);
    }
    
    @Override
    public String ReverseSentence(String str) {
        if (str == null) {
            return null;
        }
        if (str.trim().equals("")) {
            return str;
        }
        String[] s = str.split(" ");
        StringBuffer sb = new StringBuffer(s.length);
        for (int i = s.length - 1; i >= 0; i--) {
            if (i != 0)
                sb.append(s[i] + " ");
            else
                sb.append(s[i]);
        }
        return sb.toString();
    }
    
    @Override
    public boolean isContinuous(int[] numbers) {
        if (numbers == null)
            return false;
        Arrays.sort(numbers);
        int i = 0, zero = 0;
        for (; i < numbers.length && numbers[i] == 0; i++)
            zero++;
        for (; i < numbers.length - 1 && zero >= 0; i++) {
            if (numbers[i] == numbers[i + 1])
                return false;
            if (numbers[i] + 1 + zero >= numbers[i + 1]) {
                zero -= numbers[i + 1] - numbers[i] - 1;
            }
            else
                return false;
        }
        if (i == numbers.length - 1)
            return true;
        else
            return false;
    }
    
    @Override
    public int Add(int num1, int num2) {
        int sum, carry;
        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        } while (num2 != 0);
        return num1;
    }
    
    @Override
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null)
            return false;
        return matchRegCore(str, 0, str.length, pattern, 0, pattern.length);
    }
    
    @Override
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> sqList = new ArrayList<ArrayList<Integer>>();
        if (sum < 3)
            return sqList;
        int small = 1;
        int big = 2;
        int curSum = small + big;
        int middle = (1 + sum) / 2;
        while (small < middle) {
            ArrayList<Integer> sq = new ArrayList<Integer>();
            if (curSum == sum) {
                for (int i = small; i <= big; i++)
                    sq.add(i);
            }
            while (curSum > sum && small < middle) {
                curSum -= small;
                small++;
                if (curSum == sum) {
                    for (int i = small; i <= big; i++)
                        sq.add(i);
                }
            }
            
            if (sq.size() > 0)
                sqList.add(sq);
            big++;
            curSum += big;
        }
        return sqList;
    }
    
    @Override
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> lists = new ArrayList<Integer>();
        if (array == null || sum <= 0)
            return list;
        int start = 0;
        int end = array.length - 1;
        int curSum = array[start] + array[end];
        while (start < end) {
            if (curSum == sum) {
                list.add(array[start]);
                list.add(array[end]);
            }
            while (curSum > sum) {
                end--;
                curSum = array[start] + array[end];
                if (curSum == sum) {
                    list.add(array[start]);
                    list.add(array[end]);
                }
            }
            start++;
            curSum = array[start] + array[end];
        }
        if (list.size() == 0)
            return list;
        lists.add(list.get(0));
        lists.add(list.get(1));
        return lists;
    }
    
    @Override
    public int GetNumberOfK(int[] array, int k) {
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        int firstK = getFirstK(array, k, 0, length - 1);
        int lastK = getLastK(array, k, 0, length - 1);
        if (firstK != -1 && lastK != -1) {
            return lastK - firstK + 1;
        }
        return 0;
    }
    
    @Override
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0)
            return false;
        int index = 0;
        int length = str.length;
        while (index < length && str[index] == ' ')
            index++;
        if (index >= length)
            return false;
        while (str[length - 1] == ' ')
            length--;
        if (str[index] == '+' || str[index] == '-')
            index++;
        if (index >= length)
            return false;
        
        while (index < length && str[index] >= '0' && str[index] <= '9')
            index++;
        if (index == length)
            return true;
        int index2 = index;
        
        if (str[index] == '.') {
            index++;
            if (index == length)
                return true;
            index2 = index;
            while (index < length && str[index] >= '0' && str[index] <= '9')
                index++;
            if (index == index2)
                return false;
            if (index == length)
                return true;
        }
        
        if (str[index] == 'e' || str[index] == 'E') {
            index++;
            if (index == length)
                return false;
            if (str[index] == '+' || str[index] == '-')
                index++;
            index2 = index;
            while (index < length && str[index] >= '0' && str[index] <= '9')
                index++;
            if (index == index2)
                return false;
            if (index == length)
                return true;
        }
        return false;
    }
    
    private int getFirstK(int array[], int k, int start, int end) {
        if (start > end)
            return -1;
        int mid = (start + end) >> 1;
        if (array[mid] > k)
            return getFirstK(array, k, start, mid - 1);
        else if (array[mid] < k)
            return getFirstK(array, k, mid + 1, end);
        else if (mid - 1 >= 0 && array[mid - 1] == k) {
            return getFirstK(array, k, start, mid - 1);
        }
        else
            return mid;
    }
    
    private int getLastK(int array[], int k, int start, int end) {
        int length = array.length;
        int mid = (start + end) >> 1;
        while (start <= end) {
            if (array[mid] < k)
                start = mid + 1;
            else if (array[mid] > k)
                end = mid - 1;
            else if (mid + 1 < length && array[mid + 1] == k) {
                start = mid + 1;
            }
            else
                return mid;
            mid = (start + end) >> 1;
        }
        return -1;
    }
    
    private boolean matchRegCore(char[] str, int i, int length1, char[] pattern, int j, int length2) {
        if (i == length1 && j == length2) {
            if (j == length2 || pattern[j] == '*')
                return true;
            else
                return false;
        }
        if (i != length1 && j == length2)
            return false;
        if (j + 1 < length2 && pattern[j + 1] == '*') {
            if (i < length1 && (pattern[j] == str[i] || pattern[j] == '.')) {
                return matchRegCore(str, i + 1, length1, pattern, j, length2)
                    || matchRegCore(str, i + 1, length1, pattern, j + 2, length2)
                    || matchRegCore(str, i, length1, pattern, j + 2, length2);
            }
            else {
                return matchRegCore(str, i, length1, pattern, j + 2, length2);
            }
        }
        if (i < length1 && (str[i] == pattern[j] || pattern[j] == '.')) {
            return matchRegCore(str, i + 1, length1, pattern, j + 1, length2);
        }
        return false;
    }
    
    private int IsUglyNumber(int num2, int num3, int num5) {
        int min = num2 < num3 ? num2 : num3;
        return min < num5 ? min : num5;
    }
    
    private ArrayList<String> permitution(ArrayList<String> list, char[] str, int begin, int length) {
        if (begin == length - 1) {
            if (!list.contains(str)) {
                list.add(String.valueOf(str));
            }
        }
        else {
            for (int i = begin; i < length; i++) {
                if (i == begin || str[i] != str[begin]) {
                    swap(str, begin, i);
                    permitution(list, str, begin + 1, length);
                    swap(str, begin, i);
                }
            }
        }
        return list;
    }
    
    public void swap(char[] str, int begin, int i) {
        char temp = str[i];
        str[i] = str[begin];
        str[begin] = temp;
    }
    
    public boolean isRight(int[] sequence, int start, int end) {
        if (start >= end)
            return true;
        int i = end - 1;
        while (sequence[i] > sequence[end] && i > start)
            i--;
        for (int j = start; j < i; j++) {
            if (sequence[j] > sequence[end])
                return false;
        }
        return isRight(sequence, start, i) && isRight(sequence, i + 1, end - 1);
    }
    
    private boolean nodesIsEquals(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return true;
        if (root1 == null)
            return false;
        if (!root1.value.equals(root2.value))
            return false;
        return nodesIsEquals(root1.left, root2.left) && nodesIsEquals(root1.right, root2.right);
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
