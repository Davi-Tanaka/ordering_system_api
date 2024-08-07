package com.app.domain.database.entity.product;

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
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "product_images_entity")
public class ProductImagesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)
    private String image_url;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ProductEntity product;
     
    @Column
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();
}