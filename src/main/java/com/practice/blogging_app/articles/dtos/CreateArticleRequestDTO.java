package com.practice.blogging_app.articles.dtos;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.NONE)
public class CreateArticleRequestDTO {
	@NonNull
	private String title;
	@NonNull
	private String body;
	@Nullable
	private String subTitle;
}
