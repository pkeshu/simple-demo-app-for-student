package com.keshar.redditclone.repository;

import com.keshar.redditclone.model.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTestEmbedded {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        User user = new User("test", "test user", "secret password", "user@email.com", true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        User savedUser = userRepository.save(user);
        assertThat(savedUser).usingRecursiveComparison().ignoringFields("userId").isEqualTo(user);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void shouldSavedUserThroughSqlFile() {
        Optional<User> test = userRepository.findByUsername("testuser_sql");
        assertThat(test).isNotEmpty();
    }

//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    public void setup() {
//        User user = new User(null, "test user", "secret password", "user@email.com", true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
//        User savedUser = userRepository.save(user);
//    }

    // Our Tests

}
