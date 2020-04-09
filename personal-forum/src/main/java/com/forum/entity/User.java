package com.forum.entity;

/**
 * 用户实体类
 * @author 彼得·潘
 *
 */
public class User extends BaseEntity {

	
	private static final long serialVersionUID = 2626018211563711405L;
	
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String gender;    //性别
	private String birthday; 
	private String email;
	private String headPortrait;   //头像
	private String synopsis;   //简介
	private Integer isDelete;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String username, String password, String salt, String gender, String birthday, String email,
			String headPortrait, String synopsis, Integer isDelete) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.headPortrait = headPortrait;
		this.synopsis = synopsis;
		this.isDelete = isDelete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}


	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", gender="
				+ gender + ", birthday=" + birthday + ", email=" + email + ", headPortrait=" + headPortrait
				+ ", synopsis=" + synopsis + ", isDelete=" + isDelete + "]";
	}

	
	
}
