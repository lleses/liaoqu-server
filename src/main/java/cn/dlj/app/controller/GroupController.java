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

import cn.dlj.app.entity.Group;
import cn.dlj.app.entity.GroupUserRelation;
import cn.dlj.app.service.GroupService;
import cn.dlj.app.service.GroupUserRelationService;
import cn.dlj.utils.Config;
import cn.dlj.utils.FileUtils;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/group")
@Controller
public class GroupController {

	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupUserRelationService groupUserRelationService;
	public static final String GROUP_IMG_UPLOAD_PATH = Config.get("group.img.upload.path");

	/**
	 * 创建群组
	 */
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String name = ParamUtils.getStr(request, "name");
		String introduction = ParamUtils.getStr(request, "introduction");
		String imgbasc64 = ParamUtils.getStr(request, "imgbasc64");
		Integer userId = ParamUtils.getInt(request, "userId");

		String headImg = "defaultGroupHeadImg.jpg";
		if (imgbasc64 != null && !"".equals(imgbasc64)) {
			headImg = FileUtils.imgbasc64(imgbasc64, GROUP_IMG_UPLOAD_PATH);//basc64转成图片文件
		}

		Group group = new Group();
		group.setName(name);
		group.setHeadImg(headImg);
		group.setIntroduction(introduction);
		Integer groupId = groupService.add(group);

		GroupUserRelation groupUserRelation = new GroupUserRelation();
		groupUserRelation.setGroupId(groupId);
		groupUserRelation.setUserId(userId);
		groupUserRelation.setAddTime(new Date());
		groupUserRelationService.add(groupUserRelation);

		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 加载群组信息
	 */
	@RequestMapping("find_group_by_id")
	@ResponseBody
	public String findGroupById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer groupId = ParamUtils.getInt(request, "groupId");
		Group group = groupService.findById(groupId);
		List<GroupUserRelation> list = groupUserRelationService.findByGroupId(groupId);

		map.put("succ", "1");
		map.put("group", group);
		map.put("list", list);
		return StringUtils.json(map);
	}

	/**
	 * 加载群组列表
	 */
	@RequestMapping("loadGroup")
	@ResponseBody
	public String loadGroup(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = ParamUtils.getInt(request, "userId");
		List<Group> list = groupService.findByUserId(userId);
		map.put("succ", "1");
		map.put("data", list);
		return StringUtils.json(map);
	}

	/**
	 * 邀请好友
	 */
	@RequestMapping("invite_friends")
	@ResponseBody
	public String inviteFriends(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		Integer groupId = ParamUtils.getInt(request, "groupId");
		String userIds = ParamUtils.getStr(request, "userIds");
		userIds = userIds.substring(0, userIds.length() - 1);

		String[] split = userIds.split(",");
		for (String fId : split) {
			Integer friendId = Integer.valueOf(fId);
			//检查是否存在
			GroupUserRelation group = groupUserRelationService.findByUserIdAndGroupId(friendId, groupId);
			if (group == null) {
				GroupUserRelation groupUserRelation = new GroupUserRelation();
				groupUserRelation.setGroupId(groupId);
				groupUserRelation.setUserId(friendId);
				groupUserRelation.setAddTime(new Date());
				groupUserRelationService.add(groupUserRelation);
			}
		}
		map.put("succ", "1");
		return StringUtils.json(map);
	}

}
