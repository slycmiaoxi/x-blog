package cn.slycmiaoxi.tools;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 邮件发送信息类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public class MysendMail {
    
    /**
     * MIME邮件对象
     */
    private MimeMessage mimeMsg;
    
    /**
     * 邮件会话对象
     */
    private Session session;
    
    /**
     * 系统属性
     */
    private Properties props;
    
    /**
     * smtp是否需要认证
     */
    private boolean needAuth = false;
    
    /**
     * smtp认证用户名
     */
    private String username = "";
    
    /**
     * smtp认证密码
     */
    private String password = "";
    
    /**
     * Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
     */
    private Multipart mp;
    
    private Logger logger = (Logger)LoggerFactory.getLogger(this.getClass().getName());
    
    public MysendMail() {
        
        // setSmtpHost(getConfig.mailHost);//如果没有指定邮件服务器,就从getConfig类中获取
        createMimeMessage();
        
    }
    
    public MysendMail(String smtp) {
        
        setSmtpHost(smtp);
        createMimeMessage();
        
    }
    
    /**
     * @param hostName String
     */
    public void setSmtpHost(String hostName) {
        
        logger.info("设置系统属性：mail.smtp.host = " + hostName);
        if (props == null) {
            // 获得系统属性对象
            props = System.getProperties();
        }
        // 设置SMTP主机
        props.put("mail.smtp.host", hostName);
        
    }
    
    /**
     * @return boolean
     */
    public boolean createMimeMessage() {
        try {
            logger.info("准备获取邮件会话对象！");
            // 获得邮件会话对象
            session = Session.getDefaultInstance(props, null);
        }
        
        catch (Exception e) {
            System.err.println("获取邮件会话对象时发生错误！" + e);
            return false;
        }
        
        logger.info("准备创建MIME邮件对象！");
        
        try {
            // 创建MIME邮件对象
            mimeMsg = new MimeMessage(session);
            mp = new MimeMultipart();
            
            return true;
            
        }
        catch (Exception e) {
            
            System.err.println("创建MIME邮件对象失败！" + e);
            return false;
        }
    }
    
    /**
     * @param need boolean
     */
    public void setNeedAuth(boolean need) {
        
        logger.info("设置smtp身份认证：mail.smtp.auth = " + need);
        if (props == null)
            props = System.getProperties();
        
        if (need) {
            
            props.put("mail.smtp.auth", "true");
        }
        else {
            
            props.put("mail.smtp.auth", "false");
        }
    }
    
    /**
     * @param name String
     * @param pass String
     */
    public void setNamePass(String name, String pass) {
        username = name;
        password = pass;
    }
    
    /**
     * @param mailSubject String
     * @return boolean
     */
    public boolean setSubject(String mailSubject) {
        logger.info("设置邮件主题！");
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        }
        catch (Exception e) {
            System.err.println("设置邮件主题发生错误！");
            return false;
        }
    }
    
    /**
     * @param mailBody String
     */
    public boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=gb2312>" + mailBody,
                "text/html;charset=GB2312");
            mp.addBodyPart(bp);
            
            return true;
        }
        catch (Exception e) {
            System.err.println("设置邮件正文时发生错误！" + e);
            return false;
        }
    }
    
    /**
     * @param filename String
     */
    public boolean addFileAffix(String filename) {
        
        logger.info("增加邮件附件：" + filename);
        
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fileds));
            bp.setFileName(fileds.getName());
            
            mp.addBodyPart(bp);
            
            return true;
        }
        catch (Exception e) {
            System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
            return false;
        }
    }
    
    /**
     * @param from String
     */
    public boolean setFrom(String from) {
        logger.info("设置发信人！");
        try {
            // 设置发信人
            mimeMsg.setFrom(new InternetAddress(from));
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    /**
     * @param to String
     */
    public boolean setTo(String to) {
        if (to == null) {
            return false;
        }
        
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            return true;
        }
        catch (Exception e) {
            return false;
        }
        
    }
    
    /**
     * @param copyto String
     */
    public boolean setCopyTo(String copyto) {
        if (copyto == null) {
            return false;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.CC, (Address[])InternetAddress.parse(copyto));
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public boolean sendout() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            logger.info("正在发送邮件....");
            
            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect((String)props.get("mail.smtp.host"), username, password);
            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
            
            logger.info("发送邮件成功！");
            transport.close();
            
            return true;
        }
        catch (Exception e) {
            
            System.err.println("邮件发送失败！" + e);
            return false;
        }
        
    }
    
}