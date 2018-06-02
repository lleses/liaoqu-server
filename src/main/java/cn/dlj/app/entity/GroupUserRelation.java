package cn.dlj.app.entity;

import java.util.Date;

/**
 * 群组用户关联表
 */
public class GroupUserRelation {

	/** ID **/
	private Integer id;
	/** 群ID **/
	private Integer groupId;
	/** 用户ID **/
	private Integer userId;
	/** 添加时间 **/
	private Date addTime;

	/** 群用户名称 **/
	transient private String groupUserName;

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
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * 
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
	 * 
	 */
	public String getGroupUserName() {
		return groupUserName;
	}

	/**
	 * 
	 */
	public void setGroupUserName(String groupUserName) {
		this.groupUserName = groupUserName;
	}

}
