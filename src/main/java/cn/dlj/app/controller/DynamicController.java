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
import cn.dlj.app.entity.DynamicComment;
import cn.dlj.app.entity.DynamicPraise;
import cn.dlj.app.service.DynamicCommentService;
import cn.dlj.app.service.DynamicPraiseService;
import cn.dlj.app.service.DynamicService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/dynamic")
@Controller
public class DynamicController {

	@Autowired
	private DynamicService dynamicService;
	@Autowired
	private DynamicCommentService dynamicCommentService;
	@Autowired
	private DynamicPraiseService dynamicPraiseService;

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

	/**
	 * 获取动态
	 */
	@RequestMapping("get")
	@ResponseBody
	public String get(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer dynamicId = ParamUtils.getInt(request, "dynamicId");
		Integer userId = ParamUtils.getInt(request, "userId");
		//获取动态
		Dynamic dynamic = dynamicService.getByIdAndUserId(dynamicId, userId);
		//获取评论
		List<DynamicComment> comments = dynamicCommentService.getByDynamicId(dynamicId);
		//获取点赞
		List<DynamicPraise> praises = dynamicPraiseService.getByDynamicId(dynamicId);

		map.put("succ", "1");
		map.put("dynamic", dynamic);
		map.put("comments", comments);
		map.put("praises", praises);
		return StringUtils.json(map);
	}

	/**
	 * 添加评论
	 */
	@RequestMapping("addComment")
	@ResponseBody
	public String addComment(HttpServletRequest request) {
		Integer dynamicId = ParamUtils.getInt(request, "dynamicId");
		Integer userId = ParamUtils.getInt(request, "userId");
		Date addTime = ParamUtils.paramDate(request, "addTime", "yyyy-MM-dd hh:mm:ss", false);
		String content = ParamUtils.getStr(request, "content");
		DynamicComment dynamicComment = new DynamicComment();
		dynamicComment.setDynamicId(dynamicId);
		dynamicComment.setUserId(userId);
		dynamicComment.setAddTime(addTime);
		dynamicComment.setContent(content);
		dynamicCommentService.add(dynamicComment);

		//更新评论
		dynamicService.updateCommentNum(dynamicId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 点赞
	 */
	@RequestMapping("addPraise")
	@ResponseBody
	public String addPraise(HttpServletRequest request) {
		Integer dynamicId = ParamUtils.getInt(request, "dynamicId");
		Integer userId = ParamUtils.getInt(request, "userId");

		DynamicPraise dynamicPraise = new DynamicPraise();
		dynamicPraise.setDynamicId(dynamicId);
		dynamicPraise.setUserId(userId);
		dynamicPraiseService.add(dynamicPraise);
		//更新点赞
		dynamicService.updatePraiseNum(dynamicId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

	/**
	 * 取消点赞
	 */
	@RequestMapping("cancelPraise")
	@ResponseBody
	public String cancelPraise(HttpServletRequest request) {
		Integer dynamicId = ParamUtils.getInt(request, "dynamicId");
		Integer userId = ParamUtils.getInt(request, "userId");
		dynamicPraiseService.del(dynamicId, userId);
		//更新点赞
		dynamicService.updatePraiseNum(dynamicId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		return StringUtils.json(map);
	}

}
