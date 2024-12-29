package com.practice.blogging_app.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticlesRepository extends JpaRepository<ArticleEntity, Long> {

	ArticleEntity findBySlug(String slug);

}
