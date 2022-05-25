package com.keshar.redditclone.repository;

import com.keshar.redditclone.model.entities.Post;
import com.keshar.redditclone.model.entities.User;
import com.keshar.redditclone.model.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, String> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
