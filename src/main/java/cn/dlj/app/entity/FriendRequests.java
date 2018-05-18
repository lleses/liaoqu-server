package cn.dlj.app.entity;

import java.util.Date;

/**
 * 好友申请
 */
public class FriendRequests {

	/** ID **/
	private Integer id;
	/** 用户ID **/
	private Integer userId;
	/** 好友ID **/
	private Integer friendId;
	/** 状态(0:待处理 1:已添加) **/
	private Integer status;
	/** 申请时间 **/
	private Date addTime;

	/** 好友名称 **/
	transient private String friendName;

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
	 * 状态(0:待处理 1:已添加)
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态(0:待处理 1:已添加)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 好友名称
	 */
	public String getFriendName() {
		return friendName;
	}

	/**
	 * 好友名称
	 */
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

}
