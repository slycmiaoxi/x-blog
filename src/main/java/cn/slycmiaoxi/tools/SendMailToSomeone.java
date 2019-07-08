package cn.slycmiaoxi.tools;

/**
 * <p>
 * 发送邮件
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
public class SendMailToSomeone {
    
    /**
     * �����ʼ�
     * 
     * @param title 邮件标题
     * @param mailbody 邮件内容【一个网页，表格ok�?
     * @param sendTo 发�?�给�? xx@qq.com
     * @param from 从哪些发�? admin@sohu.com
     * @param passwd 密码
     * @param sendStmp 发�?�邮件的smtp smtp.sohu.com [smtp.163.com] [smtp.sina.com]
     */
    public void send(String name, String title, String mailbody, String sendTo, String from, String passwd,
        String sendStmp) {
        
        // 指明让那个smtp转发(搜狐)
        MysendMail themail = new MysendMail(sendStmp);
        
        // 校验身份
        themail.setNeedAuth(true);
        
        // 邮件标题
        if (themail.setSubject(title) == false)
            return;
        // 将要发�?�的 内容放入邮件�?
        if (themail.setBody(mailbody) == false)
            return;
        
        // 发�?�到哪里
        if (themail.setTo(sendTo) == false)
            return;
        
        // 谁发送的
        if (themail.setFrom(from) == false)
            return;
        
        // if(themail.addFileAffix("e:\\butterfly.gif") == false) return;
        
        // if()
        // 将在sohu网站上的邮件用户名和 密码 放入邮件�?
        themail.setNamePass(name, passwd);
        
        // 发�??
        if (themail.sendout() == false)
            return;
    }
}
