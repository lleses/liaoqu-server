package cn.dlj.app.entity;

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

}
