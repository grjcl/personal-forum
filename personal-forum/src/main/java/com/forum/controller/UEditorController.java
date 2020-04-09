package com.forum.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理富文本
 * @author 彼得·潘
 */
@Controller
public class UEditorController {

	@RequestMapping("/")
	private String showPage() {
		return "index";
	}

	@RequestMapping(value = "/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/ueditor/jsp";
		//System.out.println("路径++"+rootPath);
		try {
			response.setCharacterEncoding("UTF-8");
			String exec = new ActionEnter(request, rootPath).exec();
			
			PrintWriter writer = response.getWriter();
			writer.write(new ActionEnter(request, rootPath).exec());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
