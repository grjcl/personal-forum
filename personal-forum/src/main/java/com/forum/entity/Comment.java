package com.forum.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论实体类
 * 
 * @author 彼得·潘
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 8665253346196283040L;
	private Integer id;
	private Integer uid; // 用户ID
	private Integer articleId;// 文章ID
	private Integer addComments;// 被追加评论的id
	private String commentArticle; // 评论文章的内容
	private String commentAvatar; // 评论头像
	private List<Comment> addcomments;// 追加评论
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Comment(Integer id, Integer uid, Integer articleId, Integer addComments, String commentArticle, 
			String commentAvatar, List<Comment> addcomments2, String createdUser, Date createdTime, String modifiedUser,
			Date modifiedTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.articleId = articleId;
		this.addComments = addComments;
		this.commentArticle = commentArticle;
		this.commentAvatar = commentAvatar;
		addcomments = addcomments2;
		this.createdUser = createdUser;
		this.createdTime = createdTime;
		this.modifiedUser = modifiedUser;
		this.modifiedTime = modifiedTime;
	}


	



	public Integer getAddComments() {
		return addComments;
	}

	public void setAddComments(Integer addComments) {
		this.addComments = addComments;
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

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getCommentArticle() {
		return commentArticle;
	}

	public void setCommentArticle(String commentArticle) {
		this.commentArticle = commentArticle;
	}

	public String getCommentAvatar() {
		return commentAvatar;
	}

	public void setCommentAvatar(String commentAvatar) {
		this.commentAvatar = commentAvatar;
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

	public List<Comment> getAddcomments() {
		return addcomments;
	}

	public void setAddcomments(List<Comment> addcomments) {
		this.addcomments = addcomments;
	}



	@Override
	public String toString() {
		return "Comment [id=" + id + ", uid=" + uid + ", articleId=" + articleId + ", addComments=" + addComments
				+ ", commentArticle=" + commentArticle +  ", commentAvatar=" + commentAvatar
				+ ", addcomments=" + addcomments + ", createdUser=" + createdUser + ", createdTime=" + createdTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}

	
}
