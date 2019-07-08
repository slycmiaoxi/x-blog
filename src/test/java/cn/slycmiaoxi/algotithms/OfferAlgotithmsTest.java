package cn.slycmiaoxi.algotithms;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cn.slycmiaoxi.core.algotithms.base.impl.OfferAlgorithmsServiceImpl;
import cn.slycmiaoxi.object.ListNode;
import cn.slycmiaoxi.object.TreeNode;

/**
 * @Auther: slycmiaoxi
 * @Date: 2019/6/20 10:05
 * @Description:
 */
public class OfferAlgotithmsTest {
    
    final OfferAlgorithmsServiceImpl offerAlgorithmsService = new OfferAlgorithmsServiceImpl();
    
    @Test
    public void testPrintListFromTailToHead() {
        ListNode<String> list1 = new ListNode<>("1");
        ListNode<String> list2 = new ListNode<>("2");
        ListNode<String> list3 = new ListNode<>("3");
        ListNode<String> list4 = new ListNode<>("4");
        
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = null;
        
        ArrayList<String> list = offerAlgorithmsService.printListFromTailToHead(list1);
        System.out.println(list);
    }
    
    @Test
    public void testReplaceSpace() {
        String s = "i love wfl";
        StringBuffer sb = new StringBuffer(s);
        String str = offerAlgorithmsService.replaceSpace(sb);
        System.out.println(str);
    }
    
    @Test
    public void testNumberOf1() {
        int n = 5;
        int count = offerAlgorithmsService.NumberOf1(n);
        System.out.println(count);
    }
    
    @Test
    public void testPower() {
        double base = 2;
        int exp = 3;
        double resultPlus = offerAlgorithmsService.Power(base, exp);
        System.out.println(resultPlus);
        
        int exp1 = -2;
        double resultMinus = offerAlgorithmsService.Power(base, exp1);
        System.out.println(resultMinus);
    }
    
    @Test
    public void testReOrderArray() {
        int[] array = new int[] {2, 3, 4, 5, 6, 7, 8};
        offerAlgorithmsService.reOrderArray(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    
    @Test
    public void testFindKthToTail() {
        ListNode<String> list1 = new ListNode<>("1");
        ListNode<String> list2 = new ListNode<>("2");
        ListNode<String> list3 = new ListNode<>("3");
        ListNode<String> list4 = new ListNode<>("4");
        ListNode<String> list5 = new ListNode<>("5");
        
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = null;
        
        int k = 2;
        ListNode<String> resultNode = offerAlgorithmsService.FindKthToTail(list1, k);
        
        System.out.println(resultNode.val);
    }
    
    @Test
    public void testMerge() {
        ListNode<Integer> list1 = new ListNode<>(1);
        ListNode<Integer> list2 = new ListNode<>(2);
        ListNode<Integer> list3 = new ListNode<>(3);
        ListNode<Integer> list4 = new ListNode<>(4);
        ListNode<Integer> list5 = new ListNode<>(5);
        
        list1.next = list3;
        list3.next = list5;
        list5.next = null;
        
        list2.next = list4;
        list4.next = null;
        
        ListNode<Integer> resultNode = offerAlgorithmsService.Merge(list1, list2);
        while (resultNode.next != null) {
            System.out.println(resultNode.val);
            resultNode = resultNode.next;
        }
    }
    
    @Test
    public void testMirror() {
        TreeNode<String> node1 = new TreeNode<>("1");
        TreeNode<String> node2 = new TreeNode<>("2");
        TreeNode<String> node3 = new TreeNode<>("3");
        
        node1.left = node2;
        node1.right = node3;
        
        offerAlgorithmsService.Mirror(node1);
        List<String> list = new ArrayList<>();
        offerAlgorithmsService.preOrder(node1, list);
        System.out.println(list);
    }
    
    @Test
    public void testPrintFromTopToBottom() {
        TreeNode<String> node1 = new TreeNode<>("1");
        TreeNode<String> node2 = new TreeNode<>("2");
        TreeNode<String> node3 = new TreeNode<>("3");
        TreeNode<String> node4 = new TreeNode<>("4");
        
        node1.left = node3;
        node3.left = node4;
        node1.right = node2;
        
        // ArrayList<String> result = offerAlgorithmsService.PrintFromTopToBottom(node1);
        // int result = offerAlgorithmsService.TreeDepth(node1);
        
        boolean result = offerAlgorithmsService.IsBalanced_Solution(node1);
        System.out.println(result);
        
    }
    
    @Test
    public void testCountnQuit() {
        int total = 5;
        int n = 2;
        System.out.println(offerAlgorithmsService.CountnQuit(total, n));
    }
    
    @Test
    public void testGetLeastNumbers_Solution() {
        int[] input = new int[] {2, 4, 1, 3, 5};
        int k = 3;
        ArrayList<Integer> list = offerAlgorithmsService.GetLeastNumbers_Solution(input, k);
        System.out.println(list);
    }
    
    @Test
    public void testPrintMinNumber() {
        int[] data = new int[] {1, 45, 123, 321};
        String result = offerAlgorithmsService.PrintMinNumber(data);
        System.out.println(result);
        Assert.assertEquals("", "112332145", result);
    }

    @Test
    public void testMoreThanHalfNum_Solution() {
        int[] datas = new int[]{1, 12, 23, 12, 23, 12, 111};
        Assert.assertEquals("", 12, offerAlgorithmsService.MoreThanHalfNum_Solution(datas));
    }

    @Test
    public void testFirstNotRepeatingChar() {
        String str = "asdaw";
        int result = offerAlgorithmsService.FirstNotRepeatingChar(str);
        Assert.assertEquals("",2,result);
    }

    @Test
    public void testMatch() {
        String str = "s,1,3,5, 2";
        boolean flag =str.matches("((\\w)+,)+\\s(([0-9])+)$");
        Assert.assertEquals("", true, flag);

    }

}
