package cn.slycmiaoxi.robbot;

/**
 * <p>
 * 图灵机器人工具类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public class RobbetUtil {
    public static void closeDB(AutoCloseable... c) {
        for (AutoCloseable autoCloseable : c) {
            if (autoCloseable != null) {
                try {
                    autoCloseable.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}