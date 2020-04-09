package com.forum.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.entity.Article;
import com.forum.entity.ResponseResult;
import com.forum.service.IArticleService;
/**
 * 处理消息内容的接口
 * @author 彼得·潘
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{

	@Autowired
	private IArticleService articleService;
	
	@PostMapping("/add.do")
	public ResponseResult<Void> add(Article article,HttpSession session){
		
		//封装数据
		article.setUid(getUidFromSession(session));
		article.setCreatedUser(getUsernameFromSession(session));
		article.setModifiedUser(getUsernameFromSession(session));
		System.out.println("发布=="+article);
		articleService.addArticle(article);
		return new ResponseResult<Void>(SUCCESS);
		
		
		
	}
	
	@GetMapping("/showNewArticle.do")
	//显示用户的最新的文章（登录后）
	public ResponseResult<List<Article>> showNewArticle(HttpSession session) {
		
		
		Integer uid=getUidFromSession(session);
		List<Article> showNewArticle = articleService.showNewArticle(100);
		if(showNewArticle.size()==0) {
			return new ResponseResult<List<Article>>(401, "暂未找到数据");
		}else {
			return new ResponseResult<List<Article>>(SUCCESS,showNewArticle);
		}
	}

	@GetMapping("/showHotArticle.do")
	//显示热门文章
	public ResponseResult<List<Article>> showHotArticle() {
		 List<Article> showHotArticle = articleService.showHotArticle();
		if(showHotArticle.size()==0) {
			return new ResponseResult<List<Article>>(401, "暂未找到数据");
		}else {
			return new ResponseResult<List<Article>>(SUCCESS,showHotArticle);
		}
	}

	@GetMapping("/showHotReviewsArticle.do")
	//显示热评文章
	public ResponseResult<List<Article>> showHotReviewsArticle() {
		 List<Article> showHotReviewsArticle = articleService.showHotReviewsArticle();
		if(showHotReviewsArticle.size()==0) {
			return new ResponseResult<List<Article>>(401, "暂未找到数据");
		}else {
			return new ResponseResult<List<Article>>(SUCCESS,showHotReviewsArticle);
		}
	}
}
