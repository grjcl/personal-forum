package com.forum.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查找搜索实体类
 * 
 * @author 彼得·潘
 */
public class Search implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4470060784025101271L;
	private Integer id;// id
	private Integer uid;// 用户ID
	private String title;// 标题
	private String label;// 标签
	private String abst;
	private String content;// 内容
	private Integer num; // 点赞和
	private List<Comment> com; // 评论集合
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;

	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Search(Integer id, Integer uid, String title, String label, String abst, String content, Integer num,
			List<Comment> com, String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.title = title;
		this.label = label;
		this.abst = abst;
		this.content = content;
		this.num = num;
		this.com = com;
		this.createdUser = createdUser;
		this.createdTime = createdTime;
		this.modifiedUser = modifiedUser;
		this.modifiedTime = modifiedTime;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getAbst() {
		return abst;
	}

	public void setAbst(String abst) {
		this.abst = abst;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public List<Comment> getCom() {
		return com;
	}

	public void setCom(List<Comment> com) {
		this.com = com;
	}

	@Override
	public String toString() {
		return "Search [id=" + id + ", uid=" + uid + ", title=" + title + ", label=" + label + ", abst=" + abst
				+ ", content=" + content + ", num=" + num + ", com=" + com + ", createdUser=" + createdUser
				+ ", createdTime=" + createdTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime
				+ "]";
	}

}
