package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.GroupDao;
import cn.dlj.app.entity.Group;

/**
 * 群组
 */
@Service
@Transactional(readOnly = true)
public class GroupService {

	@Autowired
	private GroupDao dao;

	@Transactional
	public Integer add(Group group) {
		dao.add(group);
		if (group != null && group.getId() != null) {
			return group.getId();
		}
		return null;
	}

	public Group findById(Integer id) {
		if (id != null) {
			return dao.findById(id);
		}
		return null;
	}

	public List<Group> findByUserId(Integer userId) {
		List<Group> list = new ArrayList<Group>();
		if (userId != null) {
			list = dao.findByUserId(userId);
		}
		return list;
	}

}