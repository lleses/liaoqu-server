package cn.dlj.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.User;
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

	/**
	 * 搜索好友
	 */
	@RequestMapping("searchFriend")
	@ResponseBody
	public String searchFriend(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String content = ParamUtils.getStr(request, "content");//账号or手机号码
		List<User> list = userService.getByUsernameOrPhone(content);
		if (list == null || list.isEmpty()) {
			map.put("succ", "-1");
			return StringUtils.json(map);
		}
		map.put("succ", "1");
		map.put("data", list);
		return StringUtils.json(map);
	}
}
