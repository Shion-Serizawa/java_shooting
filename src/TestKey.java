import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TestKey extends JFrame implements KeyListener {
    // public static void main(String[] args) {
    //     TestKey test = new TestKey();
    //     //test.getContentPane().add(new JTextArea());//コメントを外すとキーイベントはこなくなる
    //     test.addKeyListener(test);
    //     test.setSize(640, 480);
    //     test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     test.setVisible(true);
    // }
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
    }
    public void keyReleased(KeyEvent e) {
        System.out.println(e);
    }
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }
}