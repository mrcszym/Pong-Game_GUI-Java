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
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555)); //nice court scale
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

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void newCounter() { //new bounce counter
        bounce = new Bounce(GAME_WIDTH, GAME_HEIGHT);
    }

    public void newClock() { //more like stopwatch actually but idc
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

        Toolkit.getDefaultToolkit().sync(); //for better animation
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {
        //to keep the ball in window:
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

        //to bounce the ball of paddles:
        if (ball.intersects(paddle1)) {
            bounce.bounceCounter++;
            ball.xVelocity = Math.abs(ball.xVelocity);

            //the ball won't speed up +1 forever...
            if (ball.xVelocity < 13) {
                ball.xVelocity++;
                if (ball.yVelocity > 0)
                    ball.yVelocity++;
                else
                    ball.yVelocity--;
            }
            //...but after 20 bounces it will speed up +0.5:
            else if (bounce.bounceCounter > 14) {
                ball.xVelocity += 0.5;
                if (ball.yVelocity > 0)
                    ball.yVelocity += 0.5;
                else
                    ball.yVelocity -= 0.5;
            }
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

            //the ball won't speed up +1 forever...
            if (ball.xVelocity < 13) {
                ball.xVelocity++;
                if (ball.yVelocity > 0)
                    ball.yVelocity++;
                else
                    ball.yVelocity--;
            }
            //...but after 20 bounces it will speed up +0.5:
            if (bounce.bounceCounter > 14) {
                ball.xVelocity += 0.5;
                if (ball.yVelocity > 0)
                    ball.yVelocity += 0.5;
                else
                    ball.yVelocity -= 0.5;
            }

            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);

            try {
                audio.audioOnPaddleBounce();
            } catch (IOException | LineUnavailableException
                    | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }

        //keeps paddles in window:
        if (paddle1.y <= 0)
            paddle1.y = 0;
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        // gives a point to round winner;
        // creates new: ball, paddles, bounce counter, clock:
        if (ball.x <= 0) {
            try {
                audio.audioOnPointOne();
            } catch (IOException | LineUnavailableException
                    | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
            score.player2++;
            newPaddles();
            newBall();
            newCounter();
            newClock();
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            try {
                audio.audioOnPointTwo();
            } catch (IOException | LineUnavailableException
                    | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
            score.player1++;
            newPaddles();
            newBall();
            newCounter();
            newClock();
        }
    }

    public void run(){
        //game loop:

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
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter{
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