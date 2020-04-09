package com.forum.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.forum.controller.exception.FileEmptyException;
import com.forum.controller.exception.FileSizeOutOfLimitException;
import com.forum.entity.ResponseResult;
import com.forum.entity.User;
import com.forum.service.IUserService;
/**
 * 处理用户的请求接口
 * @author 彼得·潘
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	/**
	 * 上传文件夹的名称
	 */
	private static final String UPLOAD_DIR_NAME = "upload";
	
	

	@Autowired
	private IUserService userService;

	//用户注册
	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user) {
		//System.out.println("注册参数=="+user.toString());
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}

	@PostMapping("/login.do")
	public ResponseResult<User> handleLogin(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		// 执行登录
		User user = userService.login(username, password);
		System.out.println("user登录=="+user);
		// 将相关信息存入到Session
		session.setAttribute("uid", user.getId());
		session.setAttribute("username", user.getUsername());
		// 返回
		return new ResponseResult<>(SUCCESS, user);
	}

	@RequestMapping("/password.do")
	public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword, HttpSession session) {
		// 获取当前登录的用户的id
		Integer uid = getUidFromSession(session);
		// 执行修改密码
		userService.changePassword(uid, oldPassword, newPassword);
		// 返回
		return new ResponseResult<>(SUCCESS);
	}

	//根据id获取用户数据
	@RequestMapping("/info.do")
	public ResponseResult<User> getInfo(HttpSession session) {
		// 获取当前登录的用户的id
		Integer id = getUidFromSession(session);
		// 执行查询，获取用户数据
		User user = userService.getById(id);
		//System.out.println("user=="+user);
		// 返回
		return new ResponseResult<User>(SUCCESS, user);
	}

	@PostMapping("/change_info.do")
	public ResponseResult<Void> changeInfo(User user, HttpSession session) {
		// 获取当前登录的用户的id
		Integer id = getUidFromSession(session);
		// 将id封装到参数user中，因为user是用户提交的数据，并不包含id
		user.setId(id);
		// 执行修改
		userService.changeInfo(user);
		// 返回
		return new ResponseResult<>(SUCCESS);
	}

	@PostMapping("/upload.do")
	public ResponseResult<String> handleUpload(
		HttpSession session,
		@RequestParam("images") MultipartFile file) {
		// 检查是否存在上传文件 > file.isEmpty()
		if (file.isEmpty()) {
			// 抛出异常：文件不允许为空
			throw new FileEmptyException(
				"上传失败！没有选择上传的文件，或选中的文件为空！");
		}

		
		// 确定上传文件夹 > session.getServletContext.getRealPath(UPLOAD_DIR_NAME) > exists() > mkdirs()
		String parentPath = session
			.getServletContext().getRealPath(UPLOAD_DIR_NAME);
		File parent = new File(parentPath);
		if (!parent.exists()) {
			parent.mkdirs();
		}

		// 确定文件名 > getOriginalFileName()
		String originalFileName = file.getOriginalFilename();
		int beginIndex = originalFileName.lastIndexOf(".");
		String suffix = originalFileName.substring(beginIndex);
		String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(90000000) + 10000000) + suffix;
		
		// 确定文件
		File dest = new File(parent, fileName);

		// 执行保存文件
		try {
			file.transferTo(dest);
			System.err.println("上传完成！");
		} catch (IllegalStateException e) {
			// 抛出异常：上传失败
			throw new FileSizeOutOfLimitException("上传失败");
		} catch (IOException e) {
			// 抛出异常：上传失败
			throw new FileSizeOutOfLimitException("上传失败");
		}

		// 获取当前用户的id
		Integer uid = getUidFromSession(session);
		// 更新头像数据
		String headPortrait = "/" + UPLOAD_DIR_NAME + "/" + fileName;
		userService.changeHeadPortrait(uid, headPortrait);

		// 返回
		ResponseResult<String> rr
			= new ResponseResult<>();
		rr.setState(SUCCESS);
		rr.setData(headPortrait);
		return rr;
	}
	
	// 退出登录
	@RequestMapping("/logout.do")
	public void handleLogout(HttpServletRequest request, HttpSession session) {
		// 清除Session中的所有数据
		session.invalidate();
		
	}
	

}
