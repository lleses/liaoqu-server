package cn.dlj.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 动态评论
 */
public class DynamicComment {

	/** ID */
	private Integer id;
	/** 动态ID */
	private Integer dynamicId;
	/** 评论人ID */
	private Integer userId;
	/** 添加时间 */
	private Date addTime;
	/** 评论内容 */
	private String content;

	/** 评论人名称 */
	transient private String userName;

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
	public Integer getDynamicId() {
		return dynamicId;
	}

	/**
	 * 
	 */
	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
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
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
