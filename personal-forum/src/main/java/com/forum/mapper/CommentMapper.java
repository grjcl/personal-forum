package com.forum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.forum.entity.Comment;

/**
 * 处理评论
 * @author 彼得·潘
 *
 */
public interface CommentMapper {

	
	//增加评论
	Integer addComment(Comment com);
	
	//删除评论
	Integer delComment(@Param("id")Integer id,@Param("uid")Integer uid,@Param("articleId")Integer articleId);

	//查找评论
	List<Comment> selComment(Integer id);
	
	//查找追加的评论
	List<Comment> selAddComment(Integer addComments);
	
	//显示最新评论
	List<Comment> showNewComment();
}
