package cn.dlj.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Message;
import cn.dlj.app.entity.MessageList;
import cn.dlj.app.service.MessageService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

/**
 * 消息
 * 
 * @author 余狄龙
 * @date 2018年5月19日
 */
@RequestMapping("app/msg")
@Controller
public class MsgController {

	@Autowired
	private MessageService messageService;

	/**
	 * 获取好友信息记录
	 */
	@RequestMapping("getFriendMsg")
	@ResponseBody
	public String getFriendMsg(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer friendId = ParamUtils.getInt(request, "friendId");
		List<Message> list = messageService.getMsg(userId, friendId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		map.put("data", list);

		MessageList msgList = messageService.getMsgList(userId, friendId);
		if (msgList != null) {
			msgList.setLastTime(new Date());
			msgList.setNum(0);
			messageService.updateList(msgList);
		}

		return StringUtils.json(map);
	}

	/**
	 * 给好友发信息
	 */
	@RequestMapping("sendMsg")
	@ResponseBody
	public String sendMsg(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer friendId = ParamUtils.getInt(request, "friendId");
		String content = ParamUtils.getStr(request, "content");
		Integer contentType = 1;//内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 )

		//我发出的信息
		Message message = new Message();
		message.setUserId(userId);
		message.setFriendId(friendId);
		message.setContent(content);
		message.setType(1);//接收类型(1:我发出的 2:我收到的 )
		message.setAddTime(new Date());
		message.setContentType(contentType);
		messageService.add(message);
		//我收到的信息
		Message message2 = new Message();
		message2.setUserId(friendId);
		message2.setFriendId(userId);
		message2.setContent(content);
		message2.setType(2);//接收类型(1:我发出的 2:我收到的 )
		message2.setAddTime(new Date());
		message2.setContentType(contentType);
		messageService.add(message2);

		//我发出的
		MessageList msgList = messageService.getMsgList(userId, friendId);
		if (msgList == null) {
			msgList = new MessageList();
			msgList.setUserId(userId);
			msgList.setFriendId(friendId);
			msgList.setContent(content);
			msgList.setLastTime(new Date());
			msgList.setNum(0);
			messageService.addList(msgList);
		} else {
			msgList.setContent(content);
			msgList.setLastTime(new Date());
			msgList.setNum(0);
			messageService.updateList(msgList);
		}

		//好友收到的
		MessageList msgList2 = messageService.getMsgList(friendId, userId);
		if (msgList2 == null) {
			msgList2 = new MessageList();
			msgList2.setUserId(friendId);
			msgList2.setFriendId(userId);
			msgList2.setContent(content);
			msgList2.setLastTime(new Date());
			msgList2.setNum(1);
			messageService.addList(msgList2);
		} else {
			msgList2.setContent(content);
			msgList2.setLastTime(new Date());
			msgList2.setNum(msgList2.getNum() + 1);
			messageService.updateList(msgList2);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 好友消息列表
	 */
	@RequestMapping("friendMsgList")
	@ResponseBody
	public String friendMsgList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = ParamUtils.getInt(request, "userId");
		List<MessageList> list = messageService.getMsgListByUserId(userId);
		if (list.isEmpty()) {
			map.put("succ", "-1");
			return StringUtils.json(map);
		}

		//查询统计总数
		int total = 0;
		for (MessageList msgList : list) {
			if (msgList != null) {
				total = total + msgList.getNum();
			}
		}

		map.put("succ", "1");
		map.put("data", list);
		map.put("total", total);
		return StringUtils.json(map);
	}

}
