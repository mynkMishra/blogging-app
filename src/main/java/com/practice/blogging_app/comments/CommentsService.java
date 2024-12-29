package com.practice.blogging_app.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentsService {
	private ICommentsRepository commentsRepository;
	
	public CommentsService(ICommentsRepository commentsRepository) {
		this.commentsRepository = commentsRepository;
	}
}
