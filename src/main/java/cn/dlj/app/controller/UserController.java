package cn.dlj.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.User;
import cn.dlj.app.service.UserService;
import cn.dlj.utils.SmsUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.Tool;

@RequestMapping("app/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	/** 主帐号 **/
	public static String accountSid = "8a216da862b36cbc0162b898d7cf040e";
	/** 令牌 **/
	public static String authToken = "b2d86a6b0b5447a8894a522cba182cbc";
	/** 应用ID **/
	public static String appId = "8aaf070862fae1b501630b4bcc120840";
	/** 膜版ID **/
	public static String templateId = "246168";

	/**
	 * 检查账号是否存在
	 */
	@RequestMapping("checkUserName")
	@ResponseBody
	public String checkUserName(HttpServletRequest request, String username) {
		User user = userService.getByUsername(username);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("succ", "1");
			map.put("msg", "账号可以注册");
		} else {
			map.put("succ", "-1");
			map.put("msg", "账号已经存在,不能注册");
		}
		return StringUtils.json(map);
	}

	/**
	 * 发送短信
	 */
	@RequestMapping("sendSms")
	@ResponseBody
	public String sendSms(HttpServletRequest request, String phone) {
		//生成6位数的验证码
		String code = StringUtils.ranNum(6);
		SmsUtils.sendSms(phone, code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		return StringUtils.json(map);
	}

	/**
	 * 注册
	 */
	@RequestMapping("registered")
	@ResponseBody
	public String registered(HttpServletRequest request, String username, String pwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.getByUsername(username);
		if (user != null) {
			map.put("succ", "-1");
			map.put("msg", "账号已经存在,不能注册");
			return StringUtils.json(map);
		}
		user = new User();
		user.setUsername(username);
		user.setPwd(Tool.md5Encode(pwd));
		userService.add(user);
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 登录
	 */
	@RequestMapping("login")
	@ResponseBody
	public String login(HttpServletRequest request, String username, String pwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.getByUsername(username);
		if (user == null) {
			map.put("succ", "-2");
			map.put("msg", "账号不存在");
			return StringUtils.json(map);
		}
		if (!user.getPwd().equals(Tool.md5Encode(pwd))) {
			map.put("succ", "-1");
			map.put("msg", "密码不正确");
			return StringUtils.json(map);
		}
		map.put("succ", "1");
		return StringUtils.json(map);//跳转内容页
	}

}
