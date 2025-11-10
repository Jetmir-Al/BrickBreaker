import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Gameplay extends  JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int lvl = 1;
    private int row;
    private int col;
    private int totalBricks;
    private String userName;
    private JFrame frame;


    private Timer timer;
    private int delay = 10;
    private int playerX = 310;
    private int ballPosX = 310;
    private int ballPosY = 350;
    private double speedIncrease = 2;
    private double ballXdirection = -2;
    private double ballYdirection = -3;

    private MapGenerator map;

    public Gameplay(String userName, JFrame frame){
        this.userName = userName;
        this.frame = frame;
        if(lvl == 1){
            row = 3;
            col = 7;
            totalBricks = 21;
        }
//        test last level
//        else if (lvl == 5) {
//            row = 6;
//            col = 8;
//            speedIncrease = 3;
//            totalBricks = row * col;
//            ballXdirection = -2 * speedIncrease;
//            ballYdirection = -3 * speedIncrease;
//        }
        map = new MapGenerator(row, col);
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

        g.setColor(Color.green);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

//        user block
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

//        score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 562, 25);

        // ball
        g.setColor(Color.green);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        if (totalBricks == 0){
            if (lvl == 5){

            play = false;
            ballYdirection = 0;
            ballXdirection = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You won! Score: " + score, 190, 300);
            UserManager.updateScore(userName, score);

            }else {
                play = false;
                ballYdirection = 0;
                ballXdirection = 0;
                g.setColor(Color.RED);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("You beat level: " + lvl, 190, 300);

                g.setColor(Color.WHITE);
                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Press Enter to continue!", 230, 350);
            }

        }

        if (ballPosY > 570){
            play = false;
            ballYdirection = 0;
            ballXdirection = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);

            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press R to Restart", 230, 350);
        }


        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (play) {

            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdirection = -ballYdirection;
            }

            for (int i = 0; i<map.map.length; i++){
                for (int j = 0; j<map.map[0].length; j++){
                    if (map.map[i][j] > 0){
                        int brickX = j * map.brickW + 80;
                        int brickY = i * map.brickH + 50;
                        int brickWidth = map.brickW;
                        int brickHeight = map.brickH;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect = rect;
                        if (ballRect.intersects(brickRect)){
                            map.setBrickVal(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width){
                                ballXdirection = -ballXdirection;
                            }else {
                                ballYdirection = -ballYdirection;
                            }
                        break;
                        }


                    }
                }
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
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            play = false;
            frame.dispose();
            new Menu();
        }
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

        if(e.getKeyCode() == KeyEvent.VK_R && totalBricks != 0){
            if (!play){
                UserManager.updateScore(userName, score);
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdirection = -2;
                ballYdirection = -3;
                playerX = 310;
                row = 3;
                col = 7;
                totalBricks = 21;
                score = 0;
                lvl = 1;
                map = new MapGenerator(row, col);
                repaint();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER && totalBricks == 0 && lvl != 5) {
            if (!play){
                    lvl++;
                if (lvl == 2) {
                    row = 4;
                    col = 7;
                    speedIncrease = 1.5;
                } else if (lvl == 3) {
                    row = 4;
                    col = 8;
                    speedIncrease = 2;
                } else if (lvl == 4) {
                    row = 5;
                    col = 8;
                    speedIncrease = 2.5;

                }else if (lvl == 5) {
                    row = 6;
                    col = 8;
                    speedIncrease = 3;
                }
                ballXdirection = -2 * speedIncrease;
                ballYdirection = -3 * speedIncrease;
                totalBricks = row * col;
                ballPosX = 310;
                ballPosY = 350;
                playerX = 310;
                map = new MapGenerator(row, col);
                play = true;
                repaint();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_ENTER && totalBricks == 0 && lvl == 5) {
                play = false;
                frame.dispose();
                new Menu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void moveRight() {
        play = true;
        playerX += 20 * lvl;

    }
    private void moveLeft() {
        play = true;
        playerX -= 20 * lvl;
    }
}
