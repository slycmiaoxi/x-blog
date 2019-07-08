package cn.slycmiaoxi.dto;

import java.util.Date;

/**
 * <p>
 * 帖子dto
 * </p>
 * 
 * @author slycmiaoxi
 * @since 2019-06-29
 */
public class ArticleDto {
    
    /**
     * 帖子id
     */
    private String blogId;
    
    /**
     * 帖子标题
     */
    private String blogTitle;
    
    /**
     * 帖子内容
     */
    private String blogContent;
    
    /**
     * 帖子标签
     */
    private String blogLable;
    
    /**
     * 发帖ip地址
     */
    private String authorIp;
    
    /**
     * 发帖人用户id
     */
    private String userId;
    
    /**
     * 发帖人用户名
     */
    private String nickName;
    
    /**
     * 发帖人头像地址
     */
    private String userHeadimg;
    
    /**
     * 帖子索引
     */
    private String blogItem;
    
    /**
     * 发帖人头像地址
     */
    private String imgUrl;
    
    /**
     * 帖子状态 {@link cn.slycmiaoxi.enumerate.BlogStatusEnum}
     */
    private Integer blogState;
    
    /**
     * 访问量
     */
    private Integer visitorHasread;
    
    /**
     * 评论数
     */
    private long commentAmounts;
    
    /**
     * 创建日期
     */
    private Date gmtCreate;
    
    public long getCommentAmounts() {
        return commentAmounts;
    }
    
    public void setCommentAmounts(long commentAmounts) {
        this.commentAmounts = commentAmounts;
    }
    
    public Integer getVisitorHasread() {
        return visitorHasread;
    }
    
    public void setVisitorHasread(Integer visitorHasread) {
        this.visitorHasread = visitorHasread;
    }
    
    public String getBlogTitle() {
        return blogTitle;
    }
    
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
    
    public String getBlogContent() {
        return blogContent;
    }
    
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
    
    public String getBlogLable() {
        return blogLable;
    }
    
    public void setBlogLable(String blogLable) {
        this.blogLable = blogLable;
    }
    
    public String getAuthorIp() {
        return authorIp;
    }
    
    public void setAuthorIp(String authorIp) {
        this.authorIp = authorIp;
    }
    
    public String getBlogId() {
        return blogId;
    }
    
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getUserHeadimg() {
        return userHeadimg;
    }
    
    public void setUserHeadimg(String userHeadimg) {
        this.userHeadimg = userHeadimg;
    }
    
    public String getBlogItem() {
        return blogItem;
    }
    
    public void setBlogItem(String blogItem) {
        this.blogItem = blogItem;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public Integer getBlogState() {
        return blogState;
    }
    
    public void setBlogState(Integer blogState) {
        this.blogState = blogState;
    }
    
    public Date getGmtCreate() {
        return gmtCreate;
    }
    
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
