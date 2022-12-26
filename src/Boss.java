import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.event.*;

public class Boss {
    private int x = 350;
    private int y = 50;
    private int width = 70;
    private int height = 104;
    private int hp = 40;

    String pathname = "Boss.png";
    private ArrayList<Danmaku> danmaku = new ArrayList<>();
    //private Boolean kari = false;
    private long lastTime;

    public Boss(){
        this.lastTime = System.currentTimeMillis();
    }

    public Boolean drawPlayer(Graphics2D g2 , double p_x, double p_y, int radius) {

        Boolean hit = false;
        // いけます！！！
        try {
            BufferedImage image = ImageIO.read(
                    new File(this.pathname));
            g2.drawImage(image, (int) x, (int) y, width, height, null);
            for (Danmaku b : this.danmaku) {
                g2.setColor(new Color(0,255,0));
                g2.fill(b);
                //判定処理
                if(b.colision(p_x, p_y, (double)radius)){
                    System.out.print("あたった！→");
                    hit = true;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(System.currentTimeMillis() - lastTime );
        //感覚は自分
        if(System.currentTimeMillis() - lastTime > 1000){
            for (int i = 0; i < 80; i++) {
                this.danmaku.add(new Danmaku((double) (this.x + width / 2.0), (double) (this.y + height/2.0)));

            }
            // kari = true;
            lastTime = System.currentTimeMillis();
        }
        return hit;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getEdgeX(){
        return this.x + width;
    }

    public int getEdgeY(){
        return this.x + height;
    }

    public int getCenterX(){
        return (int)this.x + (width / 2);
    }

    public int getCenterY(){
        return (int)this.y + (height / 2);
    }

    public int getRadius(){
        return width;
    }

    public int damage(int atack){
        this.hp -= atack;
        return 1;
    }

    public int getHP(){
        return this.hp;
    }

}
