package cn.dlj.app.entity;

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

}
