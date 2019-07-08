package cn.slycmiaoxi.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 * 邮件信息初始化类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public class MailUtil {
    private static MailUtil mailUtil = new MailUtil();
    
    private static String fromEmail;
    
    private static String password;
    
    private static String mailType;
    
    private static String name;
    
    private static Properties properties = new Properties();
    
    private MailUtil() {
    }
    
    static {
        try {
            InputStream in =
                Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/mail-config.properties");
            properties.load(in);
            fromEmail = properties.getProperty("fromEmail");
            password = properties.getProperty("password");
            mailType = properties.getProperty("mailType");
            name = properties.getProperty("name");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getFromEmail() {
        return fromEmail;
    }
    
    public static void setFromEmail(String fromEmail) {
        MailUtil.fromEmail = fromEmail;
    }
    
    public static String getPassword() {
        return password;
    }
    
    public static void setPassword(String password) {
        MailUtil.password = password;
    }
    
    public static String getMailType() {
        return mailType;
    }
    
    public static void setMailType(String mailType) {
        MailUtil.mailType = mailType;
    }
    
    public static String getName() {
        return name;
    }
    
    public static void setName(String name) {
        MailUtil.name = name;
    }
}
