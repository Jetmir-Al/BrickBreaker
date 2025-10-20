import java.awt.*;

public class MapGenerator {
    public int map[][];
    public int brickW;
    public int brickH;

    public MapGenerator(int row, int col){
        map = new int[row][col];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = 1;
            }
        }

        brickH = 150/row;
        brickW = 540/row;
    }

    public void draw(Graphics2D g){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if(map[i][j] > 0){
                    g.setColor(Color.white);
                    g.fillRect(j * brickW + 60, i * brickH + 30, brickW, brickH);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickW + 60, i * brickH + 30, brickW, brickH);
                }
            }
        }
    }
}
