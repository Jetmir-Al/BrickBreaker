import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
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
    private JLabel welcomeLabel;
    private JButton scoreBoardButton;


    public Menu() {
        setTitle("Brick Breaker");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);

//        colors
        panel1.setBackground(Color.BLACK);
        playAsGuestButton.setBackground(Color.WHITE);
        playAsGuestButton.setFocusPainted(false);
        playAsGuestButton.setBorderPainted(false);
        playAsGuestButton.setForeground(Color.BLACK);

        registerButton.setBackground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setForeground(Color.BLACK);

        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));

        textField1.setBackground(Color.DARK_GRAY);
        textField1.setForeground(Color.WHITE);
        textField1.setBounds(20, 20, 400, 50);
        textField1.setFont(new Font("Arial", Font.BOLD, 18));

        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 20));

        submitButton.setBackground(Color.GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));

        textField1.setVisible(false);
        cancelButton.setVisible(false);
        submitButton.setVisible(false);
        scoreBoardButton.addActionListener(e -> new ScoreboardWindow());

        playAsGuestButton.addActionListener(e -> startGame("Guest"));
        registerButton.addActionListener(e -> registerForm());

        setVisible(true);
    }

    private void registerForm() {
        playAsGuestButton.setVisible(false);
        registerButton.setVisible(false);
        scoreBoardButton.setVisible(false);
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

        startGame(userName);
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
        scoreBoardButton.setVisible(true);
    }

    private void startGame(String userName) {
        JFrame obj = new JFrame("Brick Breaker");
        Gameplay gameplay = new Gameplay(userName, obj);

        obj.setContentPane(gameplay);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setSize(708, 600);
        obj.setResizable(false);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);

        dispose();
    }
}
