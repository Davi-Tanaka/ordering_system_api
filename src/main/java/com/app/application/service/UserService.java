package com.app.application.service;

import com.app.application.business.UserBusiness;
import com.app.application.service.model.IResponseModel;
import com.app.application.service.model.ResponseModel;
import com.app.domain.database.entity.user.UserEntity;
import com.app.domain.database.entity.user.UserImagesEntity;
import com.app.domain.database.repository.UserRepository;
import com.app.interfaces.dto.user.CreateUserRequestDto;
import com.app.interfaces.dto.user.CreateUserResponseDto;
import java.sql.SQLException;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
        
@Service
public class UserService implements IUserService {
    private UserRepository userRepository;

    public UserService() {}
       
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Transactional
    @Override
    public UserEntity findById(Long id) throws NotFoundException {
        Optional<UserEntity> result = this.userRepository.findById(id);
        
        if(result.isEmpty()) {
            throw new NotFoundException();
        };
        
        return result.get();
    }
    
    @Transactional
    @Override
    public CreateUserResponseDto create(CreateUserRequestDto dto) throws SQLException {     
        ModelMapper mapper = new ModelMapper();

        String hashedPassword = UserBusiness.hashPassword(dto.getPassword());
        
        dto.setPassword(hashedPassword);
        
        UserEntity newUser = mapper.map(dto, UserEntity.class);
        UserEntity createdUser = userRepository.save(newUser);
        
        return new CreateUserResponseDto(
            createdUser.getId(),
            createdUser.getName(),
            createdUser.getCreatedAt(),
            createdUser.getUpdatedAt()
        );
    }

    @Override
    public boolean userExist(Long userId) {
        return this.userRepository.findById(userId).isPresent();
    }
    
    @Override
    public boolean emailExist(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }
    
    @Override
    public boolean cpfExist(String cpf) {
        return this.userRepository.findByCpf(cpf).isPresent();
    }
    
    @Override
    public boolean nameExist(String name) {
        return this.userRepository.findByName(name).isPresent();
    }
    
    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {
        this.userRepository.deleteById(id);
    }
    
    public IResponseModel<Boolean, Error> canSignup(UserEntity user) {
        boolean cpfAlreadExistsInDb = cpfExist(user.getCpf());
        boolean nameAlreadExistsInDb = nameExist(user.getName());
        boolean emailAlreadExistsInDb = emailExist(user.getEmail());  
        
        if(cpfAlreadExistsInDb || nameAlreadExistsInDb || emailAlreadExistsInDb) {
            return new ResponseModel<Boolean, Error>(false, new Error("Data already registered."));
        }
        
        return new ResponseModel(true, null);
    }
    
    @Override
    public UserEntity findByEmailAndPassword(String email, String pswd) throws NotFoundException {
        System.out.println("------- --------------- ---------\n");
        System.out.format("Hashed password -> %s \n", UserBusiness.hashPassword(pswd));
                
        Optional<UserEntity> response = userRepository.findByEmailAndPassword(
            email, 
            UserBusiness.hashPassword(pswd)
        );
        
        System.out.println("------- --------------- ---------\n");
        System.out.format("Respose email and pswd -> %s; %s\n", response.get().getEmail(), response.get().getPassword());
        
        if(!response.isPresent()) {
            throw new NotFoundException();
        }
        
        return response.get();
    }
    
    @Override
    public UserImagesEntity insertUserImage(Long userId, String url) {
        return UserImagesEntity.builder().build();
    }

    @Override
    public UserImagesEntity removeUserImage(Long userId, String url) {
        return UserImagesEntity.builder().build();
    }

    @Override
    public UserImagesEntity removeUserImage(Long userId, Long userImageId) {
        return UserImagesEntity.builder().build();
    }

    @Override
    public UserEntity findByEmail(String email) throws NotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        
        if(!user.isPresent()) {
            throw new NotFoundException();
        }
        
        return user.get();
    }
}
