package com.keshar.redditclone.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private String id;
    private String postId;
    private String text;
    private String userName;
}
