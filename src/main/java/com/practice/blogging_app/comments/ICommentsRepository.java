package com.practice.blogging_app.comments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentsRepository extends JpaRepository<CommentEntity, Long> {

}
