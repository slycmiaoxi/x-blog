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
@TableName("t_critiquerecord_info")
public class TCritiquerecordInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;
    @TableField("blog_id")
    private Long blogId;
    @TableField("ip_address")
    private String ipAddress;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
        return "TCritiquerecordInfo{" +
        "pkId=" + pkId +
        ", blogId=" + blogId +
        ", ipAddress=" + ipAddress +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
