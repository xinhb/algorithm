package Tank_09;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * @author light.huang
 * @data 2019/5/3 15:29
 */

/**<h1>实现发射子弹功能</h1>*/
public class TankFrame extends Frame {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    /**对象成员*/
    //给坦克对象传入当前窗口对象this
    Tank myTank = new Tank(100, 100, Dir.DOWN,this);
    //new处一个子弹对象
    Bullet bullet = new Bullet(200, 200, Dir.DOWN);


    /**<h2>构造游戏框</h2>*/
    TankFrame() {
        setTitle("tank");
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setVisible(true);

        /**监听游戏框的x事件*/
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        /**监听按键事件*/
        addKeyListener(new MyKeyListener());
    }

    /**<h2>双缓冲解决闪烁问题</h2>*/
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**<h2>画出坦克</h2>*/
    @Override
    public void paint(Graphics g) {
        //传入画笔，在坦克类（子弹类）中画出坦克（子弹）
        myTank.paint(g);
        bullet.paint(g);
    }

    /**自己的按键适配器内部类，给按键响应传入对象*/
    private class MyKeyListener extends KeyAdapter {

        //按键的状态，按下为true，松开为false
        private boolean bU = false;
        private boolean bD = false;
        private boolean bL = false;
        private boolean bR = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;
            }
            setTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                //增加Ctrl键松起监听，用来发射子弹
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;

                default:
                    break;
            }
            setTankDir();
        }

        /**<h3>根据按键的状态，确定坦克的方向</h3>*/
        private void setTankDir() {
            //四个键都没按下，坦克静止
            if (!bU && !bD && !bR && !bL)
                myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (bU) {myTank.setDir(Dir.UP);}
                if (bD) {myTank.setDir(Dir.DOWN);}
                if (bR) {myTank.setDir(Dir.RIGHT);}
                if (bL) {myTank.setDir(Dir.LEFT);}
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }

}

