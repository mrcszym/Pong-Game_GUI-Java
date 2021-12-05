import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1500;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 125;

    static boolean DONTRESTART = true;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    Bounce bounce;
    Clock clock;
    Audio audio;

    GamePanel() {

        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        bounce = new Bounce(GAME_WIDTH, GAME_HEIGHT);
        clock = new Clock(GAME_WIDTH, GAME_HEIGHT);
        audio = new Audio();

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    private void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    private void newCounter() { //new bounce counter
        bounce = new Bounce(GAME_WIDTH, GAME_HEIGHT);
    }

    private void newClock() { //more like stopwatch actually but idc
        clock = new Clock(GAME_WIDTH, GAME_HEIGHT);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        bounce.draw(g);
        clock.draw(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    private void checkIfBallIsInTheWindow() {
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
            try {
                audio.audioOnWallBounce();
            } catch (IOException | LineUnavailableException
                    | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
            try {
                audio.audioOnWallBounce();
            } catch (IOException | LineUnavailableException
                    | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    private void menageBallSpeed() {
        if (ball.xVelocity < 13) {
            ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
        }

        if (bounce.bounceCounter > 14) {
            ball.xVelocity += 0.5;
            if (ball.yVelocity > 0)
                ball.yVelocity += 0.5;
            else
                ball.yVelocity -= 0.5;
        }
    }

    private void givePointToPlayerTwo() {
        try {
            audio.audioOnPointOne();
        } catch (IOException | LineUnavailableException
                | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        score.player2++;
    }

    private void givePointToPlayerOne() {
        try {
            audio.audioOnPointTwo();
        } catch (IOException | LineUnavailableException
                | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        score.player1++;
    }

    private void makeNewGameObjects() {
        newPaddles();
        newBall();
        newCounter();
        newClock();
    }

    private void bounceBallOfPaddles() {
        if (ball.intersects(paddle1)) {
            bounce.bounceCounter++;
            ball.xVelocity = Math.abs(ball.xVelocity);

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);

            try {
                audio.audioOnPaddleBounce();
            } catch (IOException | LineUnavailableException
                    | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }

        if (ball.intersects(paddle2)) {
            bounce.bounceCounter++;
            ball.xVelocity = Math.abs(ball.xVelocity);

            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);

            try {
                audio.audioOnPaddleBounce();
            } catch (IOException | LineUnavailableException
                    | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    private void keepPaddlesInWindow() {
        if (paddle1.y <= 0)
            paddle1.y = 0;
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
    }

    protected void menageCollisions() {

        checkIfBallIsInTheWindow();

        bounceBallOfPaddles();

        keepPaddlesInWindow();

        if (ball.x <= 0) {
            givePointToPlayerTwo();
            makeNewGameObjects();
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            givePointToPlayerOne();
            makeNewGameObjects();
        }
    }

    public void run(){

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        try {
            audio.audioOnStart();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        while (DONTRESTART) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                move();
                menageCollisions();
                repaint();
                delta--;
            }
        }
    }

    private class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}