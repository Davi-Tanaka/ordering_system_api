package com.app.domain.database.entity.product;

import com.app.domain.database.entity.user.UserEntity;
import com.app.domain.database.enums.RatingEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@Entity
@Table(name = "product_review_entity")
public class ProductReviewEntity {

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn
    @NonNull
    private UserEntity user;

    @ManyToOne
    @JoinColumn
    @NonNull
    private ProductEntity product;

    @NonNull
    @Column
    private String title;
    
    @Column
    @NonNull
    private String review;

    @Column
    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private RatingEnum rating;
}
