import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LaunchPage implements ActionListener{

    JFrame frame = new JFrame();
    JButton playButton = new JButton("Play");
    JButton exitButton = new JButton("Exit");

    boolean isPlayButtonClicked = false;

    Audio audio = new Audio();

    LaunchPage(){

        try {
            audio.audioOnLaunch();
        } catch (IOException | LineUnavailableException
                | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        playButton.setBounds(50,100,300,80);
        playButton.setFocusable(false);
        playButton.setBackground(Color.pink);
        playButton.setForeground(Color.white);
        playButton.setFont(new Font("Consolas", Font.BOLD, 50));
        playButton.addActionListener(this);

        exitButton.setBounds(50,250,300,80);
        exitButton.setFocusable(false);
        exitButton.setBackground(Color.orange);
        exitButton.setForeground(Color.white);
        exitButton.setFont(new Font("Consolas", Font.BOLD, 50));
        exitButton.addActionListener(this);

        frame.add(playButton);
        frame.add(exitButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GamePanel.GAME_WIDTH,GamePanel.GAME_HEIGHT);
        frame.setLayout(null);

        frame.getContentPane().setBackground(Color.black);
        frame.setVisible(true);
        frame.setTitle("Welcome to PONG  //github: mrcszym");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            frame.dispose();
            isPlayButtonClicked = true;
        }
        if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
}
