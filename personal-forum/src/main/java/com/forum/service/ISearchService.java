package com.forum.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.forum.entity.Search;

/**
 * 
 * @author 彼得·潘
 */
public interface ISearchService {

	/**
	 * 每页显示多少条数据
	 */
	Integer COUNT_PER_PAGE =20;

	/**
	 * 设置每页的文章数量
	 * @param countPerPage 每页显示的文章数量
	 */
	void setCountPerPage(int countPerPage);

	/**
	 * 获取每页文章的数量
	 * @return 每页显示的文章数量
	 */
	Integer getCountPerPage();
	
	/**
	 * 查询文章的总数量
	 * @return 返回文章的总数量
	 */
	Integer getArticleCount();
	
	/**
	 * 查询文章列表
	 * @param offset 偏移量
	 * @param count 获取的数据的最大数量
	 * @return 文章列表
	 */
	List<Search> findByArticle(
		@Param("offset") Integer offset,
		@Param("count") Integer count);
	
	/**
	 * 查询文章列表
	 * @param label 关键字
	 * @return 文章列表
	 */
	List<Search> findByLabel(String label);

	/**
	 * 根据id查询文章详情
	 * @param id 文章的id
	 * @return 文章详情，如果没有匹配的数据，则返回null
	 */
	Search findById(Integer id);
}
