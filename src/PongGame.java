
public class PongGame {

    public static void main(String[] args) throws InterruptedException {

        LaunchPage launchPage = new LaunchPage();
        while (true) {
            System.out.print(""); //better keep it here..
            if (launchPage.isPlayButtonClicked) {
                GameFrame gameFrame = new GameFrame();
            }
        }
    }
}
