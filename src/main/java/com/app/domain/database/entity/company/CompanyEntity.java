package com.app.domain.database.entity.company;

import com.app.domain.database.entity.OrderEntity;
import com.app.domain.database.entity.user.UserEntity;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Builder
@Entity
@Table(name = "company_entity")
public class CompanyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NonNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<CompanyContactEntity> contacts;
    
    @NonNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<OrderEntity> orders;
    
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserEntity user;
    
    @NonNull
    @Column(nullable = false)
    private String name;
    
    @NonNull
    @Column
    private String description;
    
    @NonNull
    @Column
    @CNPJ
    private String cpnj;
    
    @Builder.Default
    @Column
    @CreatedDate
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    
    @Builder.Default
    @Column
    @LastModifiedDate
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
}