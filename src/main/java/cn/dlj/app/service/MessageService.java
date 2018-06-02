package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.MessageDao;
import cn.dlj.app.entity.Message;

/**
 * 消息
 */
@Service
@Transactional(readOnly = true)
public class MessageService {

	@Autowired
	private MessageDao dao;

	@Transactional
	public Integer add(Message message) {
		dao.add(message);
		if (message != null && message.getId() != null) {
			return message.getId();
		}
		return null;
	}

	@Transactional
	public void update(Integer id, Integer status) {
		if (id != null && status != null) {
			dao.update(id, status);
		}
	}

	public List<Message> findByUserIdAndFriendIdAndStatus(Integer userId, Integer friendId, Integer status) {
		List<Message> list = new ArrayList<Message>();
		if (userId != null && friendId != null && status != null) {
			list = dao.findByUserIdAndFriendIdAndStatus(userId, friendId, status);
		}
		return list;
	}

}