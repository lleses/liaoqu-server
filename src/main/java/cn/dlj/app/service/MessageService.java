package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.MessageDao;
import cn.dlj.app.entity.Message;
import cn.dlj.app.entity.MessageList;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.Tool;

@Service
@Transactional(readOnly = true)
public class MessageService {

	@Autowired
	private MessageDao dao;
	@Autowired
	private MessageListService msgListService;

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

	/**
	 * 处理发送好友文本内容
	 * 
	 * @param userId
	 * @param friendId
	 * @param content
	 * @param addTime
	 * @param contentType
	 */
	public void handleSendFriendText(Integer userId, Integer friendId, String content, Date addTime, int contentType) {
		handleSendFriend(userId, friendId, content, addTime, contentType, null, null, null, null, null);
	}

	/**
	 * 处理发送好友录音
	 * 
	 * @param userId
	 * @param friendId
	 * @param addTime
	 * @param contentType
	 * @param filePath
	 * @param duration
	 */
	public void handleSendFriendRecord(Integer userId, Integer friendId, Date addTime, int contentType, String filePath, Integer duration) {
		handleSendFriend(userId, friendId, "[语音]", addTime, contentType, filePath, duration, null, null, null);
	}
	
	/**
	 * 处理发送好友视频
	 * 
	 * @param userId
	 * @param friendId
	 * @param addTime
	 * @param contentType
	 * @param filePath
	 * @param duration
	 */
	public void handleSendFriendVideo(Integer userId, Integer friendId, Date addTime, int contentType, String filePath, Integer duration) {
		handleSendFriend(userId, friendId, "[视频]", addTime, contentType, filePath, duration, null, null, null);
	}

	/**
	 * 处理发送好友图片
	 * 
	 * @param userId
	 * @param friendId
	 * @param addTime
	 * @param contentType
	 * @param filePath
	 */
	public void handleSendFriendPhoto(Integer userId, Integer friendId, Date addTime, int contentType, String filePath) {
		handleSendFriend(userId, friendId, "[图片]", addTime, contentType, filePath, null, null, null, null);
	}

	/**
	 * 处理发送好友定位
	 * 
	 */
	public void handleSendFriendPosition(Integer userId, Integer friendId, Date addTime, int contentType, String filePath, String positionX, String positionY, String positionAddress) {
		handleSendFriend(userId, friendId, "[位置]", addTime, contentType, filePath, null, positionX, positionY, positionAddress);
	}

	/**
	 * 处理发送好友记录
	 * 
	 * @param userId
	 * @param friendId
	 * @param content
	 * @param addTime
	 * @param contentType
	 * @param filePath
	 * @param duration
	 */
	private void handleSendFriend(Integer userId, Integer friendId, String content, Date addTime, int contentType, String filePath, Integer duration, String positionX, String positionY, String positionAddress) {
		//好友收到的信息
		Message message = new Message();
		message.setUserId(friendId);
		message.setFriendId(userId);
		message.setContent(content);
		message.setFilePath(filePath);//文件路径
		message.setDuration(duration);//录音时长
		message.setType(2);//接收类型(1:我发出的 2:我收到的 )
		message.setAddTime(addTime);
		message.setContentType(contentType);
		message.setStatus(1);
		message.setPositionX(positionX);//定位x坐标
		message.setPositionY(positionY);//定位y坐标
		message.setPositionAddress(positionAddress);//定位地址
		dao.add(message);

		//我发出的
		MessageList msgList = msgListService.findByUserIdAndFriendId(userId, friendId);
		if (msgList == null) {
			msgList = new MessageList();
			msgList.setUserId(userId);
			msgList.setFriendId(friendId);
			msgList.setContent(content);
			msgList.setLastTime(addTime);
			msgList.setNum(0);
			msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgList.setType(1);
			msgListService.add(msgList);
		} else {
			msgList.setContent(content);
			msgList.setLastTime(addTime);
			msgList.setNum(0);
			msgList.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgListService.update(msgList);
		}

		//好友收到的
		MessageList msgList2 = msgListService.findByUserIdAndFriendId(friendId, userId);
		if (msgList2 == null) {
			msgList2 = new MessageList();
			msgList2.setUserId(friendId);
			msgList2.setFriendId(userId);
			msgList2.setContent(content);
			msgList2.setLastTime(addTime);
			msgList2.setNum(1);
			msgList2.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgList2.setType(1);
			msgListService.add(msgList2);
		} else {
			msgList2.setContent(content);
			msgList2.setLastTime(addTime);
			msgList2.setNum(msgList2.getNum() + 1);
			msgList2.setContentEncrypt(Tool.md5Encode(IdUtils.id32()));
			msgListService.update(msgList2);
		}
	}
}