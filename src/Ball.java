import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {

    Random random;
    int xVelocity, yVelocity;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();

        //ball move at the start:
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--; //negative
        int initialSpeed = 6;
        setXDirection(randomXDirection* initialSpeed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--; //negative
        setYDirection(randomYDirection* initialSpeed);
    }
    protected void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }
    protected void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }

    protected void move(){
            x += xVelocity;
            y += yVelocity;
    }

    protected void draw(Graphics g){

        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}
