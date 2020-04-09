package com.forum.mapper;

import org.apache.ibatis.annotations.Param;

import com.forum.entity.Praise;

public interface PraiseMapper {

	
	//增加点赞
	Integer addPraise(Praise praise);
	
	//删除点赞（当用户已对该文章点过赞）
	Integer delPraise(@Param("uid")Integer uid,@Param("articleId")Integer articleId);
	
	/**
	 * 查询用户是否点赞
	 * @param uid
	 * @param articleId
	 * @return 返回1表示该用户已对该文章点赞
	 *                                      返回0表示该用户未对该文章点赞
	 */
	Integer selPraise(@Param("uid")Integer uid,@Param("articleId")Integer articleId);
	
	/**
	 * 统计文章的赞数
	 * @param articleId 文章id
	 * @return 返回文章点赞数量
	 */
	Integer countPraise(Integer articleId);
	
}
