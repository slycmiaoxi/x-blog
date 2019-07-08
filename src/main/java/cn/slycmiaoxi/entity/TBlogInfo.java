package cn.slycmiaoxi.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-01
 */
@TableName("t_blog_info")
public class TBlogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "blog_id", type = IdType.AUTO)
    private Long blogId;
    @TableField("blog_title")
    private String blogTitle;
    @TableField("blog_content")
    private String blogContent;
    @TableField("author_ip")
    private String authorIp;
    @TableField("visitor_hasread")
    private Integer visitorHasread;
    @TableField("blog_lable")
    private String blogLable;
    @TableField("user_id")
    private Integer userId;
    @TableField("blog_item")
    private String blogItem;
    @TableField("img_url")
    private String imgUrl;
    @TableField("blog_state")
    private Integer blogState;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
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

    public String getAuthorIp() {
        return authorIp;
    }

    public void setAuthorIp(String authorIp) {
        this.authorIp = authorIp;
    }

    public Integer getVisitorHasread() {
        return visitorHasread;
    }

    public void setVisitorHasread(Integer visitorHasread) {
        this.visitorHasread = visitorHasread;
    }

    public String getBlogLable() {
        return blogLable;
    }

    public void setBlogLable(String blogLable) {
        this.blogLable = blogLable;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "TBlogInfo{" +
        "blogId=" + blogId +
        ", blogTitle=" + blogTitle +
        ", blogContent=" + blogContent +
        ", authorIp=" + authorIp +
        ", visitorHasread=" + visitorHasread +
        ", blogLable=" + blogLable +
        ", userId=" + userId +
        ", blogItem=" + blogItem +
        ", imgUrl=" + imgUrl +
        ", blogState=" + blogState +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
