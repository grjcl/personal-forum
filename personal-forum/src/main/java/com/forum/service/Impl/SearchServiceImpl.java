package com.forum.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.entity.Comment;
import com.forum.entity.Search;
import com.forum.mapper.CommentMapper;
import com.forum.mapper.SearchMapper;
import com.forum.service.ISearchService;
import com.forum.service.exception.DataNotFoundException;

@Service
public class SearchServiceImpl implements ISearchService{

	@Autowired
	private SearchMapper searchMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	private Integer countPerpage = COUNT_PER_PAGE;
	
	
	
	@Override
	public List<Search> findByArticle(Integer offset, Integer count) {
		return searchMapper.findByArticle(offset, count);
	}

	@Override
	public Search findById(Integer id) throws DataNotFoundException {
		Search search = searchMapper.findById(id);
		if(search==null) {
			throw new DataNotFoundException("数据异常");
		}
		//System.out.println("查找000=="+search);
		//查找评论信息
		List<Comment> com = commentMapper.selComment(id);
		for (Comment comment : com) {
			//查找追加的评论信息
			//System.out.println("id=="+comment.getId());
			List<Comment> selAddComment = commentMapper.selAddComment(comment.getId());
			//System.out.println("selAddComment=="+selAddComment);
			comment.setAddcomments(selAddComment);
		}
		//System.out.println("查找=="+com);
		//System.out.println("查找111=="+search);
		//增加到详情页中
		search.setCom(com);
		//System.out.println("查找222=="+search);
		return search;
	}

	@Override
	//获取每页文章的数量
	public Integer getCountPerPage() {
		return this.countPerpage;
	}
	
	@Override
	//查询文章的总数量
	public Integer getArticleCount() {
		return searchMapper.getArticleCount();
	}

	@Override
	public void setCountPerPage(int countPerPage) {
		if(countPerPage<5&&countPerPage>30) {
			this.countPerpage=countPerPage;
		}
	}

	@Override
	public List<Search> findByLabel(String label) {
		if("".equals(label)) {
			return null;
		}
		List<Search> findByLabel = searchMapper.findByLabel(label);
		
		return findByLabel;
	}

	
}
