package com.forum.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.entity.Comment;
import com.forum.mapper.CommentMapper;
import com.forum.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public Integer addComment(Comment com) {
		//判断是否为追加评论
		if(com.getAddComments()==null) {
			//则为最新评论，设置父评论ID为0
			com.setAddComments(0);
		}
		return commentMapper.addComment(com);
	}

	@Override
	public Integer delComment(Integer id,Integer uid, Integer articleId) {
		// TODO Auto-generated method stub
		return commentMapper.delComment(id,uid, articleId);
	}

	@Override
	public List<Comment> showNewComment() {
		// TODO Auto-generated method stub
		return commentMapper.showNewComment();
	}

}
