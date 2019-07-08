package cn.slycmiaoxi.dto;

import java.util.Date;

/**
 * <p>
 * 评论dto
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-29
 */
public class CommentDto {
    
    /**
     * 帖子id
     */
    private String blogId;
    
    /**
     * 评论id
     */
    private String commentId;
    
    /**
     * 评论内容
     */
    private String blogComment;
    
    /**
     * 评论人用户id
     */
    private String userId;
    
    /**
     * 评论人用户名
     */
    private String nickName;
    
    /**
     * 发帖人头像地址
     */
    private String imgUrl;
    
    /**
     * 创建日期
     */
    private Date gmtCreate;
    
    public String getBlogId() {
        return blogId;
    }
    
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
    
    public String getCommentId() {
        return commentId;
    }
    
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    
    public String getBlogComment() {
        return blogComment;
    }
    
    public void setBlogComment(String blogComment) {
        this.blogComment = blogComment;
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
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public Date getGmtCreate() {
        return gmtCreate;
    }
    
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
