package com.forum.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.entity.Praise;
import com.forum.service.IPraiseService;

/**
 * 处理点赞的接口
 * @author 彼得·潘
 */
@RequestMapping("/praise")
@RestController
public class PraiseController extends BaseController{

	@Autowired
	private IPraiseService praiseService;
	
	@PostMapping("/ToDealWithPraise.do")
	public Integer ToDealWithPraise(Praise praise,HttpSession session){
		//4项日志
		if(getUsernameFromSession(session)==null) {
			//未找到登录用户信息
			return null;
		}
		praise.setUid(getUidFromSession(session));
		Date now = new Date();
		praise.setCreatedUser(getUsernameFromSession(session));
		praise.setCreatedTime(now);
		praise.setModifiedUser(getUsernameFromSession(session));
		praise.setModifiedTime(now);
		//System.out.println("点赞日志=="+praise.toString());
		praiseService.ToDealWithPraise(praise);
		Integer countPraise = countPraise(praise.getArticleId());
		System.out.println(countPraise);
		//返回点赞数量
		return countPraise;
		
	};

	//统计文章的赞数
	@GetMapping("/countPraise.do")
	public Integer countPraise(Integer articleId) {
		return praiseService.countPraise(articleId);
	};
	
}
