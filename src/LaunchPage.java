import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class LaunchPage extends JFrame implements ActionListener, MouseListener {

    //logo (at frame and taskbar):
    Image logo = Toolkit.getDefaultToolkit().getImage("images/pong-logo.png");

    //menu image (50' family playing ping pong)
    ImageIcon image = new ImageIcon("images/menu-one.png");

    //menu image (man playing ping pong)
    ImageIcon image2 = new ImageIcon("images/man-play.png");

    Audio audio = new Audio();

    JLabel label = new JLabel();    //label with steering instructions
    JLabel photoLabel = new JLabel();   //menu image
    JLabel secondPhotoLabel = new JLabel(); //second menu image

    JPanel buttonBackground = new JPanel(); //play & exit button background

    JButton playButton = new JButton("Play");
    JButton exitButton = new JButton("Exit");

    JFrame frame = new JFrame();

    boolean isPlayButtonClicked = false; //important boolean in PongGame
                                         //...breaks while(true) if true
    LaunchPage(){

        buttonBackground.setBackground(Color.gray);
        buttonBackground.setBounds((GamePanel.GAME_WIDTH/2)-230, (GamePanel.GAME_HEIGHT/2)-250, 400, 250);
        buttonBackground.setLayout(new BorderLayout());

        playButton.setBounds((GamePanel.GAME_WIDTH/2)-180,(GamePanel.GAME_HEIGHT/4),300,80);
        playButton.setFocusable(false);
        playButton.setBackground(Color.pink);
        playButton.setForeground(Color.white);
        playButton.setFont(new Font("Consolas", Font.BOLD, 50));
        playButton.addActionListener(this);
        playButton.addMouseListener(this);

        exitButton.setBounds((GamePanel.GAME_WIDTH/2)-180,(GamePanel.GAME_HEIGHT/4)+100,300,80);
        exitButton.setFocusable(false);
        exitButton.setBackground(Color.orange);
        exitButton.setForeground(Color.white);
        exitButton.setFont(new Font("Consolas", Font.BOLD, 50));
        exitButton.addActionListener(this);
        exitButton.addMouseListener(this);

        label.setBounds((GamePanel.GAME_WIDTH)-430, (GamePanel.GAME_HEIGHT)/2, 400, 370);
        label.setBackground(Color.lightGray);
        label.setOpaque(true);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-25);
        label.setFont(new Font("Monospaced Bold", Font.PLAIN, 30));

        //using HTML markers to edit text in JLabel:
        label.setText("<html><center>Steering:</center>Player 1:<center>W (paddle up)" +
                "</center><center>S (paddle down)</center>" +
                "Player 2:<center>Up Arrow (paddle up)</center>" +
                "<center>Down Arrow (paddle down)</center>" +
                "<br />ESC during game - close app</html>");
        label.setVisible(true);

        photoLabel.setIcon(image);
        photoLabel.setHorizontalTextPosition(JLabel.CENTER);
        photoLabel.setVerticalTextPosition(JLabel.TOP);
        photoLabel.setBounds((GamePanel.GAME_WIDTH/2)+250, 50, image.getIconWidth(),image.getIconHeight());

        secondPhotoLabel.setText("Upgraded classic Pong");
        secondPhotoLabel.setIcon(image2);
        secondPhotoLabel.setHorizontalTextPosition(JLabel.CENTER);
        secondPhotoLabel.setVerticalTextPosition(JLabel.TOP);
        secondPhotoLabel.setForeground(Color.pink);
        secondPhotoLabel.setFont(new Font("Consolas", Font.ITALIC, 35));
        secondPhotoLabel.setIconTextGap(-25);
        secondPhotoLabel.setVerticalAlignment(JLabel.CENTER);
        secondPhotoLabel.setHorizontalAlignment(JLabel.CENTER);
        secondPhotoLabel.setBounds(0, 0, image.getIconWidth(),image.getIconHeight()+800);

        //all frame settings:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GamePanel.GAME_WIDTH,GamePanel.GAME_HEIGHT);
        frame.setIconImage(logo);

        frame.add(label);
        frame.add(photoLabel);
        frame.add(secondPhotoLabel);
        frame.add(playButton);
        frame.add(exitButton);
        frame.add(buttonBackground);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setTitle("Welcome to PONG  //github: mrcszym");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            frame.dispose();    //closing frame...
            isPlayButtonClicked = true; //...and in PongGame class starting GameFrame
        }
        if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        try {
            audio.audioMenuMouse(); //GTA San Andreas mood
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
            ioException.printStackTrace();
        }
        playButton.setBackground((Color.orange));
        exitButton.setBackground((Color.pink));
    }
    @Override
    public void mouseExited(MouseEvent e) {
        playButton.setBackground(Color.pink);
        exitButton.setBackground(Color.orange);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
