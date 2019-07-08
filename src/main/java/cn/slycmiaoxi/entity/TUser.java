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
@TableName("t_user")
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    @TableField("nick_name")
    private String nickName;
    @TableField("e_mail")
    private String eMail;
    @TableField("user_pwd")
    private String userPwd;
    @TableField("user_state")
    private Integer userState;
    @TableField("user_real_name")
    private String userRealName;
    @TableField("user_personality")
    private String userPersonality;
    @TableField("user_on_time")
    private Date userOnTime;
    @TableField("user_leave_time")
    private Date userLeaveTime;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
    @TableField("user_headimg")
    private String userHeadimg;
    @TableField("user_phone")
    private String userPhone;
    @TableField("e_code")
    private String eCode;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserPersonality() {
        return userPersonality;
    }

    public void setUserPersonality(String userPersonality) {
        this.userPersonality = userPersonality;
    }

    public Date getUserOnTime() {
        return userOnTime;
    }

    public void setUserOnTime(Date userOnTime) {
        this.userOnTime = userOnTime;
    }

    public Date getUserLeaveTime() {
        return userLeaveTime;
    }

    public void setUserLeaveTime(Date userLeaveTime) {
        this.userLeaveTime = userLeaveTime;
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

    public String getUserHeadimg() {
        return userHeadimg;
    }

    public void setUserHeadimg(String userHeadimg) {
        this.userHeadimg = userHeadimg;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "TUser{" +
        "userId=" + userId +
        ", nickName=" + nickName +
        ", eMail=" + eMail +
        ", userPwd=" + userPwd +
        ", userState=" + userState +
        ", userRealName=" + userRealName +
        ", userPersonality=" + userPersonality +
        ", userOnTime=" + userOnTime +
        ", userLeaveTime=" + userLeaveTime +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", userHeadimg=" + userHeadimg +
        ", userPhone=" + userPhone +
        ", eCode=" + eCode +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
