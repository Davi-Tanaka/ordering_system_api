package com.app.domain.database.entity.user;

import com.app.domain.database.entity.OrderEntity;
import com.app.domain.database.entity.company.CompanyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 *
 * UserEntity table.
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity implements Serializable {
    /**
     * User id;
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * User images
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserImagesEntity> images;

    /**
     * User orders
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "user")
    private List<CompanyEntity> companies;
    
    /**
     * User name.
     *
     */
    @NonNull
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * User email,
    *
     */
    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * User cpf,
    *
     */
    @NonNull
    @Column(unique = true, nullable = false)
    private String cpf;

    
    /**
     * User payment
     */
    @NonNull
    @Column(nullable = false)
    private BigDecimal payment;

    /**
     * User password.
     *
     */
    @NonNull
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    
    /**
     * The date and time that user record has been created.
     */
    @Column(nullable = false)
    @CreatedDate    
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());    
    
    /**
     * The last time date and time user record has been modified.
     */
    @Column(nullable = false)
    @LastModifiedDate
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());    
}
