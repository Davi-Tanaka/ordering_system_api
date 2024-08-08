package com.app.application.service.user;

import com.app.domain.database.entity.user.UserEntity;
import com.app.domain.database.entity.user.UserImagesEntity;
import com.app.exceptions.RecordNotFoundInDatabase;
import com.app.interfaces.dto.user.CreateUserRequestDto;
import com.app.interfaces.dto.user.CreateUserResponseDto;
import java.sql.SQLException;

public interface IUserService {
    public CreateUserResponseDto create(CreateUserRequestDto dto) throws SQLException;
    public UserImagesEntity insertUserImage(Long id, String url) throws SQLException;
    public UserImagesEntity removeUserImage(Long id, String url) throws SQLException;
    public UserImagesEntity removeUserImage(Long id, Long userImageId) throws SQLException;
    
    public boolean userExist(Long userId);
    public boolean emailExist(String email);
    public boolean nameExist(String name);
    public boolean cpfExist(String cpf);
    
    public void delete(Long userId) throws RecordNotFoundInDatabase;
    public UserEntity findById(Long userId) throws RecordNotFoundInDatabase;
    public UserEntity findByEmailAndPassword(String email, String pswd) throws RecordNotFoundInDatabase;
    public UserEntity findByEmail(String email) throws RecordNotFoundInDatabase;
}