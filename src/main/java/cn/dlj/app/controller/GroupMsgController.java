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
import cn.dlj.app.entity.GroupUserRelation;
import cn.dlj.app.entity.MessageList;
import cn.dlj.app.service.GroupMessageService;
import cn.dlj.app.service.GroupUserRelationService;
import cn.dlj.app.service.MessageListService;
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
@RequestMapping("app/groupMsg")
@Controller
public class GroupMsgController {

	@Autowired
	private MessageListService msgListService;
	@Autowired
	private GroupMessageService groupMsgService;
	@Autowired
	private GroupUserRelationService groupUserRelationService;

	/**
	 * 发送群组聊天信息
	 */
	@RequestMapping("send_msg")
	@ResponseBody
	public String sendMsg(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer groupId = ParamUtils.getInt(request, "groupId");
		String content = ParamUtils.getStr(request, "content");
		Integer contentType = 1;//内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 )
		Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);

		//我发出的信息
		GroupMessage groupMsg = new GroupMessage();
		groupMsg.setUserId(userId);
		groupMsg.setGroupId(groupId);
		groupMsg.setContent(content);
		groupMsg.setAddTime(addTime);
		groupMsg.setContentType(contentType);
		groupMsg.setStatus(1);
		groupMsgService.add(groupMsg);

		//--------------创建通知--------------
		//我发出的通知
		MessageList msgList = msgListService.findByUserIdAndGroupId(userId, groupId);
		if (msgList == null) {
			msgList = new MessageList();
			msgList.setUserId(userId);
			msgList.setGroupId(groupId);
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
			msgListService.updateByUserIdAndGroupId(msgList);
		}
		//群用户收到的通知
		List<GroupUserRelation> list = groupUserRelationService.findByGroupId(groupId);
		for (GroupUserRelation groupUserRelation : list) {
			if (userId != groupUserRelation.getUserId()) {
				MessageList msgList2 = msgListService.findByUserIdAndGroupId(groupUserRelation.getUserId(), groupId);
				if (msgList2 == null) {
					msgList2 = new MessageList();
					msgList2.setUserId(groupUserRelation.getUserId());
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
					msgListService.updateByUserIdAndGroupId(msgList2);
				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 加载新的聊天记录
	 */
	@RequestMapping("loadNewMsg")
	@ResponseBody
	public String loadNewMsg(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer groupId = ParamUtils.getInt(request, "groupId");
		List<GroupMessage> list = groupMsgService.findByGroupIdAndUserId(groupId, userId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		map.put("data", list);

		MessageList msgList = msgListService.findByUserIdAndGroupId(userId, groupId);
		if (msgList != null) {
			msgList.setLastTime(new Date());
			msgList.setNum(0);
			msgListService.update(msgList);
		}
		return StringUtils.json(map);
	}
}
