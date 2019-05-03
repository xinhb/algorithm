package Tank_09;

/**
 * @author light.huang
 * @data 2019/5/3 15:30
 */

import java.awt.*;

/**坦克类*/
public class Tank {

    /**坦克的属性*/
    private int x;
    private int y;
    private Dir dir;
    private TankFrame tankFrame;

    private static final int SPEED = 5;
    private boolean moving = false;  //默认坦克不动


    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    /**<h2>传入画笔，在坦克类内画出每个坦克</h2>*/
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x,y,30,30);
        g.setColor(c);
        move();
    }

    private void move() {
        //判断状态标志moving，看坦克是否可以move
        if (!moving) return;

        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
    }

    public void fire() {
        /**tf是窗口对象的引用，有了它可以设置成员属性*/
        tankFrame.bullet= new Bullet(this.x,this.y,this.dir);


    }
}


