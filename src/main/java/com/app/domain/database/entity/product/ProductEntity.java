package com.app.domain.database.entity.product;

import com.app.domain.database.entity.company.CompanyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "product_entity")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private CompanyEntity company;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductImagesEntity> images;

    @NonNull
    @Column(unique = true, nullable = false)
    private String name;
    
    @NonNull
    @Column(nullable = false)
    private String description;
    
    @NonNull
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column
    @CreatedDate
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column
    @LastModifiedDate
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
}
