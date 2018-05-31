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
import cn.dlj.utils.Config;
import cn.dlj.utils.FileUtils;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/group")
@Controller
public class GroupController {

	@Autowired
	private GroupService groupService;
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
		group.setHeadImg("groupImg/" + headImg);
		group.setIntroduction(introduction);
		Integer groupId = groupService.add(group);

		GroupUserRelation groupUserRelation = new GroupUserRelation();
		groupUserRelation.setGroupId(groupId);
		groupUserRelation.setUserId(userId);
		groupUserRelation.setAddTime(new Date());
		groupService.addGroupUser(groupUserRelation);

		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 创建群组
	 */
	@RequestMapping("loadGroup")
	@ResponseBody
	public String loadGroup(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = ParamUtils.getInt(request, "userId");
		List<Group> list = groupService.getGroupList(userId);
		map.put("succ", "1");
		map.put("data", list);
		return StringUtils.json(map);
	}

}
