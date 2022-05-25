package com.keshar.redditclone.repository;

import com.keshar.redditclone.model.entities.Post;
import com.keshar.redditclone.model.entities.Subreddit;
import com.keshar.redditclone.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
