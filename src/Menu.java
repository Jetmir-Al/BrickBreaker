import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;


public class Menu extends JFrame {
    private JPanel panel1;
    private JButton playAsGuestButton;
    private JButton registerButton;
    private JButton submitButton;
    private JTextField textField1;
    private JButton cancelButton;


    public Menu() {
        setTitle("Brick Breaker");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        textField1.setVisible(false);
        cancelButton.setVisible(false);
        submitButton.setVisible(false);
        playAsGuestButton.addActionListener(e -> startGame());
        registerButton.addActionListener(e -> registerForm());

        setVisible(true);
    }

    private void registerForm() {
        playAsGuestButton.setVisible(false);
        registerButton.setVisible(false);
        cancelButton.setVisible(true);
        submitButton.setVisible(true);
        textField1.setVisible(true);

        cancelButton.addActionListener(e -> ReturnToMenu());

        submitButton.addActionListener(e -> SubmitUser());

        panel1.repaint();

    }

    private void SubmitUser() {
        String userName = textField1.getText();
        saveUser(userName);

        startGame();
    }

    private void saveUser(String userName) {
        Gson gson = new Gson();
        Map<String, User> user = new HashMap<>();
        File file = new File("users.json");


        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                user = gson.fromJson(reader, new TypeToken<Map<String, User>>() {}.getType());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        user.put(userName, new User(userName));

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(user, writer);
            System.out.println("User saved: " + userName);
        } catch (Exception e) {
            System.out.println(e);
         }
    }

    private void ReturnToMenu() {
        playAsGuestButton.setVisible(true);
        registerButton.setVisible(true);
        textField1.setVisible(false);
        submitButton.setVisible(false);
        cancelButton.setVisible(false);
    }

    private void startGame() {
        JFrame obj = new JFrame("Brick Breaker");
        Gameplay gameplay = new Gameplay();

        obj.setContentPane(gameplay);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setSize(700, 600);
        obj.setResizable(false);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);

        dispose();
    }
}
