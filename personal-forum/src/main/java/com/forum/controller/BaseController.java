package com.forum.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forum.controller.exception.FileEmptyException;
import com.forum.controller.exception.FileSizeOutOfLimitException;
import com.forum.controller.exception.FileTypeNotSupportException;
import com.forum.controller.exception.FileUploadException;
import com.forum.controller.exception.RequestException;
import com.forum.entity.ResponseResult;
import com.forum.service.exception.AccessDeniedException;
import com.forum.service.exception.DeleteException;
import com.forum.service.exception.DuplicateKeyException;
import com.forum.service.exception.InsertException;
import com.forum.service.exception.PasswordNotMatchException;
import com.forum.service.exception.ServiceException;
import com.forum.service.exception.UpdateException;
import com.forum.service.exception.UserNotFoundException;

/**
 * 当前项目中所有控制器类的基类
 */
public abstract class BaseController {
	
	/**
	 * 正确响应时的代号
	 */
	public static final Integer SUCCESS = 200;

	@ExceptionHandler({ServiceException.class, 
		RequestException.class})
	@ResponseBody
	public ResponseResult<Void> handleException(
			Exception e) {
		Integer state = null;
		
		if (e instanceof DuplicateKeyException) {
			// 400-违反了Unique约束的异常
			state = 400;
		} else if (e instanceof UserNotFoundException) {
			// 401-用户数据不存在
			state = 401;
		} else if (e instanceof PasswordNotMatchException) {
			// 402-密码错误
			state = 402;
		} else if (e instanceof AccessDeniedException) {
			// 404-访问被拒绝的异常
			state = 404;
		} else if (e instanceof InsertException) {
			// 500-插入数据异常
			state = 500;
		} else if (e instanceof UpdateException) {
			// 501-更新数据异常
			state = 501;
		} else if (e instanceof DeleteException) {
			// 502-删除数据异常
			state = 502;
		} else if (e instanceof FileEmptyException) {
			// 600-上传的文件为空的异常
			state = 600;
		} else if (e instanceof FileSizeOutOfLimitException) {
			// 601-上传的文件超出了限制的异常
			state = 601;
		} else if (e instanceof FileTypeNotSupportException) {
			// 602-上传的文件类型不支持的异常
			state = 602;
		} else if (e instanceof FileUploadException) {
			// 610-文件上传异常
			state = 610;
		}
		
		return new ResponseResult<>(state, e);
	}
	
	/**
	 * 从Session中获取uid
	 * @param session HttpSession对象
	 * @return 当前登录的用户的id
	 */
	protected Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(
				session.getAttribute("uid").toString());
	}
	
	/**
	 * 从Session中获取uid
	 * @param session HttpSession对象
	 * @return 当前登录的用户的id
	 */
	protected String getUsernameFromSession(HttpSession session) {
		return String.valueOf(
				session.getAttribute("username").toString());
	}
	
}




