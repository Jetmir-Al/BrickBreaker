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

    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdirection = -1;
    private int ballYdirection = -2;

    private MapGenerator map;

    public Gameplay(){
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);

        //map
        map.draw((Graphics2D)g);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 20, 20);



        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (play) {

            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdirection = -ballYdirection;
            }

            ballPosX += ballXdirection;
            ballPosY += ballYdirection;
            if (ballPosX < 0){
                ballXdirection = -ballXdirection;
            }
            if (ballPosY < 0){
                ballYdirection = -ballYdirection;
            }
            if (ballPosX > 680){
                ballXdirection = -ballXdirection;
            }
        }
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
            if (playerX < 10){
                playerX = 10;
            }
            else {
                moveLeft();
            }
        }
    }

    private void moveRight() {
        play = true;
        playerX += 20;
    }
    private void moveLeft() {
        play = true;
        playerX -= 20;
    }
}
