package cn.slycmiaoxi.core.algotithms.base.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.slycmiaoxi.core.algotithms.base.DataStructService;
import cn.slycmiaoxi.object.HuffmNode;

/**
 * <p>
 * 数据结构实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Service("dataStructService")
public class DataStructServiceImpl implements DataStructService {
    @Override
    public int indexOf(String target, String pattern, int begin) {
        if (StringUtils.isNotEmpty(pattern) && StringUtils.isNotEmpty(target) && target.length() >= pattern.length()) {
            int i = begin;
            // patter匹配计数量
            int j = 0;
            while (i < target.length()) {
                if (target.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                }
                else {
                    // i回溯，向后移动一位匹配
                    i = i - j + 1;
                    j = 0;
                }
                if (j == pattern.length()) {
                    return i - j + 1;
                }
            }
        }
        return -1;
    }
    
    @Override
    public int indexOfKMP(String target, String pattern, int begin) {
        if (StringUtils.isNotEmpty(pattern) && StringUtils.isNotEmpty(target) && target.length() >= pattern.length()) {
            int i = begin;
            int j = 0;
            // 求得next数组，这样就可以避免一些重复比较了
            int[] next = getNext(pattern);
            while (i < target.length()) {
                if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                }
                else {
                    // 退回到next[J]个字符开始比较
                    j = next[j];
                }
                if (j == pattern.length()) {
                    return i - j + 1;
                }
            }
        }
        return -1;
    }
    
    @Override
    public List<String> getHuffer(int[] datas, String code) {
        getHuffmCode(createTree(datas), code);
        return huffmcode;
    }
    
    @Override
    public void bubbleSort(int[] a) {
        boolean flag = true;
        for (int i = a.length - 1; i > 0 && flag; i--) {
            flag = false;
            for (int j = 0; j <= i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    flag = true;
                }
            }
        }
    }
    
    @Override
    public void chooseSort(int[] a) {
        int temp;
        for (int i = 0; i < a.length; i++) {
            temp = i;
            for (int j = i; j < a.length; j++) {
                if (a[temp] > a[j]) {
                    temp = j;
                }
            }
            if (temp != i) {
                swap(a, i, temp);
            }
        }
    }
    
    @Override
    public void qSort(int[] a, int low, int high) {
        int pivor;
        if (low < high) {
            pivor = patition(a, low, high);
            qSort(a, 0, pivor);
            qSort(a, pivor + 1, high);
        }
        
    }
    
    @Override
    public void insertSort(int[] a) {
        int i, j, temp;
        for (i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                temp = a[i];
                for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                    a[j + 1] = a[j];
                }
                a[j + 1] = temp;
            }
        }
    }
    
    @Override
    public void shellSort(int[] a) {
        int inc = a.length, i, j, temp;
        do {
            inc = inc / 3 + 1;
            for (i = inc; i < a.length; i++) {
                temp = a[i];
                for (j = i - inc; j >= 0 && a[j] > temp; j -= inc) {
                    a[j + inc] = a[j];
                }
                a[j + inc] = temp;
            }
            
        } while (inc > 1);
    }
    
    @Override
    public int binarySearch(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        int mid = 0;
        while (low < high) {
            mid = (low + high) / 2;
            if (a[mid] == target) {
                return mid + 1;
            }
            else if (a[mid] > target) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }
    
    private int patition(int[] a, int low, int high) {
        int pivor = a[low];
        while (low < high) {
            while (low < high && a[high] > pivor) {
                high--;
            }
            swap(a, low, high);
            while (low < high && a[low] <= pivor) {
                low++;
            }
            swap(a, low, high);
        }
        return low;
    }
    
    private void swap(int[] a, int i, int j) {
        if (null == a) {
            return;
        }
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    // 哈夫曼编码
    public LinkedList<HuffmNode> list = new LinkedList<HuffmNode>();
    
    private List<String> huffmcode = new ArrayList<>();
    
    public HuffmNode createTree(int[] datas) {
        // 按照从小到大的将数据封装成节点
        for (int i = 0; i < datas.length; i++) {
            HuffmNode node = new HuffmNode(datas[i]);
            // 得到需要插入的位置索引
            int index = getIndex(node);
            // 将数据添加到容器中
            list.add(index, node);
        }
        // 构造哈夫曼树
        while (list.size() > 1) {
            // 移除容器中的第一个节点
            HuffmNode firstNode = list.removeFirst();
            // 容器中原来的第二个节点变成新的第一个节点
            HuffmNode secondNode = list.removeFirst();
            // 构造父节点数据域
            HuffmNode fatherNode = new HuffmNode(firstNode.getData() + secondNode.getData());
            // 构造父节点左子叶
            fatherNode.setLeft(firstNode);
            // 构造父节点右子叶
            fatherNode.setRight(secondNode);
            // 得到构造好的父节点的索引
            int index = getIndex(fatherNode);
            // 将父节点加入森林
            list.add(index, fatherNode);
        }
        // 返回根节点
        return list.getFirst();
    }
    
    // 得到哈夫曼编码
    public void getHuffmCode(HuffmNode root, String code) {
        if (root.getLeft() != null) {
            getHuffmCode(root.getLeft(), code + "0");
        }
        if (root.getRight() != null) {
            getHuffmCode(root.getRight(), code + "1");
        }
        if (root.getLeft() == null && root.getRight() == null) {
            huffmcode.add((root.getData() + ":" + code));
        }
    }
    
    // 得到索引
    public int getIndex(HuffmNode node) {
        for (int i = 0; i < list.size(); i++) {
            if (node.getData() > list.get(i).getData()) {
                continue;
            }
            else {
                return i;
            }
        }
        // 如果比容器中的任何一个数大，则插入最后面
        return list.size();
    }
    
    private int[] getNext(String pattern) {
        int j = 0, k = -1;
        int[] next = new int[pattern.length()];
        next[0] = -1;
        while (j < pattern.length() - 1) {
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                j++;
                k++;
                if (pattern.charAt(j) != pattern.charAt(k)) {
                    next[j] = k;
                }
                else {
                    next[j] = next[k];
                }
            }
            else {
                k = next[k];
            }
        }
        return next;
    }
}
