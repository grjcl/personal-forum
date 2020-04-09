package com.forum.service;

import java.util.List;

import com.forum.entity.Article;
/**
 * 
 * @author 彼得·潘
 *
 */
public interface IArticleService {

	/**
	 * 增加发布信息
	 * @param article 发布的信息
	 * @return 返回 1表示成功
	 *             0表示失败
	 * @throws InsertException 发布失败
	 */
	Integer addArticle(Article article);
	
	/**
	 * 根据标签查找发布的信息
	 * @param label 标签
	 * @return 返回内容集合
	 */
	List<Article> getArticleByLabel(String label);
	
	/**
	 * 根据id查找内容
	 * @param id 内容id
	 * @return 返回内容
	 */
	List<Article> getArticleById(Integer id);
	
	/**
	 * 根据uid查找内容
	 * @param uid 用户id
	 * @return 返回内容
	 */
	List<Article> getArticleByUid(Integer uid);
	
	/**
	 * 根据id和uid修改内容
	 * @param Article 参数
	 * @return 返回 1表示成功
	 *             0表示失败
	 */
	Integer upArticle(Article article);
	
	/**
	 * 根据id删除指定内容
	 * @param id 要删除内容的id
	 * @param uid 用户id
	 * @return 返回 1表示成功
	 *             0表示失败
	 */
	Integer delArticle(Integer id,Integer uid);
	
	/**
	 * 显示用户的最新的文章（登录后）
	 * @param uid 用户的id
	 * @return 返回用户最新文章列表
	 */
	List<Article> showNewArticle(Integer uid);
	
	/**
	 * 显示热门文章
	 * @return 返回热门文章列表
	 */
	List<Article> showHotArticle();
	
	/**
	 * 显示热评文章
	 * @return 返回热评文章列表
	 */
	List<Article> showHotReviewsArticle();
	
}
