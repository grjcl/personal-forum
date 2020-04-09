package com.forum.service;

import com.forum.entity.Praise;

/**
 * 
 * @author 彼得·潘
 */
public interface IPraiseService {

	/**
	 * 处理点赞
	 * @param praise
	 * @return  返回0取消点赞
	 *          返回1增加点赞
	 */
	Integer ToDealWithPraise(Praise praise);
	
	/**
	 * 统计文章的赞数
	 * @param articleId 文章id
	 * @return 返回文章点赞数量
	 */
	Integer countPraise(Integer articleId);
	
}
