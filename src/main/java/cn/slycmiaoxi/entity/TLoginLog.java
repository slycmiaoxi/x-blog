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
 * @since 2019-06-26
 */
@TableName("t_login_log")
public class TLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ip_address")
    private String ipAddress;
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    @TableField("visitor_ip")
    private String visitorIp;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
    @TableField("request_url")
    private String requestUrl;
    @TableField("request_type")
    private String requestType;
    @TableField("user_name")
    private String userName;
    @TableField("logger_name")
    private String loggerName;
    @TableField("logger_type")
    private String loggerType;
    @TableField("request_time")
    private long requestTime;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getVisitorIp() {
        return visitorIp;
    }

    public void setVisitorIp(String visitorIp) {
        this.visitorIp = visitorIp;
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

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerType() {
        return loggerType;
    }

    public void setLoggerType(String loggerType) {
        this.loggerType = loggerType;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "TLoginLog{" +
        "ipAddress=" + ipAddress +
        ", pkId=" + pkId +
        ", visitorIp=" + visitorIp +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", requestUrl=" + requestUrl +
        ", requestType=" + requestType +
        ", userName=" + userName +
        ", loggerName=" + loggerName +
        ", loggerType=" + loggerType +
        ", requestTime=" + requestTime +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
