import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    //i hope all of the methods names explains what are they for
    public void audioOnStart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/start_game.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    public void audioOnPaddleBounce() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/paddle_bounce.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    public void audioOnWallBounce() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/wall_bounce.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    public void audioOnPointOne() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/point_one.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    public void audioOnPointTwo() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/point_two.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}