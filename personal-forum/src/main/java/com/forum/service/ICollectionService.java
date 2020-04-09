package com.forum.service;

import com.forum.entity.Collection;

/**
 * 
 * @author 彼得·潘
 */
public interface ICollectionService {

	/**
	 * 处理收藏
	 * @param praise
	 * @return  返回0取消收藏
	 *          返回1增加收藏
	 */
	Integer ToDealWithCollection(Collection collection);
	
	/**
	 * 统计文章收藏的数量
	 * @param articleId 文章id
	 * @return 返回收藏文章的数量
	 */
	Integer countCollection(Integer articleId);
}
