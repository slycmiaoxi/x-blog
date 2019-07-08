package cn.slycmiaoxi.algotithms;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.slycmiaoxi.core.algotithms.base.impl.DataStructServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: slycmiaoxi
 * @Date: 2019/6/19 01:20
 * @Description:
 */
@Slf4j
public class DataStructTest {
    final DataStructServiceImpl dataStructService = new DataStructServiceImpl();
    
    @Test
    public void testIndexOf() {
        
        String target = "slycmiaomiaoxi";
        String patter = "miaox";
        int begin = 0;
        // int index = dataStructService.indexOf(target, patter, begin);
        int index = dataStructService.indexOfKMP(target, patter, begin);
        log.info(index + "");
    }
    
    @Test
    public void testHufferCode() {
        int[] datas = new int[] {1, 3, 5, 9};
        List<String> list = dataStructService.getHuffer(datas, "");
        log.info(list.toString());
    }
    
    @Test
    public void testSort() {
        int[] data = new int[] {3, 5, 1, 9, 7};
         dataStructService.bubbleSort(data);
        // dataStructService.chooseSort(data);
        // dataStructService.qSort(data, 0, data.length - 1);
        // dataStructService.insertSort(data);
        // dataStructService.shellSort(data);
         for (int i = 0; i < data.length; i++) {
         System.out.print(data[i] + " ");
         }
        int target = 55;
       // System.out.println(dataStructService.binarySearch(data, target));
//        List<String> list = new ArrayList<>();
//        list.add("123");
//        list.add("321");
//        list.add("4567");
//        String strBySplitList = getStrBySplitList(list);
//        System.out.println(strBySplitList);
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
}
