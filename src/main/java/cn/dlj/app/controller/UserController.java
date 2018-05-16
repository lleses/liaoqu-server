package cn.dlj.app.controller;

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

	/**
	 * 检查账号是否存在
	 */
	@RequestMapping("checkUserName")
	@ResponseBody
	public String checkUserName(HttpServletRequest request, String username) {
		User user = userService.getByUsername(username);
		if (user == null) {
			return "1";//账号可以注册
		} else {
			return "2";//账号已经存在,不能注册
		}
	}

	/**
	 * 发送短信
	 */
	@RequestMapping("sendSms")
	@ResponseBody
	public String sendSms(HttpServletRequest request, String phone) {
		//生成6位数的验证码
		String verificationCode = StringUtils.ranNum(6);
		SmsUtils.sendSms(phone, verificationCode);
		return verificationCode;
	}

	/**
	 * 注册
	 */
	@RequestMapping("registered")
	@ResponseBody
	public String registered(HttpServletRequest request, String username, String pwd) {
		User user = userService.getByUsername(username);
		if (user != null) {
			return "-1";//账号已经存在,不能注册
		}
		user = new User();
		user.setUsername(username);
		user.setPwd(Tool.md5Encode(pwd));
		userService.add(user);
		return "1";
	}

	/**
	 * 登录
	 */
	@RequestMapping("login")
	@ResponseBody
	public String login(HttpServletRequest request, String username, String pwd) {
		User user = userService.getByUsername(username);
		if (user == null) {
			return "-1";//账号不存在
		}
		if (!user.getPwd().equals(Tool.md5Encode(pwd))) {
			return "-1";//密码不正确
		}
		return "1";//跳转内容页
	}

}
