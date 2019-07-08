package cn.slycmiaoxi.entity;

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
 * @since 2019-06-17
 */
@TableName("t_algotithms_type")
public class TAlgotithmsType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("algotithms_type_id")
    private Integer algotithmsTypeId;
    @TableField("algotithms_type_name")
    private String algotithmsTypeName;
    @TableField("add_time")
    private Date addTime;
    @TableField("add_userId")
    private Integer addUserid;
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_userId")
    private Integer updateUserid;
    @TableField("delete_time")
    private Date deleteTime;
    @TableField("delete_userId")
    private Integer deleteUserid;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public Integer getAlgotithmsTypeId() {
        return algotithmsTypeId;
    }

    public void setAlgotithmsTypeId(Integer algotithmsTypeId) {
        this.algotithmsTypeId = algotithmsTypeId;
    }

    public String getAlgotithmsTypeName() {
        return algotithmsTypeName;
    }

    public void setAlgotithmsTypeName(String algotithmsTypeName) {
        this.algotithmsTypeName = algotithmsTypeName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getAddUserid() {
        return addUserid;
    }

    public void setAddUserid(Integer addUserid) {
        this.addUserid = addUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDeleteUserid() {
        return deleteUserid;
    }

    public void setDeleteUserid(Integer deleteUserid) {
        this.deleteUserid = deleteUserid;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "TAlgotithmsType{" +
        "algotithmsTypeId=" + algotithmsTypeId +
        ", algotithmsTypeName=" + algotithmsTypeName +
        ", addTime=" + addTime +
        ", addUserid=" + addUserid +
        ", updateTime=" + updateTime +
        ", updateUserid=" + updateUserid +
        ", deleteTime=" + deleteTime +
        ", deleteUserid=" + deleteUserid +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
