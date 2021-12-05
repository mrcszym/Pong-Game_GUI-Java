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

        while (true) {
            System.out.print("");
            if (launchPage.startGameFrame) {
                frame = new GameFrame();
                break;
            }
        }
    }
}
