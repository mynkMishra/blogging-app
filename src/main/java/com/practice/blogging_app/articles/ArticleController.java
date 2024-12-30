package com.practice.blogging_app.articles;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	private ArticlesService articlesService;
	
	public ArticleController(ArticlesService articlesService) {
		this.articlesService = articlesService;
	}
	
	@GetMapping("")
	public ResponseEntity<ArrayList<ArticleEntity>> getArticles(
			@RequestParam(required = false, defaultValue = "") 
			@Parameter(name = "authorId" )
			Optional<Long> authorId,
			@RequestParam(required = false, defaultValue = "1") 
			@Parameter(name = "page")
			Integer page,
			@RequestParam(required = false, defaultValue = "10") 
			@Parameter(name = "size")
			Integer size
			){
		
		var articles = (ArrayList<ArticleEntity>)articlesService.getAllArticles(page, size, authorId);
		
		return ResponseEntity.ok(articles);
	}
}
