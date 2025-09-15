import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, User> users;

    public Bank() {
        users = new HashMap<>();
        // Default user(s)
        users.put("user123", new User("user123", "1234"));
        users.put("user456", new User("user456", "4567"));
    }

    public User authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
