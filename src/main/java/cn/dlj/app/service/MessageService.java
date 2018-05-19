package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.MessageDao;
import cn.dlj.app.entity.Message;
import cn.dlj.app.entity.MessageList;

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
	public Integer addList(MessageList messageList) {
		dao.addList(messageList);
		if (messageList != null && messageList.getId() != null) {
			return messageList.getId();
		}
		return null;
	}

	@Transactional
	public void updateList(MessageList messageList) {
		if (messageList.getId() != null) {
			dao.updateList(messageList);
		}
	}

	public List<Message> getMsg(Integer userId, Integer friendId) {
		List<Message> list = new ArrayList<Message>();
		if (userId != null && friendId != null) {
			list = dao.getMsg(userId, friendId);
		}
		return list;
	}

	public MessageList getMsgList(Integer userId, Integer friendId) {
		if (userId != null && friendId != null) {
			MessageList user = dao.getMsgList(userId, friendId);
			return user;
		}
		return null;
	}

	public List<MessageList> getMsgListByUserId(Integer userId) {
		List<MessageList> list = new ArrayList<MessageList>();
		if (userId != null) {
			list = dao.getMsgListByUserId(userId);
		}
		return list;
	}

	public void delMsgList(Integer userId, Integer friendId) {
		if (userId != null && friendId != null) {
			dao.delMsgList(userId, friendId);
		}
	}
}