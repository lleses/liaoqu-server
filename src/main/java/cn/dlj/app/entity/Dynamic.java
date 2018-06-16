package cn.dlj.app.entity;

import java.util.Date;

/**
 * 动态
 */
public class Dynamic {

	/** ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 添加时间 */
	private Date addTime;
	/** 内容 */
	private String content;
	/** 模版类型(1:正常 2:黑色 3:蓝色 4:粉色) */
	private Integer stencilType;
	/** 图片数量 */
	private Integer imgNum;
	/** 权限 (1:朋友 2:广场) */
	private Integer authority;
	/** 评论数量 */
	private Integer commentNum;
	/** 点赞数量 */
	private Integer praiseNum;
	/** 动态图片 */
	private String img1;
	/** 动态图片 */
	private String img2;
	/** 动态图片 */
	private String img3;
	/** 动态图片 */
	private String img4;
	/** 动态图片 */
	private String img5;
	/** 动态图片 */
	private String img6;
	/** 动态图片 */
	private String img7;

	/** 用户名称 */
	transient private String userName;
	/** 用户签名 */
	transient private String userSignature;

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
	public Integer getStencilType() {
		return stencilType;
	}

	/**
	 * 
	 */
	public void setStencilType(Integer stencilType) {
		this.stencilType = stencilType;
	}

	/**
	 * 
	 */
	public String getImg1() {
		return img1;
	}

	/**
	 * 
	 */
	public void setImg1(String img1) {
		this.img1 = img1;
	}

	/**
	 * 
	 */
	public String getImg2() {
		return img2;
	}

	/**
	 * 
	 */
	public void setImg2(String img2) {
		this.img2 = img2;
	}

	/**
	 * 
	 */
	public String getImg3() {
		return img3;
	}

	/**
	 * 
	 */
	public void setImg3(String img3) {
		this.img3 = img3;
	}

	/**
	 * 
	 */
	public String getImg4() {
		return img4;
	}

	/**
	 * 
	 */
	public void setImg4(String img4) {
		this.img4 = img4;
	}

	/**
	 * 
	 */
	public String getImg5() {
		return img5;
	}

	/**
	 * 
	 */
	public void setImg5(String img5) {
		this.img5 = img5;
	}

	/**
	 * 
	 */
	public String getImg6() {
		return img6;
	}

	/**
	 * 
	 */
	public void setImg6(String img6) {
		this.img6 = img6;
	}

	/**
	 * 
	 */
	public String getImg7() {
		return img7;
	}

	/**
	 * 
	 */
	public void setImg7(String img7) {
		this.img7 = img7;
	}

	/**
	 * 
	 */
	public Integer getImgNum() {
		return imgNum;
	}

	/**
	 * 
	 */
	public void setImgNum(Integer imgNum) {
		this.imgNum = imgNum;
	}

	/**
	 * 
	 */
	public Integer getAuthority() {
		return authority;
	}

	/**
	 * 
	 */
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	/**
	 * 
	 */
	public Integer getCommentNum() {
		return commentNum;
	}

	/**
	 * 
	 */
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 
	 */
	public Integer getPraiseNum() {
		return praiseNum;
	}

	/**
	 * 
	 */
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
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

	/**
	 * 
	 */
	public String getUserSignature() {
		return userSignature;
	}

	/**
	 * 
	 */
	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

}
