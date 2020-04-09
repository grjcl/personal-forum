package com.forum.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.forum.entity.Search;
import com.forum.mapper.SearchMapper;
import com.forum.service.ISearchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Autowired
	private ISearchService searchService;
	
	@org.junit.Test
	public void findAll() {
		List<Search> findByArticle = searchMapper.findByArticle(0, 2);
		System.out.println("长度数量--"+findByArticle.size());
		for (Search search : findByArticle) {
			System.out.println("列表=="+search);
		}
	}

	@Test
	public void findOne() {
		
		Integer id = 10;
		Search findById = searchService.findById(id);
		System.out.println("详情数据=="+findById);

		
//		Search findByIdToMapper = searchMapper.findById(id);
//		System.out.println("详情数据=="+findByIdToMapper);
	}
	
	@Test
	public void findOnel() {
		
		List<Search> findByLabel = searchService.findByLabel("时间");
		System.out.println("详情数据=="+findByLabel);

	}
	
}
