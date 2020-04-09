package com.forum.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.entity.Comment;
import com.forum.entity.ResponseResult;
import com.forum.service.ICommentService;

/**
 * 处理评论接口
 * 
 * @author 彼得·潘
 */
@RestController
@RequestMapping("/com")
public class CommentController extends BaseController{

	@Autowired
	private ICommentService commentService;

	// 增加评论
	@PostMapping("addCom.do")
	public ResponseResult<Void> addComment(Comment com,HttpSession session) {
		com.setUid(getUidFromSession(session));
		Date now = new Date();
		com.setCreatedUser(getUsernameFromSession(session));
		com.setCreatedTime(now);
		com.setModifiedUser(getUsernameFromSession(session));
		com.setModifiedTime(now);
		System.out.println("评论数据=="+com.toString());
		Integer addComment = commentService.addComment(com);
		if(addComment==1) {
			return new ResponseResult<Void>(SUCCESS);
		}else {
		    return new ResponseResult<Void>(500);
		}

	};

	// 删除评论
	@PostMapping("/delCom.do")
	public ResponseResult<Void> delComment(Integer id, Integer uid, Integer articleId) {
		Integer delComment = commentService.delComment(id, uid, articleId);
		if(delComment==1) {
			return new ResponseResult<Void>(SUCCESS);
		}else {
		    return new ResponseResult<Void>(500);
		}

	};
	@GetMapping("/showNewComment.do")
	public ResponseResult<List<Comment>> showNewComment() {
		// TODO Auto-generated method stub
		List<Comment> showNewComment = commentService.showNewComment();
		if(showNewComment.size()==0) {
			return new ResponseResult<List<Comment>>(401, "暂未找到数据");
		}else {
			return new ResponseResult<List<Comment>>(SUCCESS,showNewComment);
		}
	}
}