import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static String FILE_NAME = "users.json";
    private static Gson gson = new Gson();

    public static Map<String, User> loadUsers() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new HashMap<>();
        }

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, new TypeToken<Map<String, User>>(){}.getType());
        } catch (Exception e) {
            System.out.println("Failed to load users: " + e.getMessage());
            return new HashMap<>();
        }
    }

    private static void saveUsers(Map<String, User> users) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(users, writer);
        } catch (Exception e) {
            System.out.println("Failed to save users: " + e.getMessage());
        }
    }

    public static void saveUser(String username) {
        Map<String, User> users = loadUsers();

        if (!users.containsKey(username)) {
            users.put(username, new User(username));
        }

        saveUsers(users);

        System.out.println("User saved: " + username);
    }

    public static void updateScore(String username, int newScore) {
        Map<String, User> users = loadUsers();
        User user = users.get(username);

        if (user != null && newScore > user.getScore()) {
            user.setHighestScore(newScore);
            saveUsers(users);
            System.out.println("High score updated for " + username + " " + newScore);
        }
    }
}
