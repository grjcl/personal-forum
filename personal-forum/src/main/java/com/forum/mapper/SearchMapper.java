package com.forum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.forum.entity.Search;

/**
 * 
 * @author 彼得·潘
 */
public interface SearchMapper {
	/**
	 * 查询文章列表
	 * @param categoryId 文章分类的id
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
	 * 查询文章的总数量
	 * @return 返回文章的总数量
	 */
	Integer getArticleCount();
	
	/**
	 * 根据id查询文章详情
	 * @param id 文章的id
	 * @return 文章详情，如果没有匹配的数据，则返回null
	 */
	Search findById(Integer id);
	
	
	
}
