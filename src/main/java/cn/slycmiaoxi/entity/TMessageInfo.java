package cn.slycmiaoxi.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-04
 */
@TableName("t_message_info")
public class TMessageInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;
    
    @TableField("ip_address")
    private String ipAddress;
    
    @TableField("message_content")
    private String messageContent;
    
    @TableField("message_state")
    private Integer messageState;
    
    @TableField("parent_node")
    private Long parentNode;
    
    @TableField("user_id")
    private int userId;
    
    @TableField("message_praise")
    private Integer messagePraise;
    
    @TableField("gmt_create")
    private Date gmtCreate;
    
    @TableField("gmt_modified")
    private Date gmtModified;
    
    @TableField("delete_flag")
    private Integer deleteFlag;
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public Long getMessageId() {
        return messageId;
    }
    
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public String getMessageContent() {
        return messageContent;
    }
    
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    
    public Integer getMessageState() {
        return messageState;
    }
    
    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }
    
    public Long getParentNode() {
        return parentNode;
    }
    
    public void setParentNode(Long parentNode) {
        this.parentNode = parentNode;
    }
    
    public Integer getMessagePraise() {
        return messagePraise;
    }
    
    public void setMessagePraise(Integer messagePraise) {
        this.messagePraise = messagePraise;
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
        return "TMessageInfo{" + "messageId=" + messageId + ", ipAddress=" + ipAddress + ", messageContent="
            + messageContent + ", messageState=" + messageState + ", parentNode=" + parentNode + ", userId=" + userId
            + ", messagePraise=" + messagePraise + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified
            + ", deleteFlag=" + deleteFlag + "}";
    }
}
