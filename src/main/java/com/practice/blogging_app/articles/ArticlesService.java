package com.practice.blogging_app.articles;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practice.blogging_app.articles.dtos.CreateArticleRequestDTO;
import com.practice.blogging_app.articles.dtos.UpdateArticleRequestDTO;
import com.practice.blogging_app.users.IUsersRepository;
import com.practice.blogging_app.users.UsersService;

@Service
public class ArticlesService {

	private IArticlesRepository articlesRepository;
	private IUsersRepository usersRepository;

	public ArticlesService(IArticlesRepository articlesRepository, IUsersRepository usersRepository) {
		this.usersRepository = usersRepository;
		this.articlesRepository = articlesRepository;
	}

	public Iterable<ArticleEntity> getAllArticles(int page, int size, Optional<Long> authorId) {
		Pageable pageable = PageRequest.of(page, size); 
		if(authorId.isEmpty()) {
			return articlesRepository.findAll(pageable);
		}
		
		// TODO: add authorId check
		return articlesRepository.findAll(pageable);
	}

	public ArticleEntity getArticleById(Long id) {
		return articlesRepository.findById(id).orElse(null);
	}

	public ArticleEntity getArticleBySlug(String slug) {
		var article = articlesRepository.findBySlug(slug);
		if (article == null) {
			throw new ArticleNotFoundException(slug);
		}

		return article;
	}

	public ArticleEntity createNewArticle(CreateArticleRequestDTO request, Long authorId) {
		var author = usersRepository.findById(authorId).orElseThrow(() -> new UsersService.UserNotFoundException(authorId));
		var article = articlesRepository.save(
				ArticleEntity.builder()
				.title(request.getTitle())
				.subTitle(request.getSubTitle())
				.body(request.getBody())
				// TODO : create proper slugification function
				.slug(request.getTitle().replaceAll("\\s+", "-"))
				.author(author)
				.build());
		return article;
	}
	
	public ArticleEntity updateArticle(UpdateArticleRequestDTO request, Long articleId) {
		var article = articlesRepository.findById(articleId).orElseThrow(() -> new ArticlesService.ArticleNotFoundException(articleId));
		
		if(request.getTitle() != null) {
			article.setTitle(request.getTitle());
			// TODO : create proper slugification function
			article.setSlug(request.getTitle().replaceAll("\\s+", "-"));
		}
		
		if(request.getSubTitle() != null) {
			article.setSubTitle(request.getSubTitle());
		}
		
		if(request.getBody() != null) {
			article.setBody(request.getBody());
		}
		
		return articlesRepository.save(article);
	}

	public static class ArticleNotFoundException extends IllegalArgumentException {
		public ArticleNotFoundException(String slug) {
			super("Article " + slug + " not found");
		}
		
		public ArticleNotFoundException(Long articleId) {
			super("Article " + articleId + " not found");
		}
	}
}
