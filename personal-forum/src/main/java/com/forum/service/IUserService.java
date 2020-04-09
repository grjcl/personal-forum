package com.forum.service;

import com.forum.entity.User;
import com.forum.service.exception.DuplicateKeyException;
import com.forum.service.exception.InsertException;
import com.forum.service.exception.PasswordNotMatchException;
import com.forum.service.exception.UpdateException;
import com.forum.service.exception.UserNotFoundException;

/**
 * 处理用户数据的业务层接口
 * 
 * @author 彼得·潘
 *
 */
public interface IUserService {

	/**
	 * 用户注册
	 * 
	 * @param user 用户的注册信息
	 * @return 成功注册的用户数据
	 * @throws DuplicateKeyException 违反了Unique约束的异常 
	 * @throws InsertException 插入数据异常
	 */
	User reg(User user) throws DuplicateKeyException, InsertException;

	/**
	 * 用户登录
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登录的用户数据
	 * @throws UserNotFoundException 用户数据不存在
	 * @throws PasswordNotMatchException 密码不匹配
	 */
	User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException;

	/**
	 * 修改密码
	 * 
	 * @param uid         用户id
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @throws UserNotFoundException 用户数据不存在
	 * @throws PasswordNotMatchException 密码不匹配
	 * @throws UpdateException 更新数据异常
	 */
	void changePassword(Integer uid, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException;

	/**
	 * 修改头像
	 * @param uid 用户id
	 * @param headPortrait 头像路径
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeHeadPortrait(
		    Integer uid, String headPortrait)
		        throws UserNotFoundException,
		            UpdateException;
	

	/**
	 * 修改用户个人资料
	 * 
	 * @param user 用户数据
	 * @throws UserNotFoundException 更新数据异常
	 * @throws UpdateException 更新数据异常
	 */
	void changeInfo(User user) throws UserNotFoundException, UpdateException;

	/**
	 * 根据id获取用户数据
	 * 
	 * @param id 用户id
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User getById(Integer id);

}
