import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    protected void openAudioFile(File file)throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    protected void audioMenuMouse() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/menu-mouse.wav");
        openAudioFile(file);
    }

    protected void audioOnStart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/start_game.wav");
        openAudioFile(file);
    }
    protected void audioOnPaddleBounce() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/paddle_bounce.wav");
        openAudioFile(file);
    }
    protected void audioOnWallBounce() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/wall_bounce.wav");
        openAudioFile(file);
    }
    protected void audioOnPointOne() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/point_one.wav");
        openAudioFile(file);
    }
    protected void audioOnPointTwo() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("audio/point_two.wav");
        openAudioFile(file);
    }
}