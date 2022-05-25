package com.keshar.redditclone.model.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {
    private String id;
    private LocalDateTime creationDate;
    private Long amount;
    private String productName;
}
