package com.keshar.redditclone.services;

import com.keshar.redditclone.mapper.SubredditMapper;
import com.keshar.redditclone.model.dtos.SubredditDto;
import com.keshar.redditclone.model.entities.Subreddit;
import com.keshar.redditclone.repository.SubredditRepository;
import com.keshar.redditclone.utils.exception.SpringRedditException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditServiceImpl implements SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Override
    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll().stream().map(subredditMapper::mapSubredditToDto)
                .collect(toList());

    }

    public SubredditDto getSubredditById(String id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new SpringRedditException("No subreddit found with id>> " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
