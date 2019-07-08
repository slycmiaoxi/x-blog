package cn.slycmiaoxi.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 对称加解密
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-20
 */
public class CodecUtils {
    private static final Logger logger = LoggerFactory.getLogger(CodecUtils.class);
    
    /**
     * 对给定的字符串进行base64解码操作
     * 
     * @param inputData 输入
     * @return 解码后的字符串
     * @author slycmiaoxi
     * @since 2019-06-20
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes("utf-8")), "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            logger.error(inputData, e);
        }
        
        return null;
    }
    
    /**
     * 对给定的字符串进行base64加密操作
     *
     * @param inputData 输入
     * @return 加密后的字符串
     * @author slycmiaoxi
     * @since 2019-06-20
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes("utf-8")), "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            logger.error(inputData, e);
        }
        
        return null;
    }
}
