package com.forum.service.Impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.forum.entity.User;
import com.forum.service.IUserService;
import com.forum.service.exception.DuplicateKeyException;
import com.forum.service.exception.InsertException;
import com.forum.service.exception.PasswordNotMatchException;
import com.forum.service.exception.UpdateException;
import com.forum.service.exception.UserNotFoundException;

import com.forum.mapper.UserMapper;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		
		// 判断查询到的数据是否为null
		if (findByUsername(user.getUsername())!=null) {
			// 用户名已被占用
			throw new DuplicateKeyException(
				"注册失败！尝试注册的用户名(" + user.getUsername() + ")已经被占用！");
			
		}else if(findByEmail(user.getEmail()) !=null){
			// true 表示用户已注册
			// 则抛出异常
			// 用户名已被占用
			throw new DuplicateKeyException("注册失败！尝试注册的邮箱已经被占用！");
		}else{
			// 是否已经删除：否
			user.setIsDelete(0); 
			// 4项日志
			Date now = new Date();
			user.setCreatedUser(user.getUsername());
			user.setCreatedTime(now);
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);
		
			// 获取随机的UUID作为盐值
			String salt = UUID.randomUUID().toString().toUpperCase();
			// 获取用户提交的原始密码
			String srcPassword = user.getPassword();
			// 基于原始密码和盐值执行加密，获取通过MD5加密的密码
			String md5Password = getMd5Password(srcPassword, salt);
			// 将加密后的密码封装在user对象中
			user.setPassword(md5Password);
			// 将盐值封装在user对象中
			user.setSalt(salt);
			// 执行注册
			addnew(user);
			// 返回注册的用户对象
			return user;
		} 
	}
	
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		// 根据参数username查询用户数据
		User data = findByUsername(username);
		// 判断用户数据是否为null
		if (data == null) {
			// 用户名不存在
			throw new UserNotFoundException(
				"用户名或密码错误！");
		}
		
		// 取出盐值
		String salt = data.getSalt();
		// 	对参数password执行加密
		String md5Password 
			= getMd5Password(password, salt);
		
		// 	判断密码是否匹配
		if (data.getPassword().equals(md5Password)) {
			//判断是否被删除
			if (data.getIsDelete() == 1) {
				// 是：已被删除，则抛出异常
				throw new UserNotFoundException(
					"登录失败！您尝试登录的用户数据已经被删除！");
			}
			
			// 登录成功，将第1步查询的用户数据中的盐值和密码设置为null
			data.setPassword(null);
			data.setSalt(null);
			// 返回用户数据
			return data;
		}else {
			// 密码错误
			throw new PasswordNotMatchException(
				"用户名或密码错误！");
		}
	}
	
	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		// 根据uid查询用户数据
		User data = findById(uid);
		// 判断查询结果是否为null
		if (data == null) {
		    //数据不存在
			throw new UserNotFoundException(
				"修改密码失败！您尝试访问的用户数据不存在！");
		}

		
		// 取出查询结果中的盐值
		String salt = data.getSalt();
		// 对参数oldPassword执行MD5加密
		String oldMd5Password 
			= getMd5Password(oldPassword, salt);
		// 将加密结果与查询结果中的password对比是否匹配
		if (data.getPassword().equals(oldMd5Password)) {
			String newMd5Password
				= getMd5Password(newPassword, salt);
			// 获取当前时间
			Date now = new Date();
			// 更新密码
			updatePassword(
				uid, newMd5Password, 
				data.getUsername(), now);
		} else {
			throw new PasswordNotMatchException(
				"修改密码失败！原密码错误！");
		}
	}
	
	@Override
	public void changeHeadPortrait(Integer uid, String avatar) throws UserNotFoundException, UpdateException {
		// 根据user.getId()查询用户数据
		User data = findById(uid);
		// 判断数据是否为null
		if (data == null) {
			// 是：抛出：UserNotFoundException
			throw new UserNotFoundException(
				"修改头像失败！您尝试访问的用户数据不存在！");
		}

		// 判断is_delete是否为1
		if (data.getIsDelete() == 1) {
			// 是：抛出：UserNotFoundException
			throw new UserNotFoundException(
				"修改头像失败！您尝试访问的用户数据已经被删除！");
		}

		// 执行更新头像
		String modifiedUser = data.getUsername();
		Date modifiedTime = new Date();
		updateHeadPortrait(uid, avatar, modifiedUser, modifiedTime);
	}
	
	/**
	 * 更新头像
	 * @param uid 用户的id
	 * @param avatar 新头像路径
	 * @param modifiedUser 修改执行人
	 * @param modifiedTime 修改时间
	 */
	private void updateHeadPortrait(
			Integer uid, String avatar,
			String modifiedUser,
			Date modifiedTime) {
		Integer rows = userMapper.updateHeadPortrait(
				uid, avatar, modifiedUser, modifiedTime);
		if (rows != 1) {
			throw new UpdateException(
					"更新头像时出现未知错误！");
		}
	}
	
	@Override
	public void changeInfo(User user) 
			throws UserNotFoundException, UpdateException {
		// 根据user.getId()查询用户数据
		User data = findById(user.getId());
		// 判断数据是否为null
		System.out.println("user--"+user);
		System.out.println("data--"+data);
		if (data == null) {
			// 是：抛出：UserNotFoundException
			throw new UserNotFoundException(
				"修改个人资料失败！您尝试访问的用户数据不存在！");
		}

		// 判断is_delete是否为1
		if (data.getIsDelete() == 1) {
			// 是：抛出：UserNotFoundException
			throw new UserNotFoundException(
				"修改个人资料失败！您尝试访问的用户数据已经被删除！");
		}
		// 向参数对象中封装：
		user.setModifiedUser(data.getUsername());
		user.setModifiedTime(new Date());
		
		// 执行修改
		updateInfo(user);
	}
	
	@Override
	public User getById(Integer id) {
		User data = findById(id);
		data.setPassword(null);
		data.setSalt(null);
		data.setIsDelete(null);
		return data;
	}
	
	/**
	 * 获取根据MD5加密的密码
	 * @param srcPassword 原密码
	 * @param salt 盐值
	 * @return 加密后的密码
	 */
	private String getMd5Password(
			String srcPassword, String salt) {
		String str = salt + srcPassword + salt;
		// 循环执行10次摘要运算
		for (int i = 0; i < 10; i++) {
			str = DigestUtils
				.md5DigestAsHex(str.getBytes())
					.toUpperCase();
		}
		// 返回摘要结果
		return str;
	}
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @throws InsertException
	 */
	private void addnew(User user) {
		Integer rows = userMapper.addnew(user);
		if (rows != 1) {
			throw new InsertException(
				"增加用户数据时出现未知错误！");
		}
	}
	
	/**
	 * 更新密码
	 * @param uid 用户的id
	 * @param password 新密码
	 * @param modifiedUser 修改执行人
	 * @param modifiedTime 修改时间
	 */
	private void updatePassword(
			Integer uid, String password,
			String modifiedUser,
			Date modifiedTime) {
		Integer rows = userMapper.updatePassword(
				uid, password, modifiedUser, modifiedTime);
		if (rows != 1) {
			throw new UpdateException(
				"更新密码时出现未知错误！");
		}
	}
	
	
	/**
	 * 更新用户的个人资料
	 * @param user 用户数据
	 */
	private void updateInfo(User user) {
		System.out.println("user=="+user);
		// 执行更新，获取返回值
		Integer rows = userMapper.updateInfo(user);
		// 判断返回值，出错时抛出“更新时的未知错误”
		if (rows != 1) {
			throw new UpdateException(
				"更新用户数据时出现未知错误！");
		}
	}
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findByEmail(String email) {
		return userMapper.findByEmail(email);
	}
	
	/**
	 * 根据用户id查询用户数据
	 * @param id 用户id
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findById(Integer id) {
		return userMapper.findById(id);
	}




	
	
}





