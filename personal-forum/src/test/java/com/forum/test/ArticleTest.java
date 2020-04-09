package com.forum.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.forum.entity.Article;
import com.forum.mapper.ArticleMapper;
import com.forum.service.IArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	
	
	@Test
	public void add() {
		for (int i = 0; i < 50; i++) {
			Article article=new Article(i, i, "建筑风格ya", "14561","建筑，样式,风格","非常好，不错，nice",1);
			//article.setCreatedTime(new Date());
			article.setCreatedUser("李四");
			//article.setModifiedTime(new Date());
			article.setModifiedUser("李四");
			articleService.addArticle(article);
		}
		
	}
	
	@Test
	public void get() {
		List<Article> articleById = articleService.getArticleById(4);
		System.out.println("查询=="+articleById);
		List<Article> articleByLabel = articleService.getArticleByLabel("建筑");
		System.out.println("查询=="+articleByLabel);
		List<Article> articleByUid = articleService.getArticleByUid(1);
		System.out.println("查询=="+articleByUid);
		
	}
	
	@Test
	public void del() {
//		Article article=new Article(4, 1, "建筑风格ya11", "建筑11样式风11格","非常好11不错，ni11ce",0);
//		article.setModifiedUser("张三");
//		articleService.upArticle(article);
		System.out.println(articleService.delArticle(4, 1));
	}
	
}
