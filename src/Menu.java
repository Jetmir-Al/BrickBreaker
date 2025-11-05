import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JPanel panel1;
    private JButton playButton;
    private JTextField welcomeTextField;

    public Menu(){
        playButton = new JButton("Play!");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
                setVisible(false);
            }
        });
        welcomeTextField = new JTextField("Welcome to Wall Breaker");
        add(panel1);
        add(welcomeTextField);
        add(playButton);

        setBounds(10, 10, 700, 592);
        setTitle("Wall Breaker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void startGame(){
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();

        obj.setBounds(10, 10, 692, 592);
        obj.setTitle("Wall Breaker!");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}
