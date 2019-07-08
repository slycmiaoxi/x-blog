package cn.slycmiaoxi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 留言dto
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-03
 */
public class MessageDto implements Serializable {

    /**
     * 留言id
     */
    private String messageId;

    /**
     * 留言内容
     */
    private String messageContent;

    /**
     * 当前结点用户名
     */
    private String currentName;

    /**
     * 父节点用户名
     */
    private String parentName;

    /**
     * 父节点id
     */
    private String parentNode;

    /**
     * 添加日期
     */
    private String time;

    /**
     * 添加日期
     */
    private Date gmtCreate;

    /**
     * 该节点下的所有子节点集合
     */
    private List<MessageDto> child;

    public MessageDto(String messageId) {
        this.messageId = messageId;
    }

    public MessageDto() {

    }

    public String getMessageId() {
        return messageId;
    }
    
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
    public String getMessageContent() {
        return messageContent;
    }
    
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    
    public String getCurrentName() {
        return currentName;
    }
    
    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }
    
    public String getParentName() {
        return parentName;
    }
    
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
    public String getParentNode() {
        return parentNode;
    }
    
    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public List<MessageDto> getChild() {
        return child;
    }
    
    public void setChild(List<MessageDto> child) {
        this.child = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageDto that = (MessageDto) o;

        return messageId.equals(that.messageId);
    }

    @Override
    public int hashCode() {
        return messageId.hashCode();
    }
}
