package view;

import java.awt.CardLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.GameController;

public class View extends JFrame implements ViewInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardLayout cardLayout;
	private JPanel cards;
	GameController gameController;

	public View(GameController gameController) {
		this.gameController = gameController;
		init();
		start();
	}

	// phương thức di chuyển sang screen khác.
	public void navigateTo(String screen) {
		cardLayout.show(cards, screen);
	}

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cờ tỉ phú");
		setSize(950, 653);
		setLocationRelativeTo(null);
		setResizable(false);
		// layout đè lên nhau.

		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);

		Screen1 screen1 = new Screen1(this);
		Screen2 screen2 = new Screen2(this, gameController);
		Screen3 screen3 = new Screen3(this, gameController);

		cards.add(screen1, "Screen1");
		cards.add(screen2, "Screen2");
		cards.add(screen3, "Screen3");
		add(cards);
	}

	public void start() {
		SwingUtilities.invokeLater(() -> {
			this.setVisible(true);
		});
	}

	public JPanel getPanel() {
		return cards;
	}

	public static void main(String[] args) {
		GameController GC = new GameController();
		ViewInterface view1 = new View(GC);
//		ViewInterface view2 = new View(GC);

//		try {
//			// Đọc ảnh từ file
//			File imageFile = new File("src/img/bottom1.jpg");
//			BufferedImage image = ImageIO.read(imageFile);
//
//			// Lấy chiều rộng và chiều cao
//			int width = image.getWidth();
//			int height = image.getHeight();
//
//			// In ra kích thước của ảnh
//			System.out.println("Width: " + width);
//			System.out.println("Height: " + height);
//		} catch (IOException e) {
//			System.out.println("Error: " + e.getMessage());
//		}
	}
}