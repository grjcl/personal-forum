package com.forum.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.forum.entity.Comment;

/**
 * 
 * @author 彼得·潘
 */
public interface ICommentService {

	// 增加评论
	Integer addComment(Comment com);

	// 删除评论
	Integer delComment(@Param("id") Integer id, @Param("uid") Integer uid, @Param("articleId") Integer articleId);
	
	//显示最新评论
	List<Comment> showNewComment();
}
