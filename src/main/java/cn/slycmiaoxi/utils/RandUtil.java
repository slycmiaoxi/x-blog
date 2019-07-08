package cn.slycmiaoxi.utils;

import java.util.Random;

/**
 * <p>
 * 生成随机数
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-10
 */
public class RandUtil {
    
    public static String createActiveCode(Integer size) {
        String str = "1234567890";
        String result = "";
        
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            result += str.charAt(random.nextInt(str.length()));
        }
        
        return result;
    }
}