package cn.slycmiaoxi.common;

/**
 * <p>
 * 常量池
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public class Constants {
    
    /**
     * 盐值
     */
    public static final String SALT = "slycmiaoxi";
    
    /**
     * 登录时，用户信息保存的session的key
     */
    public static final String SESSION_USER = "currentUser";
    
    /**
     * 系统默认每页显示最大记录数
     */
    public static final int DEFAULT_EVERY_PAGE = 2;
    
    /**
     * 强制踢出的会话key
     */
    public static final String SESSION_FORCE_LOGOUT_KEY = "session.force.logout";
    
    /**
     * 同一账号并发登陆最大数seesion（必须与ehcache.xml中相应缓存声明的name保持一致)
     */
    public static final String LOGIN_SESSION = "shiro-kickout-session";

    
    /**
     * 首页热门贴限制最大显示个数
     */
    public static final int HOT_BLOG_COUNT = 300;
    
    /**
     * es检索文章的类型名称
     */
    public static final String BLOG_SEARCH = "blogSearch";
    
    /**
     * 用户第一次注册成功默认的头像地址
     */
    public static final String DEFAULT_HEAD_IMG = "http://localhost:8080/x-blog/images/none.gif";
    
    /**
     * 项目根路径
     */
    public static final String UPLOAD_PATH = "http://localhost:8080/x-blog";

    /**
     * 项目评论首页路径
     */
    public static final String BLOG_SEARCH_URL = "http://localhost:8080/x-blog/core/v1/tCommentInfo/show/1/";
    
    /**
     * fastDfs图片映射地址
     */
    public static final String BASE_URL = "http://fastdfs.luyiyi.cn//";

    /**
     *
     */
    public static final String MQ_TOPIC_DESTIONATION="mq.topic";
    /**
     *
     */
    public static final String MQ_QUEUE_DESTIONATION="mq.queue";
    /**
     *
     */
    public static final String PUSH_MSG="push";
}
