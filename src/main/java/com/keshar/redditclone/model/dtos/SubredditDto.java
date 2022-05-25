package com.keshar.redditclone.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {
    private String id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
