import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

    private final int id;
    private int yVelocity;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }

    public void keyPressed(KeyEvent e) {

        //ESC is closing app:
        if(e.getKeyCode() == 27){
            System.out.println("Paddle - exit (ESC)");
            System.exit(0);
        }

        if(e.getKeyCode() == 32){
            System.out.println("Paddle - back to menu (SPACE)");

        }

        int speed = 16;
        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
            }
        }
    }
    public void keyReleased(KeyEvent e) {

        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
            }
        }
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        y= y + yVelocity;
    }
    public void draw(Graphics g) {
        if(id==1)
            g.setColor(Color.pink);
        else if(id==2)
            g.setColor(Color.orange);
        g.fillRect(x, y, width, height);
    }
}