package com.forum.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.entity.Praise;
import com.forum.mapper.PraiseMapper;
import com.forum.service.IPraiseService;

@Service
public class PraiseServiceImpl implements IPraiseService{

	@Autowired
	private PraiseMapper praiseMapper;
	
	@Override
	public Integer ToDealWithPraise(Praise praise) {
		Integer uid = praise.getUid();
		Integer articleId = praise.getArticleId();
		//判断用户是否对该文章点赞
		Integer selPraise = selPraise(uid, articleId);
		
		if(selPraise==1) {
			//返回1表示该用户已对该文章点赞
			//则应响应为取消点赞
			delPraise(uid, articleId);
			return 0;
		}else {
			//返回0表示该用户未对该文章点赞
			//则相应为增加点赞
			addPraise(praise);
			return 1;
		}
		
	}
	
	public Integer addPraise(Praise praise) {
		// TODO Auto-generated method stub
		return praiseMapper.addPraise(praise);
	}

	
	public Integer delPraise(Integer uid, Integer articleId) {
		// TODO Auto-generated method stub
		return praiseMapper.delPraise(uid, articleId);
	}

	
	public Integer selPraise(Integer uid, Integer articleId) {
		// TODO Auto-generated method stub
		return praiseMapper.selPraise(uid, articleId);
	}

	@Override
	public Integer countPraise(Integer articleId) {
		// TODO Auto-generated method stub
		return praiseMapper.countPraise(articleId);
	}

	

	

}
