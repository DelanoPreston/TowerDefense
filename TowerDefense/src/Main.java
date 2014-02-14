import javax.swing.JFrame;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ContentBank.ContentLoader();
		JFrame frame = new JFrame();
		GamePanel gamePanel = new GamePanel();
		frame.setSize(350, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(gamePanel);
		frame.setVisible(true);
	}
}