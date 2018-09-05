package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.GroupDao;
import cn.dlj.app.entity.Group;
import cn.dlj.app.entity.GroupUserRelation;
import cn.dlj.app.entity.MessageList;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.Tool;

/**
 * 群组
 */
@Service
@Transactional(readOnly = true)
public class GroupService {

	@Autowired
	private GroupDao dao;
	@Autowired
	private GroupUserRelationService groupUserRelationService;
	@Autowired
	private MessageListService msgListService;

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
	
	/**
	 * 邀请好友
	 */
	public void inviteFriends(String[] fIds,Integer groupId) {
		for (String fId : fIds) {
			Integer friendId = Integer.valueOf(fId);
			//检查是否存在
			GroupUserRelation group = groupUserRelationService.findByUserIdAndGroupId(friendId, groupId);
			if (group == null) {
				GroupUserRelation groupUserRelation = new GroupUserRelation();
				groupUserRelation.setGroupId(groupId);
				groupUserRelation.setUserId(friendId);
				groupUserRelation.setAddTime(new Date());
				groupUserRelationService.add(groupUserRelation);
				
				MessageList msgList = msgListService.findByUserIdAndGroupId(friendId, groupId);
				if (msgList == null) {
					msgList = new MessageList();
					msgList.setUserId(friendId);
					msgList.setGroupId(groupId);
					msgList.setContent("欢迎你加入了群聊");
					msgList.setLastTime(new Date());
					msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
					msgList.setType(2);
					msgList.setNum(1);
					msgListService.add(msgList);
				}
			}
		}
	}

}