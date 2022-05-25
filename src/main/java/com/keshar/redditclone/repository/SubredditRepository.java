package com.keshar.redditclone.repository;

import com.keshar.redditclone.model.entities.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubredditRepository extends JpaRepository<Subreddit,String> {
    Optional<Subreddit> findByName(String name);
}
