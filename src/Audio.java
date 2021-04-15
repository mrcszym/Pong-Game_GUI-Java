import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    //i hope all of the methods names explains what are they for:

    protected void audioMenuMouse() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/menu-mouse.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    protected void audioOnStart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/start_game.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    protected void audioOnPaddleBounce() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/paddle_bounce.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    protected void audioOnWallBounce() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/wall_bounce.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    protected void audioOnPointOne() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/point_one.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    protected void audioOnPointTwo() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/point_two.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}