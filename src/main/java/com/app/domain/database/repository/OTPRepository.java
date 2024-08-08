package com.app.domain.database.repository;

import com.app.domain.database.entity.OTPEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity, UUID> {
    
    @Query(
        value = "SELECT * FROM otp_entity WHERE user_id = ?1 ORDER BY created_at ASC LIMIT 1",
        nativeQuery = true
    )
    
    public Optional<OTPEntity> getTheUsersLastOTP(Long userId);
}
