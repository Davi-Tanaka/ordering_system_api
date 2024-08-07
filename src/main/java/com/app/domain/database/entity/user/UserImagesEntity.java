package com.app.domain.database.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Builder
@Data

@Entity
@Table(name = "user_images_entity")
public class UserImagesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private UserEntity user;
    
    @NonNull
    @Column(unique = true)
    private String imageUrl;
    
    @Builder.Default
    @Column
    @CreatedDate
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    
    @Builder.Default
    @Column
    @LastModifiedDate
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
}