package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.MessageListDao;
import cn.dlj.app.entity.MessageList;

/**
 * 消息
 */
@Service
@Transactional(readOnly = true)
public class MessageListService {

	@Autowired
	private MessageListDao dao;

	@Transactional
	public Integer add(MessageList messageList) {
		dao.add(messageList);
		if (messageList != null && messageList.getId() != null) {
			return messageList.getId();
		}
		return null;
	}

	@Transactional
	public void update(MessageList messageList) {
		if (messageList.getId() != null) {
			dao.update(messageList);
		}
	}

	@Transactional
	public void updateByUserIdAndGroupId(MessageList messageList) {
		if (messageList.getId() != null) {
			dao.updateByUserIdAndGroupId(messageList);
		}
	}

	public void del(Integer userId, Integer friendId) {
		if (userId != null && friendId != null) {
			dao.del(userId, friendId);
		}
	}

	public MessageList findByUserIdAndFriendId(Integer userId, Integer friendId) {
		if (userId != null && friendId != null) {
			MessageList user = dao.findByUserIdAndFriendId(userId, friendId);
			return user;
		}
		return null;
	}

	public MessageList findByUserIdAndGroupId(Integer userId, Integer groupId) {
		if (userId != null && groupId != null) {
			MessageList user = dao.findByUserIdAndGroupId(userId, groupId);
			return user;
		}
		return null;
	}

	public List<MessageList> findByUserId(Integer userId) {
		List<MessageList> list = new ArrayList<MessageList>();
		if (userId != null) {
			list = dao.findByUserId(userId);
		}
		return list;
	}

}