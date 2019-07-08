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
@TableName("t_floor_info")
public class TFloorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "floor_id", type = IdType.AUTO)
    private Long floorId;
    @TableField("blog_id")
    private Long blogId;
    @TableField("comment_id")
    private Long commentId;
    @TableField("user_id")
    private Integer userId;
    @TableField("floor_comment")
    private String floorComment;
    @TableField("replyed_user_id")
    private Integer replyedUserId;
    @TableField("img_url")
    private String imgUrl;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFloorComment() {
        return floorComment;
    }

    public void setFloorComment(String floorComment) {
        this.floorComment = floorComment;
    }

    public Integer getReplyedUserId() {
        return replyedUserId;
    }

    public void setReplyedUserId(Integer replyedUserId) {
        this.replyedUserId = replyedUserId;
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
        return "TFloorInfo{" +
        "floorId=" + floorId +
        ", blogId=" + blogId +
        ", commentId=" + commentId +
        ", userId=" + userId +
        ", floorComment=" + floorComment +
        ", replyedUserId=" + replyedUserId +
        ", imgUrl=" + imgUrl +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
