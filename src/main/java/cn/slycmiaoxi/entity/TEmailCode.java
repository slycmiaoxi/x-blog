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
 * @since 2019-06-25
 */
@TableName("t_email_code")
public class TEmailCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    @TableField("email_address")
    private String emailAddress;
    @TableField("email_code")
    private String emailCode;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
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
        return "TEmailCode{" +
        "pkId=" + pkId +
        ", emailAddress=" + emailAddress +
        ", emailCode=" + emailCode +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
