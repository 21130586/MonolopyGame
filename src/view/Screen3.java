package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.GameController;
import model.BoardModel;
import model.BoardObserver;
import model.DiceObserver;
import model.GameModelObserver;
import model.Player;
import model.PlayerObserver;
import model.Square;
import model.SquareObserver;

public class Screen3 extends Screen
		implements GameModelObserver, BoardObserver, PlayerObserver, SquareObserver, DiceObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel border, border_center, jpl, east, screen3;
	private JPanel[] border_jpl, dice_jpl, player_jpl;
	private JLabel[] lb_title_dice, lb_title_player, listLb, lbDice;

	private JTextField[] player_tf;
	private JButton btnDice, btnTerminal, btnBuy, btnSell;
	// *** con số tui để 4 là tượng trưng thôi. Dựa vào việc tạo ra bao nhiêu người
	// thì set lại nha.
	private JLabel[] img_player;
	// tạo viền đen cho các component.
	private Border blackline = BorderFactory.createLineBorder(Color.black);
	private JTextArea jta;
	// private Color[] colors = { Color.BLUE, Color.RED, Color.green, Color.YELLOW};
	private ImageIcon[] img_chance, img_community;
	GameController gameController;

	public Screen3(View mainFrame, GameController gameController) {
		super(mainFrame);
		this.gameController = gameController;
		this.gameController.getGameModel().registerObserver(this);
		this.gameController.getGameModel().getBoardModel().registerBoardObserver(this);
		this.gameController.getGameModel().getBoardModel().registerPlayerObserver(this);
		this.gameController.getGameModel().getBoardModel().registerSquareObserver(this);
		this.gameController.getGameModel().getBoardModel().registerDiceObserver(this);
		setLayout(new BorderLayout());
		screen3 = new JPanel();
		screen3.setLayout(new BorderLayout());

// phần bản đồ di chuyển
		border = new JPanel();
		border.setLayout(new BorderLayout());
		createCard();
		border_jpl = new JPanel[4];

		// danh sách các label hiển thị các ô.
		listLb = new JLabel[32];

		// Tạo ảnh cho từng player.
		img_player = new JLabel[4];
		for (int i = 0; i < img_player.length; i++) {
			img_player[i] = new JLabel();
		}
//		new ImageIcon("src/img/token" + (i + 1) + ".png")
		for (int i = 0; i < border_jpl.length; i++) {
			border_jpl[i] = new JPanel();
		}

		border_jpl[0].setLayout(new BoxLayout(border_jpl[0], BoxLayout.X_AXIS));
		border_jpl[1].setLayout(new BoxLayout(border_jpl[1], BoxLayout.Y_AXIS));
		border_jpl[2].setLayout(new BoxLayout(border_jpl[2], BoxLayout.X_AXIS));
		border_jpl[3].setLayout(new BoxLayout(border_jpl[3], BoxLayout.Y_AXIS));

		for (int i = 16; i < listLb.length; i++) {
			listLb[i] = new JLabel();

			if (i >= 16 && i <= 24) {
				border_jpl[2].add(listLb[i]);
			}
			if (i >= 25 && i <= 31) {
				border_jpl[3].add(listLb[i]);
			}
		}

		for (int i = 15; i >= 0; i--) {
			listLb[i] = new JLabel();
			if (i < 9) {
				border_jpl[0].add(listLb[i]);
			}
			if (i >= 9 && i <= 15) {
				border_jpl[1].add(listLb[i]);
			}
		}
		add(img_player[0]);
		add(img_player[1]);
		add(img_player[2]);
		add(img_player[3]);

		for (int i = 0; i < listLb.length; i++) {
			if (i < 10) {
				listLb[i].setIcon(new ImageIcon("src/img/bottom" + i + ".jpg"));
			}
			if (i >= 9 && i < 16) {
				listLb[i].setIcon(new ImageIcon("src/img/left" + (i - 8) + ".jpg"));
			}
			if (i > 16 && i < 24) {
				listLb[i].setIcon(new ImageIcon("src/img/top" + (i - 16) + ".jpg"));
			}
			if (i > 24 && i < listLb.length) {
				listLb[i].setIcon(new ImageIcon("src/img/right" + (i - 24) + ".jpg"));
			}
			switch (i) {
			case 0:
				listLb[i].setIcon(new ImageIcon("src/img/start.jpg"));
				break;
			case 3:
				listLb[i].setIcon(new ImageIcon("src/img/bottom8.jpg"));
				break;
			case 6:
				listLb[i].setIcon(new ImageIcon("src/img/bottom3.jpg"));
				break;
			case 16:
				listLb[i].setIcon(new ImageIcon("src/img/topLeft.jpg"));
				break;
			case 18:
				listLb[i].setIcon(new ImageIcon("src/img/top2.jpg"));
				break;
			case 24:
				listLb[i].setIcon(new ImageIcon("src/img/topRight.jpg"));
				break;
			case 8:
				listLb[i].setIcon(new ImageIcon("src/img/bottomLeft.jpg"));
				break;
			default:
				break;
			}
		}

		// hiển thị phần xúc sắc.
		border_center = new JPanel();
		border_center.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));

		dice_jpl = new JPanel[2];

		lbDice = new JLabel[2];

		lb_title_dice = new JLabel[2];

		for (int i = 0; i < dice_jpl.length; i++) {
			dice_jpl[i] = new JPanel();
			lbDice[i] = new JLabel();
			lb_title_dice[i] = new JLabel();
			dice_jpl[i].add(lb_title_dice[i]);
			dice_jpl[i].add(lbDice[i]);
			dice_jpl[i].setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
			border_center.add(dice_jpl[i]);
		}

		lbDice[0].setIcon(new ImageIcon("src/img/dice1.jpg"));
		lbDice[1].setIcon(new ImageIcon("src/img/dice2.jpg"));
		lb_title_dice[0].setText("số của xúc sắc 1:");
		lb_title_dice[1].setText("Số của xúc sắc 2:");

		// kết thúc phần hiển thị xúc sắc.
		// add các component vào giao diện chính.
		border.add(border_jpl[0], BorderLayout.SOUTH);
		border.add(border_jpl[1], BorderLayout.WEST);
		border.add(border_jpl[2], BorderLayout.NORTH);
		border.add(border_jpl[3], BorderLayout.EAST);
		border.add(border_center, BorderLayout.CENTER);

		screen3.add(border, BorderLayout.CENTER);
