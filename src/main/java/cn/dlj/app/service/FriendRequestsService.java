package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.FriendRequestsDao;
import cn.dlj.app.entity.FriendRequests;
import cn.dlj.app.entity.UserFriendRelation;

/**
 * 好友申请
 */
@Service
@Transactional(readOnly = true)
public class FriendRequestsService {

	@Autowired
	private FriendRequestsDao dao;

	@Transactional
	public Integer add(FriendRequests friendRequests) {
		dao.add(friendRequests);
		if (friendRequests != null && friendRequests.getId() != null) {
			return friendRequests.getId();
		}
		return null;
	}

	@Transactional
	public Integer addUserFriend(UserFriendRelation userFriendRelation) {
		dao.addUserFriend(userFriendRelation);
		if (userFriendRelation != null && userFriendRelation.getId() != null) {
			return userFriendRelation.getId();
		}
		return null;
	}

	@Transactional
	public void update(Integer id, Integer status) {
		if (id != null && status != null) {
			dao.update(id, status);
		}
	}

	public FriendRequests getByUserIdAndFriendId(Integer userId, Integer friendId) {
		if (userId != null && friendId != null) {
			FriendRequests friendRequests = dao.getByUserIdAndFriendId(userId, friendId);
			return friendRequests;
		}
		return null;
	}

	public List<FriendRequests> getByUserId(Integer userId) {
		List<FriendRequests> list = new ArrayList<FriendRequests>();
		if (userId != null) {
			list = dao.getByUserId(userId);
			return list;
		}
		return list;
	}

	public UserFriendRelation getUserFriendRelation(Integer userId, Integer friendId) {
		if (userId != null && friendId != null) {
			UserFriendRelation userFriendRelation = dao.getUserFriendRelation(userId, friendId);
			return userFriendRelation;
		}
		return null;
	}
}