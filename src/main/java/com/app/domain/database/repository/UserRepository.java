package com.app.domain.database.repository;

import com.app.domain.database.entity.user.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);
    public Optional<UserEntity> findByName(String name);
    public Optional<UserEntity> findByCpf(String cpf);
    public Optional<UserEntity> findByEmailAndPassword(String email, String password);
}