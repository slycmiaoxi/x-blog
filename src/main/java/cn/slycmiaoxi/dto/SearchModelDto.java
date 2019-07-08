package cn.slycmiaoxi.dto;

import java.util.Date;

/**
 * <p>
 * 帖子搜索dto
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-29
 */
public class SearchModelDto {
    
    /**
     * 帖子内容
     */
    private String blogContent;
    
    /**
     * 帖子标题
     */
    private String blogTitle;
    
    /**
     * 创建日期
     */
    private Date gmtCreate;
    
    /**
     * 帖子索引
     */
    private String blogItem;
    
    public String getBlogContent() {
        return blogContent;
    }
    
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
    
    public String getBlogTitle() {
        return blogTitle;
    }
    
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
    
    public Date getGmtCreate() {
        return gmtCreate;
    }
    
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    
    public String getBlogItem() {
        return blogItem;
    }
    
    public void setBlogItem(String blogItem) {
        this.blogItem = blogItem;
    }
}
