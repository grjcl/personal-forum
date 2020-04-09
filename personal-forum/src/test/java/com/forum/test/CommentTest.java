package com.forum.test;


import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.forum.entity.Collection;
import com.forum.entity.Comment;
import com.forum.entity.Praise;
import com.forum.mapper.CommentMapper;
import com.forum.service.ICollectionService;
import com.forum.service.ICommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {
	
	@Autowired
	private ICommentService commentService;
	
	
	@Autowired
	private CommentMapper commentMapper;
	
	
	@org.junit.Test
	public void addComment() {
		for (int i = 0; i < 2; i++) {
			Comment p = new Comment();
			p.setCommentAvatar("头像="+i);
			p.setCommentArticle("feichanghao"+i+i);
			p.setArticleId(10);
			p.setAddComments(12);
			p.setUid(2);
			Date now = new Date();
			p.setCreatedUser("zs");
			p.setCreatedTime(now);
			p.setModifiedUser("zs");
			p.setModifiedTime(now);
			Integer toDealWithPraise = commentService.addComment(p);
			System.out.println("返回值=="+toDealWithPraise);
		}
		
		
	}

	
	
	@org.junit.Test
	public void selComment() {
		
		List<Comment> toDealWithPraise = commentMapper.selComment(10);
		System.out.println(toDealWithPraise);
		for (Comment comment : toDealWithPraise) {
			System.out.println("返回值=="+comment);
		}
		
		
	}
	
	@org.junit.Test
	public void selAddComment() {
		
		List<Comment> toDealWithPraise = commentMapper.selAddComment(12);
		System.out.println(toDealWithPraise);
		for (Comment comment : toDealWithPraise) {
			System.out.println("返回值=="+comment);
		}
		
		
	}
	
	@org.junit.Test
	public void delComment() {
		
		Integer toDealWithPraise = commentService.delComment(2,2, 12);
		System.out.println("返回值=="+toDealWithPraise);
		
	}
	
}
