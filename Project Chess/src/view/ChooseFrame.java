package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChooseFrame extends JFrame {

    public ChooseFrame(){
        this.setTitle("国际象棋");
        this.setLayout(null);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口可以关闭

        ImageIcon mainBtn1Picture = new ImageIcon("src/picture/难度一白方.png");
        JLabel mainBtn1Label = new JLabel(mainBtn1Picture);
        mainBtn1Label.setSize(200, 60);
        mainBtn1Label.setLocation(150, 100);
        add(mainBtn1Label);

        ImageIcon mainBtn2Picture = new ImageIcon("src/picture/难度一黑方.png");
        JLabel mainBtn2Label = new JLabel(mainBtn2Picture);
        mainBtn2Label.setSize(200, 60);
        mainBtn2Label.setLocation(150, 220);
        add(mainBtn2Label);

        ImageIcon mainBtn3Picture = new ImageIcon("src/picture/难度二白方.png");
        JLabel mainBtn3Label = new JLabel(mainBtn3Picture);
        mainBtn3Label.setSize(200, 60);
        mainBtn3Label.setLocation(150, 340);
        add(mainBtn3Label);

        ImageIcon mainBtn4Picture = new ImageIcon("src/picture/难度二黑方.png");
        JLabel mainBtn4Label = new JLabel(mainBtn4Picture);
        mainBtn4Label.setSize(200, 60);
        mainBtn4Label.setLocation(150, 460);
        add(mainBtn4Label);

        this.setVisible(true);

        mainBtn1Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(1);
                mainFrame.doubleHuiQi = true;
                mainFrame.mode = 1;
                mainFrame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn2Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(2);
                mainFrame.doubleHuiQi = true;
                mainFrame.mode = 2;
                mainFrame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn3Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(3);
                mainFrame.doubleHuiQi = true;
                mainFrame.mode = 3;
                mainFrame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn4Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(4);
                mainFrame.doubleHuiQi = true;
                mainFrame.mode = 4;
                mainFrame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });
    }
}