// kết thúc phần giao diện bản đồ

// giao diện hiển thị nút và thông tin người chơi
		jpl = new JPanel();
		jpl.setLayout(new BoxLayout(jpl, BoxLayout.Y_AXIS));
		jpl.setBorder(blackline);

		player_jpl = new JPanel[3];
		player_tf = new JTextField[3];
		lb_title_player = new JLabel[3];

		for (int i = 0; i < player_jpl.length - 1; i++) {
			player_jpl[i] = new JPanel();
			player_tf[i] = new JTextField(15);
			lb_title_player[i] = new JLabel();
			player_tf[i].setEditable(false);
			player_tf[i].setSize(200, 100);
			player_jpl[i].add(lb_title_player[i]);
			player_jpl[i].add(player_tf[i]);
			player_jpl[i].setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
			jpl.add(player_jpl[i]);
		}
		lb_title_player[0].setText("Player:           ");
		lb_title_player[1].setText("Tiền hiện có: ");

		player_jpl[2] = new JPanel();
		player_jpl[2].setLayout(new FlowLayout());
		lb_title_player[2] = new JLabel("Tài sản:   ");
		jta = new JTextArea(15, 22);

		jta.setEditable(true);
		player_jpl[2].add(lb_title_player[2]);
		player_jpl[2].add(jta);
		jpl.add(player_jpl[2]);
		jpl.setBorder(blackline);

		// chức năng quay số, mua, bán, kết thúc lượt.
		east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));

		btnDice = new JButton(new ImageIcon("src/img/rollthedice.jpg"));
		btnBuy = new JButton(new ImageIcon("src/img/buyIcon.jpg"));
		btnBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnSell = new JButton(new ImageIcon("src/img/sellIcon.png"));
		btnTerminal = new JButton(new ImageIcon("src/img/finishturn.jpg"));
		btnTerminal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameController.endTurn();
				ableBTN(btnDice);
			}
			
		});
		
		east.add(jpl);
		east.add(btnDice);
		east.add(btnBuy);
		east.add(btnSell);
		east.add(btnTerminal);
// Vị trí phương thức action chạy test thử.
		action();
		setLocationPlayer(0, 0);
		setLocationPlayer(1, 0);
		setLocationPlayer(2, 0);
		setLocationPlayer(3, 0);
		screen3.add(east, BorderLayout.EAST);
// kết thúc giao diện thông tin người chơi và nút.
		add(screen3);

	}

