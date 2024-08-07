package com.app.domain.database.entity.company;

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
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "company_contact_entity")
@Data
public class CompanyContactEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private CompanyEntity company;
        
    @NonNull
    @Column
    private String contact;

    @NonNull
    @Column
    private String type;

    @Column
    @CreatedDate
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column
    @LastModifiedDate
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

}
