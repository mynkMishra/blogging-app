package com.practice.blogging_app.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentsRepository extends JpaRepository<CommentEntity, Long> {

}
