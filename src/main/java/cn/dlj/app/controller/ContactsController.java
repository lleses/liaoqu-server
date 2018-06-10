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

import cn.dlj.app.entity.FriendRequests;
import cn.dlj.app.entity.MessageList;
import cn.dlj.app.entity.User;
import cn.dlj.app.entity.UserFriendRelation;
import cn.dlj.app.service.FriendRequestsService;
import cn.dlj.app.service.MessageListService;
import cn.dlj.app.service.UserService;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.Tool;

/**
 * 通讯录
 * 
 * @author 余狄龙
 * @date 2018年5月19日
 */
@RequestMapping("app/contacts")
@Controller
public class ContactsController {

	@Autowired
	private UserService userService;
	@Autowired
	private FriendRequestsService friendRequestsService;
	@Autowired
	private MessageListService msgListService;

	/**
	 * 搜索好友
	 */
	@RequestMapping("searchFriend")
	@ResponseBody
	public String searchFriend(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String content = ParamUtils.getStr(request, "content");//账号or手机号码
		Integer userId = ParamUtils.getInt(request, "userId");
		List<User> list = userService.getByUsernameOrPhone(content, userId);
		if (list == null || list.isEmpty()) {
			map.put("succ", "-1");
			return StringUtils.json(map);
		}
		map.put("succ", "1");
		map.put("data", list);
		return StringUtils.json(map);
	}

	/**
	 * 好友申请
	 */
	@RequestMapping("friendRequests")
	@ResponseBody
	public String friendRequests(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer friendId = ParamUtils.getInt(request, "friendId");
		FriendRequests friendRequests = friendRequestsService.getByUserIdAndFriendId(friendId, userId);
		if (userId != null && friendId != null && friendRequests == null) {
			friendRequests = new FriendRequests();
			friendRequests.setUserId(friendId);
			friendRequests.setFriendId(userId);
			friendRequests.setStatus(0);
			friendRequests.setAddTime(new Date());
			friendRequestsService.add(friendRequests);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 好友申请列表
	 */
	@RequestMapping("friendRequestsList")
	@ResponseBody
	public String friendRequestsList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = ParamUtils.getInt(request, "userId");
		List<FriendRequests> list = friendRequestsService.getByUserId(userId);
		if (list.isEmpty()) {
			map.put("succ", "-1");
			return StringUtils.json(map);
		}
		map.put("succ", "1");
		map.put("data", list);
		return StringUtils.json(map);
	}

	/**
	 * 添加好友
	 */
	@RequestMapping("addFriend")
	@ResponseBody
	public String addFriend(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer friendId = ParamUtils.getInt(request, "friendId");
		Integer friendRequestsId = ParamUtils.getInt(request, "friendRequestsId");
		friendRequestsService.update(friendRequestsId, 1);

		UserFriendRelation userFriendRelation = friendRequestsService.getUserFriendRelation(userId, friendId);
		if (userFriendRelation == null) {
			userFriendRelation = new UserFriendRelation();
			userFriendRelation.setUserId(userId);
			userFriendRelation.setFriendId(friendId);
			friendRequestsService.addUserFriend(userFriendRelation);
		}
		UserFriendRelation userFriendRelation2 = friendRequestsService.getUserFriendRelation(friendId, userId);
		if (userFriendRelation2 == null) {
			userFriendRelation2 = new UserFriendRelation();
			userFriendRelation2.setUserId(friendId);
			userFriendRelation2.setFriendId(userId);
			friendRequestsService.addUserFriend(userFriendRelation2);
		}

		//同意好友申请后，在通知页显示好友

		//我发出的
		MessageList msgList = msgListService.findByUserIdAndFriendId(userId, friendId);
		if (msgList == null) {
			msgList = new MessageList();
			msgList.setUserId(userId);
			msgList.setFriendId(friendId);
			msgList.setContent("你已添加了该好友");
			msgList.setLastTime(new Date());
			msgList.setNum(0);
			msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgList.setType(1);
			msgListService.add(msgList);
		}

		//好友收到的
		MessageList msgList2 = msgListService.findByUserIdAndFriendId(friendId, userId);
		if (msgList2 == null) {
			msgList2 = new MessageList();
			msgList2.setUserId(friendId);
			msgList2.setFriendId(userId);
			msgList2.setContent("我通过了你的朋友验证请求");
			msgList2.setLastTime(new Date());
			msgList2.setNum(1);
			msgList2.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgList2.setType(1);
			msgListService.add(msgList2);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 好友列表
	 */
	@RequestMapping("friendList")
	@ResponseBody
	public String friendList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = ParamUtils.getInt(request, "userId");
		List<User> friends = userService.getFriends(userId);
		if (friends.isEmpty()) {
			map.put("succ", "-1");
			return StringUtils.json(map);
		}
		map.put("succ", "1");
		map.put("data", friends);

		//查询统计总数
		int total = 0;
		for (User friend : friends) {
			MessageList msgList = msgListService.findByUserIdAndFriendId(userId, friend.getId());
			if (msgList != null) {
				total = total + msgList.getNum();
			}
		}
		map.put("total", total);

		return StringUtils.json(map);
	}

}
