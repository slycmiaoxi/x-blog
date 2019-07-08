package cn.slycmiaoxi.entity;

import java.util.Date;
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
@TableName("t_role_permisssion")
public class TRolePermisssion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private Integer roleId;
    @TableField("permission_id")
    private Integer permissionId;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
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
        return "TRolePermisssion{" +
        "roleId=" + roleId +
        ", permissionId=" + permissionId +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
