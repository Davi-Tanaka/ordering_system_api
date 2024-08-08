package share.mock.entity;

import com.app.domain.database.entity.user.UserEntity;
import java.math.BigDecimal;

public class UserEntityMock {

    public static UserEntity getValidMock() {

        UserEntity mock_saved_user = new UserEntity();
        mock_saved_user.setName("David");
        mock_saved_user.setCpf("000.000.000-12");
        mock_saved_user.setEmail("mockUser@mock.com");
        mock_saved_user.setPassword("randomPswd");
        mock_saved_user.setPayment(new BigDecimal(1450.40));

        return mock_saved_user;
    }
    
    public static UserEntity getInvalidMock() {
        UserEntity mock_saved_user = new UserEntity();
        mock_saved_user.setName("David");
        mock_saved_user.setEmail("mockUser@mock.com");
        mock_saved_user.setPassword("randomPswd");

        return mock_saved_user;
    }
}
