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
	/** 好友头像 **/
	transient private String friendHeadImg;

	/**
	 * ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 好友ID
	 */
	public Integer getFriendId() {
		return friendId;
	}

	/**
	 * 好友ID
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
	 * 申请时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 申请时间
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

	/**
	 * 好友头像
	 */
	public String getFriendHeadImg() {
		return friendHeadImg;
	}

	/**
	 * 好友头像
	 */
	public void setFriendHeadImg(String friendHeadImg) {
		this.friendHeadImg = friendHeadImg;
	}

}
