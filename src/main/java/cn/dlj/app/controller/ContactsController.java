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
import cn.dlj.app.entity.User;
import cn.dlj.app.entity.UserFriendRelation;
import cn.dlj.app.service.FriendRequestsService;
import cn.dlj.app.service.UserService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

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
		FriendRequests friendRequests = friendRequestsService.getByUserIdAndFriendId(userId, friendId);
		if (userId != null && friendId != null && friendRequests == null) {
			friendRequests = new FriendRequests();
			friendRequests.setUserId(userId);
			friendRequests.setFriendId(friendId);
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
		return StringUtils.json(map);
	}

}
