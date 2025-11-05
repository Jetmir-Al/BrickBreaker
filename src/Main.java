import javax.swing.*;

public class Main extends Lvl{
    public static void main(String[] args) {

        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();

        obj.setBounds(10, 10, 692, 592);
        obj.setTitle("Wall Breaker!");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}