package com.forum.mapper;

import org.apache.ibatis.annotations.Param;

import com.forum.entity.Collection;

public interface CollectionMapper {

	
	//增加收藏
	Integer addCollection(Collection collection);
	
	//删除收藏（当用户已对该文章点过赞）
	Integer delCollection(@Param("uid")Integer uid,@Param("articleId")Integer articleId);
	
	/**
	 * 查询用户是否收藏
	 * @param uid
	 * @param articleId
	 * @return 返回1表示该用户已对该文章收藏
	 *                                      返回0表示该用户未对该文章收藏
	 */
	Integer selCollection(@Param("uid")Integer uid,@Param("articleId")Integer articleId);
	
	/**
	 * 统计文章收藏的数量
	 * @param articleId 文章id
	 * @return 返回收藏文章的数量
	 */
	Integer countCollection(Integer articleId);
	
}
