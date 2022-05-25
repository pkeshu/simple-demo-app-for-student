package com.keshar.redditclone.mapper;

import com.keshar.redditclone.model.dtos.SubredditDto;
import com.keshar.redditclone.model.entities.Post;
import com.keshar.redditclone.model.entities.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "id", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subreddit);
}