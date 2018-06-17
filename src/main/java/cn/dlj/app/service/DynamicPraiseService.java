package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.DynamicPraiseDao;
import cn.dlj.app.entity.DynamicPraise;

@Service
@Transactional(readOnly = true)
public class DynamicPraiseService {

	@Autowired
	private DynamicPraiseDao dao;

	@Transactional
	public Integer add(DynamicPraise dynamicPraise) {
		dao.add(dynamicPraise);
		if (dynamicPraise != null && dynamicPraise.getId() != null) {
			return dynamicPraise.getId();
		}
		return null;
	}

	public void del(Integer dynamicId, Integer userId) {
		if (dynamicId != null && userId != null) {
			dao.del(dynamicId, userId);
		}
	}

	public List<DynamicPraise> getByDynamicId(Integer dynamicId) {
		List<DynamicPraise> list = new ArrayList<DynamicPraise>();
		if (dynamicId != null) {
			list = dao.getByDynamicId(dynamicId);
		}
		return list;
	}
}