// phương thức set lại ví trí của player trên màn hình
	public void setLocationPlayer(int n, int index) {
		// n là player thứ mấy, index là ô thứ mấy.
		int[] x1 = { 590, 449, 393, 337, 281, 225, 169, 113, 13, 13, 13, 13, 13, 13, 13, 13, 90, 113, 169, 225, 281,
				337, 393, 449, 590, 560, 560, 560, 560, 560, 560, 560, };

		int[] y1 = { 500, 560, 560, 560, 560, 560, 560, 560, 560, 448, 392, 336, 280, 224, 168, 112, 0, 50, 50, 50, 50,
				50, 50, 50, 0, 112, 168, 224, 280, 336, 392, 448 };

		int[] x2 = { 590, 479, 423, 367, 311, 255, 199, 143, 43, 43, 43, 43, 43, 43, 43, 43, 90, 143, 199, 255, 311,
				367, 423, 479, 590, 585, 585, 585, 585, 585, 585, 585, };

		int[] y2 = { 525, 560, 560, 560, 560, 560, 560, 560, 560, 448, 392, 336, 280, 224, 168, 112, 25, 50, 50, 50, 50,
				50, 50, 50, 25, 112, 168, 224, 280, 336, 392, 448, };

		int[] x3 = { 590, 449, 393, 337, 281, 225, 169, 113, 13, 13, 13, 13, 13, 13, 13, 13, 90, 113, 169, 225, 281,
				337, 393, 449, 590, 560, 560, 560, 560, 560, 560, 560, };
		int[] y3 = { 550, 585, 585, 585, 585, 585, 585, 585, 585, 473, 417, 361, 305, 249, 193, 137, 50, 75, 75, 75, 75,
				75, 75, 75, 50, 143, 199, 255, 311, 367, 423, 479, };

		int[] x4 = { 590, 479, 423, 367, 311, 255, 199, 143, 43, 43, 43, 43, 43, 43, 43, 43, 90, 143, 199, 255, 311,
				367, 423, 479, 590, 585, 585, 585, 585, 585, 585, 585, };

		int[] y4 = { 575, 585, 585, 585, 585, 585, 585, 585, 585, 473, 417, 361, 305, 249, 193, 137, 75, 75, 75, 75, 75,
				75, 75, 75, 75, 143, 199, 255, 311, 367, 423, 479 };
		switch (n) {
		case 0:
			img_player[0].setBounds(550, 505, 25, 25);
			break;
		case 1:
			img_player[1].setBounds(550, 535, 25, 25);
			break;
		case 2:
			img_player[2].setBounds(550, 565, 25, 25);
			break;
		case 3:
			img_player[3].setBounds(550, 595, 25, 25);
			break;
		default:
			break;

		}
	}

//**** bấm vào nút xúc sắt để test giao diện với xử lý. 
//Ông lấy code trong action() bỏ vào chỗ điều kiện đi vào đất người khác.
// Thêm thuộc tính lấy dữ liệu nếu cần.
	public void action() {
		btnDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameController.rollDice();
			}
		});
	}

