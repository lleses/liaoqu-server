package cn.dlj.app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Dynamic;
import cn.dlj.app.service.DynamicService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/dynamic")
@Controller
public class DynamicController {

	@Autowired
	private DynamicService dynamicService;

	/**
	 * 添加动态
	 */
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		Integer userId = ParamUtils.getInt(request, "userId");
		Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);
		String content = ParamUtils.getStr(request, "content");
		Integer stencilType = ParamUtils.getInt(request, "stencilType");//模版类型(1:正常 2:黑色 3:蓝色 4:粉色)
		Integer imgNum = ParamUtils.getInt(request, "imgNum");//图片数量
		Integer authority = ParamUtils.getInt(request, "authority");//权限 (1:朋友 2:广场)
		String imgsBasc64 = ParamUtils.getStr(request, "imgsBasc64");
		try {
			dynamicService.handleAdd(userId, addTime, content, stencilType, imgNum, authority, imgsBasc64);
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 获取动态列表
	 */
	@RequestMapping("list")
	@ResponseBody
	public String list(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		Integer userId = ParamUtils.getInt(request, "userId");
		List<Dynamic> list = dynamicService.getByUserId(userId);

		map.put("succ", "1");
		map.put("data", list);
		return StringUtils.json(map);
	}
}
