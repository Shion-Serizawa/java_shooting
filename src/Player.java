import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class Player /* implements Runnable */ {
    // Graphics2D g2;
    private double x = 700;
    private double y = 500;
    private final int width = 31;
    private final int height = 50;

    private final double vx = 20;
    private final double vy = 20;
    private long lastTime;
    private Boolean pressingFlag = false;
    private ArrayList<Ziki_shot> danmaku = new ArrayList<>();

    public Player(){
        this.lastTime = System.currentTimeMillis();
    }

    public int drawPlayer(Graphics2D g2, double p_x, double p_y, int radius) {
        int damage = 0;
        // いけます！！！
        try {
            BufferedImage image = ImageIO.read(
                    new File("ziki.png"));
            g2.drawImage(image, (int) x, (int) y, width, height, null);
            g2.setColor(new Color(0, 0, 255));
            // g2.fill(new Ziki_maru( (int)x + 40, (int)y + 60));
            //当たり判定（敵にあたったら、そのたまを飛ばす。）
            for (Ziki_shot b : this.danmaku) {
                g2.setColor(new Color(255,0,0));
                g2.fill(b);
                //判定処理
                if(b.colision(p_x, p_y, (double)radius)){
                    System.out.println("\n4が敵に被弾!!!!");
                    damage = 4;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(System.currentTimeMillis() - lastTime > 500){
            
                this.danmaku.add(new Ziki_shot(this.x,this.y));

            
            // kari = true;
            lastTime = System.currentTimeMillis();
        }

        return damage;
    }

    public void moving(KeyEvent ev) {

        // 同時押し https://ameblo.jp/ogitsu-hama/entry-10082212467.html
        if (ev.getKeyCode() == KeyEvent.VK_UP) {
            if (ev.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
                this.y -= (vy / 4.0);
            } else {
                this.y -= vy;
            }
        }
        if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
            if (ev.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
                this.y += (vy / 4.0);
            } else {
                this.y += vy;
            }
        }
        if (ev.getKeyCode() == KeyEvent.VK_LEFT) {
            if (ev.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
                this.x -= (vx / 4.0);
            } else {
                this.x -= vx;
            }
        }
        if (ev.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (ev.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
                this.x += (vx / 4.0);
            } else {
                this.x += vx;
            }
        }
        panelLimit();

    }

    private void panelLimit() {
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x > 960 - 31) {
            this.x = 960 - 31;
        }

        if (this.y < 0) {
            this.y = 0;
        } else if (this.y > 720 - 70) {
            this.y = 720 - 70;
        }

    }

    public double getCenterX(){
        return this.x + (width / 2.0);
    }

    public double getCenterY(){
        return (int)this.y + (height / 2.0);
    }

    public int getRadius(){
        return 11;
    }
}
