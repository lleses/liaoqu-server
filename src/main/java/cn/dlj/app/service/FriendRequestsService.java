package cn.dlj.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.FriendRequestsDao;
import cn.dlj.app.entity.FriendRequests;

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

	public FriendRequests getByUserIdAndFriendId(Integer userId, Integer friendId) {
		if (userId != null && friendId != null) {
			FriendRequests friendRequests = dao.getByUserIdAndFriendId(userId, friendId);
			return friendRequests;
		}
		return null;
	}
}