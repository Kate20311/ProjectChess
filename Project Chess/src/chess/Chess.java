package chess;

import view.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public abstract class Chess implements Serializable {
    // 棋子属性
    protected static final int SIZE = 60; // 棋子大小
    protected static final int MARGIN = 20; // 棋子外边距
    protected static final int SPACE = 72; // 棋子间距

    public static int getSIZE() {
        return SIZE;
    }
    public static int getMARGIN() {
        return MARGIN;
    }
    public static int getSPACE() {
        return SPACE;
    }

    private String name; // 棋子名字
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    protected int player; // 棋子阵营，0黑，1白
    public void setPlayer(int player) {
        this.player = player;
    }
    public int getPlayer() {
        return player;
    }

    protected int x, y; // 棋子绘制时的坐标位置
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    protected int pointX;
    protected int pointY;
    public int getPointX() {
        return pointX;
    }
    public void setPointX(int pointX) {
        this.pointX = pointX;
    }
    public int getPointY() {
        return pointY;
    }
    public void setPointY(int pointY) {
        this.pointY = pointY;
    }

    public int index; // 保存每个棋子的索引位置
    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }

    public Point p; // 棋子的网格坐标
    protected Point initP; // 棋子初始位置的网络坐标，不可改变
    public void setP(Point p) {
        this.p = (Point) p.clone(); // 一定要clone()！不然会出现两方棋子公用一套坐标的问题
        if (initP == null){
            initP = this.p;
        }
        CalXY();
    }
    public Point getP() {
        return p;
    }

    protected BufferedImage img; // 图片
    public BufferedImage getImg() {
        return img;
    }

    public Chess(String name, int player, Point p) {
        this.name = name;
        this.player = player;
        setP(p);
    }

    public Chess(String name, Point p, int player) {
        this.name = name;
        this.player = player;
        setP(p);
    }

    // 棋子绘制方法
    public void draw(Graphics g, JPanel panel){
        String path = "src" + File.separator + "picture" + File.separator + name + player + ".png";
        Image img = Toolkit.getDefaultToolkit().getImage(path);
        g.drawImage(img, x, y, SIZE, SIZE, panel);
    }

    // 计算xy的绘制坐标
    public void CalXY(){
        x = MARGIN - SIZE / 2 + SPACE * (p.x - 1) + 25;
        y = MARGIN - SIZE / 2 + SPACE * (p.y - 1) + 25;
    }

    // 反转网格坐标
    public void reverse(){
        p.y = 9 - p.y;
        initP = p;
        CalXY();
    }

    // 根据xy坐标计算网格坐标对象
    public static Point getPointFromXY(int x, int y){
        Point p = new Point();
        p.x = (x - MARGIN + SIZE / 2 - 25) / SPACE + 1;
        p.y = (y - MARGIN + SIZE / 2 - 25) / SPACE + 1;
        if (p.x < 1 || p.x > 8 || p.y < 1 || p.y > 8){
            return null;
        }
        return p;
    }


    // 是否符合走棋规则（不包含吃子）
    public abstract boolean isAbleStep(Point tp, GamePanel gamePanel);

    // 是否符合吃子规则
    public abstract boolean isAbleEat(Point tp, GamePanel gamePanel);

    // 是否能移动
    // 想要移动，必须  ①符合走棋/吃子规则   ②移动完之后不能被将军
    public boolean isAbleMove(Point tp, GamePanel gamePanel){
        return true;
    }


    // 走完这一步之后，是否被将军
    public boolean check(Point tp){
        return false;
    }
}
