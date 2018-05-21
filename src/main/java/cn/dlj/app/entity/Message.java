package cn.dlj.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息
 */
public class Message {

	/** ID **/
	private Integer id;
	/** 用户ID **/
	private Integer userId;
	/** 好友ID **/
	private Integer friendId;
	/** 内容 **/
	private String content;
	/** 接收类型(1:我发出的 2:我收到的 )备注(好像没用，准备删除) **/
	private Integer type;
	/** 内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 ) **/
	private Integer contentType;
	/** 创建时间 **/
	private Date addTime;
	/** 状态(1:未处理 2:已处理) **/
	private Integer status;

	/**
	 * 
	 */
	public String getAddTimeStr() {
		String format = "";
		if (addTime != null) {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format = simple.format(addTime);
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
	public Integer getType() {
		return type;
	}

	/**
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 
	 */
	public Integer getContentType() {
		return contentType;
	}

	/**
	 * 
	 */
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
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
	 * 状态(1:未处理 2:已处理)
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态(1:未处理 2:已处理)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
