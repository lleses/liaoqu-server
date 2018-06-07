package cn.dlj.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.dlj.app.entity.GroupMessage;
import cn.dlj.app.entity.MessageList;
import cn.dlj.app.service.GroupMessageService;
import cn.dlj.app.service.MessageListService;
import cn.dlj.utils.Config;
import cn.dlj.utils.FileUtils;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

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
	/** 录音保存地址 */
	public static final String UPLOAD_PATH = Config.get("upload.path");

	/**
	 * 加载新的聊天记录
	 */
	@RequestMapping("loadNewMsg")
	@ResponseBody
	public String loadNewMsg(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer groupId = ParamUtils.getInt(request, "groupId");
		List<GroupMessage> list = groupMsgService.findByGroupIdAndUserId(groupId, userId);
		for (GroupMessage groupMessage : list) {
			groupMsgService.update(groupMessage.getId(), 2);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		map.put("data", list);

		MessageList msgList = msgListService.findByUserIdAndGroupId(userId, groupId);
		if (msgList != null && msgList.getNum() != 0) {
			msgList.setLastTime(new Date());
			msgList.setNum(0);
			msgListService.updateByUserIdAndGroupId(msgList);
		}
		return StringUtils.json(map);
	}

	/**
	 * 发送群组聊天信息
	 */
	@RequestMapping("sendMsg")
	@ResponseBody
	public String sendMsg(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer groupId = ParamUtils.getInt(request, "groupId");
		String content = ParamUtils.getStr(request, "content");
		Integer contentType = 1;//内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 )
		Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);
		//处理发送群组文本内容
		groupMsgService.handleSendGroupText(groupId, userId, content, addTime, contentType);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 发送群组录音
	 */
	@RequestMapping("sendRecord")
	@ResponseBody
	public String sendRecord(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (file.isEmpty()) {
			map.put("succ", "-1");
			return StringUtils.json(map);
		}

		String filePath = null;
		try {
			InputStream fis = file.getInputStream();
			filePath = "groupMsg/record/" + IdUtils.id32() + ".amr";
			File outFile = new File(UPLOAD_PATH + filePath);
			FileUtils.copyFile(fis, outFile);

			Integer userId = ParamUtils.getInt(request, "userId");
			Integer groupId = ParamUtils.getInt(request, "groupId");
			Integer contentType = 3;//内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 )
			Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);
			Integer duration = ParamUtils.getInt(request, "duration");//录音时长

			groupMsgService.handleSendGroupRecord(groupId, userId, addTime, contentType, filePath, duration);
			map.put("succ", "1");
			map.put("filePath", filePath);
		} catch (IOException e) {
			map.put("succ", "-1");
			e.printStackTrace();
		}

		return StringUtils.json(map);
	}

}
