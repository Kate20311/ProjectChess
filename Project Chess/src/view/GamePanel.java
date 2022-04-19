package view;

import chess.Chess;
import chess.ChessFactory;
import model.Cursors;
import model.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener {

    private Chess[] chesses = new Chess[32]; // 保存所有棋子的成员变量（一个数组）
    public Chess[] getChesses() {
        return chesses;
    }
    public void setChesses(Chess[] chesses) {
        this.chesses = chesses;
        repaint();
    }

    private Chess selectedChess; // 用一个成员变量储存当前选中的棋子（用来区分第一次选择和重新选择）
    public Chess getSelectedChess() {
        return selectedChess;
    }
    public void setSelectedChess(Chess selectedChess) {
        this.selectedChess = selectedChess;
    }

    private int currentPlayer = 1; // 用来记住当前阵营，默认1（白方）先走
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private LinkedList<Record> StepList = new LinkedList<>(); // 用集合记录每一步以实现悔棋功能
    public LinkedList<Record> getStepList() {
        return StepList;
    }
    public void setStepList(LinkedList<Record> stepList) {
        StepList = stepList;
    }

    private JLabel hintLabel; // 提示的label
    public JLabel getHintLabel() {
        return hintLabel;
    }
    public void setHintLabel(JLabel hintLabel) {
        this.hintLabel = hintLabel;
    }


    private static Cursors mouseCursor; // 鼠标光标
    public static Cursors getMouseCursor() {
        return mouseCursor;
    }

    private static Cursors chooseCursor; // 已选择光标
    public static Cursors getChooseCursor() {
        return chooseCursor;
    }

    private int mode; // 模式：0 单机玩家   1 难度一（白）   2 难度一（黑）   3 难度二（白）   4 难度二（黑）   -1 联机
    public int getMode() {
        return mode;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        String bgPath = "src" + File.separator + "picture" + File.separator + "qipan.png"; // 准备背景图片路径
        Image bgImg = Toolkit.getDefaultToolkit().getImage(bgPath); // 获取Toolkit实例，再获取图片
        g.drawImage(bgImg,0,0,600,600,this); // 将图片绘制到面板上

        drawChesses(g);

        drawCursor(g);
    }

    // 创建棋子
    private void createChesses(){
        String[] names = {"Rook","Knight","Bishop","Queen","King","Bishop","Knight","Rook","Pawn",
                "Pawn","Pawn","Pawn","Pawn","Pawn","Pawn","Pawn"};
        int[] xs = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < names.length; i++){
            Chess c = ChessFactory.create(names[i], 0, xs[i]);
            c.setIndex(i); // 指定棋子索引
            c.reverse();
            c.reverse();
            chesses[c.getIndex()] = c; // 将棋子保存到数组中
        }
        for (int i = 0; i < names.length; i++){
            Chess c = ChessFactory.create(names[i], 1, xs[i]);
            c.reverse();
            c.setIndex(i + 16); // 指定棋子索引
            chesses[c.getIndex()] = c;
        }
    }

    // 绘制棋子
    private void drawChesses(Graphics g){
        for (Chess item : chesses){
            if (item != null){
                item.draw(g,this);
            }
        }
    }

    // 绘制选择光标
    public void drawCursor(Graphics g) {
        g.drawImage(mouseCursor.getImg(), mouseCursor.getX(), mouseCursor.getY(), null);
        g.drawImage(chooseCursor.getImg(), chooseCursor.getX(), chooseCursor.getY(), null);
    }

    // 无参构造方法
    public GamePanel(){
        createChesses();

        mouseCursor = new Cursors(-2,-2,"/picture/cursor-yellow.png");
        chooseCursor = new Cursors(-2,-2,"/picture/cursor-red.png");

        addMouseListener(this);

        addMouseMotionListener(this);

    }

    // 结束当前回合，切换阵营
    public void overMyTurn(){
        currentPlayer = currentPlayer == 0 ? 1 : 0;
        selectedChess = null;
        hintLabel.setText(currentPlayer == 0 ? "黑方走棋" : "白方走棋");
    }

    // 根据网格坐标对象p查找棋子
    public Chess getChessByP(Point p){
        for (Chess item : chesses){
            if (item != null && item.getP().equals(p)){
                return item;
            }
        }
        return null;
    }

    // 实现悔棋功能
    public void regretOneStep(){
        Record record = StepList.pollLast(); // 将最后一个数据弹出
        // 将操作棋子的坐标修改回去
        record.getChess().setP(record.getStart());
        chesses[record.getChess().getIndex()] = record.getChess();
        if (record.getAteChess() != null){
            chesses[record.getAteChess().getIndex()] = record.getAteChess();
        }
        currentPlayer = record.getChess().getPlayer(); // 修改当前走棋阵营
        selectedChess = null;
        chooseCursor.setPointX(-2);
        chooseCursor.setPointY(-2);
        // 修改提示label
        hintLabel.setText(currentPlayer == 0 ? "黑方走棋" : "白方走棋");
        repaint(); // 刷新棋盘
    }

    @Override
    public void mouseClicked(MouseEvent e) {

            //super.mouseClicked(e);
            System.out.println("坐标 x=" + e.getX() + " y=" + e.getY());
            Point p = Chess.getPointFromXY(e.getX(),e.getY());
            System.out.println("网格坐标 x=" + p.x + " y=" + p.y);

            if (selectedChess == null){
                selectedChess = getChessByP(p); // 第一次选择
                chooseCursor.setPointX(p.x);
                chooseCursor.setPointY(p.y);
                if (selectedChess != null && selectedChess.getPlayer() != currentPlayer){
                    selectedChess = null; // 必须选择的是己方棋子，否则选择无效
                }

            } else {  // 重新选择，移动，吃子
                Chess c = getChessByP(p);

                if (c != null) {  // 第n次点击的地方有棋子，重新选择，吃子

                    if (c.getPlayer() == selectedChess.getPlayer()) {
                        // 点击的是己方棋子，重新选择
                        System.out.println("重新选择");
                        selectedChess = c;
                        chooseCursor.setPointX(c.getP().x);
                        chooseCursor.setPointY(c.getP().y);
                    } else {
                        // 吃子
                        System.out.println("吃子");
                        if (selectedChess.isAbleEat(p, GamePanel.this)) {
                            eatChess(selectedChess, p, c);

                            if (isGameOver()){
                                gameOver();
                            }

                            // 人机行动
                            System.out.println("mode " + mode);
                            if (mode == 1 || mode == 2) {
                                robot1();
                                if (isGameOver()){
                                    gameOver();
                                }
                            }

                        }
                    }

                } else {  // 第n次点击的地方没有棋子，点的是空白的地方，移动
                    System.out.println("移动");
                    if (selectedChess.isAbleMove(p, GamePanel.this)){ // 判断是否可以移动
                        moveChess(selectedChess, p);
                        repaint();

                        if (isGameOver()){
                            gameOver();
                        }

                        // 人机行动
                        System.out.println("mode " + mode);
                        if (mode == 1 || mode == 2) {
                            robot1();
                            if (isGameOver()){
                                gameOver();
                            }
                        }

                    }

                }
            }
            repaint(); // 刷新棋盘,即重新执行paint方法
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int PointX = (e.getX() - Chess.getMARGIN() + Chess.getSIZE() / 2 - 25) / Chess.getSPACE() + 1;
        int PointY = (e.getY() - Chess.getMARGIN() + Chess.getSIZE() / 2 - 25) / Chess.getSPACE() + 1;

        PointX = PointX > 8 ? 8 : PointX;
        PointY = PointY > 8 ? 8 : PointY;

        mouseCursor.setPointX(PointX);
        mouseCursor.setPointY(PointY);

        repaint();
    }

    public void eatChess(Chess selected, Point p, Chess c){ // selected是当前选中棋子，p是目标点，c是在目标点位置的棋子
        Record record = new Record();
        record.setChess(selected);
        record.setStart(selected.getP());
        record.setEnd(p);
        record.setAteChess(c);
        StepList.add(record);
        chesses[c.getIndex()] = null; // 从棋子数组中删除被吃掉的棋子
        selected.setP(p); // 修改要移动棋子的坐标
        chooseCursor.setPointX(p.x);
        chooseCursor.setPointY(p.y);
        overMyTurn();
    }

    public void moveChess(Chess selected, Point p){ // selected是当前选中棋子，p是目标点
        Record record = new Record();
        record.setChess(selected);
        record.setStart(selected.getP());
        record.setEnd(p);
        StepList.add(record);
        selected.setP(p); // 修改棋子坐标以实现移动
        chooseCursor.setPointX(p.x);
        chooseCursor.setPointY(p.y);
        overMyTurn();
    }

    public void resetGame(){
        selectedChess = null;
        currentPlayer = 1;
        StepList.clear();
        hintLabel.setText("白方走棋");
        chooseCursor.setPointX(-2);
        chooseCursor.setPointY(-2);
        createChesses();
        repaint();
    }

    public void save(String fileName){
        String fileAllName = fileName + ".txt";
        String fileLocation="savings\\"+fileAllName;
        File file = new File(fileLocation);

        try {
            if(file.exists()){     // 若文档存在，询问是否覆盖
                int n = JOptionPane.showConfirmDialog(this, "是否覆盖?", "取消", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    file.delete();
                }
            }

            // 创建文档
            FileWriter fileWritter = new FileWriter(fileLocation,true);
            for (int i = 0; i < StepList.size(); i++){
                for (int j = 0; j < 6; j++){
                    fileWritter.write(StepList.get(i).toStringArray()[j]);
                    fileWritter.write(" ");
                }
                fileWritter.write("\n");
            }

            fileWritter.close();
            System.out.println("Save Done");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void load(){
        System.out.println("clicked Load Btn");

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("savings"));
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();

        if (!file.getName().endsWith(".txt")){
            JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
            System.out.println("检测到非法修改存档！重新开始游戏");
            System.out.println("后缀错误");
            resetGame();
            return;
        }

        try {
            String temp;
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
            ArrayList<String> readList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(read);

            while((temp = reader.readLine()) != null && !"".equals(temp)){
                readList.add(temp);
                System.out.println(temp);
            }

            // 检查是否白棋开始，黑白交替
            if (!readList.get(0).startsWith("1")){
                JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
                System.out.println("检测到非法修改存档！重新开始游戏");
                System.out.println("不是白棋开始");
                resetGame();
                return;
            }
            for (int i = 0; i < readList.size() - 1; i++){
                if (Math.abs(Integer.parseInt(readList.get(i).substring(0,1)) - Integer.parseInt(readList.get(i + 1).substring(0,1))) != 1){
                    JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
                    System.out.println("检测到非法修改存档！重新开始游戏");
                    System.out.println("不是黑白交替");
                    resetGame();
                    return;
                }
            }

            resetGame();
            for (int i = 0; i < readList.size(); i++){
                String[] strArr = readList.get(i).split(" ");
                // 检查棋子名字
                if (!strArr[1].equals("Bishop") && !strArr[1].equals("King") && !strArr[1].equals("Knight") &&
                        !strArr[1].equals("Pawn") && !strArr[1].equals("Queen") && !strArr[1].equals("Rook")){
                    JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
                    System.out.println("检测到非法修改存档！重新开始游戏");
                    System.out.println("棋子名字不正确");
                    resetGame();
                    return;
                }

                Chess selected = getChessByP(new Point(Integer.parseInt(strArr[2]),Integer.parseInt(strArr[3])));
                Point tp = new Point(Integer.parseInt(strArr[4]),Integer.parseInt(strArr[5]));
                Chess tpChess = getChessByP(tp);
                if (!selected.isAbleMove(tp,this) && !selected.isAbleEat(tp,this)){
                    JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
                    System.out.println("检测到非法修改存档！重新开始游戏");
                    System.out.println("储存的步骤不符合移动规则");
                    resetGame();
                    return;
                } else {
                    if (tpChess == null){  // 移动，selected是当前选中棋子，tp是目标点
                        moveChess(selected,tp);
                        repaint();
                        if (isGameOver()){
                            gameOver();
                        }
                    } else {  // 吃子
                        eatChess(selected,tp,tpChess);
                        if (isGameOver()){
                            gameOver();
                        }
                    }
                }
            }
        } catch (Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
            System.out.println("检测到非法修改存档！重新开始游戏");
            System.out.println("无法读取存档");
            resetGame();
        }
    }

    public void robot1(){
        Chess c0 = null;
        Random random = new Random();
        do {
            int index = (mode - 1) * 16 + random.nextInt(16);
            if (chesses[index] != null) {
                c0 = chesses[index];
            }
        } while (c0 == null);
        ArrayList<Point> points = new ArrayList<>();
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                if (getChessByP(new Point(x, y)) == null || getChessByP(new Point(x, y)).getPlayer() != currentPlayer) {
                    points.add(new Point(x, y));
                }
            }
        }
        Point tp = points.get(random.nextInt(points.size()));
        Chess tpChess = getChessByP(tp);
        if (tpChess == null) {  // 移动
            moveChess(c0, tp);
            repaint();
        } else {
            eatChess(c0, tp, tpChess);
            repaint();
        }
    }

    public boolean isGameOver(){  // 判断是否游戏结束，将死或和棋
        // toDo
        return false;
    }

    public void gameOver(){  // 执行游戏结束的动作，比如弹窗等
        // toDo
    }

    public boolean whiteCheckmate(){  // 判断白王是否被将死
        // toDo
        return false;
    }

    public boolean blackCheckmate(){  // 判断黑王是否被将死
        // toDo
        return false;
    }

    public boolean dawn(){  // 判断是否和棋
        // toDo
        return false;
    }
}