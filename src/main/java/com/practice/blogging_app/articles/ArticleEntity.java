package com.practice.blogging_app.articles;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.practice.blogging_app.users.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "articles")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	@NonNull
	private Long id;
	
	@Column(nullable = false)
	@NonNull
	private String title;
	
	@Column(nullable = false, unique = true)
	@NonNull
	private String slug;
	
	@Column(nullable = true)
	@Nullable
	private String subTitle;
	
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
	
	// TODO: add tags
}
