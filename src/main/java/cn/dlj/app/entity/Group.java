package cn.dlj.app.entity;

/**
 * 群组
 */
public class Group {

	/** ID **/
	private Integer id;
	/** 群名称 **/
	private String name;
	/** 群简介 **/
	private String introduction;
	/** 群头像 **/
	private String headImg;

	/**
	 * 
	 */
	public String getIntroductionStr() {
		return introduction == null ? "" : introduction;
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
	public String getName() {
		return name;
	}

	/**
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * 
	 */
	public String getHeadImg() {
		return headImg;
	}

	/**
	 * 
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
