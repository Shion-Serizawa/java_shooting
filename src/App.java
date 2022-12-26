import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                // フレームの設定関連
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(960, 720);
                frame.setResizable(false);
                Mypanel panel = new Mypanel();
                frame.getContentPane().add(panel);
                frame.setVisible(true);
            }
        });
    }
}

//画面固定はオリジナル。
