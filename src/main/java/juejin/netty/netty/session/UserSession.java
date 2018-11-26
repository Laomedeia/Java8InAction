package juejin.netty.netty.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author neptune
 * @create 2018 11 26 10:34 AM
 */
@Data
@NoArgsConstructor
public class UserSession {

    private String userId;
    private String userName;

    public UserSession(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