// show ra các thẻ chance or comnunity.
	public void showChanceOrCommunity(ImageIcon ima) {
		JOptionPane.showMessageDialog(null, ima, "Thẻ đặc quyền", JOptionPane.INFORMATION_MESSAGE);
	}

	public void createCard() {
		img_chance = new ImageIcon[16];
		for (int i = 0; i < img_chance.length; i++) {
			if (i < 10) {
				img_chance[i] = new ImageIcon("src/img/chance0" + i + ".jpg");
			} else {
				img_chance[i] = new ImageIcon("src/img/chance" + i + ".jpg");
			}
		}

		img_community = new ImageIcon[17];
		for (int i = 0; i < img_community.length; i++) {
			if (i < 10) {
				img_community[i] = new ImageIcon("src/img/community0" + i + ".jpg");
			} else {
				img_community[i] = new ImageIcon("src/img/community" + i + ".jpg");
			}
		}
	}

	private JPanel[] jpl_show_grid;
	private JComboBox<String>[] array_cbb_show;
	private JTextField[] tf_show_grid_name_place, tf_show_grid_money;
	private JButton[] btn_grid;
	private JPanel jpl_show_north, jpl_show_center;
	private JTextField tf_show_money, tf_show_moneyToPay;
	private JLabel jlb_show_money, jlb_show_moneyToPay;

	public JPanel help(int n) {
		JPanel all = new JPanel();
		all.setLayout(new BorderLayout());
		jpl_show_north = new JPanel();
		jpl_show_north.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
		jlb_show_money = new JLabel("Tiền hiện có:");
		jlb_show_moneyToPay = new JLabel("Tiền cần trả:");

		tf_show_money = new JTextField(5);
// Đây là text field show tiền hiện có của player.
// nào chạy thì xoá và set giá trị khi click combobox.
		tf_show_money.setText("2000");
		tf_show_money.setEditable(false);

// Đây là text field show tiền phải trả. Set lại là được.
		tf_show_moneyToPay = new JTextField(5);
		tf_show_moneyToPay.setEditable(false);
		tf_show_moneyToPay.setText("4000");

		jpl_show_north.add(jlb_show_money);
		jpl_show_north.add(tf_show_money);
		jpl_show_north.add(jlb_show_moneyToPay);
		jpl_show_north.add(tf_show_moneyToPay);

		all.add(jpl_show_north, BorderLayout.NORTH);

		jpl_show_center = new JPanel();
		jpl_show_grid = new JPanel[n];
		tf_show_grid_money = new JTextField[n];
		tf_show_grid_name_place = new JTextField[n];
		array_cbb_show = new JComboBox[n];
		btn_grid = new JButton[n];
		if (n > 10) {
			jpl_show_center.setLayout(new GridLayout(10, 2, 20, 10));
		} else {
			jpl_show_center.setLayout(new GridLayout(5, 2, 20, 10));
		}

// tượng trưng phần set thông tin thôi.
		String[] list = { "Cấp đất" };

		for (int i = 0; i < jpl_show_grid.length; i++) {
			jpl_show_grid[i] = new JPanel();
			tf_show_grid_name_place[i] = new JTextField(15);
			tf_show_grid_name_place[i].setText("Tên đất");
			tf_show_grid_name_place[i].setEditable(false);
			jpl_show_grid[i].add(tf_show_grid_name_place[i]);

			array_cbb_show[i] = new JComboBox<String>(list);
			jpl_show_grid[i].add(array_cbb_show[i]);

			jpl_show_grid[i].add(new JLabel("Trị giá:"));

//Đây là text field hiện tiền khi chọn cấp độ đất.
			tf_show_grid_money[i] = new JTextField(8);
			tf_show_grid_money[i].setText("");
			tf_show_grid_money[i].setEditable(false);
			jpl_show_grid[i].add(tf_show_grid_money[i]);

			btn_grid[i] = new JButton("Bán");
			jpl_show_grid[i].add(btn_grid[i]);

			jpl_show_center.add(jpl_show_grid[i]);
// 2 phương thức xử lý khi nhấn nút bán và  set giá khi chọn combobox.
			handleEventButtonGrid(i);
			handleEvenCBBGrid(i);
		}
		all.add(jpl_show_center, BorderLayout.CENTER);
		return all;
	}

	public void handleEventButtonGrid(int i) {
		btn_grid[i].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == btn_grid[i]) {
					Double d = Double.valueOf(tf_show_grid_money[i].getText());
					Double money = Double.valueOf(tf_show_money.getText());
					tf_show_money.setText(String.valueOf(money + d));
					tf_show_grid_money[i].setText("");
				}
			}
		});

	}

	public void handleEvenCBBGrid(int i) {
		array_cbb_show[i].addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
// Khi chuột chọn cấp độ nhà thì ông lấy dữ liệu tiền ra set vào nha.
				if (array_cbb_show[i] == e.getSource()) {
					tf_show_grid_money[i].setText("Cấp 1");
// dòng này xoá cấp đã chọn trong combobox.
					array_cbb_show[i].removeItemAt(array_cbb_show[i].getSelectedIndex());
				}
			}
		});
	}

	public void updatePlayer1Gui(int index) {
		int[] X = { 550, 464, 408, 352, 296, 240, 184, 128, 52, 82, 82, 82, 82, 82, 82, 82, 82, 128, 184, 240, 296, 352,
				408, 464, 550, 505, 505, 505, 505, 505, 505, 505 };
		int[] Y = { 505, 505, 505, 505, 505, 505, 505, 505, 505, 462, 412, 352, 296, 240, 184, 128, 52, 82, 82, 82, 82,
				82, 82, 82, 82, 128, 184, 240, 296, 352, 412, 462 };
		img_player[0].setLocation(X[index], Y[index]);
	}

	public void updatePlayer2Gui(int index) {
		int[] X = { 550, 464, 408, 352, 296, 240, 184, 128, 52, 57, 57, 57, 57, 57, 57, 57, 57, 128, 184, 240, 296, 352,
				408, 464, 550, 535, 535, 535, 535, 535, 535, 535 };
		int[] Y = { 535, 535, 535, 535, 535, 535, 535, 535, 535, 462, 412, 352, 296, 240, 184, 128, 52, 57, 57, 57, 57,
				57, 57, 57, 57, 128, 184, 240, 296, 352, 412, 462 };
		img_player[1].setLocation(X[index], Y[index]);
	}

	public void updatePlayer3Gui(int index) {
		int[] X = { 550, 464, 408, 352, 296, 240, 184, 128, 52, 32, 32, 32, 32, 32, 32, 32, 32, 128, 184, 240, 296, 352,
				408, 464, 550, 565, 565, 565, 565, 565, 565, 565 };
		int[] Y = { 565, 565, 565, 565, 565, 565, 565, 565, 565, 462, 412, 352, 296, 240, 184, 128, 52, 32, 32, 32, 32,
				32, 32, 32, 32, 128, 184, 240, 296, 352, 412, 462 };
		img_player[2].setLocation(X[index], Y[index]);
	}

	public void updatePlayer4Gui(int index) {
		int[] X = { 550, 464, 408, 352, 296, 240, 184, 128, 52, 7, 7, 7, 7, 7, 7, 7, 7, 128, 184, 240, 296, 352, 408,
				464, 550, 595, 595, 595, 595, 595, 595, 595 };
		int[] Y = { 595, 595, 595, 595, 595, 595, 595, 595, 595, 462, 412, 352, 296, 240, 184, 128, 52, 7, 7, 7, 7, 7,
				7, 7, 7, 128, 184, 240, 296, 352, 412, 462 };
		img_player[3].setLocation(X[index], Y[index]);
	}

	@Override
	public void update(int numOfPlayer) {
		// TODO Auto-generated method stub
		mainFrame.navigateTo("Screen3");
		for (int i = 0; i < numOfPlayer; i++) {
			img_player[i].setIcon(new ImageIcon("src/img/token" + (i + 1) + ".png"));
		}

	}

	@Override
	public void update(List<Square> propertyList, double money, int X, int Y) {
		// TODO Auto-generated method stub

		Player currPlayer = gameController.getGameModel().getBoardModel().getCurrPlayer();
		int index = gameController.getGameModel().getPlayerList().indexOf(currPlayer) + 1;
		String currPlayerSerialNum = "" + index;
		player_tf[0].setText("Player " + currPlayerSerialNum);
		player_tf[1].setText("" + money + " $");

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int level, String color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(double money) {
		// TODO Auto-generated method stub
		player_tf[1].setText("" + money + " $");
	}

	@Override
	public void update(int X, int Y) {
		// TODO Auto-generated method stub
		Player currPlayer = gameController.getGameModel().getBoardModel().getCurrPlayer();
		int index = gameController.getGameModel().getPlayerList().indexOf(currPlayer);
		int playerIndex = gameController.getGameModel().getBoardModel().getCurrPlayer().getIndex();
		if (index == 0) {
			updatePlayer1Gui(playerIndex);
		}
		if (index == 1) {
			updatePlayer2Gui(playerIndex);
		}
		if (index == 2) {
			updatePlayer3Gui(playerIndex);
		}
		if (index == 3) {
			updatePlayer4Gui(playerIndex);
		}
		disableBTN(btnDice);
	}

	@Override
	public void update(List<Square> propertyList) {
		// TODO Auto-generated method stub
		Player currPlayer = gameController.getGameModel().getBoardModel().getCurrPlayer();
		int numOfproperties = currPlayer.getPropertyList().size();
		String properties = "";
		if (!currPlayer.getPropertyList().isEmpty()) {
			for (int i = 0; i < numOfproperties - 1; i++) {
				properties += currPlayer.getPropertyList().get(i).getTitle() + ",\n";
			}
			properties += currPlayer.getPropertyList().get(numOfproperties - 1).getTitle();
		}
		jta.setText(properties);
	}

	@Override
	public void update(int diceA, int diceB, boolean isPair) {
		// TODO Auto-generated method stub
		System.out.println(diceA + "//" + diceB);
		lbDice[0].setIcon(new ImageIcon("src/img/dice" + diceA + ".jpg"));
		lbDice[1].setIcon(new ImageIcon("src/img/dice" + diceB + ".jpg"));
		lb_title_dice[0].setText("số của xúc sắc 1: " + diceA);
		lb_title_dice[1].setText("số của xúc sắc 2: " + diceB);

	}

	@Override
	public void cardNotice(String src) {
		// TODO Auto-generated method stub
		showChanceOrCommunity(new ImageIcon("src/img/" + src + ".jpg"));
	}
	// phương thức vô hiệu hoá một JButton.
	public void disableBTN(JButton btn) {
		btn.setEnabled(false);
	}
	// phương thức gỡ vô hiệu hoá button.
	public void ableBTN(JButton btn) {
		btn.setEnabled(true);
	}
}
