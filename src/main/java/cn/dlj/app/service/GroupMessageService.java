package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.GroupMessageDao;
import cn.dlj.app.entity.GroupMessage;
import cn.dlj.app.entity.GroupUserRelation;
import cn.dlj.app.entity.MessageList;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.Tool;

@Service
@Transactional(readOnly = true)
public class GroupMessageService {

	@Autowired
	private GroupMessageDao dao;
	@Autowired
	private GroupUserRelationService groupUserRelationService;
	@Autowired
	private MessageListService msgListService;

	@Transactional
	public Integer add(GroupMessage groupMessage) {
		dao.add(groupMessage);
		if (groupMessage != null && groupMessage.getId() != null) {
			return groupMessage.getId();
		}
		return null;
	}

	@Transactional
	public void update(Integer id, Integer status) {
		if (id != null && status != null) {
			dao.update(id, status);
		}
	}

	public List<GroupMessage> findByGroupIdAndUserId(Integer groupId, Integer userId) {
		List<GroupMessage> list = new ArrayList<GroupMessage>();
		if (groupId != null && userId != null) {
			list = dao.findByGroupIdAndUserId(groupId, userId);
		}
		return list;
	}

	/**
	 * 处理发送群组文本内容
	 * 
	 */
	public void handleSendGroupText(Integer groupId, Integer userId, String content, Date addTime, int contentType) {
		handleSendGroup(groupId, userId, content, addTime, contentType, null, null, null, null, null);
	}

	/**
	 * 处理发送群组录音
	 * 
	 */
	public void handleSendGroupRecord(Integer groupId, Integer userId, Date addTime, int contentType, String filePath, Integer duration) {
		handleSendGroup(groupId, userId, "[语音]", addTime, contentType, filePath, duration, null, null, null);
	}

	/**
	 * 处理发送群组图片
	 * 
	 */
	public void handleSendGroupPhoto(Integer groupId, Integer userId, Date addTime, int contentType, String filePath) {
		handleSendGroup(groupId, userId, "[图片]", addTime, contentType, filePath, null, null, null, null);
	}

	/**
	 * 处理发送群组定位
	 * 
	 */
	public void handleSendGroupPosition(Integer groupId, Integer userId, Date addTime, int contentType, String filePath, String positionX, String positionY, String positionAddress) {
		handleSendGroup(groupId, userId, "[位置]", addTime, contentType, filePath, null, positionX, positionY, positionAddress);
	}

	/**
	 * 处理发送群组记录
	 * 
	 */
	private void handleSendGroup(Integer groupId, Integer userId, String content, Date addTime, int contentType, String filePath, Integer duration, String positionX, String positionY, String positionAddress) {
		//获取群用户
		List<GroupUserRelation> list = groupUserRelationService.findByGroupId(groupId);
		for (GroupUserRelation groupUserRelation : list) {
			//群用户ID
			Integer groupUserId = groupUserRelation.getUserId();

			//--------------创建群组聊天记录,每人一条，自己除外--------------
			if (!userId.equals(groupUserId)) {
				//其他用户收到的信息
				GroupMessage groupMsg = new GroupMessage();
				groupMsg.setUserId(groupUserId);
				groupMsg.setGroupId(groupId);
				groupMsg.setContent(content);
				groupMsg.setAddTime(addTime);
				groupMsg.setContentType(contentType);
				groupMsg.setFilePath(filePath);
				groupMsg.setDuration(duration);
				groupMsg.setStatus(1);
				groupMsg.setPositionX(positionX);//定位x坐标
				groupMsg.setPositionY(positionY);//定位y坐标
				groupMsg.setPositionAddress(positionAddress);//定位地址
				dao.add(groupMsg);
			}

			//--------------创建通知--------------
			MessageList msgList = msgListService.findByUserIdAndGroupId(groupUserId, groupId);

			int num = 0;//我的通知数为0
			if (!userId.equals(groupUserId) && msgList == null) {//其他用户的通知数,当不存在通知的时候,num=1
				num = 1;
			} else if (!userId.equals(groupUserId) && msgList != null) {//其他用户的通知数,当存在通知的时候 ,num+1
				num = msgList.getNum() + 1;
			}
			//创建
			if (msgList == null) {
				msgList = new MessageList();
				msgList.setUserId(groupUserId);
				msgList.setGroupId(groupId);
				msgList.setContent(content);
				msgList.setLastTime(addTime);
				msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
				msgList.setType(2);
				msgList.setNum(num);
				msgListService.add(msgList);
			} else {//更新
				msgList.setContent(content);
				msgList.setLastTime(addTime);
				msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
				msgList.setNum(num);
				msgListService.updateByUserIdAndGroupId(msgList);
			}
		}
	}

}