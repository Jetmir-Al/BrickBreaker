import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private  int score = 0;

    private  int totalBricks = 21;

    private  Timer timer;
    private int delay = 0;
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdirection = -1;
    private int ballYdirection = -2;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 700, 600);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 600);
        g.fillRect(0, 0, 700, 3);
        g.fillRect(699, 0, 3, 600);

        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 30, 30);

        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (playerX >= 600){
                playerX = 600;
            }
            else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (playerX >= 10){
                playerX = 10;
            }
            else {
                moveLeft();
            }
        }
    }

    private void moveLeft() {
        play = true;
        playerX = playerX - 20;
    }
    private void moveRight() {
        play = true;
        playerX += 20;
    }
}
