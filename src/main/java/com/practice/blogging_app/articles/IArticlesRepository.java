package com.practice.blogging_app.articles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticlesRepository extends JpaRepository<ArticleEntity, Long> {

}
