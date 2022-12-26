import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

//12回花火
public class Danmaku extends Ellipse2D.Double {
    public double vx = 0.0;
    public double vy = 0.0;
    private long lastTime;
    // private HanabiPanel parent;
    private Thread thread;

    public Danmaku(double x, double y/* , HanabiPanel parent */) {
        // System.out.print("うまれた！");
        this.lastTime = System.currentTimeMillis();
        this.x = x;
        this.y = y;
        this.height = 10;
        this.width = 10;
        // this.parent = parent;
        this.vx = Math.random() * 200.0 - 100.0;
        if (Math.random() < 0.5) {
            this.vy = Math.sqrt(10000.0 - Math.pow(vx, 2));
        } else {
            this.vy = -1.0 * Math.sqrt(10000.0 - Math.pow(vx, 2));
        }

        thread = new Thread(new Runnable() {
            Boolean flag = true;

            @Override
            public void run() {
                while (flag) {
                    long dt = System.currentTimeMillis() - Danmaku.this.lastTime;
                    Danmaku.this.lastTime = System.currentTimeMillis();

                    Danmaku.this.x = (dt / 1000.0) * Danmaku.this.vx + Danmaku.this.x;
                    Danmaku.this.y = (dt / 1000.0) * Danmaku.this.vy + Danmaku.this.y;

                    try {
                        Thread.sleep(10);
                    } // 100FPS
                    catch (InterruptedException e) {
                    }
                    // System.out.println("x:" + Monster.this.x + " y:" + Monster.this.y + " vx:" +
                    // Monster.this.vx
                    // + " vy:" + Monster.this.vy);
                    if (Danmaku.this.panelLimit()) {
                        flag = false;
                    }
                }

            }
        });
        thread.start();
    }

    // 自分
    private Boolean panelLimit() {
        if (this.x < -10.0) {
            return true;
        } else if (this.x > 980) {
            return true;
        }

        if (this.y < -10.0) {
            return true;
        } else if (this.y > 730) {
            return true;
        }

        return false;

    }

    public Boolean colision(double x, double y, double r) {
        double d = Math.sqrt(Math.pow(x - (this.x + width / 2.0), 2)
                + Math.pow(y - (this.y + height / 2.0), 2));
        
        if (d <= (r + height)/2.0) {
            // 衝突
            return true;
        }
        return false;
    }

}
