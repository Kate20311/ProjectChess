package view;

import chess.Chess;
import model.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {

    private GamePanel gp;
    boolean doubleHuiQi = false;
    int mode;

    public MainFrame(int mode){

        setSize(900,635); // 设置窗口大小
        setLocationRelativeTo(null);// 设置窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口可以关闭

        setLayout(new BorderLayout()); // 设置布局管理员
        gp = new GamePanel();
        gp.setSize(635,635);
        gp.setLocation(0,0);
        gp.setMode(mode);
        add(gp); // 将面板添加到窗口中



        JLabel hintLabel = new JLabel("白方走棋");
        hintLabel.setSize(100,40);
        hintLabel.setLocation(650,50);
        add(hintLabel);
        gp.setHintLabel(hintLabel);

        JLabel btn1 = new JLabel(new ImageIcon("src\\picture\\悔棋.png"));
        btn1.setSize(100, 80);
        btn1.setLocation(650, 100);
        add(btn1);

        JLabel btn2 = new JLabel(new ImageIcon("src\\picture\\保存.png"));
        btn2.setSize(100, 80);
        btn2.setLocation(650, 200);
        add(btn2);

        JLabel btn3 = new JLabel(new ImageIcon("src\\picture\\导入.png"));
        btn3.setSize(100, 80);
        btn3.setLocation(650, 300);
        add(btn3);

        JLabel btn4 = new JLabel(new ImageIcon("src\\picture\\重新开始.png"));
        btn4.setSize(200, 80);
        btn4.setLocation(650, 400);
        add(btn4);

        JLabel btn5 = new JLabel(new ImageIcon("src\\picture\\重新开始.png"));
        btn5.setSize(200, 80);
        btn5.setLocation(650, 500);
        add(btn5);

        btn1.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gp.regretOneStep();
                if (doubleHuiQi){
                    gp.regretOneStep();
                }
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

        btn2.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println("clicked Save Btn");
                    String fileName = JOptionPane.showInputDialog("请输入存档名" );
                    if(fileName != null){
                        gp.save(fileName);
                    }
                }catch (Exception a){
                    a.printStackTrace();
                }
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

        btn3.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gp.load();
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

        btn4.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gp.resetGame();
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

        setVisible(true); // 设置窗口可见

        if (mode == 2){
            Chess c0 = gp.getChessByP(new Point(4,7));
            Point tp = new Point(4,5);
            Record record0 = new Record();
            record0.setChess(c0);
            record0.setStart(c0.getP());
            record0.setEnd(tp);
            record0.setAteChess(null);
            gp.getStepList().add(record0);
            c0.setP(tp); // 修改棋子坐标以实现移动
            GamePanel.getChooseCursor().setPointX(tp.x);
            GamePanel.getChooseCursor().setPointY(tp.y);
            gp.overMyTurn();
            repaint();
        }

    }

}
