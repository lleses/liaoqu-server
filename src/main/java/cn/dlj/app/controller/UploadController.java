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
import cn.dlj.utils.Config;
import cn.dlj.utils.FileUtils;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/upload")
@Controller
public class UploadController {

	@Autowired
	private UserService userService;
	/** 头像保存地址 */
	public static final String HEAD_IMG_UPLOAD_PATH = Config.get("head.img.upload.path");

	/**
	 * 上传头像
	 */
	@RequestMapping("headImg")
	@ResponseBody
	public String headImg(HttpServletRequest request) {
		Integer userId = ParamUtils.getInt(request, "userId");
		String imgbasc64 = ParamUtils.getStr(request, "imgbasc64");
		String imgSrc = FileUtils.imgbasc64(userId, imgbasc64, HEAD_IMG_UPLOAD_PATH);//basc64转成图片文件

		User user = userService.getById(userId);
		user.setHeadImg("headImg/" + imgSrc);
		userService.update(user);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("headImg", imgSrc);
		String json = StringUtils.json(map);
		return json;
	}

}
