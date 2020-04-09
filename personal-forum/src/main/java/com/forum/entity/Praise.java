package com.forum.entity;

/**
 * 点赞实体类
 * 
 * @author 彼得·潘
 */
public class Praise extends BaseEntity {

	private static final long serialVersionUID = 7904640981806887198L;
	private Integer id;
	private Integer uid; // 用户ID
	private Integer articleId;// 文章ID

	public Praise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Praise(Integer id, Integer uid, Integer articleId) {
		super();
		this.id = id;
		this.uid = uid;
		this.articleId = articleId;
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

	@Override
	public String toString() {
		return "Praise [id=" + id + ", uid=" + uid + ", articleId=" + articleId + "]";
	}

}
