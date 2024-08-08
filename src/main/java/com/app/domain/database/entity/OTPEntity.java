package com.app.domain.database.entity;

import com.app.domain.database.entity.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "otp_entity")
public class OTPEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @ManyToOne
    @JoinColumn
    private final UserEntity user;

    @NonNull
    @Column(nullable = false, length = 8)
    private String value;

    @NonNull
    @Column(nullable = false)
    private Timestamp expiresAt;
    
    @Column(nullable = false)
    @CreatedDate
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    
    @Column(nullable = false)
    @LastModifiedDate
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
    
    public boolean isNotExpired() {
        return expiresAt.getTime() > Timestamp.from(Instant.now()).getTime();
    }
}