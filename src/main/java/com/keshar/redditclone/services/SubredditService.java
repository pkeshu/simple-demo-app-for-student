package com.keshar.redditclone.services;

import com.keshar.redditclone.model.dtos.SubredditDto;

import java.util.List;

public interface SubredditService {
    SubredditDto save(SubredditDto subredditDto);

    List<SubredditDto> getAll();
}
