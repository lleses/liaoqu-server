package cn.dlj.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.GroupMessageDao;
import cn.dlj.app.entity.GroupMessage;

@Service
@Transactional(readOnly = true)
public class GroupMessageService {

	@Autowired
	private GroupMessageDao dao;

	@Transactional
	public Integer add(GroupMessage groupMessage) {
		dao.add(groupMessage);
		if (groupMessage != null && groupMessage.getId() != null) {
			return groupMessage.getId();
		}
		return null;
	}

}