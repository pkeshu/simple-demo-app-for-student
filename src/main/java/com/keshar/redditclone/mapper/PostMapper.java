package com.keshar.redditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.keshar.redditclone.model.dtos.PostRequest;
import com.keshar.redditclone.model.dtos.PostResponse;
import com.keshar.redditclone.model.entities.Post;
import com.keshar.redditclone.model.entities.Subreddit;
import com.keshar.redditclone.model.entities.User;
import com.keshar.redditclone.repository.CommentRepository;
import com.keshar.redditclone.repository.VoteRepository;
import com.keshar.redditclone.services.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "subreddit", source = "subreddit")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedAt().toInstant().toEpochMilli());
    }
}