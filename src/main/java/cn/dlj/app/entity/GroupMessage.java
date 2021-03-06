package cn.dlj.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 群组消息
 */
public class GroupMessage {

	/** ID **/
	private Integer id;
	/** 用户ID **/
	private Integer userId;
	/** 群组ID **/
	private Integer groupId;
	/** 内容 **/
	private String content;
	/** 内容类型(1:文本 2:图片 3:录音 4:视频 5:文件 6:定位 ) **/
	private Integer contentType;
	/** 创建时间 **/
	private Date addTime;
	/** 状态(1:未处理 2:已处理) **/
	private Integer status;
	/** 文件路径 **/
	private String filePath;
	/** 录音时长 **/
	private Integer duration;

	/** 定位x坐标 **/
	private String positionX;
	/** 定位y坐标 **/
	private String positionY;
	/** 定位地址 **/
	private String positionAddress;

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
	 * 
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * 
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * 
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * 定位x坐标
	 */
	public String getPositionX() {
		return positionX;
	}

	/**
	 * 定位x坐标
	 */
	public void setPositionX(String positionX) {
		this.positionX = positionX;
	}

	/**
	 * 定位y坐标
	 */
	public String getPositionY() {
		return positionY;
	}

	/**
	 * 定位y坐标
	 */
	public void setPositionY(String positionY) {
		this.positionY = positionY;
	}

	/**
	 * 定位地址
	 */
	public String getPositionAddress() {
		return positionAddress;
	}

	/**
	 * 定位地址
	 */
	public void setPositionAddress(String positionAddress) {
		this.positionAddress = positionAddress;
	}

}
