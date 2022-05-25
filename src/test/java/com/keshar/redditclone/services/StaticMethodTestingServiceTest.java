package com.keshar.redditclone.services;

import com.keshar.redditclone.model.dtos.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;


class StaticMethodTestingServiceTest {

    private final StaticMethodTestingService cut = new StaticMethodTestingService();
    private final LocalDateTime defaultDateTime = LocalDateTime.now();

    @Test
    @DisplayName("Random Id should be generated is parentId is null")
    public void shouldIncludeRandomOrderIdWithNoParentOrderExist() {

        try (MockedStatic<UUID> mockedStatic = Mockito.mockStatic(UUID.class)) {
            mockedStatic.when(UUID::randomUUID).thenReturn("81d519e2-a50f-11ec-b909-0242ac120002");
            Order result = cut.createOrder("Macbook Pro", 2L, null);

            Assertions.assertThat("81d519e2-a50f-11ec-b909-0242ac120002").isEqualTo(result.getId());
        }

    }

    @Test
    public void shouldIncludeCurrentTimeWhenCreatingANewOrder() {

        try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultDateTime);
            Order result = cut.createOrder("Macbook Pro", 2L, null);

            Assertions.assertThat(defaultDateTime).isEqualTo(result.getCreationDate());
        }

    }

}