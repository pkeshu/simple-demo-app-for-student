package com.keshar.redditclone.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Timestamp;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_rdt_tokens")
public class VerificationToken {
    @Id
    @GeneratedValue(generator = "uuid4", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String id;
    private String token;
    @OneToOne(fetch = LAZY)
    private User user;
    @CreationTimestamp
    private Timestamp createdAt;
}
