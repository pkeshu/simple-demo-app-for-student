package com.keshar.redditclone.services;

import com.keshar.redditclone.mapper.PostMapper;
import com.keshar.redditclone.model.dtos.PostRequest;
import com.keshar.redditclone.model.dtos.PostResponse;
import com.keshar.redditclone.model.entities.Post;
import com.keshar.redditclone.model.entities.Subreddit;
import com.keshar.redditclone.model.entities.User;
import com.keshar.redditclone.repository.PostRepository;
import com.keshar.redditclone.repository.SubredditRepository;
import com.keshar.redditclone.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private SubredditRepository subredditRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthServiceImpl authService;
    @Mock
    private PostMapper postMapper;

    private PostService postService;

    @Captor
    private ArgumentCaptor<Post> postArgumentCaptor;

    @BeforeEach
    public void setup() {
        postService = new PostService(postRepository, subredditRepository, userRepository, authService, postMapper);
    }

    @Test
    @DisplayName("Should Find Post By Id")
    void shouldFindPostById() {
        Post post = new Post();
        post.setPostId("test");
        post.setPostName("Test");
        post.setUrl("http://url.site");
        post.setVoteCount(0);
        post.setCreatedAt(Timestamp.from(Instant.now()));
        PostResponse expectedPostResponse = new PostResponse("test", "First Post", "http://url.site", "Test",
                "Test User", "Test Subredit", 0, 0, "1 Hour Ago");

        when(postRepository.findById("test")).thenReturn(Optional.of(post));
        when(postMapper.mapToDto(any(Post.class))).thenReturn(expectedPostResponse);

        PostResponse actualResponse = postService.getPost("test");
        assertThat(actualResponse.getId()).isEqualTo(expectedPostResponse.getId());
        assertThat(actualResponse.getPostName()).isEqualTo(expectedPostResponse.getPostName());

    }

    @Test
    @DisplayName("Should Save Post")
    void shouldSavePost() {
        User currentUser = new User("test", "test user", "secret password", "user@email.com", true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        Subreddit subreddit = new Subreddit("subreddit_id", "First Subreddit", "Subreddit Description", emptyList(), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), currentUser);
        Post post = new Post("post_id", "First Post", "http://url.site", "Test",
                0, null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), null);
        PostRequest postRequest = new PostRequest(null, "First Subreddit", "First Post", "http://url.site", "Test");

        when(subredditRepository.findByName("First Subreddit"))
                .thenReturn(Optional.of(subreddit));
        when(postMapper.map(postRequest, subreddit, currentUser))
                .thenReturn(post);
        when(authService.getCurrentUser()).thenReturn(currentUser);
        postService.save(postRequest);
        verify(postRepository, times(1)).save(postArgumentCaptor.capture());

        assertThat(postArgumentCaptor.getValue().getPostId()).isEqualTo(post.getPostId());
        assertThat(postArgumentCaptor.getValue().getPostName()).isEqualTo(post.getPostName());
    }
}