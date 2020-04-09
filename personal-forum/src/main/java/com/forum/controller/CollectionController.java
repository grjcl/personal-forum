package com.forum.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.entity.Collection;
import com.forum.service.ICollectionService;

/**
 * 处理收藏的接口
 * @author 彼得·潘
 */
@RequestMapping("/coll")
@RestController
public class CollectionController extends BaseController{

	@Autowired
	private ICollectionService collectionService;
	
	@PostMapping("/ToDealWithCollection.do")
	public Integer ToDealWithCollection(Collection collection,HttpSession session){
		System.out.println(collection);
		//4项日志
	System.out.println("session==="+session);
		if(getUsernameFromSession(session)==null) {
			//未找到登录用户信息
			return null;
		}
		collection.setUid(getUidFromSession(session));
		Date now = new Date();
		collection.setCreatedUser(getUsernameFromSession(session));
		collection.setCreatedTime(now);
		collection.setModifiedUser(getUsernameFromSession(session));
		collection.setModifiedTime(now);
		System.out.println("点赞日志=="+collection.toString());
		collectionService.ToDealWithCollection(collection);
		Integer countCollection = countCollection(collection.getArticleId());
		//返回点赞数量
		return countCollection;
	};
	
	//统计文章的赞数
	@GetMapping("/countCollection.do")
	public Integer countCollection(Integer articleId) {
		return collectionService.countCollection(articleId);
	};
}
