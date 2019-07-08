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
 * @since 2019-06-17
 */
@TableName("t_algotithms_type_info_rel")
public class TAlgotithmsTypeInfoRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    @TableField("algotithms_type_id")
    private Integer algotithmsTypeId;
    @TableField("algotithms_info_id")
    private Integer algotithmsInfoId;
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


    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getAlgotithmsTypeId() {
        return algotithmsTypeId;
    }

    public void setAlgotithmsTypeId(Integer algotithmsTypeId) {
        this.algotithmsTypeId = algotithmsTypeId;
    }

    public Integer getAlgotithmsInfoId() {
        return algotithmsInfoId;
    }

    public void setAlgotithmsInfoId(Integer algotithmsInfoId) {
        this.algotithmsInfoId = algotithmsInfoId;
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
        return "TAlgotithmsTypeInfoRel{" +
        "pkId=" + pkId +
        ", algotithmsTypeId=" + algotithmsTypeId +
        ", algotithmsInfoId=" + algotithmsInfoId +
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
