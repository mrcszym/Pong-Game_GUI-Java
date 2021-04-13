import java.awt.*;

public class Bounce extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int bounceCounter = 0;

    Bounce(int GAME_WIDTH, int GAME_HEIGHT){
        this.GAME_WIDTH = GAME_WIDTH;
        this.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Monospaced Bold", Font.ITALIC, 30));
        g.drawString("bounce: " + bounceCounter, (GAME_WIDTH/2)-160, (GAME_HEIGHT/5)-80);
    }
}
