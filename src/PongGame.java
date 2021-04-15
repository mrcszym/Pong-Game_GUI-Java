
public class PongGame {

    static boolean gameIsOn = true;
    public GameFrame frame;

    public static void main(String[] args) throws InterruptedException {

        while(gameIsOn) {
            PongGame game = new PongGame();
            game.startTheWholeThing();
        }
    }

    public void startTheWholeThing() throws InterruptedException {

        LaunchPage launchPage = new LaunchPage();

        //if isPlayButtonClicked has been clicked (and so the LaunchPage is closed)
        //...the GameFrame is starting its job:
        while (true) {
            System.out.print(""); //better keep it here..
            if (launchPage.isPlayButtonClicked) {
                frame = new GameFrame();
                break;
            }
        }
    }
}
