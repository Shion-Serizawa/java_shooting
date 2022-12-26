import java.awt.geom.Ellipse2D;

public class Ziki_shot extends Ellipse2D.Double {
    public final double v = -130;

    private long lastTime;
    // private HanabiPanel parent;
    private Thread thread;

    public Ziki_shot(double x, double y){
        this.lastTime = System.currentTimeMillis();
        this.x = x;
        this.y = y;
        this.height = 7;
        this.width = 7;

        thread = new Thread(new Runnable() {
            Boolean flag = true;

            @Override
            public void run() {
                while (flag) {
                    long dt = System.currentTimeMillis() - Ziki_shot.this.lastTime;
                    Ziki_shot.this.lastTime = System.currentTimeMillis();

                    //Danmaku.this.x = (dt / 1000.0) * Danmaku.this.vx + Danmaku.this.x;
                    Ziki_shot.this.y = (dt / 1000.0) * Ziki_shot.this.v + Ziki_shot.this.y;

                    try {
                        Thread.sleep(10);
                    } // 100FPS
                    catch (InterruptedException e) {
                    }
                    // System.out.println("x:" + Monster.this.x + " y:" + Monster.this.y + " vx:" +
                    // Monster.this.vx
                    // + " vy:" + Monster.this.vy);
                    if (Ziki_shot.this.panelLimit()) {
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
            this.x = -20.0;
            this.y = -20.0;
            return true;
        }
        return false;
    }
}
