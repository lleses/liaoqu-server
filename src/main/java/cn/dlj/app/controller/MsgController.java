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

import cn.dlj.app.entity.Message;
import cn.dlj.app.entity.MessageList;
import cn.dlj.app.service.MessageListService;
import cn.dlj.app.service.MessageService;
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
@RequestMapping("app/msg")
@Controller
public class MsgController {

	@Autowired
	private MessageService msgService;
	@Autowired
	private MessageListService msgListService;
	/** 录音保存地址 */
	public static final String UPLOAD_PATH = Config.get("upload.path");

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
		if (msgList != null && msgList.getNum() != 0) {
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
		//处理发送好友信息
		msgService.handleSendFriendText(userId, friendId, content, addTime, contentType);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 给好友发录音
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
			filePath = "msg/record/" + IdUtils.id32() + ".amr";
			File outFile = new File(UPLOAD_PATH + filePath);
			FileUtils.copyFile(fis, outFile);

			Integer userId = ParamUtils.getInt(request, "userId");
			Integer friendId = ParamUtils.getInt(request, "friendId");
			Integer contentType = 3;//内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 )
			Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);
			Integer duration = ParamUtils.getInt(request, "duration");//录音时长
			msgService.handleSendFriendRecord(userId, friendId, addTime, contentType, filePath, duration);
			map.put("succ", "1");
			map.put("filePath", filePath);
		} catch (IOException e) {
			map.put("succ", "-1");
			e.printStackTrace();
		}

		return StringUtils.json(map);
	}

	/**
	 * 给好友发图片
	 */
	@RequestMapping("sendPhoto")
	@ResponseBody
	public String sendPhoto(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (file.isEmpty()) {
			map.put("succ", "-1");
			return StringUtils.json(map);
		}

		String filePath = null;
		try {
			InputStream fis = file.getInputStream();
			filePath = "msg/photo/" + IdUtils.id32() + ".jpg";
			File outFile = new File(UPLOAD_PATH + filePath);
			FileUtils.copyFile(fis, outFile);

			Integer userId = ParamUtils.getInt(request, "userId");
			Integer friendId = ParamUtils.getInt(request, "friendId");
			Integer contentType = 2;//内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 )
			Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);
			msgService.handleSendFriendPhoto(userId, friendId, addTime, contentType, filePath);
			map.put("succ", "1");
			map.put("filePath", filePath);
		} catch (IOException e) {
			map.put("succ", "-1");
			e.printStackTrace();
		}

		return StringUtils.json(map);
	}

	/**
	 * 给好友发定位
	 */
	@RequestMapping("sendPosition")
	@ResponseBody
	public String sendPosition(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (file.isEmpty()) {
			map.put("succ", "-1");
			return StringUtils.json(map);
		}

		String filePath = null;
		try {
			InputStream fis = file.getInputStream();
			filePath = "msg/position/" + IdUtils.id32() + ".png";
			File outFile = new File(UPLOAD_PATH + filePath);
			FileUtils.copyFile(fis, outFile);

			Integer userId = ParamUtils.getInt(request, "userId");
			Integer friendId = ParamUtils.getInt(request, "friendId");
			String positionX = ParamUtils.getStr(request, "positionX");
			String positionY = ParamUtils.getStr(request, "positionY");
			String positionAddress = ParamUtils.getStr(request, "positionAddress");
			Integer contentType = 6;//内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 6:定位)
			Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);
			msgService.handleSendFriendPosition(userId, friendId, addTime, contentType, filePath, positionX, positionY, positionAddress);
			map.put("succ", "1");
			map.put("filePath", filePath);
		} catch (IOException e) {
			map.put("succ", "-1");
			e.printStackTrace();
		}

		return StringUtils.json(map);
	}

}
