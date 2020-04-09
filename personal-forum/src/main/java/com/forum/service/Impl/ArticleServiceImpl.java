package com.forum.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.entity.Article;
import com.forum.mapper.ArticleMapper;
import com.forum.service.IArticleService;
import com.forum.service.exception.InsertException;

@Service
public class ArticleServiceImpl implements IArticleService{

	@Autowired
	private ArticleMapper articleMapper;
	
	//增加发布信息
	@Override
	public Integer addArticle(Article article) throws InsertException{
		//添加内容创建时间
		article.setCreatedTime(new Date());
		article.setModifiedTime(new Date());
		Integer addArticle = articleMapper.addArticle(article);
		if(addArticle==1) {
			return 1;
		}else {
			throw new InsertException("发布失败");
		}
	}

	@Override
	public List<Article> getArticleByLabel(String label) {
		return articleMapper.getArticleByLabel(label);
	}

	@Override
	public List<Article> getArticleById(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleById(id);
	}

	@Override
	public List<Article> getArticleByUid(Integer uid) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleByUid(uid);
	}

	@Override
	public Integer upArticle(Article article) {
		return articleMapper.upArticle(article);
	}

	@Override
	public Integer delArticle(Integer id, Integer uid) {
		// TODO Auto-generated method stub
		return articleMapper.delArticle(id, uid);
	}

	@Override
	public List<Article> showNewArticle(Integer uid) {
		// TODO Auto-generated method stub
		return articleMapper.showNewArticle(uid);
	}

	@Override
	public List<Article> showHotArticle() {
		// TODO Auto-generated method stub
		return articleMapper.showHotArticle();
	}

	@Override
	public List<Article> showHotReviewsArticle() {
		// TODO Auto-generated method stub
		return articleMapper.showHotReviewsArticle();
	}

}
