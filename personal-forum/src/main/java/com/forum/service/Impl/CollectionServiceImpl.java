package com.forum.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.entity.Collection;
import com.forum.mapper.CollectionMapper;
import com.forum.service.ICollectionService;

@Service
public class CollectionServiceImpl implements ICollectionService{

	@Autowired
	private CollectionMapper collectionMapper;
	
	@Override
	public Integer ToDealWithCollection(Collection collection) {
		Integer uid = collection.getUid();
		Integer articleId = collection.getArticleId();
		//判断用户是否对该文章收藏
		Integer selPraise = selCollection(uid, articleId);
		if(selPraise==1) {
			//返回1表示该用户已对该文章收藏
			//则应响应为取消收藏
			delCollection(uid, articleId);
			return 0;
		}else {
			//返回0表示该用户未对该文章收藏
			//则相应为增加收藏
			addCollection(collection);
			return 1;
		}
		
	}
	
	public Integer addCollection(Collection collection) {
		// TODO Auto-generated method stub
		return collectionMapper.addCollection(collection);
	}

	
	public Integer delCollection(Integer uid, Integer articleId) {
		// TODO Auto-generated method stub
		return collectionMapper.delCollection(uid, articleId);
	}

	
	public Integer selCollection(Integer uid, Integer articleId) {
		// TODO Auto-generated method stub
		return collectionMapper.selCollection(uid, articleId);
	}

	@Override
	public Integer countCollection(Integer articleId) {
		// TODO Auto-generated method stub
		return collectionMapper.countCollection(articleId);
	}

	

	

}
