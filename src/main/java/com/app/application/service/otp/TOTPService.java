package com.app.application.service.otp;

import com.app.domain.database.entity.OTPEntity;
import com.app.domain.database.entity.user.UserEntity;
import com.app.domain.database.repository.OTPRepository;
import com.app.domain.database.repository.UserRepository;
import com.app.exceptions.RecordNotFoundInDatabase;
import com.app.interfaces.dto.otp.totp.CreatedTOTPDto;
import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TOTPService {
    @Autowired
    private OTPRepository otpRepository;
    
    @Autowired
    private UserRepository userRepository;
        
    @Autowired
    @Qualifier("totp_secret_key")
    private Key defaultKey;
    
    private Integer defaultTotalDigits = 8;
    private Long defaultOTPExpirationDurationInSeconds = 30L;

    /**
     * It'll generate a TOTP
     * @param key The secret key the function will use to generate the TOTP. The secret key size must be 256 bit maximum.
     * @param expirationInSeconds The exipiration in seconds.
     * @param digits The total of digits the funciton will generate.
     * @param algorithm Can be: HmacSHA1, HmacSHA256, HmacSHA512
     * @return String The TOTP generated.
     * @throws Exception 
     */
    public String generate(Key key, Long expirationInSeconds, Integer digits, String algorithm) throws Exception {
        TimeBasedOneTimePasswordGenerator DEFAULT_TOTP_GENERATOR = new TimeBasedOneTimePasswordGenerator(
            Duration.ofSeconds(expirationInSeconds), digits, algorithm
        );
        
        return DEFAULT_TOTP_GENERATOR.generateOneTimePasswordString(defaultKey, Instant.now());
    }

    /**
     * It'll generate a TOTP with 8 digits, valid for 30 seconds, and using SHA512 algorithm.
     * @return String
     */
    public String generate() throws Exception {
        return generate(
            defaultKey,
            defaultOTPExpirationDurationInSeconds,
            defaultTotalDigits, 
            TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA512
        );
    }
    
    /**
     * It'll create and save a TOTP for a user.
     * @param userId A existing user id.
     * @throws RecordNotFoundInDatabase
     * @return {@link CreatedTOTPDto}
     */
    public CreatedTOTPDto save(Long userId) throws RecordNotFoundInDatabase, Exception {
        String otpGenerated = this.generate();
        Optional<UserEntity> user = userRepository.findById(userId);
        
        if(user.isEmpty()) {
            throw new RecordNotFoundInDatabase(String.format("User with id [ %s ] do not found.", userId));
        }
        
        OTPEntity record = new OTPEntity(
            user.get(),
            otpGenerated, 
            Timestamp.from(Instant.now().plusSeconds(defaultOTPExpirationDurationInSeconds))
        );
        
        OTPEntity recordSaved = otpRepository.save(record);
        
        return new CreatedTOTPDto(recordSaved.getId(), recordSaved.getValue());
    }
    
    /**
     * It'll check in database if a TOTP for a certain user exists, and if it exists it will check if it's valid.
     * @param userId A existing user id.
     * @param totp A TOTP
     * @return boolean 
     */
    public boolean isValid(Long userId, String totp) {
        Optional<OTPEntity> usersLastOtp = otpRepository.getTheUsersLastOTP(userId);
        if(usersLastOtp.isEmpty()) { return false; }
        
        return usersLastOtp.get().isNotExpired() && usersLastOtp.equals(totp);
    }
}