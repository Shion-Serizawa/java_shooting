import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import java.util.*;

public class Mypanel extends JPanel implements KeyListener {
    private Thread repaintThread;
    private int x;
    private Player player;
    private Boss boss;

    private int flag = 0;
    private int image_count = 0;
    private long lastTime;

    private ArrayList<String> opening = new ArrayList<String>();

    public Mypanel() {
        opening.add("op1.png");
        opening.add("op2.png");
        opening.add("op3.png");
        opening.add("op4.png");
        opening.add("op5.png");
        opening.add("op6.png");
        opening.add("op7.png");
        opening.add("op8.png");

        this.addKeyListener(this);
        setFocusable(true);
        requestFocus();
        // Threadの定義（無名クラス）→開始処理
        this.repaintThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Mypanel.this.repaint();
                    try {
                        Thread.sleep(10);
                    } // 100FPS
                    catch (InterruptedException e) {
                    }
                }
            }
        });
        this.repaintThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        if (flag == 0) {
            if (image_count < opening.size()) {
                Graphics2D g2 = (Graphics2D) g;
                try {
                    BufferedImage image = ImageIO.read(
                            new File(opening.get(image_count)));
                    g2.drawImage(image, null, -10, -20);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                image_count = 0;
                flag = 1;
            }

        }
        if (flag == 1) {
            player = new Player();
            boss = new Boss();
            flag = 2;
            lastTime = System.currentTimeMillis();
        }
        if (flag == 2) {
            Graphics2D g2 = (Graphics2D) g;
            try {
                BufferedImage image = ImageIO.read(
                        new File("kari_back.png"));
                g2.drawImage(image, null, 0, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // playerはダメージを返すようにしよう！

            int damageByZiki = player.drawPlayer(g2, boss.getCenterX(), boss.getCenterY(), 70);

            if (boss.drawPlayer(g2, player.getCenterX(), player.getCenterY(), player.getRadius())) {
                flag = 3;
            }
            boss.damage(damageByZiki);

            if (damageByZiki != 0) {
                System.out.println(boss.getHP());
            }

            if (boss.getHP() < 0) {
                flag = 4;
                lastTime = System.currentTimeMillis() - lastTime;
            }
        }
        if (flag == 3) {
            Graphics2D g2 = (Graphics2D) g;
            try {
                BufferedImage image = ImageIO.read(
                        new File("over.png"));
                g2.drawImage(image, null, -10, -20);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Game OVER");

        }

        if (flag == 4) {
            Graphics2D g2 = (Graphics2D) g;
            try {
                BufferedImage image = ImageIO.read(
                        new File("clear.png"));
                g2.drawImage(image, null, -10, -20);
                g2.setColor(Color.BLACK);

                Font font = new Font("メイリオ", Font.PLAIN, 40);
                g2.setFont(font);

                String str = "" + (lastTime);
                g2.drawString(str, 60,600);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // System.out.println("Game CLEAR");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("iii");
        if (flag == 2) {
            player.moving(e);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (flag == 2) {
            player.moving(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if (flag == 0) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                image_count++;
            }
        }
        if (flag == 2) {
            player.moving(e);
        }
        if (flag == 3 || flag == 4) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                flag = 0;
            }
        }

    }

}

// 画像表示を改良
