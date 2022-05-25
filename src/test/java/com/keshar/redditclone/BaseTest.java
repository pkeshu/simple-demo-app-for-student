package com.keshar.redditclone;

import org.testcontainers.containers.MySQLContainer;

public class BaseTest {
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("reddit-clone")
            .withUsername("testuser")
            .withPassword("pass");

    static {
        mySQLContainer.start();
    }
}
