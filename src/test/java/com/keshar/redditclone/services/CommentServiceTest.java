package com.keshar.redditclone.services;

import com.keshar.redditclone.utils.exception.SpringRedditException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.AssertFalse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {

    private CommentService commentService;

    @BeforeEach
    public void setUp() {
        commentService = new CommentService(null, null, null, null, null, null, null);
    }

    @Test
    @DisplayName("Test Should Pass Comment do not Contains Swear Words.")
    void shouldNotContainSwearWordsInsideComment() {
//        assertFalse(commentService.containsSwearWords("This is clean comment"));
        assertThat(commentService.containsSwearWords("This is a comment")).isFalse();

    }

    @Test
    @DisplayName("Should Throw Exception when Exception Contains Swear Words")
    void shouldFailWhenCommentContainSwearWords() {
//        SpringRedditException exception = assertThrows(SpringRedditException.class, () -> {
//            commentService.containsSwearWords("This is shitty comment");
//        });
//        assertTrue(exception.getMessage().contains("Comments contains unacceptable language"));

        assertThatThrownBy(() -> {
            commentService.containsSwearWords("This is shitty comment");

        }).isInstanceOf(SpringRedditException.class)
                .hasMessage("Comments contains unacceptable language");
    }
}