package cn.dlj.app.entity;

/**
 * 动态点赞
 */
public class DynamicPraise {

	/** ID */
	private Integer id;
	/** 动态ID */
	private Integer dynamicId;
	/** 点赞人ID */
	private Integer userId;

	/** 评论人名称 */
	transient private String userName;

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
