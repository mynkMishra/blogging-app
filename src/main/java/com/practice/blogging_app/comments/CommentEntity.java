package com.practice.blogging_app.comments;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import com.practice.blogging_app.articles.ArticleEntity;
import com.practice.blogging_app.users.entities.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "comments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	@NonNull
	private Long id;
	
	@Column(nullable = false)
	@NonNull
	private String title;
	
	@Column(nullable = false)
	@NonNull
	private String body;
	
	@CreatedDate
	@Column(nullable = false, unique = true)
	@NonNull
	private String createdAt;
	
	@ManyToOne
	@JoinColumn(name = "authorId", nullable = false)
	private UserEntity author;
	
	@ManyToOne
	@JoinColumn(name = "articleId", nullable = false)
	private ArticleEntity article;
}
