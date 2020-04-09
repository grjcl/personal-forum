package com.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.entity.ResponseResult;
import com.forum.entity.Search;
import com.forum.service.ISearchService;

/**
 * 处理查找的接口实现
 * @author 彼得·潘
 */
@RestController
@RequestMapping("/search")
public class SearchController extends BaseController{

	
	@Autowired
	private ISearchService searchService;
	
	//返回页数
	@GetMapping("/pages.do")
	public Integer pages() {
		// 文章的总数量
		Integer articleCount = searchService.getArticleCount();
		//总页数
		Integer pages;
		// 每页显示的文章数量
		Integer countPerPage = searchService.getCountPerPage();
		// 计算总页数
		pages = articleCount / countPerPage;
		pages += articleCount % countPerPage == 0 ? 0 : 1;
		System.out.println("总页数=="+pages);
		return pages;
		
	}
	
	//查询文章列表
	@GetMapping("/findByArticle.do")
	public ResponseResult<List<Search>> findByArticle(Integer page){
		if (page == null || page < 1) {
			page = 1;
		}
		//列表
		List<Search> searchList;
	
		// 每页显示的文章数量
		Integer countPerPage = searchService.getCountPerPage();
		
		//计算出偏移量
		Integer count=page*countPerPage;
		Integer offset=(page-1)*countPerPage;
		//System.out.println(offset+"==="+count);
		searchList=searchService.findByArticle(offset, count);
		//System.out.println("文章列表=="+searchList);
		if(searchList.size()==0) {
			return new ResponseResult<List<Search>>(401,"暂无文章");
		}else {
			return new ResponseResult<List<Search>>(SUCCESS,searchList);
		}
		
	};

	//查询文章列表
	@GetMapping("/findByLabel.do")
	public ResponseResult<List<Search>> findByArticle(String label){
		
		List<Search> searchList;
		searchList=searchService.findByLabel(label.replaceAll(" ", ""));
		//System.out.println("标志=="+label.replaceAll(" ", ""));
		//System.out.println("搜索文章列表=="+searchList);
		if(searchList.size()==0) {
			return new ResponseResult<List<Search>>(401,"暂无文章");
		}else {
			return new ResponseResult<List<Search>>(SUCCESS,searchList);
		}
		
	};
	
	/**
	 * 根据id查询文章详情
	 * @param id 文章的id
	 * @return 文章详情，如果没有匹配的数据，则返回null
	 */
	@GetMapping("/findById.do")
	public ResponseResult<Search> findById(Integer id) {
		Search findById = searchService.findById(id);
		if(findById==null) {
			return new ResponseResult<Search>(501, "数据异常");
		}else {
			return new ResponseResult<Search>(SUCCESS,findById);
		}
		
		
	};
}
