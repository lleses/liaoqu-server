package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.DynamicCommentDao;
import cn.dlj.app.entity.DynamicComment;

@Service
@Transactional(readOnly = true)
public class DynamicCommentService {

	@Autowired
	private DynamicCommentDao dao;

	@Transactional
	public Integer add(DynamicComment dynamicComment) {
		dao.add(dynamicComment);
		if (dynamicComment != null && dynamicComment.getId() != null) {
			return dynamicComment.getId();
		}
		return null;
	}

	public List<DynamicComment> getByDynamicId(Integer dynamicId) {
		List<DynamicComment> list = new ArrayList<DynamicComment>();
		if (dynamicId != null) {
			list = dao.getByDynamicId(dynamicId);
		}
		return list;
	}
}