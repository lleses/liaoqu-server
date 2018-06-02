package cn.dlj.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.MessageList;
import cn.dlj.app.service.MessageListService;
import cn.dlj.app.service.UserService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

/**
 * 通知
 * 
 * @author 余狄龙
 * @date 2018年6月2日
 */
@RequestMapping("app/notice")
@Controller
public class NoticeController {

	@Autowired
	private MessageListService msgListService;
	
	//TODO
	@Autowired
	private UserService userService;

	//TODO
	/**
	 * 初始化数据库
	 */
	@RequestMapping("init")
	@ResponseBody
	public String init(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		userService.del();

		map.put("succ", "初始化成功");
		return StringUtils.json(map);
	}

	/**
	 * 通知列表
	 */
	@RequestMapping("list")
	@ResponseBody
	public String list(HttpServletRequest request) {
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
}
