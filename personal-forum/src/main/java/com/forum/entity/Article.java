package com.forum.entity;

/**
 * 发布实体类
 * 
 * @author 彼得·潘
 *
 */
public class Article extends BaseEntity {

	private static final long serialVersionUID = -809368175489312466L;
	private Integer id;// id
	private Integer uid;// 用户ID
	private String title;// 标题
	private String label;// 标签
	private String abst;
	private String content;// 内容
	private Integer permissions;// 权限 0：仅自己可见 1：公开

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(Integer id, Integer uid, String title, String label, String abst, String content,
			Integer permissions) {
		super();
		this.id = id;
		this.uid = uid;
		this.title = title;
		this.label = label;
		this.abst = abst;
		this.content = content;
		this.permissions = permissions;
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

	public String getAbst() {
		return abst;
	}

	public void setAbst(String abst) {
		this.abst = abst;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", uid=" + uid + ", title=" + title + ", label=" + label + ", abst=" + abst
				+ ", content=" + content + ", permissions=" + permissions + "]";
	}

}
