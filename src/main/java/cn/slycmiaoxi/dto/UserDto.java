package cn.slycmiaoxi.dto;

import java.util.Date;

/**
 * <p>
 * 用户dto
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-24
 */
public class UserDto {
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 用户名
     */
    private String nickName;
    
    /**
     * email地址
     */
    private String eMail;
    
    /**
     * 密码
     */
    private String userPwd;
    
    /**
     * 用户状态
     */
    private Integer userState;
    
    /**
     * 用户真实姓名
     */
    private String userRealName;
    
    /**
     * 用户个性签名
     */
    private String userPersonality;
    
    /**
     * 用户头像
     */
    private String userHeadimg;
    
    /**
     * 联系电话
     */
    private String userPhone;
    
    /**
     * 验证码
     */
    private String eCode;
    
    /**
     * 创建日期
     */
    private Date gmtCreate;
    
    public Date getGmtCreate() {
        return gmtCreate;
    }
    
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
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
}
