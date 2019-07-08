package cn.slycmiaoxi.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.controller.WebsocketController;

/**
 * <p>
 * topic监听器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-06
 */
@Component("topicReceiverlistener")
public class TopicReceiverListener implements MessageListener {
    protected static final Logger logger = Logger.getLogger(TopicReceiverListener.class);
    
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            String jsonStr = textMessage.getText();
            if (jsonStr != null) {
                // 推送给前台
                WebsocketController.broadcast(Constants.PUSH_MSG, jsonStr);
            }
        }
        catch (JMSException e) {
            logger.error("[Message]:receive message occured an exception", e);
        }
    }
    
}
