package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.GroupUserRelationDao;
import cn.dlj.app.entity.GroupUserRelation;

@Service
@Transactional(readOnly = true)
public class GroupUserRelationService {

	@Autowired
	private GroupUserRelationDao dao;

	@Transactional
	public Integer add(GroupUserRelation groupUserRelation) {
		dao.add(groupUserRelation);
		if (groupUserRelation != null && groupUserRelation.getId() != null) {
			return groupUserRelation.getId();
		}
		return null;
	}

	public GroupUserRelation findByUserIdAndGroupId(Integer userId, Integer groupId) {
		if (userId != null && groupId != null) {
			return dao.findByUserIdAndGroupId(userId, groupId);
		}
		return null;
	}

	public List<GroupUserRelation> findByGroupId(Integer groupId) {
		List<GroupUserRelation> list = new ArrayList<GroupUserRelation>();
		if (groupId != null) {
			list = dao.findByGroupId(groupId);
		}
		return list;
	}
}