import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GamePanel panel;

    GameFrame() throws InterruptedException {

        Image icon = Toolkit.getDefaultToolkit()
                .getImage("pong-logo.png");
        this.setIconImage(icon);

        panel = new GamePanel();
        this.add(panel);
        this.setTitle("PONG     //github: mrcszym");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setBackground(Color.DARK_GRAY); //starting color
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        panel.checkCollision();

        //changing background colors:
        while (true) {
            Thread.sleep(2000);
            changeBackground();
        }
    }

    public void changeBackground() {
        //radnom rgb colors:
        setBackground(new Color((int) (Math.random() * 0x1000000)));
    }
}
