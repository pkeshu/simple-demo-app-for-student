package com.keshar.redditclone.repository;

import com.keshar.redditclone.model.entities.Comment;
import com.keshar.redditclone.model.entities.Post;
import com.keshar.redditclone.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,String> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
