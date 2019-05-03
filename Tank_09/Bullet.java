package Tank_09;

/**
 * @author light.huang
 * @data 2019/5/3 20:41
 */

import java.awt.*;

/**<h1>子弹类</h1>*/
public class Bullet {

    //属性
    private int x,y;
    private static final int SPEED = 3;
    private static int WIDTH = 10, HEIGHT = 10;
    private Dir dir = Dir.DOWN;

    //方法
    public Bullet(int x, int y ,Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    /**<h2>画出自己</h2>*/
    public void paint(Graphics g) {
        Color c = g.getColor();  //获取画笔当前颜色
        g.setColor(Color.red);   //设置颜色
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);    //还原画笔原来颜色
        move();
    }

    private void move() {
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

}
