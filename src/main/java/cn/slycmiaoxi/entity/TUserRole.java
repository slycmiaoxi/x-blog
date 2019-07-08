package cn.slycmiaoxi.entity;

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
@TableName("t_user_role")
public class TUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Integer userId;
    @TableField("role_id")
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "TUserRole{" +
        "userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
