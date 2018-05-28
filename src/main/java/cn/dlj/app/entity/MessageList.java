package cn.dlj.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息列表
 */
public class MessageList {

	/** ID **/
	private Integer id;
	/** 用户ID **/
	private Integer userId;
	/** 消息朋友ID **/
	private Integer friendId;
	/** 未读数量 **/
	private Integer num;
	/** 最后消息发送时间 **/
	private Date lastTime;
	/** 内容 **/
	private String content;
	/** 内容加密 **/
	private String contentEncrypt;

	/** 好友名称 **/
	transient private String friendName;

	/**
	 * 
	 */
	public String getLastTimeStr() {
		String format = "";
		if (lastTime != null) {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format = simple.format(lastTime);
		}
		return format;
	}

	/**
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 
	 */
	public Integer getFriendId() {
		return friendId;
	}

	/**
	 * 
	 */
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	/**
	 * 
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * 
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * 
	 */
	public Date getLastTime() {
		return lastTime;
	}

	/**
	 * 
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 */
	public String getFriendName() {
		return friendName;
	}

	/**
	 * 
	 */
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	/**
	 * 
	 */
	public String getContentEncrypt() {
		return contentEncrypt;
	}

	/**
	 * 
	 */
	public void setContentEncrypt(String contentEncrypt) {
		this.contentEncrypt = contentEncrypt;
	}

}
