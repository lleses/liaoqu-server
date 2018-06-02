package cn.dlj.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.User;
import cn.dlj.app.service.UserService;
import cn.dlj.utils.Config;
import cn.dlj.utils.FileUtils;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.SmsUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.Tool;

@RequestMapping("app/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	/** 头像保存地址 */
	public static final String HEAD_IMG_UPLOAD_PATH = Config.get("head.img.upload.path");

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
	public String registered(HttpServletRequest request) {
		String phone = ParamUtils.getStr(request, "phone");
		String username = ParamUtils.getStr(request, "username");
		String pwd = ParamUtils.getStr(request, "pwd");
		String name = ParamUtils.getStr(request, "name");
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
		user.setPhone(phone);
		user.setName(name);
		user.setLockPwd(pwd);
		Integer userId = userService.add(user);

		map.put("succ", "1");
		map.put("id", userId);
		map.put("username", user.getUsername());
		map.put("name", user.getName());
		map.put("phone", user.getPhone());
		map.put("headImg", user.getHeadImg());

		try {
			FileInputStream fis = new FileInputStream(HEAD_IMG_UPLOAD_PATH + "defaultHeadImg.jpg");
			File file = new File(HEAD_IMG_UPLOAD_PATH + userId + ".jpg");
			FileUtils.copyFile(fis, file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return StringUtils.json(map);
	}

	/**
	 * 登录
	 */
	@RequestMapping("login")
	@ResponseBody
	public String login(HttpServletRequest request) {
		String username = ParamUtils.getStr(request, "username");
		String pwd = ParamUtils.getStr(request, "pwd");
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.getByUsername(username);
		if (user == null) {
			map.put("succ", "-1");
			map.put("msg", "账号不存在");
			return StringUtils.json(map);
		}
		if (!user.getPwd().equals(Tool.md5Encode(pwd))) {
			map.put("succ", "-1");
			map.put("msg", "密码不正确");
			return StringUtils.json(map);
		}
		map.put("succ", "1");
		map.put("id", user.getId());
		map.put("username", user.getUsername());
		map.put("pwd", user.getLockPwd());
		map.put("name", user.getName());
		map.put("phone", user.getPhone());
		map.put("sex", user.getSex());
		map.put("organization", user.getOrganization());
		map.put("signature", user.getSignature());
		map.put("headImg", user.getHeadImg());
		map.put("email", user.getEmail());
		map.put("lockPwd", user.getLockPwd());
		return StringUtils.json(map);//跳转内容页
	}

	/**
	 * 解锁
	 */
	@RequestMapping("unlock")
	@ResponseBody
	public String unlock(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		userService.updateLock(userId, "1");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 修改用户文本内容
	 */
	@RequestMapping("eidtText")
	@ResponseBody
	public String eidtText(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = ParamUtils.getInt(request, "userId");
		String type = ParamUtils.getStr(request, "type");
		String content = ParamUtils.getStr(request, "content");
		User user = userService.getById(userId);
		if ("name".equals(type)) {//名称
			user.setName(content);
		} else if ("phone".equals(type)) {//电话
			user.setPhone(content);
		} else if ("organization".equals(type)) {//组织
			user.setOrganization(content);
		} else if ("signature".equals(type)) {//签名
			user.setSignature(content);
		} else if ("email".equals(type)) {//邮箱
			user.setEmail(content);
		} else {
			map.put("succ", "-1");
			map.put("msg", "修改失败");
			return StringUtils.json(map);
		}
		userService.update(user);
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 获取用户信息
	 */
	@RequestMapping("get_user")
	@ResponseBody
	public String getUser(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = ParamUtils.getInt(request, "userId");
		User user = userService.getById(userId);
		map.put("succ", "1");
		map.put("data", user);
		return StringUtils.json(map);//跳转内容页
	}
}
