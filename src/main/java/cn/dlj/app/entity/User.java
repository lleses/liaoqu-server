package cn.dlj.app.entity;

/**
 * 用户信息
 */
public class User {

	/** ID */
	private Integer id;
	/** 账号 */
	private String username;
	/** 密码 */
	private String pwd;
	/** 名称 */
	private String name;
	/** 性别(0:女 1:男) */
	private Integer sex;
	/** 电话 */
	private String phone;
	/** 组织 */
	private String organization;
	/** 签名 */
	private String signature;
	/** 头像 */
	private String headImg;

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
	 * 
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public Integer getSex() {
		return sex;
	}

	/**
	 * 
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * 
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * 
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 
	 */
	public void setSignature(String signature) {
		this.signature = signature;
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
