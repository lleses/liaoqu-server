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

import cn.dlj.app.entity.GroupMessage;
import cn.dlj.app.entity.Message;
import cn.dlj.app.entity.MessageList;
import cn.dlj.app.service.GroupMessageService;
import cn.dlj.app.service.MessageListService;
import cn.dlj.app.service.MessageService;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.Tool;

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
	private MessageService msgService;
	@Autowired
	private MessageListService msgListService;
	@Autowired
	private GroupMessageService groupMessageService;

	/**
	 * 加载新的好友聊天记录
	 */
	@RequestMapping("loadFriendNewMsg")
	@ResponseBody
	public String loadFriendNewMsg(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer friendId = ParamUtils.getInt(request, "friendId");
		List<Message> list = msgService.findByUserIdAndFriendIdAndStatus(userId, friendId, 1);
		for (Message message : list) {
			msgService.update(message.getId(), 2);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		map.put("data", list);

		MessageList msgList = msgListService.findByUserIdAndFriendId(userId, friendId);
		if (msgList != null) {
			msgList.setLastTime(new Date());
			msgList.setNum(0);
			msgListService.update(msgList);
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
		Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);

		//好友收到的信息
		Message message = new Message();
		message.setUserId(friendId);
		message.setFriendId(userId);
		message.setContent(content);
		message.setType(2);//接收类型(1:我发出的 2:我收到的 )
		message.setAddTime(addTime);
		message.setContentType(contentType);
		message.setStatus(1);
		msgService.add(message);

		//我发出的
		MessageList msgList = msgListService.findByUserIdAndFriendId(userId, friendId);
		if (msgList == null) {
			msgList = new MessageList();
			msgList.setUserId(userId);
			msgList.setFriendId(friendId);
			msgList.setContent(content);
			msgList.setLastTime(addTime);
			msgList.setNum(0);
			msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgListService.add(msgList);
		} else {
			msgList.setContent(content);
			msgList.setLastTime(addTime);
			msgList.setNum(0);
			msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgListService.update(msgList);
		}

		//好友收到的
		MessageList msgList2 = msgListService.findByUserIdAndFriendId(friendId, userId);
		if (msgList2 == null) {
			msgList2 = new MessageList();
			msgList2.setUserId(friendId);
			msgList2.setFriendId(userId);
			msgList2.setContent(content);
			msgList2.setLastTime(addTime);
			msgList2.setNum(1);
			msgList2.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgListService.add(msgList2);
		} else {
			msgList2.setContent(content);
			msgList2.setLastTime(addTime);
			msgList2.setNum(msgList2.getNum() + 1);
			msgList2.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgListService.update(msgList2);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 好友消息列表
	 */
	@RequestMapping("loadFriendMsgList")
	@ResponseBody
	public String loadFriendMsgList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = ParamUtils.getInt(request, "userId");
		List<MessageList> list = msgListService.findByUserId(userId);
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

	//TODO
	/**
	 * 发送群组聊天信息
	 */
	@RequestMapping("send_group_msg")
	@ResponseBody
	public String sendGroupMsg(HttpServletRequest request) {

		Integer userId = ParamUtils.getInt(request, "userId");
		Integer groupId = ParamUtils.getInt(request, "groupId");
		String content = ParamUtils.getStr(request, "content");
		Integer contentType = 1;//内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 )
		Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);

		//好友收到的信息
		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setUserId(userId);
		groupMessage.setGroupId(groupId);
		groupMessage.setContent(content);
		groupMessage.setAddTime(addTime);
		groupMessage.setContentType(contentType);
		groupMessage.setStatus(1);
		groupMessageService.add(groupMessage);

//		//我发出的
//		MessageList msgList = messageService.getMsgList(userId, friendId);
//		if (msgList == null) {
//			msgList = new MessageList();
//			msgList.setUserId(userId);
//			msgList.setFriendId(friendId);
//			msgList.setContent(content);
//			msgList.setLastTime(addTime);
//			msgList.setNum(0);
//			msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
//			messageService.addList(msgList);
//		} else {
//			msgList.setContent(content);
//			msgList.setLastTime(addTime);
//			msgList.setNum(0);
//			msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
//			messageService.updateList(msgList);
//		}
//
//		//好友收到的
//		MessageList msgList2 = messageService.getMsgList(friendId, userId);
//		if (msgList2 == null) {
//			msgList2 = new MessageList();
//			msgList2.setUserId(friendId);
//			msgList2.setFriendId(userId);
//			msgList2.setContent(content);
//			msgList2.setLastTime(addTime);
//			msgList2.setNum(1);
//			msgList2.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
//			messageService.addList(msgList2);
//		} else {
//			msgList2.setContent(content);
//			msgList2.setLastTime(addTime);
//			msgList2.setNum(msgList2.getNum() + 1);
//			msgList2.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
//			messageService.updateList(msgList2);
//		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}
}
