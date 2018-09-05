package cn.dlj.app.entity;

/**
 * 用户-好友关联表
 */
public class UserFriendRelation {

	/** ID **/
	private Integer id;
	/** 用户ID **/
	private Integer userId;
	/** 好友ID **/
	private Integer friendId;

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

}
