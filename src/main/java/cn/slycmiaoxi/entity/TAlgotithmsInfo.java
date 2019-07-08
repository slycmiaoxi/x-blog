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
@TableName("t_algotithms_info")
public class TAlgotithmsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("algotithms_info_id")
    private Integer algotithmsInfoId;
    @TableField("algotithms_type_id")
    private Integer algotithmsTypeId;
    @TableField("algotithms_type_name")
    private String algotithmsTypeName;
    @TableField("algotithms_title")
    private String algotithmsTitle;
    @TableField("algotithms_description")
    private String algotithmsDescription;
    @TableField("input_expression")
    private String inputExpression;
    @TableField("output_expression")
    private String outputExpression;
    @TableField("sample_input")
    private String sampleInput;
    @TableField("sample_out")
    private String sampleOut;
    private String author;
    @TableField("func_name")
    private String funcName;
    @TableField("func_index")
    private Long funcIndex;
    @TableField("algotithms_code")
    private String algotithmsCode;
    @TableField("controller_code")
    private String controllerCode;
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


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAlgotithmsInfoId() {
        return algotithmsInfoId;
    }

    public void setAlgotithmsInfoId(Integer algotithmsInfoId) {
        this.algotithmsInfoId = algotithmsInfoId;
    }

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

    public String getAlgotithmsTitle() {
        return algotithmsTitle;
    }

    public void setAlgotithmsTitle(String algotithmsTitle) {
        this.algotithmsTitle = algotithmsTitle;
    }

    public String getAlgotithmsDescription() {
        return algotithmsDescription;
    }

    public void setAlgotithmsDescription(String algotithmsDescription) {
        this.algotithmsDescription = algotithmsDescription;
    }

    public String getInputExpression() {
        return inputExpression;
    }

    public void setInputExpression(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    public String getOutputExpression() {
        return outputExpression;
    }

    public void setOutputExpression(String outputExpression) {
        this.outputExpression = outputExpression;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOut() {
        return sampleOut;
    }

    public void setSampleOut(String sampleOut) {
        this.sampleOut = sampleOut;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public Long getFuncIndex() {
        return funcIndex;
    }

    public void setFuncIndex(Long funcIndex) {
        this.funcIndex = funcIndex;
    }

    public String getAlgotithmsCode() {
        return algotithmsCode;
    }

    public void setAlgotithmsCode(String algotithmsCode) {
        this.algotithmsCode = algotithmsCode;
    }

    public String getControllerCode() {
        return controllerCode;
    }

    public void setControllerCode(String controllerCode) {
        this.controllerCode = controllerCode;
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
}
