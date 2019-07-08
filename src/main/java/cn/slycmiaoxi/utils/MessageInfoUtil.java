package cn.slycmiaoxi.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * 加载提示信息
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public class MessageInfoUtil {
    
    @SuppressWarnings("rawtypes")
    private static Map map = null;
    
    protected static String dirParh = "conf/info.properties";
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void loadFile() {
        map = new HashMap();
        try {
            Properties p = new Properties();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(dirParh);
            p.load(in);
            Iterator it = p.keySet().iterator();
            while (it.hasNext()) {
                String key = (String)it.next();
                String value = p.getProperty(key);
                map.put(key, value);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getValue(String str) {
        if (map == null) {
            loadFile();
        }
        return (String)map.get(str);
    }
    
    public static void main(String[] args) {
        String suc = MessageInfoUtil.getValue("info.success");
        String error = MessageInfoUtil.getValue("info.error");
        System.out.println(suc);
        System.out.println(error);
    }
}
