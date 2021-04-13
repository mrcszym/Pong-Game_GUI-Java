import java.awt.*;

public class Clock extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    long createdMillis = System.currentTimeMillis();

    Clock(int GAME_WIDTH, int GAME_HEIGHT){
        Clock.GAME_WIDTH = GAME_WIDTH;
        Clock.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Monospaced Bold", Font.ITALIC, 30));

        long nowMillis = System.currentTimeMillis();

        g.drawString("time: " + (nowMillis - this.createdMillis) / 1000, (GAME_WIDTH/2)+15, (GAME_HEIGHT/5)-80);
    }
}
