package cn.slycmiaoxi.controller;

import javax.annotation.Resource;

import cn.slycmiaoxi.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.core.activemq.TopicSender;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/core/v1/activemq")
public class ActivemqController {


	@Resource
	TopicSender topicSender;



	/**
	 * 发送消息到主题
	 * Topic主题 ：放入一个消息，所有订阅者都会收到
	 * 这个是主题目的地是一对多的
	 * @param msg 推送的消息
	 * @return R
	 */
	@RequestMapping("topicSender")
	@ResponseBody
	public R topicSender(@RequestParam("msg")String msg){
		try {
			topicSender.send(Constants.MQ_TOPIC_DESTIONATION, msg);
			return R.ok();
		} catch (Exception e) {
			return R.fail();
		}
	}

	/**
	 * 返回首页信息页
	 *
	 * @return 首页信息页
	 * @author slycmiaoxi
	 * @since 2019-06-28
	 */
	@RequestMapping("/show")
	public ModelAndView showIndex() {
		return new ModelAndView("sys/sysPushInfo");
	}
}

