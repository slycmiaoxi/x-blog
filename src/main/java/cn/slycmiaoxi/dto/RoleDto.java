package cn.slycmiaoxi.dto;

import java.util.Date;

/**
 * <p>
 * 角色dto
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-24
 */
public class RoleDto {
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 用户名
     */
    private String nickName;
    
    /**
     * 创建日期
     */
    private Date gmtCreate;
    
    /**
     * 角色id
     */
    private String roleId;
    
    /**
     * 角色描述
     */
    private String roleDescription;
    
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
    
    public Date getGmtCreate() {
        return gmtCreate;
    }
    
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    
    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleDescription() {
        return roleDescription;
    }
    
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
